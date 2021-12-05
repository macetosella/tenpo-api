[![Build Status](https://travis-ci.com/macetosella/tenpo-api.svg?branch=master)](https://travis-ci.com/macetosella/tenpo-api)
[![codecov](https://codecov.io/gh/macetosella/tenpo-api/branch/master/graph/badge.svg)](https://codecov.io/gh/macetosella/tenpo-api)

![img_2.png](https://tenpo.cl/blog/wp-content/uploads/2021/01/logo-tenpo-white-1.png)

# Desafio Tenpo

## Requerimientos:

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