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
$ docker container run --name postgres-alpha -e POSTGRES_PASSWORD=mypass -dp 5432:5432 postgres
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
```

## Ejemplo: Montar mariadb:jammy
```bash
# Imagen
$ docker pull mariadb:jammy
# Contenedor (\ => salto de línea | ^ => salto de línea en cmd)
$ docker container run --name world-db \
	-dp 3306:3306 \
	-e MARIADB_USER=example-user \
	-e MARIADB_PASSWORD=user-password \
	-e MARIADB_ROOT_PASSWORD=root-secret-password \
	-e MARIADB_DATABASE=world-db \
	mariadb:jammy
```

## Volúmnes => Usados para hacer persistente la data entre reinicios y levantamientos de imágenes (3 tipos de volúmenes - Named Volumes más usado)
```bash
# Crear nuevo volumen (como crear una carpeta que persiste)
$ docker volume create <nombre_volumne>
# Ver descripcion
$ docker volume inspect <nombre_volumne>
# Colocar volumen a un contenedor (--volume <host>:<path> => ver documentación para el path)
$ docker container run --name world-db ^
	-dp 3306:3306 ^
	-e MARIADB_USER=example-user ^
	-e MARIADB_PASSWORD=user-password ^
	-e MARIADB_ROOT_PASSWORD=root-secret-password ^
	-e MARIADB_DATABASE=world-db ^
	--volume world-db:/var/lib/mysql:Z ^
	mariadb:jammy
```

## Montando phpmyadmin
```bash
# Asignando puerto usando servidor arbitrario (pero esto no se conecta a BD world-db | necesita una red - network)
$ docker container run ^
	--name phpmyadmin ^
	-dp 8080:80 ^
	-e PMA_ARBITRARY=1 ^
	phpmyadmin:5.2.1-apache
```

## Container Networking => Para que dos contenedores se comuniquen entre sí mediante una red
```bash
# Ver las redes
$ docker network ls
# Crear nueva red
$ docker network create <nombre_de_red>
$ docker network create world-app
# Conectar con phpmyadmin (docker network connect <nombre_de_red> <id_contenedor | nombre_contenedor>) y...
$ docker network connect world-app 8bb
# Conectar con mysql
$ docker network connect world-app 610
# Inspeccionar red y ver los contenedores asociados
$ docker network inspect world-app
# Asignar la red desde la inicialización (network creada previamente - docker network create <nombre_de_red>)
# Contenedor de BD
$ docker container run --name world-db ^
	-dp 3306:3306 ^
	-e MARIADB_USER=example-user ^
	-e MARIADB_PASSWORD=user-password ^
	-e MARIADB_ROOT_PASSWORD=root-secret-password ^
	-e MARIADB_DATABASE=world-db ^
	--volume world-db:/var/lib/mysql:Z ^
	--network world-app ^
	mariadb:jammy
# Contenedor de BD
$ docker container run ^
	--name phpmyadmin ^
	-dp 8080:80 ^
	-e PMA_ARBITRARY=1 ^
	--network world-app ^
	phpmyadmin:5.2.1-apache
```

## Bind Volumes => Vincular volúmenes (entre el local y otro SO - linux)
```bash
# Tener un app (nest por ejemplo sin módulos de node). Estar en el path de la app
# -w => working directory (cd app - para mover a la carpeta app)
# -v "$(pwd)" => Todo el path donde se encuentra la terminal
# sh -c => comando shell (se ejecuta luego de que la imagen está montada)
$ docker container run ^
	--name nest-app ^
	-w /app ^
	-p 80:3000 ^
	-v "$(pwd)":/app ^
	node:16-alpine ^
	sh -c "npm install && npm run start:dev"
```

## Terminal interactiva -it => Meterse al contenedor y ejecutar comandos o modificar archivos
```bash
# Entrar al linux (docker exec -it <id_contenedor> /bin/sh) => Buscar dentro del file system la carpeta bin y ejecute ell shell
$ docker exec -it 3a5 /bin/sh
```