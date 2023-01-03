package se.lernholt.springbatch.chunk.reader;

import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.batch.item.support.AbstractItemStreamItemReader;
import org.springframework.stereotype.Component;

@Component
public class InMemoryItemReader extends AbstractItemStreamItemReader<Integer> {

    private static int[] ITEMS = new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };

    private int          index;

    @Override
    public Integer read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        if (index < ITEMS.length) {
            var item = ITEMS[index];
            index++;
            return item;
        }
        index = 0;
        return null;
    }
}
