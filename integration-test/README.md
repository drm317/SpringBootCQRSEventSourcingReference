## Integration Tests - Instructions

The integration tests include an end-to-end test and system health check. They require the mongodb, rabbitmq, configuration-service, service-registry, gateway-service, order-service and order-query-service containers to be up and running.

Ensure the code is compiled and Docker images are built successfully for each container.

```bash
./gradlew clean image
```

To start the containers for the tests issue the following commands in order. Check that each service is started within its container before proceeding to the next container.

```bash
docker-compose up mongodb
docker-compose up rabbitmq
docker-compose up configuration-service
docker-compose up service-registry
docker-compose up gateway-service
docker-compose up order-service
docker-compose up order-query-service
```

Run the integration tests with the following...

```bash
./gradlew integration-test:integrationTest
```

The architecture makes use of a design pattern called 'event sourcing' with 'eventual consistency'. There is a delay between each test to allow for event messages to propagate from the command service to the query service.

