FROM maven:3.8.5-openjdk-11 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

FROM openjdk:11
WORKDIR /app
COPY --from=build ./app/target/*.jar ./app.jar
ARG EUREKA_SERVER=eureka-server
ARG EUREKA_USER=cursoms-eureka-user
ARG EUREKA_PASSWORD=password
ARG KEYCLOAK_SERVER=keycloak
ARG KEYCLOAK_PORT=8080
ENTRYPOINT java -jar app.jar