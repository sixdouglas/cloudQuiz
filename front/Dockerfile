FROM openjdk:8-jdk-alpine

RUN apk update && apk add bash

ENV SPRING_OUTPUT_ANSI_ENABLED=ALWAYS \
    DELAY_SLEEP=0 \
    JAVA_OPTS=""

RUN mkdir -p /app

ENV PROJECT_HOME /app

EXPOSE 6060

COPY ./target/front-0.0.1-SNAPSHOT.jar /app/front.jar

WORKDIR $PROJECT_HOME

CMD echo "The application will start in ${DELAY_SLEEP}s..." && \
    sleep ${DELAY_SLEEP} && \
    java ${JAVA_OPTS} -jar ./front.jar