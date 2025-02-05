# Proyecto Conecta

## Descripción

Con la nueva Ley de Formación Profesional se ha incrementado el número de horas que el alumnado de FP debe pasar en la empresa, y ya no basta con la clásica Formación en Centros de Trabajo del 2º curso, sino que deben realizar al menos una estancia formativa en 1º. Esto ha supuesto que los centros educativos deban aumentar el número de empresas con las que colaboran. La colaboración entre centros educativos y empresas desempeña un papel clave en la formación de futuros profesionales. Esta sinergia no solo permite que los estudiantes adquieran experiencia práctica en entornos reales de trabajo, sino que también facilita que las empresas identifiquen y formen talento adaptado a sus necesidades.

La comunicación entre estas dos partes puede presentar desafíos, como la falta de un canal centralizado y eficiente para gestionar propuestas, solicitudes y seguimiento de prácticas. Por ello, se propone el desarrollo de una herramienta que facilite y mejore la interacción entre profesores de formación profesional y empresas colaboradoras.

El objetivo de este proyecto es diseñar e implementar una plataforma que permita:
- Gestionar de forma centralizada las ofertas de prácticas ofrecidas por las empresas.
- Proporcionar a los profesores un medio sencillo para coordinar con las empresas.
- Ofrecer un sistema para realizar el seguimiento de los alumnos durante su estancia en la empresa.
- Este proyecto no solo ayudará a mejorar la comunicación, sino que también sentará las bases para una colaboración más sólida y efectiva entre los centros de formación profesional y las empresas, en beneficio tanto de los estudiantes como del tejido empresarial local.

![image](https://github.com/user-attachments/assets/6058479f-e601-4636-8c27-d0e53cab2270)


## Tecnologías Utilizadas

- **Spring Boot 3**: Framework para el desarrollo de aplicaciones Java.
- **Spring Data JPA**: Para la persistencia de datos.
- **PostgreSQL**: Base de datos utilizada para almacenar la información.
- **OpenAPI / Swagger**: Para la documentación de la API.
- **Postman**: Para probar la API REST.

## Ejecución del proyecto

1. **Clonar repositorio:** utilizando git clone <url>
2. **Configurar la base de datos:**
   - Ejecutar los comandos mvn clean y mvn install
   - Ejecutar el comando docker-compose up -d
   - Crear la base de datos en PostgreSQL
   - Configurar los datos de conexión en las application.properties
4. Ejecutar la aplicación con mvn spring-boot run

Para acceder a la documentación del proyecto iremos a:
localhost:8080/swagger-ui.html

Se adjunta una colección de postman para ir probando los diferentes endpoints de la API REST.

## Funcionamiento



  

