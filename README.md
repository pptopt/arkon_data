# Arkon Data
Proyecto de localización de unidades de Metrobus de CDMX

# Proyecto en GitHub
https://github.com/pptopt/arkon_data

# Dirección de containers de docker
## Proyecto: https://hub.docker.com/repository/docker/pptopt/geo-server
## BD Postgres: https://hub.docker.com/repository/docker/pptopt/postgres

## Descripción: 

Desarrollar un pipeline de análisis de datos utilizando los datos abiertos de la Ciudad de México correspondientes a la ubicación de las unidades del metrobús durante la última hora para obtener un histórico de la posición en la que se encuentra cada unidad que pueda ser consultado mediante un API Rest filtrando por unidad o por alcaldía. 

## Requerimientos y reglas de negocio 

### Presentar un diagrama con el diseño de su solución
    No se han incluido diagramas en esta version 0.1
### Consultar periódicamente la fuente de datos 
    Ejecutar el siguiente llamado al endpoint: http://localhost:8090/puntosDeRecorrido/updateProcess
    Este proceso ejecuta una actualización de la BD con la información más reciente de las ubicaciones de las unidades en la fuente original que se encuentra en http://app.citi-mb.mx/GTFS-RT/vehiculosPosicion. Esta ruta se encuentra configurada bajo la propiedad archivo.metrobus.rutaOrigen en el archivo application.properties del proyecto. 
### Obtener la alcaldía correspondiente a cada posición.
  Ejecutar el siguiente llamado al endpoint: http://localhost:8090/puntosDeRecorrido/getById/11
  Este llamado devuelve el detalle a nivel de registro de cada ubicación de cada unidad en un solo punto de tiempo. Se incluye tambien la alcaldia en la que la ubicación registrada se encuentra. 
### Almacenar la información en una base de datos 
    Si se incluye.
### Diseñar e implementar un API que permita consultar la información almacenada, con las siguientes características: 
  #### Obtener una lista de unidades disponibles
    Ejecutar el siguiente llamado al endpoint: http://localhost:8090/puntosDeRecorrido/getByVehicleCurrentStatus/2
    La lógica de la consulta es buscar a las unidades que tienen determinado status en el mes actual y el anterior.
    El valor del id del estatus buscado es 2.
    Se muestra solamente el id de la unidad y su etiqueta visible.
  #### Consultar los el historial de ubicaciones/fechas de una unidad dado su ID
    Ejecutar el siguiente llamado al endpoint: http://localhost:8090/puntosDeRecorrido/getByIdVehiculo/1085
    El parametro indica el numero de unidad al que se le requiere consultar su historial de ubicaciones.
  #### Obtener una lista de alcaldías disponibles 
    Ejecutar el siguiente llamado al endpoint: http://localhost:8090/alcaldias/listAll
    Solamente se presenta el id de la alcaldia y el nombre.
    
    Se puede usar tambien el siguiente endpoint: http://localhost:8090/alcaldias/getById/3
    Para consultar con mas detalle por cada id de alcaldia.
    Este endpoint podria ser accesible solamente a usuarios registrados y el listado completo podria ser de acceso publico.
  
  #### Obtener una lista de unidades que hayan estado dentro de una alcaldía 
    Ejecutar el siguiente llamado al endpoint: http://localhost:8090/puntosDeRecorrido/getByIdAlcaldia/14
    El parametro indica el numero de alcaldia.
    Se puede hacer una consulta al endpoint http://localhost:8090/alcaldias/listAll para obtener el catalogo de alcaldias. 

## Código 
  ### El candidato tendrá completa libertad de elegir el stack tecnológico 
      Se presenta la solución en Java. Proyecto desarrollado con Spring Boot Tools.
  ### Incluir comentarios en el código 
      Se incluyen comentarios.
  ### Manejar control de versiones
      Se mantienen en este repositorio las versiones del proyecto.
  ### Utilizar docker para empaquetar su(s) servicio(s) 
      Dirección de containers
      Proyecto: https://hub.docker.com/repository/docker/pptopt/geo-server
      BD Postgres: https://hub.docker.com/repository/docker/pptopt/postgres

#Puntos extra 
  ### Implementar el API usando GraphQL 
      * No se incluye.
  ### Las configuraciones necesarias para desplegar su(s) servicio(s) en kubernetes 
      * No se incluye.
  ### Implementar una parte de su solución usando programación funcional
      En algunas parte del código se incluiye programación funcional.
  ### Incluir pruebas unitarias
      * No se incluyen pruebas unitarias
  
