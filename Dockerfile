FROM openjdk:11-jre-slim

WORKDIR /app

COPY /target/test.task-0.0.1-SNAPSHOT.jar nicole-spring-app.jar

EXPOSE 5558

# Команда для запуска приложения при запуске контейнера
CMD ["java", "-jar", "nicole-spring-app.jar"]
