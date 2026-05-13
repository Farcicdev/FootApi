# FootApi

API REST para gerenciamento de clubes, jogadores, estádios e usuários do futebol brasileiro, construída com Spring Boot, PostgreSQL, Flyway, Spring Security e autenticação JWT com chaves RSA.

O projeto simula uma base de dados esportiva com foco em boas práticas de backend: separação em camadas, DTOs, validação de entrada, migrations versionadas, controle de acesso por escopos e testes automatizados.

## Destaques

- API REST com Spring Boot e Java 17
- Autenticação stateless com JWT assinado por RSA
- Autorização por escopos, como `club:read`, `player:write` e `admin:all`
- Persistência com Spring Data JPA e PostgreSQL
- Versionamento de banco com Flyway
- Mapeamento entre entidades e DTOs com MapStruct
- Validações com Jakarta Validation
- Tratamento centralizado de exceções
- Ambiente local com Docker Compose

## Stack

| Camada | Tecnologias |
| --- | --- |
| Linguagem | Java 17 |
| Framework | Spring Boot 4 |
| API | Spring Web MVC |
| Segurança | Spring Security, OAuth2 Resource Server, JWT |
| Banco | PostgreSQL 16 |
| Persistência | Spring Data JPA, Hibernate |
| Migrations | Flyway |
| Mapeamento | MapStruct |
| Boilerplate | Lombok |
| Build | Maven |
| Testes | JUnit, Mockito, Spring Boot Test |

## Domínio

A API trabalha com os principais recursos do contexto futebolístico:

- `Club`: clubes com nome, data de fundação, imagem e estádio vinculado.
- `Stadium`: estádios com nome, cidade, capacidade e imagem.
- `Player`: jogadores com nome, posição, número de camisa e clube.
- `Users`: usuários autenticáveis com senha criptografada e escopos de acesso.
- `Scopes`: permissões associadas aos usuários para proteger endpoints.

O banco já possui uma carga inicial com clubes e estádios paulistas, incluindo São Paulo, Palmeiras, Corinthians, Santos, Guarani e Ponte Preta.

## Arquitetura

```text
src/main/java/farcic/dev/footApi
├── config        # Segurança, CORS, handlers e annotations de autorização
├── controller    # Entrada HTTP da API
├── dto           # Contratos de request e response
├── entity        # Entidades JPA
├── exception     # Exceções de domínio
├── mapper        # Conversões com MapStruct
├── repository    # Repositórios Spring Data JPA
└── service       # Regras de negócio
```

## Como Rodar Localmente

### 1. Pré-requisitos

- Java 17
- Docker e Docker Compose
- Maven ou Maven Wrapper

### 2. Suba o PostgreSQL

```bash
docker compose up -d
```

O banco ficará disponível em:

```text
localhost:5431
database: db_foot
user: postgres
password: postgre
```

### 3. Gere as chaves RSA do JWT

O projeto lê as chaves configuradas em `src/main/resources/application.yml`:

```yaml
jwt:
  private:
    key: /home/augusto/keys/authz.pem
  public:
    key: /home/augusto/keys/authz.pub
```

Para gerar as chaves:

```bash
mkdir -p /home/augusto/keys
openssl genrsa -out /home/augusto/keys/authz.pem 2048
openssl rsa -in /home/augusto/keys/authz.pem -pubout -out /home/augusto/keys/authz.pub
```

### 4. Rode a aplicação

```bash
bash mvnw spring-boot:run
```

A API ficará disponível em:

```text
http://localhost:8080
```

## Autenticação

A API usa autenticação JWT. Os endpoints públicos são:

- `POST /users`
- `POST /login`

Os demais endpoints exigem token Bearer.

### Criar usuário

Antes de criar usuários com escopos, cadastre os escopos desejados na tabela `scopes`, por exemplo:

```sql
INSERT INTO scopes (name) VALUES
  ('admin:all'),
  ('club:read'),
  ('club:write'),
  ('stadium:read'),
  ('stadium:write'),
  ('player:read'),
  ('player:write');
```

Request:

```http
POST /users
Content-Type: application/json
```

```json
{
  "name": "Admin",
  "email": "admin@email.com",
  "password": "123456",
  "scopes": [1]
}
```

### Login

```http
POST /login
Content-Type: application/json
```

```json
{
  "email": "admin@email.com",
  "password": "123456"
}
```

