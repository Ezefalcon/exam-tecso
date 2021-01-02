Se utilizó postgreSQL como DBMS, y H2 para tests de integración

###Pasos para iniciar el servicio:

Levantar el container de docker(imagen de postgres) o utilizar base de datos propia con respectiva config:

    docker-compose -f docker-postgresql.yml up

Las configuraciones se encuentran en src/main/resources/application.yml


Instalar dependencias del proyecto

    mvn clean install

Levantar aplicación utilizando la configuración automática de Intellij o corriendo este comando: 

    mvn spring-boot:run