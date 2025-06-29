FROM eclipse-temurin:17-jdk-alpine
ADD target/product-api-1.2.1.jar product-api.jar
EXPOSE 9902
ENTRYPOINT ["java", "-jar", "product-api.jar"]