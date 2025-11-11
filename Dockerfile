FROM ubuntu:latest
LABEL authors="iagoPC"

ENTRYPOINT ["top", "-b"]

# --- ESTÁGIO 1: Build (Construção) ---
# Usamos uma imagem oficial do Maven com Java 21
FROM maven:3.9.6-eclipse-temurin-21 AS build

# Define o diretório de trabalho dentro do container
WORKDIR /app

# Copia os arquivos do projeto para o container
COPY . .

# Roda o comando do Maven para "empacotar" o projeto
RUN mvn clean package -DskipTests

# --- ESTÁGIO 2: Run (Execução) ---
# Usamos uma imagem leve, apenas com o Java 21
FROM eclipse-temurin:21-jre-jammy

# Define o diretório de trabalho
WORKDIR /app

# Expõe a porta 8080 (a porta padrão do Spring Boot)
EXPOSE 8080

# Copia APENAS o arquivo .jar final do estágio de build para esta imagem
COPY --from=build /app/target/*.jar app.jar

# Comando que será executado quando o container iniciar
ENTRYPOINT ["java", "-jar", "app.jar"]