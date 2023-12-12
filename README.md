# Comandos Docker

## Descargar imagen
```bash
# imagen de postgres
$ docker pull postgres
```
## Correr Contenedor => Instancia de la imagen
```bash
# Contenedor de postgres
# -d => desenlazado de la línea de comandos
$ docker container run -d postgres
# Otro Contenedor de postgres estableciendo puertos, variables de entorno, nombre de contenedor
# => --name [Nombre-de-contenedor]
# => -e [NOMBRE_DE_VARIABLE DE ENTORNO]=[VALOR]  || Ver documentacion para los nombres de las variables
# => -p [PUERTO_DE_PC]:[PUERTO_DE_CONTENEDOR]
$ docker container run --name postgres-alpha -q POSTGRES_PASSWORD=mypass -dp 5432:5432 postgres
```

## Ver todos los contenedores
```bash
$ docker container ls -a
```

## Borrar varios contenedores => Deben estar detenidos (stop)
```bash
# rm [CONTAINER_ID_1] [CONTAINER_ID_2] [CONTAINER_ID_3] || Los 3 primeros valores (o todos)
$ docker container rm 6aa 2e2
# -f => Forzar borrado (parar y borrar)
$ docker container rm -f <container_id_1> <container_id_2>
```

## Ver logs de contenedores
```bash
# rm [CONTAINER_ID_1] [CONTAINER_ID_2] [CONTAINER_ID_3] || Los 3 primeros valores (o todos)
# -f => Dar seguimiento
$ docker container logs -f <container_id>
```

## Borrar Imágenes (primero se debe eliminar los contenedores asociados a la imagen)
```bash
# Contenedores deben estar parados (stop) y borrados (rm)
$ docker image rm <image_id_1> <image_id_2>