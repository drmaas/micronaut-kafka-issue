## Reproduction of issue with Micronaut Kafka Streams integration

When mulitple streams are configured, DefaultKafkaStreamsConfiguration and default ConfiguredStreamBuilder is still created. However it is not assigned to any topic which fails immediately with Kafka 2.5.1

Run to get the error
```
./gradlew run
```
To workaround
* replace named stream with default
    * change application.yml `kafka.streams.stream-2` -> `kafka.streams.default`
    * Remove `@Named("stream-2")` annotation in `StreamFactory`