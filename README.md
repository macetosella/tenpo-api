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
Probablemente la solución para el login/signup/logout no sea la mejor, se decidió ir por el camino de utilizar cookies/sessions ya que no estoy del todo familiarizado con `sprint securty` y la idea es entregar una solución funcional en un corto plazo. Con lo cual queda como deuda técnica.

### Setup

#### Instrucciones

#### Uso

#### API Url

### Servicios

#### Signup

#### Login

#### Logout 

#### Sum

#### History

### Test

#### Unitarios

Para la ejecucion de los test unitarios utilice jUnit.

#### Cobertura

Para la cobertura de código se utilizó la herramienta Codecov, muestra un 93% de coverage.
