FROM openjdk:11-jdk-slim

WORKDIR /app

# Copia o JAR da aplicação
COPY target/Vini-autos-0.0.1-SNAPSHOT.jar app.jar

# Expõe a porta da aplicação
EXPOSE 8081

# Comando para executar a aplicação com timeout para aguardar o banco
CMD ["java", "-jar", "app.jar", "--spring.profiles.active=docker"]

