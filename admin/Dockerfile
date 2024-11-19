FROM openjdk:17-ea-slim
VOLUME /tmp
COPY build/libs/admin-1.0.war code-challenge-admin.war
EXPOSE 28081
ENTRYPOINT ["java",  "-jar", "code-challenge-admin.war", "--spring.profiles.active=prod"]