(defproject com.wjoel/reddit-to-kafka "0.1.0"
  :description "Post reddit data to a Kafka topic"
  :url "https://github.com/wjoel/reddit-to-kafka"
  :license {:name "BSD 3-clause"
            :url "http://opensource.org/licenses/BSD-3-Clause"}
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [org.clojure/tools.cli "0.3.3"]
                 [clj-kafka "0.3.3"]
                 [cheshire "5.5.0"]
                 [com.wjoel/reddit-listener "0.0.1"]]
  :main ^:skip-aot com.wjoel.reddit-to-kafka.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
