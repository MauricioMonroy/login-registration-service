# API REST para Registro y Autenticación de Usuarios

## Introducción

En el desarrollo de sistemas de software y aplicaciones, la implementación de mecanismos robustos y seguros para la autorización y la autenticación es fundamental. Este proyecto es un ejercicio práctico desarrollado en el marco del programa de Tecnología en Análisis y Desarrollo de Software del SENA. Se ha construido una API REST destinada a gestionar el registro de usuarios y el inicio de sesión, utilizando tecnologías modernas y estándares de seguridad como JWT (JSON Web Tokens).

El objetivo principal es familiarizar a los desarrolladores con los principios y prácticas básicas de un sistema de autenticación y autorización, y comprender cómo estos conceptos se integran en un entorno de desarrollo real. Este proyecto sirve como preparación para implementaciones en proyectos más grandes y facilita la comprensión de los sistemas de seguridad utilizados en aplicaciones empresariales.

Este README incluye una descripción detallada de las tecnologías utilizadas, cómo configurar y ejecutar el proyecto, y cómo interactuar con los diferentes endpoints de la API.

## Tecnologías Utilizadas

En el desarrollo de esta API REST se utilizaron las siguientes herramientas y tecnologías:

- **JDK 21.0.2**: Se utilizó la última versión del JDK para aprovechar las mejoras en el lenguaje Java y el rendimiento de la JVM.
- **Maven 3.9.7**: Se empleó Maven como herramienta de gestión de dependencias y construcción del proyecto, facilitando la integración de librerías y la configuración del entorno.
- **MySQL**: Como base de datos, se optó por MySQL debido a su robustez y amplia adopción en proyectos de desarrollo web.
- **Spring Boot**: El proyecto se construyó sobre la base de Spring Boot, que simplifica la configuración y el desarrollo de aplicaciones Java empresariales.
- **Spring Security**: Se utilizó para la implementación de la seguridad, incluyendo la autenticación y autorización de usuarios.
- **JWT (JSON Web Tokens)**: Utilizado para la generación de tokens seguros que permiten la autenticación de usuarios en la API.

## Instalación y Configuración

### Prerrequisitos

Antes de empezar, asegúrate de tener instalado:

- JDK 11 o superior
- Maven 3.8 o superior
- MySQL o una base de datos compatible

### Paso 1: Clonar el Repositorio

```bash
git clone https://github.com/tu-usuario/registro-login-api.git
cd registro-login-api
```

### Paso 2: Configurar la Base de Datos
Crea una base de datos MySQL o la que prefieras para la aplicación:

```sql
CREATE DATABASE registro_login_db;
```
Actualiza las credenciales de la base de datos en el archivo `application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/registro_login_db
spring.datasource.username=tu-usuario
spring.datasource.password=tu-contraseña
```

### Paso 3: Compilar y Ejecutar el Proyecto
Para compilar y ejecutar el proyecto, utiliza los siguientes comandos:

```bash
mvn clean install
mvn spring-boot:run
```
La API REST estará disponible en `http://localhost:8080`, o en el puerto que hayas configurado en el archivo `application.properties`.

# Principales Endpoints de la API

## Registro de Usuarios
- URL: `/auth/sign-up`
- Método: `POST`
- Descripción: Permite registrar un nuevo usuario en el sistema.
- Request Body:
```json
{
  "username": "nombreUsuario",
  "email": "usuario@correo.com",
  "password": "contraseñaSegura"
}
```
- Respuestas:
#### Código: `201 OK` si el usuario se registró correctamente.
#### Código: `400 Bad Request` si hay errores en los datos enviados.

## Inicio de Sesión
- URL: `/auth/sign-in`
- Método: `POST`
- Descripción: Permite iniciar sesión en el sistema y obtener un token de autenticación.
- Request Body:
```json
{
  "username": "nombreUsuario",
  "password": "contraseñaSegura"
}
```
- Respuestas:
#### Código: `200 OK` si el inicio de sesión fue exitoso y un token JWT es devuelto.
#### Código: `401 Unauthorized` si las credenciales son inválidas.

## Perfil de Usuario
- URL: `/user/profile`
- Método: `GET`
- Descripción: Obtiene la información del perfil del usuario autenticado (recurso protegido).
- Headers:
#### `Authorization: Bearer <JWT Token>`
- Respuestas:
#### Código `200 OK` si el token es válido y se devuelve la información del usuario.
#### Código `403 Forbidden` si el token es inválido o ha expirado.

## Contribuciones
Si deseas contribuir a este proyecto, por favor sigue los siguientes pasos:
1. Realiza un fork del repositorio.
2. Crea una nueva rama (`git checkout -b feature/nueva-funcionalidad`).
3. Realiza los cambios necesarios y confirma tus modificaciones (`git commit -am 'Agrega nueva funcionalidad'`).
4. Haz un push a la rama (`git push origin feature/nueva-funcionalidad`).
5. Crea un nuevo Pull Request.

## Licencia
Este proyecto está licenciado bajo la Licencia MIT. Para más información, consulta el archivo `LICENSE`.

## Autor
### Mauricio Alberto Monroy Calle
- GitHub: [MauricioMonroy](https://github.com/MauricioMonroy)
- Email: <mauricio.monroy0@soy.sena.edu.co>


