# Wordle Backend

<p align="center">
  <img src="https://img.shields.io/badge/Java-21-orange?style=for-the-badge&logo=openjdk" alt="Java 21" />
  <img src="https://img.shields.io/badge/Spring%20Boot-3.4.x-6DB33F?style=for-the-badge&logo=springboot" alt="Spring Boot" />
  <img src="https://img.shields.io/badge/PostgreSQL-Database-316192?style=for-the-badge&logo=postgresql" alt="PostgreSQL" />
  <img src="https://img.shields.io/badge/JWT-Security-000000?style=for-the-badge&logo=jsonwebtokens" alt="JWT Security" />
  <img src="https://img.shields.io/badge/Maven-Build-C71A36?style=for-the-badge&logo=apachemaven" alt="Maven" />
</p>

Backend para un juego tipo Wordle desarrollado con Java y Spring Boot, fue durante el curso de DAM y teníamos libertad creativa siempre que cubriéramos la temática alrededor de Ignacio de Loyola.
Este proyecto gestiona autenticación, usuarios, puntuaciones y selección de palabras, proporcionando una API REST robusta y mantenible.

## Overview

Este backend está desarrollado con una estructura modular y escalable. La lógica está separada en capas para facilitar mantenimiento, pruebas y evolución del proyecto.

<img width="1024" height="858" alt="image" src="https://github.com/user-attachments/assets/b3404986-aa06-4d06-9cef-b2b02eef054b" />


> Screenshot del juego.

## Características principales

- Registro e inicio de sesión de usuarios
- Autenticación segura con JWT
- Gestión de puntuaciones y rankings
- Selección aleatoria de palabras según longitud
- Soporte para diccionarios con normalización de texto
- API REST organizada por controladores y servicios
- Persistencia con JPA y PostgreSQL
- Arquitectura en capas para facilitar mantenimiento

## Arquitectura y buenas prácticas

El proyecto sigue una estructura clara y ordenada:

- Modelo: entidades JPA (`Usuario`, `Palabras`, etc.)
- Repositorio: acceso a datos con consultas específicas
- Servicio: lógica de negocio
- Controlador: exposición de endpoints REST
- Seguridad: autenticación y autorización con JWT

Entre las buenas prácticas que destacan en este backend se encuentran:

- Separación clara de responsabilidades
- Uso de DTOs para controlar el flujo de datos
- Validación de lógica de negocio en servicios
- Uso de transacciones para operaciones sensibles
- Persistencia con restricciones de integridad
- Mapeo de entidades con MapStruct
- Consultas optimizadas para búsquedas frecuentes

## Gestión de datos

La gestión de datos del proyecto está pensada para mantener consistencia y rendimiento:

- El usuario se almacena con nombre único, puntuación y rol de administrador
- Las palabras se guardan con distintas representaciones para comparación y validación
- Se evita la duplicación de registros mediante restricciones en la base de datos
- Se utilizan consultas nativas para obtener rankings y búsquedas clave
- Las operaciones relacionadas con puntos y perfiles se ejecutan dentro de transacciones

Esto hace que la aplicación sea más robusta y preparada para crecer sin perder claridad en la lógica.

## Tecnologías utilizadas

- Java 21
- Spring Boot 3
- Spring Security
- JWT (Json Web Token)
- Spring Data JPA
- PostgreSQL
- Maven
- Lombok
- MapStruct

## Estructura del proyecto

```text
Wordle-Backend/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/example/wordlebackend/
│   │   │       ├── controlador
│   │   │       ├── servicio
│   │   │       ├── repositorio
│   │   │       ├── modelo
│   │   │       ├── DTO
│   │   │       ├── converter
│   │   │       └── Security
│   │   └── resources/
│   │       └── bbdd_diccionario.sql
│   └── test/
│       └── java/
├── pom.xml
├── mvnw
├── mvnw.cmd
└── README.md

```
## 🚀 Cómo ejecutar

### 📋 Requisitos previos

Antes de ejecutar el proyecto, asegúrate de tener instalado:

* ☕ **Java 21**
* 📦 **Maven**
* 🐘 **PostgreSQL**
* 🔧 **Git**

### 1. Clonar el repositorio

```bash
git clone https://github.com/tu-usuario/Wordle-Backend.git
cd Wordle-Backend
```

### 2. Configurar la base de datos

Asegúrate de tener **PostgreSQL** en ejecución y crea una base de datos para el proyecto.

Configura las credenciales en tu archivo `application.properties`:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/wordle_db
spring.datasource.username=tu_usuario
spring.datasource.password=tu_password

spring.jpa.hibernate.ddl-auto=update
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
```

> **Nota:** Si utilizas un archivo `application.yml` o una configuración diferente, adapta estos valores según tu entorno.

### 3. Ejecutar la aplicación

Compila e instala las dependencias:

```bash
./mvnw clean install
```

Inicia la aplicación:

```bash
./mvnw spring-boot:run
```

En Windows también puedes utilizar:

```bash
mvnw.cmd clean install
mvnw.cmd spring-boot:run
```

### 4. Comprobar la API

Una vez iniciada la aplicación, estará disponible en:

```text
http://localhost:8080
```

---

## 🔌 Endpoints principales

### 🔐 Autenticación

| Método | Endpoint         | Descripción                |
| ------ | ---------------- | -------------------------- |
| `POST` | `/auth/registro` | Registrar un nuevo usuario |
| `POST` | `/auth/login`    | Iniciar sesión             |

### 🎮 Wordle

| Método | Endpoint                          | Descripción                                   |
| ------ | --------------------------------- | --------------------------------------------- |
| `GET`  | `/wordle/palabra/{numLetras}`     | Obtener una palabra según su número de letras |
| `GET`  | `/wordle/palabraIgnaciana`        | Obtener una palabra ignaciana                 |
| `GET`  | `/wordle/palabraExiste/{palabra}` | Comprobar si una palabra existe               |

### 👤 Usuarios

| Método | Endpoint        |
| ------ | --------------- |
| `GET`  | `/usuario/...`  |
| `GET`  | `/usuarios/...` |

---

## Estado del proyecto

🟡 **En desarrollo activo**

Actualmente, el proyecto se centra en:

* 🔒 Reforzar la seguridad
* ✅ Mejorar las validaciones de negocio
* 🎮 Ampliar las funcionalidades del juego
* ⚡ Optimizar el rendimiento
* 🗄️ Mejorar la gestión de datos

---

## Contribución

Las contribuciones son bienvenidas.

Si quieres mejorar el proyecto, puedes:

1. Abrir un **Issue** para informar de errores o proponer mejoras.
2. Crear un **Fork** del repositorio.
3. Realizar tus cambios.
4. Enviar un **Pull Request**.

Toda contribución que ayude a mejorar el proyecto es bienvenida. 🎮

