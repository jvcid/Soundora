````markdown
# Soundora

## Descrição

O Soundora é uma API REST desenvolvida em Java utilizando Spring Boot, cuja proposta é fornecer a infraestrutura para uma plataforma de streaming de músicas. O sistema permite o gerenciamento de usuários, músicas, playlists, comentários, curtidas e relacionamentos entre usuários, adotando autenticação baseada em JWT (JSON Web Token) e persistência de dados com PostgreSQL.

O projeto foi concebido seguindo uma arquitetura em camadas, visando modularidade, escalabilidade e facilidade de manutenção.

---

# Sumário

1. Introdução
2. Objetivos
3. Tecnologias Utilizadas
4. Arquitetura do Projeto
5. Estrutura de Diretórios
6. Modelo de Domínio
7. Camada de Persistência
8. Camada de Serviços
9. Camada de Controladores
10. DTOs
11. Segurança
12. Fluxo de Autenticação
13. Dependências
14. Estado Atual do Projeto
15. Próximas Etapas

---

# 1. Introdução

O Soundora consiste em uma API responsável pelo gerenciamento de uma plataforma de streaming musical.

A aplicação foi construída utilizando os princípios de separação de responsabilidades, organizando a aplicação em camadas independentes responsáveis por persistência, regras de negócio, segurança e comunicação HTTP.

A autenticação é realizada utilizando Spring Security em conjunto com JSON Web Tokens, permitindo uma arquitetura stateless.

---

# 2. Objetivos

Os principais objetivos do projeto são:

- gerenciamento de usuários;
- autenticação segura utilizando JWT;
- publicação de músicas;
- criação de playlists;
- sistema de comentários;
- sistema de curtidas;
- sistema de seguidores;
- gerenciamento de gêneros musicais;
- controle de acesso baseado em autenticação.

---

# 3. Tecnologias Utilizadas

## I. Linguagem

- Java 21

## II. Framework

- Spring Boot 4

## III. Persistência

- Spring Data JPA
- Hibernate
- PostgreSQL

## IV. Segurança

- Spring Security
- JSON Web Token (JWT)

## V. Ferramentas

- Maven
- Lombok
- ModelMapper

---

# 4. Arquitetura do Projeto

O projeto foi organizado seguindo o padrão de arquitetura em camadas.

```
src
└── main
    └── java
        └── com.br.Soundora
            ├── api
            │   └── handler
            │       ├── config
            │       └── controller
            │
            ├── core
            │   ├── dto
            │   ├── entity
            │   ├── repository
            │   └── service
            │
            └── SoundoraApplication
```

Cada camada possui responsabilidades específicas.

## I. API

Responsável pela comunicação HTTP.

Contém:

- Controllers;
- Configurações relacionadas ao Spring Security.

## II. Core

Responsável pela lógica principal do sistema.

É composta por:

- Entidades;
- DTOs;
- Serviços;
- Repositórios.

---

# 5. Estrutura de Diretórios

```
api
│
├── handler
│   ├── config
│   └── controller
│
core
│
├── dto
├── entity
├── repository
└── service
```

---

# 6. Modelo de Domínio

## I. User

Representa um usuário da plataforma.

### Atributos

- id
- username
- email
- password
- bio
- profilePicture
- creationDate
- role

### Relacionamentos

- OneToMany → Track
- OneToMany → Playlist

A entidade implementa `UserDetails`, permitindo integração direta com o Spring Security.

---

## II. Track

Representa uma música.

### Atributos

- id
- title
- description
- urlAudio
- urlCover
- uploadDate
- duration
- reproductions
- isPublic

### Relacionamentos

- ManyToOne → User
- ManyToMany → Genre
- OneToMany → Comment
- OneToMany → Like

---

## III. Playlist

Representa uma playlist.

### Atributos

- id
- name
- description
- coverImage
- type
- isPublic
- createdAt

### Relacionamentos

- ManyToOne → User
- OneToMany → PlaylistTrack
- ManyToMany → Collaborators

---

## IV. PlaylistTrack

Representa a relação entre playlists e músicas.

Permite manter a ordem das músicas dentro de cada playlist.

---

## V. Genre

Representa um gênero musical.

Relaciona-se com múltiplas músicas.

---

## VI. Comment

Representa comentários realizados em músicas.

Relacionamentos:

- User
- Track

---

## VII. Like

Representa curtidas realizadas pelos usuários.

Relacionamentos:

- User
- Track

---

## VIII. Follower

Representa o relacionamento entre usuários.

Relacionamentos:

- follower
- followed

Foi definida uma restrição de unicidade impedindo que um usuário siga outro mais de uma vez.

---

# 7. Camada de Persistência

A persistência utiliza Spring Data JPA.

Cada entidade possui um Repository responsável pelas operações de banco de dados.

Exemplos:

- UserRepository
- TrackRepository
- PlaylistRepository

O UserRepository foi expandido com:

```java
Optional<User> findByEmail(String email);
```

Esse método é utilizado durante o processo de autenticação.

---

# 8. Camada de Serviços

Os serviços concentram toda a regra de negócio.

## I. UserService

Responsabilidades:

- cadastrar usuários;
- validar unicidade de email;
- validar unicidade de username;
- criptografar senhas;
- persistir usuários.

---

## II. JwtService

Responsável pela manipulação completa dos tokens JWT.

