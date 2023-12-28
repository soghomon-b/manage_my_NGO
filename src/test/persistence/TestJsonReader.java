package persistence;

import model.*;
import persistence.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import model.Organisation;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TestJsonReader {
    private JsonReader jsonReader;
    private static final String JSON_STORE = "./data/testRead.json";

    @BeforeEach
    public void runBefore() {
        jsonReader = new JsonReader(JSON_STORE);
    }

    @Test
    void testConstructor() {
        assertEquals(JSON_STORE, jsonReader.getSource());
    }

    @Test
    void testGetSource() {
        assertEquals(JSON_STORE, jsonReader.getSource());
    }

    @Test
    void testReadNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            Organisation org = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReadImplicitlyTestsParseOrgParseTaskParseStudentParseVolunteer() {
        try {
            Organisation org = jsonReader.read();
            assertEquals("SYE", org.getName());
            List<Volunteer> volunteers = org.getVolunteers();
            assertEquals(1, volunteers.size());
            Volunteer vol = volunteers.get(0);
            assertEquals("loda", vol.getName());
            assertEquals("111", vol.getPhone());
            assertEquals(1, vol.getId());
            assertEquals(1, vol.getMaximumHours());
            assertEquals(1, vol.getTotalHours());
            assertEquals(1, vol.getListOfStudent().size());

            List<Student> students = org.getStudents();
            assertEquals(1, students.size());
            Student s = students.get(0);
            assertEquals("sevan", s.getName());
            assertEquals(11, s.getId());
            assertEquals("null", s.getVolName());
            assertEquals(0, s.getHomework());

            List<Task> tasks = org.getTasks();
            assertEquals(1, tasks.size());
            Task t = tasks.get(0);
            assertEquals("work", t.getTitle());
            assertEquals(1, t.getHours());


        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }


}
