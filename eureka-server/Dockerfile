FROM openjdk:11
WORKDIR /app
COPY ./app/target/*.jar ./app.jar
EXPOSE 8761
ENTRYPOINT java -jar app.jar