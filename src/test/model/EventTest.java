package model;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the Event class
 */
public class EventTest {
    private Event e;
    private Date d;

    //NOTE: these tests might fail if time at which line (2) below is executed
    //is different from time that line (1) is executed.  Lines (1) and (2) must
    //run in same millisecond for this test to make sense and pass.

    @BeforeEach
    public void runBefore() {
        e = new Event("Volunteer Added");   // (1)
        d = Calendar.getInstance().getTime();   // (2)
    }

    @Test
    public void testEvent() {
        assertEquals("Volunteer Added", e.getDescription());
        assertEquals(d, ""+e.getDate());
    }

    @Test
    public void testToString() {
        assertEquals(d.toString() + "\n" + "Volunteer Added", e.toString());
    }


    @Test
    public void testEquals() {
        Event event1 = new Event("Test Event");
        Event event2 = new Event("Test Event");
        Event event3 = new Event("Different Event");


        assertTrue(event1.equals(event2));
        assertTrue(event2.equals(event1));


        assertFalse(event1.equals(event3));
        assertFalse(event3.equals(event1));

        assertFalse(event1.equals(null));


        assertFalse(event1.equals("Not an Event"));
    }


    @Test
    public void testHashCode() {
        Event event1 = new Event("Test Event");
        Event event2 = new Event("Test Event");
        Event event3 = new Event("Different Event");


        assertEquals(event1.hashCode(), event2.hashCode());


        assertNotEquals(event1.hashCode(), event3.hashCode());
    }
}