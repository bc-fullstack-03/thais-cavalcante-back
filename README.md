<p align="center">
 <h1 align="center" style="font-size:40px">PARROT API</h2>
</p>


<br />

- [🧾 Descrição](#-descrição)
- [🔧 Tech stack](#-tech-stack)
- [🚀 Rodando o projeto](#-rodando-o-projeto)
  - [Pré-Requisitos](#pre-requisitos)
  - [Clonando o repositório](#clonando-repositorio)
  - [Rodando](#running)
  - [Documentação Swagger](#swagger)
  - [Autenticação](#autenticação)
- [👤 Desenvolvedora](#-desenvolvedora)

<br />

<a name="descrição"></a>
## 🧾 Descrição

**Projeto Backend - Programa Trainee SysMap de Excelência Full Stack**

**Objetivo:** Desenvolver uma API para uma rede social.

<br />

<a name="tech-stack"></a>
## 🔧 Tech stack

- Java 17
- Spring Boot 3.0.6
- Spring Data MongoDB
- Docker

<br />

<a name="roodando-o-projeto"></a>
## 🚀 Rodando o projeto

<a name="pre-requisitos"></a>
### Pré-Requisitos

Antes de começar, certifique-se de que o Docker esteja instalado em sua máquina.

<br />

<a name="clonando-repositorio"></a>
### Clonando o repositório

```
## SSH
git@github.com:bc-fullstack-03/thais-cavalcante-backend.git
```
```
## HTTPS
https://github.com/bc-fullstack-03/thais-cavalcante-backend.git
```
```
## GitHub CLI
gh repo clone bc-fullstack-03/thais-cavalcante-backend
```

<a name="running"></a>
### Rodando

```
docker compose up
```

<br />

<a name="setting"></a>
### Configurando o LocalStack

```
aws configure --profile default
```

```
AWS Access Key ID [None]: mykey
AWS Secret Access Key [None]: mykey
Default region name [None]: us-west-2
Default output format [None]: json
```

```
aws s3 mb s3://demo-bucket –endpoint-url http://localhost:4566

```

<br />

<a name="swagger"></a>
### Documentação Swagger

Acesse a seguinte URL em seu navegador:

**http://localhost:8082/swagger-ui/index.html**

Isso abrirá a interface do Swagger, onde você pode testar os endpoints da API.

<br />

![screenshot (11)](https://github.com/bc-fullstack-03/thais-cavalcante-backend/assets/94868398/2286c84e-a775-45d2-9d00-aa97a64c7d0a)



<br />

<a name="autenticação"></a>
### Autenticação

Com exceção da rota de criar usuário e da própria rota de autenticação, todas as outras rotas só podem ser acessadas por um usuário autenticado. Então tenha certeza de que está logado antes de testar os outros endpoints. 


![screenshot (12)](https://github.com/bc-fullstack-03/thais-cavalcante-backend/assets/94868398/64bf5539-277b-4d41-921e-fec52f121a87)

<a name="desenvolvedora"></a>
## 👤 Desenvolvedora

[Thais Cavalcante](https://www.linkedin.com/in/thaispcavalcante/)
