# Rest API

API Backend para gerenciamento de membros e envio de emails.

## Sumário

- [Estrutura do Projeto](#estrutura-do-projeto)
- [Modelo DDD e Clean Code](#modelo-ddd-e-clean-code)
- [Autenticação JWT](#autenticação-jwt)
- [Banco de Dados PostgreSQL](#banco-de-dados-postgresql)
- [Principais Tecnologias](#principais-tecnologias)
- [Endpoints Principais](#endpoints-principais)
- [Como Rodar o Projeto](#como-rodar-o-projeto)

---

## Estrutura do Projeto

O projeto segue o padrão Maven e está organizado da seguinte forma:

```
src/main/java/com/vazcode/
├── application/      # Casos de uso e serviços de aplicação
│   ├── services/
│   └── usecases/
├── domain/           # Entidades, repositórios e value objects do domínio
│   ├── entities/
│   ├── repositories/
│   └── valueobjects/
├── infrastructure/   # Implementações técnicas, integrações, configs, segurança
│   ├── repositories/
│   ├── config/
│   └── security/
├── presentation/     # Camada de apresentação (controllers, dtos)
│   ├── controllers/
│   └── dtos/
└── exception/        # Tratamento de exceções
```

## Modelo DDD e Clean Code

- **Domain-Driven Design (DDD):**  
  O projeto está dividido em camadas claras: `domain` (regras de negócio), `application` (casos de uso), `infrastructure` (persistência, segurança, configs) e `presentation` (controllers REST).
- **Clean Code:**  
  Utiliza boas práticas como injeção de dependências, separação de responsabilidades, uso de DTOs, tratamento centralizado de exceções e nomes claros para classes/métodos.

## Autenticação JWT

- Implementação de autenticação baseada em JWT (JSON Web Token).
- Classes principais:  
  - `JwtTokenProvider` (geração e validação de tokens)
  - `JwtAuthenticationFilter` (filtro de autenticação)
  - `JwtAuthenticationEntryPoint` (tratamento de acessos não autorizados)
- Endpoints de autenticação em `/auth/login`.

## Banco de Dados PostgreSQL

- Configuração no arquivo `application.properties`:
  ```
  spring.datasource.url=jdbc:postgresql://localhost:5432/java_api
  spring.datasource.username=root
  spring.datasource.password=root
  spring.jpa.hibernate.ddl-auto=update
  spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
  ```
- Utiliza Spring Data JPA para persistência.

## Principais Tecnologias

- **Java 17**
- **Spring Boot 3.1**
- **Spring Data JPA**
- **Spring Security**
- **JWT (io.jsonwebtoken)**
- **PostgreSQL**
- **Lombok**
- **MapStruct**
- **Swagger/OpenAPI** (documentação automática)
- **Envio de Email** (Spring Boot Starter Mail)

### Autenticação

- `POST /auth/login`  
  Autentica o usuário e retorna um token JWT.


## Como Rodar o Projeto

1. **Pré-requisitos:**  
   - Java 17+  
   - Maven  
   - PostgreSQL rodando e configurado conforme o `application.properties`

2. **Build e execução:**
   ```bash
   mvn clean install
   mvn spring-boot:run
   ```

3. **Acesse a documentação Swagger:**  
   - [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)
