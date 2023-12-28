package model;

import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestTask {
    Task testTask;

    @BeforeEach
    void runBefore(){
        testTask = new Task("Answer", 1);
    }

    @Test
    void testConstructor(){
        assertEquals("Answer", testTask.getTitle());
        assertEquals(1, testTask.getHours());

    }

    @Test
    void testGetTitle(){
        assertEquals("Answer", testTask.getTitle());

    }

    @Test
    void testGetHours() {
        assertEquals(1, testTask.getHours());
    }

    @Test
    void testToJson() {
        JSONObject result = testTask.toJson();
        JSONObject expected = new JSONObject();
        expected.put("title", "Answer");
        expected.put("hours", 1);
        assertEquals(expected.toString(), result.toString());
    }


}
