package com.danicode.springboot.backend.apirest.springbootbackendapirest.models.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class UploadServiceImpl implements IUploadService {
    private final Logger log = LoggerFactory.getLogger(UploadServiceImpl.class);
    private final static String DIRECTORIO_UPLOAD = "uploads";

    @Override
    public Resource cargar(String nombreFoto) throws MalformedURLException {
        Path path = getPath(nombreFoto);
        log.info(path.toString());
        Resource resource = null;

        resource = new UrlResource(path.toUri());

        if (!resource.exists() && !resource.isReadable()) {
            path = Paths.get("src/main/resources/static/images").resolve("no-image.png").toAbsolutePath();
            resource = new UrlResource(path.toUri());
            log.error("No se pudo cargar la imagen " + nombreFoto);
        }
        return resource;
    }

    @Override
    public String guardarImage(MultipartFile archivo) throws IOException {
        String fileName = UUID.randomUUID().toString() + "_" + archivo.getOriginalFilename().replace(" ", "");
        // directorio donde iran las fotos
        Path path = getPath(fileName);
        log.info(path.toString());
        Files.copy(archivo.getInputStream(), path);

        return fileName;
    }

    @Override
    public boolean eliminar(String nombreFoto) {
        if (nombreFoto != null && nombreFoto.length() > 0) {
            Path pathAnterior = Paths.get("uploads").resolve(nombreFoto).toAbsolutePath();
            // Convertir el path a un archivo
            File archivoFotoAnterior = pathAnterior.toFile();
            if (archivoFotoAnterior.exists() && archivoFotoAnterior.canRead()) {
                archivoFotoAnterior.delete();
                return true;
            }
        }
        return false;
    }

    @Override
    public Path getPath(String nombreFoto) {
        return Paths.get(DIRECTORIO_UPLOAD).resolve(nombreFoto).toAbsolutePath();
    }
}
