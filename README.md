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

- JDK 21.0.2 o superior
- Maven 3.9.7 o superior
- MySQL

### Paso 1: Clonar el Repositorio

```bash
git clone https://github.com/tu-usuario/registro-login-api.git
cd registro-login-api
