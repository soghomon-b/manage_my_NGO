package model;
import model.Event;
import model.EventLog;
import model.LogWriter;
import model.Observer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestLogWriter {

    private LogWriter logWriter;

    @BeforeEach
    void setUp() {
        logWriter = new LogWriter();
        EventLog.getInstance().clear();
    }

    @Test
    void testUpdate() {
        Event testEvent = new Event("Test Event");
        logWriter.update(testEvent);

        Iterator<Event> iterator = EventLog.getInstance().iterator();
        assertEquals(2, getEventLogSize(iterator));


        iterator = EventLog.getInstance().iterator();
        assertEquals(testEvent, getFirstEvent(iterator));

    }


    private int getEventLogSize(Iterator<Event> iterator) {
        int size = 0;
        while (iterator.hasNext()) {
            iterator.next();
            size++;
        }
        return size;
    }

    private Event getFirstEvent(Iterator<Event> iterator) {
        iterator.next();
        return iterator.next();
    }
}
