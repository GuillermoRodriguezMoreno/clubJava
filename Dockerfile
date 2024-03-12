#Configuración Dockerfile para compilación y despliegue en Render
FROM maven:3.8.5-openjdk-17 AS build
COPY . .
RUN mvn clean
RUN mvn package -DskipTests


FROM amazoncorretto:17-alpine
COPY --from=build /target/clubJava.jar clubJava.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar","clubJava.jar"]