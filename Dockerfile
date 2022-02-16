FROM openjdk:11.0-oracle
ARG JAR_FILE=build/libs/project-one-ms-account-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} account.jar
EXPOSE 8089
ENTRYPOINT ["java","-jar","/account.jar"]