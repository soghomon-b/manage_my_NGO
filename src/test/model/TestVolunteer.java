package model;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TestVolunteer {

    Volunteer testVolunteer;
    Task t1;
    Task t2;
    Student s1;
    Student s2;

    @BeforeEach
    void runBefore(){
        testVolunteer = new Volunteer("Loda", 1, "2369990100", 3);
        s1 = new Student("Sevan Boujkian", 2212);
        s2 = new Student("Saughmon Boujkian", 1221);
    }

    @Test
    void testConstructor(){
        assertEquals("Loda", testVolunteer.getName());
        assertEquals(1, testVolunteer.getId());
        assertEquals(3, testVolunteer.getMaximumHours());
        assertEquals("2369990100", testVolunteer.getPhone());
        List<Task> tasks = testVolunteer.getListOfTask();
        assertEquals(0, tasks.size());
        List<Student> students = testVolunteer.getListOfStudent();
        assertEquals(0, students.size());

    }

    @Test
    void testAddStudentOneStudent(){
        testVolunteer.addStudent(s1);
        List<Student> students = testVolunteer.getListOfStudent();
        assertEquals(1, students.size());
        Student student = students.get(0);
        assertEquals(s1, student);

    }

    @Test
    void testAddStudentTwoStudents(){
        testVolunteer.addStudent(s1);
        testVolunteer.addStudent(s2);
        List<Student> students = testVolunteer.getListOfStudent();
        assertEquals(2, students.size());
        Student student1 = students.get(0);
        assertEquals(s1, student1);
        Student student2 = students.get(1);
        assertEquals(s2, student2);

    }

    @Test
    void testRemoveStudent(){
        testVolunteer.addStudent(s1);
        testVolunteer.addStudent(s2);
        testVolunteer.removeStudent(s1);
        List<Student> students = testVolunteer.getListOfStudent();
        assertEquals(1, students.size());
        assertFalse(students.contains(s1));

    }

    @Test
    void TestGetTotalHoursOneTask(){
        t1 = new Task("SS", 1);
        testVolunteer.addTask(t1);
        assertEquals(1, testVolunteer.getTotalHours());


    }

    @Test
    void TestGetTotalHoursTwoTasks(){
        t1 = new Task("SS", 1);
        t2 = new Task("AS", 2);
        testVolunteer.addTask(t1);
        testVolunteer.addTask(t2);
        assertEquals(3, testVolunteer.getTotalHours());


    }

    @Test
    void testAddTaskOneTaskBelowMaximumHours(){
        t1 = new Task("t1", 2);
        assertTrue(testVolunteer.addTask(t1));
        List<Task> tasks = testVolunteer.getListOfTask();
        assertEquals(1, tasks.size());
        Task task = tasks.get(0);
        assertEquals(t1, task);

    }
    @Test
    void testAddTaskOneTaskAtMaximumHours(){
        t1 = new Task("t1", 3);
        assertTrue(testVolunteer.addTask(t1));
        List<Task> tasks = testVolunteer.getListOfTask();
        assertEquals(1, tasks.size());
        Task task = tasks.get(0);
        assertEquals(t1, task);

    }

    @Test
    void testAddTaskOneTaskOverMaximumHours(){
        t1 = new Task("t1", 4);
        assertFalse(testVolunteer.addTask(t1));
        List<Task> tasks = testVolunteer.getListOfTask();
        assertEquals(0, tasks.size());

    }

    @Test
    void testAddTaskTwoTasksAddingBothAtMaximumHour(){
        t1 = new Task("t1", 2);
        t2 = new Task("t2", 1);
        testVolunteer.addTask(t1);
        testVolunteer.addTask(t2);
        List<Task> tasks = testVolunteer.getListOfTask();
        assertEquals(2, tasks.size());
        Task task1 = tasks.get(0);
        assertEquals(t1, task1);
        Task task2 = tasks.get(1);
        assertEquals(t2, task2);

    }

    @Test
    void testAddTaskTwoTasksAddingBothOverMaximumHour(){
        t1 = new Task("t1", 2);
        t2 = new Task("t2", 2);
        testVolunteer.addTask(t1);
        testVolunteer.addTask(t2);
        List<Task> tasks = testVolunteer.getListOfTask();
        assertEquals(1, tasks.size());
        Task task1 = tasks.get(0);
        assertEquals(t1, task1);

    }

    @Test
    void testRemoveTask(){
        t1 = new Task("t1", 2);
        t2 = new Task("t2", 1);
        testVolunteer.addTask(t1);
        testVolunteer.addTask(t2);
        testVolunteer.removeTask(t1);
        List<Task> tasks = testVolunteer.getListOfTask();
        assertEquals(1, tasks.size());
        assertFalse(tasks.contains(s1));

    }

    @Test
    void testWorkNoTasks(){
        List<Task> tasks = testVolunteer.work(2);
        assertEquals(0, tasks.size());
    }

    @Test
    void testWorkZeroHours(){
        List<Task> tasks = testVolunteer.work(0);
        assertEquals(0, tasks.size());
    }

    @Test
    void testWorkOneTaskLessTimeNeeded(){
        t1 = new Task("t1", 2);
        testVolunteer.addTask(t1);
        List<Task> tasks = testVolunteer.work(1);
        assertEquals(0, tasks.size());
    }

    @Test
    void testWorkOneTaskAtTimeNeeded(){
        t1 = new Task("t1", 2);
        testVolunteer.addTask(t1);
        List<Task> tasks = testVolunteer.work(2);
        assertEquals(1, tasks.size());
        Task task1 = tasks.get(0);
        assertEquals(t1, task1);
    }

    @Test
    void testWorkTwoTaskAtTimeNeededForFirstTask(){
        t1 = new Task("t1", 2);
        t2 = new Task("t2", 1);
        testVolunteer.addTask(t1);
        testVolunteer.addTask(t2);
        List<Task> tasks = testVolunteer.work(2);
        assertEquals(1, tasks.size());
        Task task1 = tasks.get(0);
        assertEquals(t1, task1);
    }

    @Test
    void testWorkTwoTaskAtTimeNeededForTwoTask(){
        t1 = new Task("t1", 2);
        t2 = new Task("t2", 1);
        testVolunteer.addTask(t1);
        testVolunteer.addTask(t2);
        List<Task> tasks = testVolunteer.work(2+1);
        assertEquals(2, tasks.size());
        Task task1 = tasks.get(0);
        assertEquals(t1, task1);
        Task task2 = tasks.get(1);
        assertEquals(t2, task2);
    }

    @Test
    void testWorkTwoTaskLessTimeNeededForSecondTask(){
        t1 = new Task("t1", 2);
        t2 = new Task("t2", 2);
        testVolunteer.addTask(t1);
        testVolunteer.addTask(t2);
        List<Task> tasks = testVolunteer.work(2+1);
        assertEquals(1, tasks.size());
        Task task1 = tasks.get(0);
        assertEquals(t1, task1);
    }

    @Test
    void testWorkTwoTaskOnlyFirstTaskEnough(){
        t1 = new Task("t1", 1);
        t2 = new Task("t2", 1);
        testVolunteer.addTask(t1);
        testVolunteer.addTask(t2);
        List<Task> tasks = testVolunteer.work(1);
        assertEquals(1, tasks.size());
        Task task1 = tasks.get(0);
        assertEquals(t1, task1);
    }

    @Test
    void testWorkTwoTaskMoreTimeNeeded(){
        t1 = new Task("t1", 2);
        t2 = new Task("t2", 2);
        testVolunteer.addTask(t1);
        testVolunteer.addTask(t2);
        List<Task> tasks = testVolunteer.work(10);
        assertEquals(1, tasks.size());
        Task task1 = tasks.get(0);
        assertEquals(t1, task1);
    }

    @Test
    void testAssignHomeworkTrue(){
        testVolunteer.addStudent(s1);
        testVolunteer.addStudent(s2);
        assertTrue(testVolunteer.assignHomework(s1));
        assertEquals(1, s1.getHomework());

    }

    @Test
    void testAssignHomeworkFalse(){
        Student s3 = new Student("SS", 1121);
        assertFalse(testVolunteer.assignHomework(s3));

    }

    @Test
    void TestGetInformation(){
        String a = "Name: Loda, Phone Number: 2369990100";
        assertEquals(a, testVolunteer.getInformation());
    }

    @Test
    void TestGetNumberOfTasksNoTasks(){
        assertEquals(0, testVolunteer.getNumberOfTasks());
    }

    @Test
    void TestGetNumberOfTasksOneTasks(){
        t1 = new Task("t1", 2);
        testVolunteer.addTask(t1);
        assertEquals(1, testVolunteer.getNumberOfTasks());
    }

    @Test
    void TestGetNumberOfTasksMultipleTasks(){
        t1 = new Task("t1", 1);
        t2 = new Task("t2", 2);
        testVolunteer.addTask(t1);
        testVolunteer.addTask(t2);
        assertEquals(2, testVolunteer.getNumberOfTasks());
    }

    @Test
    void testSetMaximumHours(){
        testVolunteer.setMaximumHour(4);
        assertEquals(4, testVolunteer.getMaximumHours());

    }

    @Test
    void testGetID(){
        assertEquals(1, testVolunteer.getId());

    }

    @Test
    void testGetName(){
        assertEquals("Loda", testVolunteer.getName());

    }

    @Test
    void testGetPhone(){
        assertEquals("2369990100", testVolunteer.getPhone());

    }

    @Test
    void testGetListOfStudent(){
        testVolunteer.addStudent(s1);
        testVolunteer.addStudent(s2);
        List<Student> students = testVolunteer.getListOfStudent();
        assertEquals(2, students.size());
        assertEquals(s1, students.get(0));
        assertEquals(s2, students.get(1));

    }

    @Test
    void testGetListOfTask(){
        t1 = new Task("study", 1);
        t2 = new Task("love", 1);
        testVolunteer.addTask(t1);
        testVolunteer.addTask(t2);
        List<Task> students = testVolunteer.getListOfTask();
        assertEquals(2, students.size());
        assertEquals(t1, students.get(0));
        assertEquals(t2, students.get(1));

    }

    @Test
    void getNumOfStudentsNoStudents() {
        assertEquals(0, testVolunteer.getNumberOfStudents());
    }

    @Test
    void getNumOfStudentsOneStudent() {
        testVolunteer.addStudent(s1);
        assertEquals(1, testVolunteer.getNumberOfStudents());
    }

    @Test
    void getNumOfStudentsMultipleStudents(){
        testVolunteer.addStudent(s1);
        testVolunteer.addStudent(s2);
        assertEquals(2, testVolunteer.getNumberOfStudents());
    }

    @Test
    void testSetListOfStudent() {
        List<Student> students = new ArrayList<>();
        students.add(s1);
        students.add(s2);
        testVolunteer.setListOfStudent(students);
        Student expectedS1 = testVolunteer.getListOfStudent().get(0);
        Student expectedS2 = testVolunteer.getListOfStudent().get(1);
        assertEquals(s1.getName(), expectedS1.getName());
        assertEquals(s2.getName(), expectedS2.getName());
    }

    @Test
    void testSetListOfTask() {
        t1 = new Task("Loda", 1);
        t2 = new Task("o", 1);
        List<Task> tasks = new ArrayList<>();
        tasks.add(t1);
        tasks.add(t2);
        testVolunteer.setListOfTask(tasks);
        Task expectedT1 = testVolunteer.getListOfTask().get(0);
        Task expectedT2 = testVolunteer.getListOfTask().get(1);
        assertEquals(t1.getTitle(), expectedT1.getTitle());
        assertEquals(t2.getTitle(), expectedT2.getTitle());
    }

    @Test
    void testToJson() {

        testVolunteer.addStudent(s1);
        testVolunteer.addStudent(s2);

        t1 = new Task("Loda", 1);
        List<Task> tasks = new ArrayList<>();
        tasks.add(t1);
        testVolunteer.setListOfTask(tasks);

        JSONObject result = testVolunteer.toJson();


        JSONObject expected = new JSONObject();
        expected.put("name", "Loda");
        expected.put("id", 1);
        expected.put("phone number", "2369990100");
        expected.put("max hour", 3);


        JSONArray studentsArray = new JSONArray();
        JSONObject student1Json = new JSONObject();
        student1Json.put("name", "Sevan Boujkian");
        student1Json.put("id", 2212);
        student1Json.put("volunteer", "null");
        student1Json.put("homework", 0);
        studentsArray.put(student1Json);

        JSONObject student2Json = new JSONObject();
        student2Json.put("name", "Saughmon Boujkian");
        student2Json.put("id", 1221);
        student2Json.put("volunteer", "null");
        student2Json.put("homework", 0);
        studentsArray.put(student2Json);
        expected.put("students", studentsArray);

        JSONArray tasksArray = new JSONArray();
        JSONObject task1Json = new JSONObject();
        task1Json.put("title", "Loda");
        task1Json.put("hours", 1);
        tasksArray.put(task1Json);
        expected.put("tasks", tasksArray);


        assertEquals(expected.toString(), result.toString());
    }




}
