package se.lernholt.springbatch.listener;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class SimpleJobExecutionListener implements JobExecutionListener {

    @Override
    public void beforeJob(JobExecution jobExecution) {
        log.info("Before starting job '{}'.", jobExecution.getJobInstance()
                .getJobName());
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        log.info("After finishing job '{}'.", jobExecution.getJobInstance()
                .getJobName());
    }
}