Implementações:

- geração de tokens;
- leitura de claims;
- extração do email;
- validação de assinatura;
- validação de expiração.

---

## III. CustomUserDetailsService

Implementa a interface UserDetailsService.

Responsável por localizar usuários através do email utilizando:

```java
findByEmail(email)
```

Esse serviço é utilizado automaticamente pelo Spring Security.

---

## IV. FollowerService

Responsável pelas regras relacionadas ao sistema de seguidores.

---

# 9. Camada de Controladores

Atualmente existem dois controladores principais.

## I. UserController

Responsável pelo cadastro de usuários.

Endpoint:

```
POST /users
```

---

## II. AuthController

Responsável pelo processo de autenticação.

Endpoint:

```
POST /auth/login
```

Recebe:

```json
{
    "email": "...",
    "password": "..."
}
```

Retorna:

```json
{
    "token": "..."
}
```

---

# 10. DTOs

As entidades nunca são expostas diretamente pela API.

Foram definidos DTOs contendo apenas informações relevantes.

## I. UserDTO

Campos:

- id
- username
- bio
- profilePicture

---

## II. TrackDTO

Campos:

- id
- title
- description
- urlAudio
- urlCover

---

## III. PlaylistDTO

Campos:

- id
- name
- description
- coverImage
- UserDTO

---

## IV. LikeDTO

Campos:

- id
- UserDTO
- TrackDTO

---

## V. FollowerDTO

Campos:

- id
- UserDTO follower
- UserDTO followed

---

## VI. LoginRequestDTO

Representa os dados enviados para autenticação.

Campos:

- email
- password

---

## VII. LoginResponseDTO

Representa a resposta do processo de autenticação.

Campos:

- token

---

# 11. Segurança

O projeto utiliza Spring Security com autenticação baseada em JWT.

A autenticação é completamente stateless.

## I. PasswordEncoder

Foi configurado um bean utilizando BCryptPasswordEncoder.

As senhas nunca são armazenadas em texto plano.

---

## II. AuthenticationManager

Responsável por autenticar usuários utilizando email e senha.

---

## III. DaoAuthenticationProvider

Integra:

- UserDetailsService;
- PasswordEncoder.

---

## IV. SecurityFilterChain

Define:

- rotas públicas;
- rotas protegidas;
- política stateless;
- filtro JWT.

Rotas públicas atualmente:

```
POST /users

POST /auth/login

/tracks/**
```

Todas as demais exigem autenticação.

---

## V. JwtAuthenticationFilter

Executado antes do processamento das requisições protegidas.

Responsável por:

- identificar o header Authorization;
- extrair o token;
- validar assinatura;
- verificar expiração;
- recuperar o usuário;
- registrar a autenticação no SecurityContext.

---

# 12. Fluxo de Autenticação

## I. Cadastro

```
Cliente

↓

POST /users

↓

UserService

↓

PasswordEncoder

↓

Repository

↓

Banco de Dados
```

---

## II. Login

```
Cliente

↓

POST /auth/login

↓

AuthenticationManager

↓

CustomUserDetailsService

↓

PasswordEncoder

↓

JwtService

↓

JWT

↓

Cliente
```

---

## III. Requisições Autenticadas

```
Cliente

↓

Authorization: Bearer TOKEN

↓

JwtAuthenticationFilter

↓

JwtService

↓

CustomUserDetailsService

↓

SecurityContext

↓

Controller
```

---

# 13. Dependências

Principais dependências utilizadas:

- Spring Boot Starter Web
- Spring Boot Starter Security
- Spring Boot Starter Validation
- Spring Boot Starter Data JPA
- PostgreSQL Driver
- JJWT API
- JJWT Impl
- JJWT Jackson
- Lombok
- ModelMapper

---

# 14. Estado Atual do Projeto

## Infraestrutura

- Estrutura em camadas implementada.
- Configuração do Maven concluída.
- Configuração do PostgreSQL concluída.
- Configuração do Spring Security concluída.
- Configuração do JWT concluída.
- Configuração do PasswordEncoder concluída.
- Configuração do AuthenticationManager concluída.
- Configuração do DaoAuthenticationProvider concluída.
- Configuração do JwtAuthenticationFilter concluída.

## Modelagem

- Entidades principais implementadas.
- DTOs principais implementados.
- Repositórios implementados.

## Funcionalidades

Implementadas:

- Cadastro de usuários.
- Login utilizando JWT.

Em desenvolvimento:

- CRUD de músicas.
- CRUD de playlists.
- Sistema de comentários.
- Sistema de curtidas.
- Sistema de seguidores.
- Busca.
- Upload de arquivos.
- Testes automatizados.
- Documentação OpenAPI.
- Deploy.

---

# 15. Próximas Etapas

1. Validar completamente o fluxo de autenticação.
2. Implementar o CRUD de músicas.
3. Implementar o CRUD de playlists.
4. Desenvolver o sistema de comentários.
5. Desenvolver o sistema de curtidas.
6. Desenvolver o sistema de seguidores.
7. Implementar mecanismos de busca.
8. Desenvolver upload de arquivos de áudio e imagem.
9. Implementar documentação da API utilizando OpenAPI/Swagger.
10. Desenvolver testes unitários e de integração.
11. Preparar a aplicação para implantação em ambiente de produção.
````
