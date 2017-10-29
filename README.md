# Cloud-Native Reference

This is a reference implementation for a cloud-native architecture. The microservice architectural style is used with the following design patterns:

* Command Query Responsibility Separation (CQRS).
* Event Sourcing
* Gateway Router
* Circuit-Breaker
* External Configuration Service
* Throttling

## Prerequisites

This reference implementation can be run through a series of Docker containers. The following software is required to build and execute the code.

* Java 8 SDK
* Docker
* Docker-Compose
* Gradle

The code was developed and tested with the Spring Tool Suite IDE running on MacOS 10.12.6 Sierra. A machine with at least 8GB is required to run all the containers concurrently.

## Running this Reference Implementation

### 1. Build the Docker Containers

Clone the Git repository to a directory

```bash
git clone https://github.com/drm317/cloud-native-reference.git
```

Then build the code and all the required Docker container images

```bash
cd cloud-native-reference
./gradlew clean image
```

## 2. Start the System

## 3. Execute the Integration Tests

The integration tests include an end-to-end test and system health check. They require the mongodb, rabbitmq, configuration-service, service-registry, gateway-service, order-service and order-query-service containers to be up and running.

Run the integration tests with the following:

```bash
./gradlew integration-test:integrationTest
```

The architecture makes use of a design pattern called 'Event Sourcing' with 'Eventual Consistency'. There is a delay between each test to allow for event messages to propagate from the command service to the query service. In addition, a demonstration throttling filter in the Gateway Service currently limits traffic to one request every 10 seconds.

## 4. Clean the Docker Containers

The Docker build artifacts will be stored locally. These can be cleaned up to save disk space

```bash
./clean-docker.sh
```

