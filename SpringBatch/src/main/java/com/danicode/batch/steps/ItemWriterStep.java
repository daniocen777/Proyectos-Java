package com.danicode.batch.steps;

import com.danicode.batch.entity.Person;
import com.danicode.batch.service.IPersonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ItemWriterStep implements Tasklet {

    @Autowired
    private IPersonService personService;

    private static final Logger log = LoggerFactory.getLogger(ItemWriterStep.class);

    @Override
    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
        log.info("-------------------> INICIO del paso de ESCRITURA <-------------------");

        // Recuperando data del contexto (del paso anterior)
        List<Person> personList = (List<Person>) chunkContext
                .getStepContext()
                .getStepExecution()
                .getJobExecution()
                .getExecutionContext()
                .get("personList");
        // Ver la data
        personList.forEach(person -> {
            if (person != null) {
                log.info(person.toString());

            }
        });
        // guardar lista
        personService.saveAll(personList);

        log.info("-------------------> FIN del paso de ESCRITURA <-------------------");
        // Notificar
        return RepeatStatus.FINISHED;
    }
}
