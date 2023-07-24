FROM maven:3.8.5-openjdk-11 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

FROM openjdk:11
WORKDIR /app
COPY --from=build ./app/target/*.jar ./app.jar
ARG POSTGRES_SERVER=localhost
ARG POSTGRES_USER=postgres
ARG POSTGRES_PASSWORD=postgres
ARG EUREKA_SERVER=eureka_server
ARG EUREKA_USER=cursoms-eureka-user
ARG EUREKA_PASSWORD=R0To1up4fY2E96XTjld8uhPwJ
ENTRYPOINT java -jar -Dspring.profiles.active=production app.jar