(defproject com.wjoel/reddit-to-kafka "0.1.0"
  :description "Post reddit data to a Kafka topic"
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [org.clojure/tools.cli "0.3.3"]
                 [org.clojure/tools.logging "0.3.1"]
                 [clj-kafka "0.3.3"]
                 [cheshire "5.5.0"]
                 [com.wjoel/reddit-listener "0.0.1"]]
  :main ^:skip-aot com.wjoel.reddit-to-kafka.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
