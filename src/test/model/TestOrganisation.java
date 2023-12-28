package model;

import model.Organisation;
import model.Student;
import model.Task;
import model.Volunteer;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestOrganisation {

    private Organisation testOrganisation;

    @BeforeEach
    void runBefore() {
        testOrganisation = new Organisation("TestOrg");
        EventLog.getInstance().clear();
        LogWriter logWriter = new LogWriter();
        testOrganisation.addObserver(logWriter);
    }

    @Test
    void testConstructor() {
        assertEquals("TestOrg", testOrganisation.getName());
        assertEquals(0, testOrganisation.getStudents().size());
        assertEquals(0, testOrganisation.getVolunteers().size());
        assertEquals(0, testOrganisation.getTasks().size());

        Iterator<Event> iterator = EventLog.getInstance().iterator();
        assertEquals(1, getEventLogSize(iterator));
    }

    @Test
    void testAddStudent() {
        Student student = new Student("John Doe", 1);
        testOrganisation.addStudent(student);
        assertEquals(1, testOrganisation.getStudents().size());
        assertEquals(student, testOrganisation.getStudents().get(0));


        Iterator<Event> iterator = EventLog.getInstance().iterator();
        assertEquals(2, getEventLogSize(iterator));
    }

    @Test
    void testAddTask() {
        Task task = new Task("Task 1", 10);
        testOrganisation.addTask(task);
        assertEquals(1, testOrganisation.getTasks().size());
        assertEquals(task, testOrganisation.getTasks().get(0));

        Iterator<Event> iterator = EventLog.getInstance().iterator();
        assertEquals(2, getEventLogSize(iterator));
    }

    @Test
    void testAddVolunteer() {
        Volunteer volunteer = new Volunteer("Jane Doe", 1, "123-456-7890", 20);
        testOrganisation.addVolunteer(volunteer);
        assertEquals(1, testOrganisation.getVolunteers().size());
        assertEquals(volunteer, testOrganisation.getVolunteers().get(0));

        Iterator<Event> iterator = EventLog.getInstance().iterator();
        assertEquals(2, getEventLogSize(iterator));
    }

    @Test
    void testOrgToJson() {
        // Assuming you have methods to set students, volunteers, and tasks for the testOrganisation
        List<Student> students = new ArrayList<>();
        students.add(new Student("John Doe", 1));

        List<Volunteer> volunteers = new ArrayList<>();
        volunteers.add(new Volunteer("Jane Doe", 2, "123-456-7890", 20));

        List<Task> tasks = new ArrayList<>();
        tasks.add(new Task("Task 1", 10));

        testOrganisation.setStu(students);
        testOrganisation.setVol(volunteers);
        testOrganisation.setTask(tasks);

        JSONObject orgJson = testOrganisation.orgtoJson();

        assertEquals("TestOrg", orgJson.getString("name"));
        assertEquals(1, orgJson.getJSONArray("Students").length());
        assertEquals(1, orgJson.getJSONArray("volunteers").length());
        assertEquals(1, orgJson.getJSONArray("Tasks").length());

        Iterator<Event> iterator = EventLog.getInstance().iterator();
        assertEquals(2, getEventLogSize(iterator));
    }

    @Test
    void testGetVolInfo() {
        Volunteer volunteer = new Volunteer("test", 1,"111",221);
        testOrganisation.addVolunteer(volunteer);
        assertEquals(volunteer.getInformation() + "\n", testOrganisation.getVolunteerInformation());
        Iterator<Event> iterator = EventLog.getInstance().iterator();
        assertEquals(3, getEventLogSize(iterator));
    }

    @Test
    void testGetStuInfo() {
        Student student = new Student("test", 1);
        testOrganisation.addStudent(student);
        assertEquals(student.getInformation() + "\n", testOrganisation.getStudentInformation());
        Iterator<Event> iterator = EventLog.getInstance().iterator();
        assertEquals(3, getEventLogSize(iterator));
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
