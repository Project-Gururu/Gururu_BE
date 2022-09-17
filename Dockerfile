FROM openjdk:11-jdk

ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} cicd-gururu.jar

ARG IDLE_PROFILE
ENV ENV_IDLE_PROFILE=$IDLE_PROFILE

ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=${ENV_IDLE_PROFILE}", "/cicd-gururu.jar"]
