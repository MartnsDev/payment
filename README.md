# Payment Service (MS de Pagamentos)

Microserviço responsável pelo fluxo de pagamentos de aplicações que utilizarem sua API.
Ele centraliza criação, consulta, atualização e gerenciamento de pagamentos, seguindo boas práticas de microsserviços, escalabilidade e separação clara de domínios.

 Tecnologias Utilizadas
```
Java 17
Spring Boot 4
Spring Web
Spring Data JPA
Flyway
ModelMapper
MySQL 8
Maven
Spring Validation
```
📂 Estrutura do Projeto
```
br.com.ms.pagamentos
 ├── api
 │    ├── controller
 │    └── dto
 ├── domain
 │    ├── model
 │    ├── repository
 │    └── service
 ├── infra
 │    ├── config
 │    └── security 
 ├── PagamentosApplication.java
resources
 ├── db/migration
 ├── application.properties
 ├── static/
 └── templates/
```
 Config Banco de Dados:
```
spring.application.name=pagamentos

spring.datasource.url=jdbc:mysql://localhost:3306/seu_banco?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC
spring.datasource.username=${db:username}
spring.datasource.password=${db:password}

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true

spring.flyway.baseline-on-migrate=true
spring.flyway.baseline-version=1
spring.flyway.locations=classpath:db/migration

server.port=8081
```
🔐 Segurança
```
Pasta reservada para futuras implementações de:
Autenticação JWT
Autorização por roles
Regras de uso multi-projeto
Rate limiting
```
📄 Licença
```
Este projeto não possui permissão de uso ou redistribuição.
Todo o código é de uso exclusivo do autor MartnsDev.
```
