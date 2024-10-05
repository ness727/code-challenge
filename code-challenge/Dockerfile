FROM openjdk:17-ea-slim
VOLUME /tmp
COPY build/libs/code-challenge-1.0.jar code-challenge.jar
EXPOSE 8080
ENTRYPOINT ["java",  "-jar", "code-challenge.jar"]