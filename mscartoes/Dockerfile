FROM maven:3.8.5-openjdk-11 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

FROM openjdk:11
WORKDIR /app
COPY --from=build ./app/target/*.jar ./app.jar
ARG RABBITMQ_SERVER=rabbitmq_host
ARG RABBITMQ_USER=guest
ARG RABBITMQ_PASSWORD=password
ARG POSTGRES_SERVER=localhost
ARG POSTGRES_USER=postgres
ARG POSTGRES_PASSWORD=password
ARG EUREKA_SERVER=eureka-server
ARG EUREKA_USER=cursoms-eureka-user
ARG EUREKA_PASSWORD=password
ENTRYPOINT java -jar app.jar