package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestObservable {

    private Observable testObservable;
    private Observer mockObserver;

    @BeforeEach
    void setUp() {
        testObservable = new Observable();
        mockObserver = new LogWriter();
        EventLog.getInstance().clear();
    }

    @Test
    void testAddObserver() {
        testObservable.addObserver(mockObserver);
        assertEquals(1, testObservable.getObservers().size());
        assertTrue(testObservable.getObservers().contains(mockObserver));
    }

    @Test
    void testAddObserverAlreadyThere() {
        testObservable.addObserver(mockObserver);
        testObservable.addObserver(mockObserver);
        assertEquals(1, testObservable.getObservers().size());
        assertTrue(testObservable.getObservers().contains(mockObserver));
    }

    @Test
    void testNotifyObserver() {
        testObservable.addObserver(mockObserver);
        Event testEvent = new Event("test");
        testObservable.notifyObserver(testEvent);

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
