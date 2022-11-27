FROM openjdk:8
EXPOSE 8080
ADD target/api-chess-management-0.0.1.jar api-chess-management-0.0.1.jar
ENTRYPOINT ["java","-jar","proyecto-gestion-ajedrez-0.0.1.jar"]