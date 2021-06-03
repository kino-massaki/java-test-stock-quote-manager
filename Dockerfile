FROM maven:3.8.1-openjdk-11
WORKDIR /stock-quote-manager
COPY ./target/stock-quote-manager-0.0.1-SNAPSHOT.jar ./stock-quote-manager.jar
ENTRYPOINT ["java", "-jar", "stock-quote-manager.jar"]