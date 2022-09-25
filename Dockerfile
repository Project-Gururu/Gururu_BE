FROM openjdk:11-jdk

ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} cicd-gururu.jar

ARG IDLE_PROFILE
ENV ENV_IDLE_PROFILE=$IDLE_PROFILE

ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=${ENV_IDLE_PROFILE}","-Dspring.config.location=classpath:/application.yml,/Users/kiwon/secret/application-secret.yml" ,"/cicd-gururu.jar"]
