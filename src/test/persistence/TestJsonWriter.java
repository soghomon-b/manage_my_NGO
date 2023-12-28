package persistence;

import model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ui.OrganisationInteraction;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TestJsonWriter {

    private JsonWriter jWriter;
    private Organisation org;
    private List<Student> students;
    private List<Volunteer> volunteers;
    private List<Task> tasks;
    private static final String JSON_READ = "./data/testWrite.json";

    @BeforeEach
    void runBefore() {
        jWriter = new JsonWriter(JSON_READ);
        students = new ArrayList<>();
        students.add(new Student("Sevan", 11));
        volunteers = new ArrayList<>();
        volunteers.add(new Volunteer("loda", 1, "111", 1));
        tasks = new ArrayList<>();
        tasks.add(new Task("work", 1));
        org = new Organisation("SYE");
        org.setStudentsRead(students);
        org.setTasksRead(tasks);
        org.setVolunteerRead(volunteers);
    }

    @Test
    void testConstructor() {
        assertEquals(JSON_READ, jWriter.getDestination());
    }

    @Test
    void testWrite() {
        try {
            jWriter.open();
            jWriter.write(org);
            jWriter.close();
            JsonReader reader = new JsonReader(JSON_READ);


            Organisation readOrg = reader.read();


            assertEquals("SYE", readOrg.getName());
            List<Student> readStudents = readOrg.getStudents();
            assertEquals(1, readStudents.size());
            Student s = readStudents.get(0);
            assertEquals("Sevan", s.getName());
            assertEquals(11, s.getId());
            assertEquals("null", s.getVolName());
            assertEquals(0, s.getHomework());

            List<Volunteer> readVolunteers = readOrg.getVolunteers();
            assertEquals(1, readVolunteers.size());
            Volunteer vol = readVolunteers.get(0);
            assertEquals("loda", vol.getName());
            assertEquals("111", vol.getPhone());
            assertEquals(1, vol.getId());
            assertEquals(1, vol.getMaximumHours());
            assertEquals(0, vol.getTotalHours());
            assertEquals(0, vol.getListOfStudent().size());

            List<Task> readTasks = readOrg.getTasks();
            assertEquals(1, readTasks.size());
            Task t = readTasks.get(0);
            assertEquals("work", t.getTitle());
            assertEquals(1, t.getHours());
        } catch (IOException e) {
            fail("IOException should not have been thrown");
        }
    }

    @Test
    void testOpen() {
        try {
            jWriter.open();
        } catch (FileNotFoundException e) {
            fail("Should not be here!");
        }
    }

    @Test
    void testClose() {
        try {
            jWriter.open();

        } catch (FileNotFoundException e) {
            fail("Should not fail!");
        }
        assertEquals(1, jWriter.close() );

    }

    @Test
    void testSaveToFile() {
        try {
            jWriter.open();
            assertEquals(1, jWriter.saveToFile(JSON_READ));
            jWriter.close();
        } catch (FileNotFoundException e) {
           fail();
        }

    }


}
