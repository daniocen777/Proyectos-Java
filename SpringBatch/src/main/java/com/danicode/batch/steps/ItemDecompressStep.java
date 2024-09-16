package com.danicode.batch.steps;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/* Un step no tiene que estar compuesto por un reader, processor y writer.
   También puede tener únicamente una lógica de negocio.
   Es el caso del tasklet con el código que se desea ejecutar en el step.
*/

public class ItemDecompressStep implements Tasklet {

    @Autowired
    private ResourceLoader resourceLoader; // objeto para inportar archivos desde carpeta "resources"

    private static final Logger log = LoggerFactory.getLogger(ItemDecompressStep.class);

    @Override
    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
        log.info("-------------------> INICIO del paso de DESCOMPRESION <-------------------");
        // *** Obtener el archivo comprimido
        Resource resource = resourceLoader.getResource("classpath:files/people.zip");
        String filePath = resource.getFile().getAbsolutePath(); // ruta absoluta de donde se encuentra el archivo
        // *** Descomprimir el archivo
        ZipFile zipFile = new ZipFile(filePath);
        // directorio destino => directorio padre de la ruta (sin el nombre del archivo)
        File directory = new File(resource.getFile().getParent(), "destination");
        if (!directory.exists()) {
            directory.mkdir(); // crearlo, si no existe
        }

        // Un comprimido puede tener muchas cosas (conocidas como "entradas")
        // Descomrimir lo que haya en el zip
        Enumeration<? extends ZipEntry> entries = zipFile.entries();
        // buscar las carpetas
        while (entries.hasMoreElements()) {
            ZipEntry zipEntry = entries.nextElement(); // recuperar el elemento (ficheros, carpetas, etc)
            File file = new File(directory, zipEntry.getName());
            if (file.isDirectory()) { // si es una carpeta, crearla en el directorio
                file.mkdirs();
            } else {
                // si no carpeta, es archivo
                InputStream inputStream = zipFile.getInputStream(zipEntry); // secuencia de bytes
                // escribir en la ruta la secuencia de bytes
                FileOutputStream outputStream = new FileOutputStream(file);
                byte[] buffer = new byte[1024]; // pedazo de bytes
                int length;
                // escribir el archivo
                while ((length = inputStream.read(buffer)) > 0) {
                    outputStream.write(buffer, 0, length);
                }
                // cerrar el archivo
                outputStream.close();
                inputStream.close();
            }
        }
        // Liberar recusrsos
        zipFile.close();

        log.info("-------------------> FIN del paso de DESCOMPRESION <-------------------");

        return RepeatStatus.FINISHED;
    }
}
