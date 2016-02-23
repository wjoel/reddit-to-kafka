# reddit-to-kafka

Moves messages from reddit to a Kafka topic.

## Usage

    $ java -jar target/uberjar/reddit-to-kafka-0.1.0-standalone.jar [args]

## Options

`--kafka-connect BROKERS` Kafka broker list
`--topic TOPIC` Kafka topic
`--client-id` reddit client ID
`--client-secret` reddit client secret

## Examples

Send new posts in JSON format to the topic `reddit-testing`

    $ java -jar target/uberjar/reddit-to-kafka-0.1.0-standalone.jar
           -k localhost:9092
           -t reddit-testing
           -c KDL92zNNDPKolI
           -s 82lKCKGHZMliqdpErq2oLNqzdN3
