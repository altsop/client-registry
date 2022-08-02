FROM gradle:7.5.0-jdk11-alpine AS build
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle build --no-daemon -x test

FROM openjdk:11.0.4-jre-slim
EXPOSE 8080
RUN mkdir /app
COPY --from=build /home/gradle/src/app/build/libs/client-registry-1.0.jar /app/client-registry.jar
ENTRYPOINT ["java","-jar","/app/client-registry.jar"]