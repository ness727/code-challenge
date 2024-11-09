FROM openjdk:17-ea-slim
VOLUME /tmp
COPY build/libs/admin-1.0.jar code-challenge-admin.jar
EXPOSE 8081
ENTRYPOINT ["java",  "-jar", "code-challenge-admin.jar", "--spring.profiles.active=prod"]