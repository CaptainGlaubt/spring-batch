package se.lernholt.springbatch.chunk.writer;

import java.util.List;

import org.springframework.batch.item.support.AbstractItemStreamItemWriter;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class InMemoryItemWriter extends AbstractItemStreamItemWriter<Integer> {

    @Override
    public void write(List<? extends Integer> items) throws Exception {
        log.info("Processing of chunk started!");
        items.stream()
                .forEach(item -> log.info("Item: {}.", item));
        log.info("Processing of chunk finished!");
    }
}
