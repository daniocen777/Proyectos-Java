package com.danicode.batch.config;

import com.danicode.batch.steps.ItemDecompressStep;
import com.danicode.batch.steps.ItemProcessorStep;
import com.danicode.batch.steps.ItemReaderStep;
import com.danicode.batch.steps.ItemWriterStep;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BatchConfig {

    @Bean
    public ItemDecompressStep itemDecompressStep() {
        return new ItemDecompressStep();
    }

    @Bean
    public ItemReaderStep itemReaderStep() {
        return new ItemReaderStep();
    }

    @Bean
    public ItemProcessorStep itemProcessorStep() {
        return new ItemProcessorStep();
    }

    @Bean
    public ItemWriterStep itemWriterStep() {
        return new ItemWriterStep();
    }
}
