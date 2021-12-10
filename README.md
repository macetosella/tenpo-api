[![Build Status](https://travis-ci.com/macetosella/tenpo-api.svg?branch=master)](https://travis-ci.com/macetosella/tenpo-api)
[![codecov](https://codecov.io/gh/macetosella/tenpo-api/branch/master/graph/badge.svg)](https://codecov.io/gh/macetosella/tenpo-api)

![img_2.png](https://krealo.pe/wp-content/uploads/2020/12/Tengo-original-pa%CC%81gina-web--e1609189663257.png)

# Desafio Tenpo

## Tabla de Contenidos

- [Requerimientos](#requerimientos)
   - [Tecnologias usadas](#tecnologias-usadas)
   - [Comentarios](#comentarios)
- [Setup](#setup)
   - [Instrucciones](#instrucciones)
   - [Uso](#uso)
   - [API Url](#api)
   - [Servicios](#servicios)
      - [Swagger](#swagger) 
      - [Signup](#ignup)
      - [Login](#login)
      - [Logout](#logut)
      - [Sum](#sum)
      - [History](#history)
- [Test](#test)
   - [Automaticos](#automaticos)
   - [Cobertura](#cobertura)
   
### Tecnologias usadas
- [JDK 11](https://www.oracle.com/index.html)
- [Spring Boot](https://projects.spring.io/spring-boot/)
- [Spring JPA](https://docs.spring.io/spring-data/jpa/docs/current/reference/html/)
- [Docker](https://www.docker.com/)
- [Postgre SQL](https://www.postgresql.org/)
- [Log4J2](http://www.slf4j.org/)
- [jUnit](http://junit.org/junit5/)
- [Mockito](http://site.mockito.org/)
- [Gradle](https://gradle.org/)
- [Jacoco](https://www.jacoco.org/jacoco/trunk/index.html)
- [Travis](https://travis-ci.com/)

### Requerimientos:

1. Debes desarrollar una API REST en Spring Boot con las siguientes funcionalidades:
   * Sign up usuarios.
   * Login usuarios.
   * Sumar dos números. Este endpoint debe retornar el resultado de la suma y puede ser consumido solo por usuarios logueados.
   * Historial de todos los llamados a todos los endpoint. Responder en Json, con data paginada.
   * Logout usuarios.
   * El historial y la información de los usuarios se debe almacenar en una database PostgreSQL.
   * Incluir errores http. Mensajes y descripciones para la serie 4XX.


2. Esta API debe ser desplegada en un docker container. Este docker puede estar en un dockerhub público. La base de datos también debe correr en un contenedor docker. Recomendación usar docker compose.


3. Debes agregar un Postman Collection o Swagger para que probemos tu API.


4. Tu código debe estar disponible en un repositorio público, junto con las instrucciones de cómo desplegar el servicio y cómo utilizarlo.

### Comentarios
Probablemente la solución para el login/signup/logout no sea la mejor, se decidió ir por el camino de utilizar cookies/sessions, pero la mejor forma es manejar un token como jwt y usar cookies por front.

### Setup
#### Instrucciones
En primer lugar hay q tener instalado `docker-compose`. Se instala de la siguiente manera:

Para mac
```
brew install docker-compose
```
Para linux
```
apt install docker-compose
```

Clonar este repositorio: https://github.com/macetosella/tenpo-api
Luego ejecutar el siguiente comando, desde la raiz del projecto
```
./docker-compose up
```
### API Url
URL Local: http://localhost:8009/

### Servicios
#### Swagger
http://localhost:8009/swagger-ui/
#### Signup
Este endpoint se utiliza para dar de alta usuarios y es de tipo POST (ver swagger para mas información).
#### Login
Endpoint para login de usuario, recibe un nombre y un password, chequea que sean validos y setea un jwt por cookie .
#### Logout
Este endpoint se usa para el deslogeo de un usuario, lo que hace es matar la cookie, para probarlo es necesario hacerlo desde un navegador.
#### Sum
Este recurso recibe dos parametros enteros y devuelve la suma, previamente chequeando que reciba por cookie un jwt valido.
#### History
Endpoint que muestra el historial de todas las api calls de la api, paginadas. Recibe la pagina y el tamaño de las mismas.

### Test
#### Unitarios

Para la ejecucion de los test unitarios utilice jUnit.

#### Cobertura

Para la cobertura de código se utilizó la herramienta Codecov, muestra un 93% de coverage.
