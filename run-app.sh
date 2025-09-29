#!/bin/bash

# Script para executar a aplicação Vini Autos
# Configura o Java 23 e inicia a aplicação

echo "🚀 Iniciando Vini Autos API..."

# Configura o Java 23
export JAVA_HOME=/Users/gabrielcarvalho/Library/Java/JavaVirtualMachines/valhalla-ea-23-valhalla+1-90/Contents/Home

# Verifica se o Docker está rodando
if ! docker ps | grep -q vini_autos_postgres; then
    echo "📦 Iniciando banco de dados PostgreSQL..."
    docker-compose up -d postgres
    sleep 5
fi

# Verifica se o banco está acessível
echo "🔍 Verificando conexão com o banco de dados..."
if docker exec vini_autos_postgres pg_isready -U postgres; then
    echo "✅ Banco de dados está rodando!"
else
    echo "❌ Erro: Banco de dados não está acessível"
    exit 1
fi

# Compila e executa a aplicação
echo "🔨 Compilando aplicação..."
./mvnw clean compile

echo "🚀 Iniciando aplicação Spring Boot..."
./mvnw spring-boot:run