Response:

```json
{
  "token": "eyJhbGciOiJSUzI1NiJ9...",
  "expiresIn": 3600
}
```

Use o token nos endpoints protegidos:

```http
Authorization: Bearer <token>
```

## Endpoints

### Clubes

| Método | Rota | Descrição | Escopo |
| --- | --- | --- | --- |
| `GET` | `/clubs` | Lista clubes com paginação | `club:read` ou `admin:all` |
| `GET` | `/clubs/{id}` | Busca detalhes de um clube | `club:read` ou `admin:all` |
| `POST` | `/clubs` | Cria um clube | `club:write` ou `admin:all` |
| `GET` | `/clubs/{id}/players` | Lista jogadores de um clube | `club:read` ou `admin:all` |

Exemplo de criação:

```json
{
  "name": "São Paulo Futebol Clube",
  "founded": "1930-01-25",
  "urlImg": "https://example.com/sao-paulo.png",
  "stadiumId": 1
}
```

### Estádios

| Método | Rota | Descrição | Escopo |
| --- | --- | --- | --- |
| `GET` | `/stadiums` | Lista estádios com paginação | `stadium:read` ou `admin:all` |
| `POST` | `/stadiums` | Cria um estádio | `stadium:write` ou `admin:all` |

Exemplo de criação:

```json
{
  "name": "Morumbi",
  "city": "São Paulo",
  "capacity": 66000,
  "urlImg": "https://example.com/morumbi.png"
}
```

### Jogadores

| Método | Rota | Descrição | Escopo |
| --- | --- | --- | --- |
| `GET` | `/players` | Lista jogadores com paginação | `player:read` ou `admin:all` |
| `GET` | `/players/{id}` | Busca detalhes de um jogador | `player:read` ou `admin:all` |
| `POST` | `/players` | Cria um jogador | `player:write` ou `admin:all` |

Exemplo de criação:

```json
{
  "name": "Lucas Moura",
  "position": "FORWARD",
  "shirtNumber": 7,
  "urlImg": "https://example.com/lucas.png",
  "clubId": 1
}
```

### Usuários

| Método | Rota | Descrição | Acesso |
| --- | --- | --- | --- |
| `POST` | `/users` | Cria usuário | Público |
| `PATCH` | `/users?id={id}` | Atualiza senha | Autenticado |

### Recursos

| Método | Rota | Descrição |
| --- | --- | --- |
| `GET` | `/resources/possition` | Lista posições disponíveis |

Posições disponíveis:

- `GOALKEEPER`
- `DEFENDER`
- `FULLBACK`
- `MIDFIELDER`
- `FORWARD`

## Paginação

Endpoints de listagem aceitam os parâmetros padrão do Spring Data:

```http
GET /clubs?page=0&size=10&sort=name,asc
```

## Migrations

As migrations ficam em:

```text
src/main/resources/db/migration
```

Arquivos atuais:

- `V1__create_foot_table.sql`: cria tabelas principais de estádios, clubes e jogadores.
- `V2__insert_clubs_sao_paulo.sql`: insere dados iniciais de clubes e estádios.
- `V3__create_scopes_tables.sql`: cria usuários, escopos e tabela de relacionamento.

## Testes

Para rodar os testes:

```bash
bash mvnw test
```

Para compilar sem executar os testes:

```bash
bash mvnw test -DskipTests
```

Para empacotar ignorando compilação e execução dos testes:

```bash
bash mvnw clean package -Dmaven.test.skip=true
```

## Decisões Técnicas

- JWT com RSA: separa chave pública e privada, deixando a validação de token mais segura e alinhada com cenários reais.
- Flyway: garante versionamento do schema e reprodutibilidade do banco.
- DTOs e MapStruct: evita expor entidades diretamente e reduz código manual de conversão.
- Escopos no token: permite autorização granular por recurso e ação.
- Services isoladas: mantém regra de negócio fora dos controllers e facilita testes unitários.

## Melhorias Futuras

- Documentação OpenAPI/Swagger
- Seeds automáticas para escopos padrão
- Refresh token
- Testes de integração com Testcontainers
- Endpoints de atualização e remoção para clubes, jogadores e estádios
- Padronização de respostas de erro para validações

## Autor

Desenvolvido como projeto de backend Java com foco em API REST, segurança, persistência relacional e boas práticas de arquitetura em camadas.
