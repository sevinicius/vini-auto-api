# Configuração para IntelliJ IDEA

## Pré-requisitos
- Java 23 instalado (já configurado)
- Docker e Docker Compose rodando
- IntelliJ IDEA

## Passos para executar no IntelliJ

### 1. Configurar o Java no IntelliJ
1. Abra o IntelliJ IDEA
2. Vá em `File > Project Structure > Project`
3. Configure o Project SDK para Java 23:
   - Path: `/Users/gabrielcarvalho/Library/Java/JavaVirtualMachines/valhalla-ea-23-valhalla+1-90/Contents/Home`

### 2. Configurar o Maven
1. Vá em `File > Settings > Build, Execution, Deployment > Build Tools > Maven`
2. Configure o Maven home directory para usar o wrapper do projeto
3. Ou use o Maven wrapper: `./mvnw`

### 3. Iniciar o Banco de Dados
Execute no terminal (dentro do diretório do projeto):
```bash
docker-compose up -d postgres
```

### 4. Executar a Aplicação
1. No IntelliJ, localize a classe `ViniAutosApplication.java`
2. Clique com o botão direito e selecione "Run 'ViniAutosApplication'"
3. Ou use o script: `./run-app.sh`

## Configurações do Banco
- **Host**: localhost
- **Porta**: 5432
- **Database**: vini_autos
- **Usuário**: postgres
- **Senha**: postgres

## Endpoints da API
- **Base URL**: http://localhost:8081
- **Swagger UI**: http://localhost:8081/swagger-ui.html (se configurado)

## Solução de Problemas
- Se o banco não conectar, execute: `docker-compose down && docker-compose up -d postgres`
- Se houver problemas de compilação, limpe o projeto: `./mvnw clean compile`
