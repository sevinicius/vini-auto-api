# Vini Autos API

API REST desenvolvida em Spring Boot para o sistema de gerenciamento de autopeças Vini Autos.

## 🚀 Tecnologias

- **Java 11**
- **Spring Boot 2.7.18**
- **Spring Security** (JWT)
- **Spring Data JPA**
- **PostgreSQL**
- **Maven**

## 📋 Pré-requisitos

- Java 11 ou superior
- Maven 3.6+
- PostgreSQL 12+

## 🔧 Execução Local

### 1. Configurar Banco de Dados

Crie um banco PostgreSQL:
```sql
CREATE DATABASE vini_autos;
CREATE USER postgres WITH PASSWORD 'postgres';
GRANT ALL PRIVILEGES ON DATABASE vini_autos TO postgres;
```

### 2. Configurar Aplicação

Edite o arquivo `src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/vini_autos
spring.datasource.username=postgres
spring.datasource.password=postgres
```

### 3. Executar Aplicação

```bash
# Instalar dependências e executar
./mvnw spring-boot:run

# Ou compilar e executar JAR
./mvnw clean package
java -jar target/Vini-autos-0.0.1-SNAPSHOT.jar
```

A API estará disponível em: http://localhost:8081

## 🐳 Execução com Docker

```bash
# Construir imagem
docker build -t vini-autos-api .

# Executar container
docker run -p 8081:8081 \
  -e SPRING_DATASOURCE_URL=jdbc:postgresql://host.docker.internal:5432/vini_autos \
  -e SPRING_DATASOURCE_USERNAME=postgres \
  -e SPRING_DATASOURCE_PASSWORD=postgres \
  vini-autos-api
```

## 📚 Endpoints da API

### Autenticação

#### Registrar Usuário
```http
POST /auth/register
Content-Type: application/json

{
  "username": "admin",
  "password": "admin123",
  "email": "admin@vini.com"
}
```

#### Fazer Login
```http
POST /auth/login
Content-Type: application/json

{
  "username": "admin",
  "password": "admin123"
}
```

**Resposta:**
```json
{
  "token": "eyJhbGciOiJIUzI1NiJ9...",
  "username": "admin",
  "role": "USER"
}
```

### Produtos

#### Listar Todos os Produtos
```http
GET /produto
Authorization: Bearer {token}
```

#### Criar Produto
```http
POST /produto
Authorization: Bearer {token}
Content-Type: application/json

{
  "nome": "Filtro de Ar",
  "descricao": "Filtro de ar para motor",
  "preco": 45.90,
  "quantidade": "10 unidades"
}
```

#### Atualizar Produto
```http
PUT /produto?id={id}
Authorization: Bearer {token}
Content-Type: application/json

{
  "nome": "Filtro de Ar Atualizado",
  "descricao": "Nova descrição",
  "preco": 50.00,
  "quantidade": "15 unidades"
}
```

#### Excluir Produto
```http
DELETE /produto?nome={nome}
Authorization: Bearer {token}
```

#### Buscar Produto por Nome
```http
GET /produto/buscar?nome={nome}
Authorization: Bearer {token}
```

## 🗄️ Estrutura do Banco

### Tabela `usuario`
| Campo | Tipo | Descrição |
|-------|------|-----------|
| id | SERIAL | ID único |
| username | VARCHAR(50) | Nome de usuário (único) |
| email | VARCHAR(100) | Email |
| password | VARCHAR(255) | Senha criptografada |
| role | VARCHAR(20) | Papel do usuário |

### Tabela `produto`
| Campo | Tipo | Descrição |
|-------|------|-----------|
| id | SERIAL | ID único |
| nome | VARCHAR(100) | Nome do produto (único) |
| descricao | TEXT | Descrição |
| preco | DECIMAL(10,2) | Preço |
| quantidade | VARCHAR(50) | Quantidade em estoque |

## 🔐 Segurança

- **JWT**: Tokens com expiração de 24 horas
- **BCrypt**: Criptografia de senhas
- **CORS**: Configurado para frontend
- **Validação**: Bean Validation para dados

## 🧪 Testes

```bash
# Executar todos os testes
./mvnw test

# Executar testes com cobertura
./mvnw test jacoco:report
```

## 📊 Monitoramento

### Health Check
```http
GET /actuator/health
```

### Logs
Os logs são configurados para nível INFO. Para debug, altere em `application.properties`:
```properties
logging.level.com.okta.SAAM.Vini_autos=DEBUG
```

## 🚀 Deploy

### Perfil Docker
Para produção, use o perfil `docker`:
```bash
java -jar app.jar --spring.profiles.active=docker
```

### Variáveis de Ambiente
- `SPRING_DATASOURCE_URL`
- `SPRING_DATASOURCE_USERNAME`
- `SPRING_DATASOURCE_PASSWORD`
- `JWT_SECRET`

## 📝 Swagger/OpenAPI

Documentação da API disponível em:
- http://localhost:8081/swagger-ui.html (se configurado)

## 🔧 Desenvolvimento

### Estrutura do Projeto
```
src/main/java/com/okta/SAAM/Vini_autos/
├── busines/          # Services e DTOs
├── config/           # Configurações
├── controller/       # Controllers REST
└── infraestructure/  # Entities e Repositories
```

### Adicionar Nova Funcionalidade

1. Criar Entity em `infraestructure/entitys/`
2. Criar Repository em `infraestructure/repository/`
3. Criar Service em `busines/`
4. Criar Controller em `controller/`
5. Adicionar testes se necessário

---

Para mais informações, consulte o README.md principal do projeto.