 Usar imagen base de Java
FROM openjdk:21-bookworm

# Directorio de trabajo
WORKDIR /app

# Copiar el JAR al contenedor
COPY target/example-app4-0.0.1-SNAPSHOT.jar app.jar

# Exponer puerto
EXPOSE 8080

# Comando de ejecución
ENTRYPOINT ["java", "-jar", "app.jar"]