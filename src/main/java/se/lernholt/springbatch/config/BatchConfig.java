package se.lernholt.springbatch.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.RequiredArgsConstructor;
import se.lernholt.springbatch.chunk.processor.InMemoryItemProcessor;
import se.lernholt.springbatch.chunk.reader.InMemoryItemReader;
import se.lernholt.springbatch.chunk.writer.InMemoryItemWriter;
import se.lernholt.springbatch.listener.SimpleJobExecutionListener;
import se.lernholt.springbatch.listener.SimpleStepExecutionListener;
import se.lernholt.springbatch.task.HelloWorldTask;

@Configuration
@EnableBatchProcessing
@RequiredArgsConstructor
public class BatchConfig {

    private final JobBuilderFactory           jobBuilderFactory;
    private final StepBuilderFactory          stepBuilderFactory;
    private final SimpleJobExecutionListener  jobExecutionListener;
    private final SimpleStepExecutionListener stepExecutionListener;
    private final InMemoryItemReader          inMemoryItemReader;
    private final InMemoryItemProcessor       inMemoryItemProcessor;
    private final InMemoryItemWriter          inMemoryItemWriter;

    @Bean
    public Step hello_world_step_1() {
        return stepBuilderFactory.get("hello_world_step_1")
                .listener(stepExecutionListener)
                .tasklet(new HelloWorldTask())
                .build();
    }

    @Bean
    public Step hello_world_step_2() {
        return stepBuilderFactory.get("hello_world_step_2")
                .<Integer, Integer>chunk(3)
                .reader(inMemoryItemReader)
                .processor(inMemoryItemProcessor)
                .writer(inMemoryItemWriter)
                .build();
    }

    @Bean
    public Job hello_world_job() {
        return jobBuilderFactory.get("hello_world_job")
                .incrementer(new RunIdIncrementer())
                .listener(jobExecutionListener)
                .start(hello_world_step_1())
                .start(hello_world_step_2())
                .build();
    }

}
