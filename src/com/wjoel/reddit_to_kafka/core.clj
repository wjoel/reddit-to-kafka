(ns com.wjoel.reddit-to-kafka.core
  (:require [clojure.tools.cli :refer [parse-opts]]
            [clj-kafka.new.producer :as kafka]
            [cheshire.core :as json]
            [com.wjoel.reddit-listener.core :as reddit-listener])
  (:gen-class))

(def cli-options
  [["-k" "--kafka-connect BROKERS" "Kafka broker list (comma separated list of host:port)"
    :default "localhost:9092"
    :validate [#(re-matches #"^([\p{Alnum}\.]+:\p{Digit}+)(,[\p{Alnum}\.]+:\p{Digit}+)*" %)
               "Invalid broker list"]]
   ["-t" "--topic TOPIC" "Kafka topic"
    :default "reddit-stream"]
   ["-c" "--client-id ID" "reddit client ID"
    :missing "reddit client ID required"]
   ["-s" "--client-secret SECRET" "reddit client secret"
    :missing "reddit client secret required"]])

(defn -main [& args]
  (let [parse-opts-result (parse-opts args cli-options)
        options (:options parse-opts-result)]

    (when-not (empty? (:errors parse-opts-result))
      (println "Errors:" (:errors parse-opts-result))
      (System/exit 1))

    (let [{:keys [kafka-connect
                  client-id
                  client-secret
                  topic]} options
          session (reddit-listener/get-session client-id client-secret)]
      (let [reddit-posts (reddit-listener/new-posts-stream session "all")]
        (with-open [producer (kafka/producer {"bootstrap.servers" kafka-connect}
                                             (kafka/byte-array-serializer)
                                             (kafka/byte-array-serializer))]
          (dorun
           (map (fn [post]
                  (let [json-str (json/generate-string post)]
                    (kafka/send producer (kafka/record topic (.getBytes json-str)))))
                reddit-posts)))))))
