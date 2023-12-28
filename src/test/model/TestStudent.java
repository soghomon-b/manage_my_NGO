package model;

import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TestStudent {

    Student testStudent;

    @BeforeEach
    void runBefore(){
        testStudent = new Student("X Xian", 1121);

    }

    @Test
    void testConstructor(){
        assertEquals("X Xian", testStudent.getName());
        assertEquals(1121, testStudent.getId());
        assertEquals(0, testStudent.getHomework());
        assertNull(testStudent.getVolunteer());
    }

    @Test
    void testIncreaseHomework(){
        testStudent.increaseHomework();
        assertEquals(1, testStudent.getHomework());
    }

    @Test
    void testDoHomeworkTrue(){
        testStudent.increaseHomework();
        testStudent.increaseHomework();
        assertTrue(testStudent.doHomework());
        assertEquals(1, testStudent.getHomework());

    }

    @Test
    void testDoHomeWorkFalse() {
        assertFalse(testStudent.doHomework());
    }

    @Test
    void testsetVolunteer(){
        Volunteer vol = new Volunteer("loda", 1, "1121", 2);
        testStudent.setVolunteer(vol);
        assertEquals(vol, testStudent.getVolunteer());
    }

    @Test
    void testGetInformation(){
        String a = "Name: X Xian, Student Number: 1121";
        assertEquals(a, testStudent.getInformation());
    }

    @Test
    void testContactVolunteer(){
        Volunteer vol = new Volunteer("loda", 1, "1121", 2);
        testStudent.setVolunteer(vol);
        String a = testStudent.contactVolunteer();
        String b = vol.getInformation();
        assertEquals(b, a);
    }

    @Test
    void testGetID() {
        assertEquals(1121, testStudent.getId());
    }

    @Test
    void testGetName() {
        assertEquals("X Xian", testStudent.getName());
    }

    @Test
    void testGetHomework() {
        assertEquals(0, testStudent.getHomework());

    }

    @Test
    void increaseHomework() {
        testStudent.increaseHomework();
        assertEquals(1, testStudent.getHomework());
    }

    @Test
    void testSetVolunteer() {
        Volunteer vol1 = new Volunteer("sossi", 1, "222", 2);
        testStudent.setVolunteer(vol1);
        assertEquals("sossi", testStudent.getVolunteer().getName());

    }

    @Test
    void testSetHomework() {
        testStudent.setHomework(3);
        assertEquals(3, testStudent.getHomework());
    }

    @Test
    void testToJson() {
        JSONObject result = testStudent.toJson();
        JSONObject expected = new JSONObject();
        expected.put("name", "X Xian");
        expected.put("id", 1121);
        expected.put("volunteer", "null");
        expected.put("homework", 0);
        assertEquals(expected.toString(), result.toString());
    }

    @Test
    void testGetVolNameName() {
        Volunteer vol1 = new Volunteer("sossi", 1, "222", 2);
        testStudent.setVolunteer(vol1);
        assertEquals("sossi", testStudent.getVolName() );

    }

    @Test
    void testGetVolNameNull() {
        assertEquals("null", testStudent.getVolName() );
    }

}