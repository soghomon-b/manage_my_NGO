package model;


import model.Student;
import model.Task;
import model.Volunteer;
import org.json.JSONArray;
import org.json.JSONObject;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


// the UI interface for the Organisation to use.
public class Organisation extends Observable {
    private String name;
    private List<Task> tasks;
    private List<Student> students;
    private List<Volunteer> volunteers;


    //EFFECTS: constructs an Organisation class with the name given and empty lists for takss, students, and volunteers.
    public Organisation(String name) {
        this.name = name;
        tasks = new ArrayList<>();
        students = new ArrayList<>();
        volunteers = new ArrayList<>();
    }


    //EFFECTS: makes the organisation a json object
    public JSONObject orgtoJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("volunteers", volunteersToJson());
        json.put("Students", studentsToJason());
        json.put("Tasks", tasksToJason());
        notifyObserver(new Event("Organisation Saved"));
        return json;
    }

    //EFFECTS: transforms the volunteers into a jason array.
    private JSONArray volunteersToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Volunteer v : volunteers) {
            jsonArray.put(v.toJson());
        }

        return jsonArray;
    }

    //EFFECTS: transforms the students into a jason array.
    private JSONArray studentsToJason() {
        JSONArray jsonArray = new JSONArray();

        for (Student s : students) {
            jsonArray.put(s.toJson());
        }

        return jsonArray;
    }

    //EFFECTS: transforms the tasks into a jason array.
    private JSONArray tasksToJason() {
        JSONArray jsonArray = new JSONArray();

        for (Task t : tasks) {
            jsonArray.put(t.toJson());
        }

        return jsonArray;
    }

    //MODIFIES: this
    //EFFECTS: sets the student given
    void setStu(List<Student> students) {
        this.students = students;
    }

    //MODIFIES: this
    //EFFECTS: sets the volunteer given
    void setVol(List<Volunteer> volunteers) {
        this.volunteers = volunteers;
    }

    //MODIFIES: this
    //EFFECTS: sets the task given
    void setTask(List<Task> tasks) {
        this.tasks = tasks;
    }

    //MODIFIES: this
    //EFFECTS: returns the students in the object for the JsonRead object.
    public void setStudentsRead(List<Student> students) {
        this.students = students;
    }

    //MODIFIES: this
    //EFFECTS: returns the tasks in the object for the JsonRead object.
    public void setTasksRead(List<Task> tasks) {
        this.tasks = tasks;
    }

    //MODIFIES: this
    //EFFECTS: sets the list of volunteers to the one given to be used in the JsonRead
    public void setVolunteerRead(List<Volunteer> volunteers) {
        this.volunteers = volunteers;
    }


    //EFFECTS: returns the students in the object
    public List<Student> getStudents() {
        return students;
    }

    //EFFECTS: returns the volunteers in the object
    public List<Volunteer> getVolunteers() {
        return volunteers;
    }

    //EFFECTS: returns the tasks in the object
    public List<Task> getTasks() {
        return tasks;
    }

    //EFFECTS: returns the organisation's name
    public String getName() {
        return this.name;
    }


    //EFFECTS: adds a student
    public void addStudent(Student newStudent) {
        this.students.add(newStudent);
        notifyObserver(new Event("Student Added"));
    }

    //EFFECTS: adds a task
    public void addTask(Task newTask) {
        this.tasks.add(newTask);
        notifyObserver(new Event("Task Added"));
    }

    //EFFECTS: adds a volunteer
    public void addVolunteer(Volunteer newVolunteer) {
        this.volunteers.add(newVolunteer);
        notifyObserver(new Event("Volunteer Added"));
    }

    //EFFECTS: returns the volunteers' information
    public String getVolunteerInformation() {
        String answer = "";
        for (Volunteer volunteer : volunteers) {
            answer += volunteer.getInformation() + "\n";
        }
        notifyObserver(new Event("Volunteer Information Retreived"));
        return answer;
    }

    //EFFECTS: returns the students' information
    public String getStudentInformation()  {
        String answer = "";
        for (Student student : students) {
            answer += student.getInformation() + "\n";
        }
        notifyObserver(new Event("Student Information Retreived"));
        return answer;
    }
}

