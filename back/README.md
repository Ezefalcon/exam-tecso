Se utilizó postgreSQL como DBMS, y H2 para tests de integración

Se hicieron dos entidades diferentes para los titulares, personas juridicas y personas fisicas. 
Esta hecho de esta manera asumiendo que ambas pueden llegar a tener comportamiento diferente.
Si no tienen comportamiento diferente, seria mejor utilizar una sola entidad y tener todos los campos ahi.
Aunque estariamos guardando los datos en nulo a menos que utilicemos una base de datos NoSql.

###Pasos para iniciar el servicio:

Levantar el container de docker(imagen de postgres) o utilizar base de datos propia con respectiva config:

    docker-compose -f docker-postgresql.yml up

Las configuraciones se encuentran en src/main/resources/application.yml


Instalar dependencias del proyecto

    mvn clean install

Levantar aplicación utilizando la configuración automática de Intellij o corriendo este comando: 

    mvn spring-boot:run