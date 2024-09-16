package com.danicode.batch.steps;

import com.danicode.batch.entity.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

public class ItemProcessorStep implements Tasklet {

    private static final Logger log = LoggerFactory.getLogger(ItemProcessorStep.class);

    @Override
    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
        log.info("-------------------> INICIO del paso de PROCESAMIENTO <-------------------");
        // Recuperando data del contexto (del paso anterior)
        List<Person> personList = (List<Person>) chunkContext
                .getStepContext()
                .getStepExecution()
                .getJobExecution()
                .getExecutionContext()
                .get("personList");
        // Colocando la fecha de insercion (agregar atributos en este paso de procesamiento)
        List<Person> people = personList.stream().map(person -> {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
            person.setInsertionDate(formatter.format(LocalDateTime.now()));
            return person;
        }).collect(Collectors.toList());

        // Enviar la nueva lista al CONTEXTO
        chunkContext.getStepContext()
                .getStepExecution()
                .getJobExecution()
                .getExecutionContext()
                .put("personList", people); // sobreescribe la lista anterior del contexto

        log.info("-------------------> FIN del paso de PROCESAMIENTO <-------------------");
        // Notificar
        return RepeatStatus.FINISHED;
    }
}
