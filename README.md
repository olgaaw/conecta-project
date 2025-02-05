# Proyecto Conecta

## Descripción

Este proyecto consiste en el desarrollo de una API REST utilizando Spring Boot 3, diseñada para gestionar la interacción entre centros educativos y empresas en el contexto de la nueva Ley de Formación Profesional. Esta ley ha incrementado el número de horas que los estudiantes de Formación Profesional deben pasar en las empresas, lo que requiere una colaboración más estrecha y eficiente entre ambas partes.

### Objetivos de la API

La API REST tiene como objetivo principal facilitar y optimizar la gestión de las prácticas formativas, permitiendo:
- **Gestión Centralizada de Ofertas de Prácticas**: Gestionar las ofertas de prácticas de manera centralizada.
- **Coordinación entre Profesores y Empresas**: Proporcionar herramientas sencillas para realizar el seguiminto de las comunicaciones con las empresas.
- **Seguimiento de Alumnos**: Se implementará un sistema que permitirá realizar el seguimiento de los alumnos durante su estancia en la empresa.


![image](https://github.com/user-attachments/assets/6058479f-e601-4636-8c27-d0e53cab2270)


## Tecnologías Utilizadas

- **Spring Boot 3**: Framework para el desarrollo de aplicaciones Java.
- **Spring Data JPA**: Para la persistencia de datos.
- **PostgreSQL**: Base de datos utilizada para almacenar la información.
- **OpenAPI / Swagger**: Para la documentación de la API.
- **Postman**: Para probar la API REST.

## Ejecución del proyecto

1. **Clonar repositorio:** utilizando 
`git clone` 
2. **Configurar la base de datos:**
   - Ejecutar los comandos `mvn clean y mvn install`
   - Ejecutar el comando `docker-compose up -d`
   - Crear la base de datos en PostgreSQL
   - Configurar los datos de conexión en las application.properties
4. **Ejecutar la aplicación:** con `mvn spring-boot run`
5. **Acceso a la documentación:**
`localhost:8080/swagger-ui.html`

Se adjunta una colección de postman para ir probando los diferentes endpoints de la API REST.


