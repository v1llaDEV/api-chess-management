FROM openjdk:17.0.2-slim-buster
EXPOSE 8080:8080
COPY target/api-chess-management.jar /usr/local/service/api-chess-management.jar
ENTRYPOINT ["java","-jar","/usr/local/service/service/api-chess-management.jar"]