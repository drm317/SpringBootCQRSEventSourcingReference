FROM frolvlad/alpine-oraclejdk8:slim
MAINTAINER Daniel Marlow
VOLUME ["/tmp"]
EXPOSE 8901
ADD order-query-service.jar app.jar
RUN sh -c 'touch /app.jar'
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/app.jar"]
