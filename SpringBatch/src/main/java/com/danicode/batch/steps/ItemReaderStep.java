package com.danicode.batch.steps;

import com.danicode.batch.entity.Person;
import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;

import java.io.FileReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

public class ItemReaderStep implements Tasklet {
    private static final Logger log = LoggerFactory.getLogger(ItemReaderStep.class);

    @Autowired
    private ResourceLoader resourceLoader; // objeto para inportar archivos desde carpeta "resources"


    @Override
    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
        log.info("-------------------> INICIO del paso de LECTURA <-------------------");
        Reader reader = new FileReader(
                resourceLoader.getResource("classpath:files/destination/people.csv").getFile());
        // especificar el separador del documento (paquete com.opencsv)
        CSVParser parser = new CSVParserBuilder()
                .withSeparator(',') // caracter
                .build();
        // Objeto para leer el documento
        CSVReader csvReader = new CSVReaderBuilder(reader)
                .withCSVParser(parser)
                .withSkipLines(1) // iniciar a partir de la segunda linea (saltar linea de titulos)
                .build();
        // Convertir a objetos de tipo Person
        List<Person> personList = new ArrayList<>();
        String[] line;
        while ((line = csvReader.readNext()) != null) {
            Person person = new Person();
            person.setName(line[0]);
            person.setLastname(line[1]);
            person.setAge(Integer.parseInt(line[2]));

            personList.add(person);
        }
        // Liberar recursos
        csvReader.close();
        reader.close();
        log.info("-------------------> FIN del paso de LECTURA <-------------------");
        /* Luego, enviar esta lista al CONTEXTO del JOB (espacio compartido entre todos los pasos)
        para que el siguiente paso pueda tomar esos datos (datos enviado como MAP) */
        chunkContext.getStepContext()
                .getStepExecution()
                .getJobExecution()
                .getExecutionContext()
                .put("personList", personList); // Map<key, value>

        // Notificar que este paso se ha ejecutado
        return RepeatStatus.FINISHED;
    }
}
