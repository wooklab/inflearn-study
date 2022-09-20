FROM openjdk:17-ea-jdk-slim
VOLUME /tmp
COPY target/catalog-service-1.0.jar CatalogService.jar
ENTRYPOINT ["java", "-jar", "CatalogService.jar"]