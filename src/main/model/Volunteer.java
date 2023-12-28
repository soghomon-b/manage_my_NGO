package model;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

// Volunteer class, each volunteer has a name, id, phone, and the maximum hours they can work.
// In addition to a list of tasks and list of students.

public class Volunteer implements Entity {
    private String name;
    private int id;
    private String phone;
    private int maximumHours;
    private List<Student> listOfStudent;
    private List<Task> listOfTask;

    //EFFECTS: Constructs a volunteer object with the name, id, phone number and maximum hours they can work a
    // and empty list of students and tasks.
    public Volunteer(String name, int id, String phone, int maximumHours) {
        this.name = name;
        this.id = id;
        this.phone = phone;
        this.maximumHours = maximumHours;
        listOfStudent = new ArrayList<>();
        listOfTask = new ArrayList<>();


    }

    //MODIFIES: this
    //EFFECTS: Adds a new student to the listOfStudent
    public void addStudent(Student student) {
        listOfStudent.add(student);

    }

    // REQUIRES: student in ListOfStudent
    //MODIFIES: this
    //EFFECTS: Removes the student from the listOfStudent
    public void removeStudent(Student student) {
        listOfStudent.remove(student);

    }

    // EFFECTS: counts the number of hours assigned to the volunteer.
    public int getTotalHours() {
        int hours = 0;
        for (Task task : listOfTask) {
            hours += task.getHours();
        }
        return hours;
    }

    //MODIFIES: this
    //EFFECTS: Adds a new task to the listOfTask if the number of maximum hours is okay and returns true.
    // false otherwise
    public Boolean addTask(Task task) {
        if (getTotalHours() + task.getHours() <= maximumHours) {
            listOfTask.add(task);

            return true;

        }
        return false;
    }

    //MODIFIES: this
    //EFFECTS: Removes the task from the listOfTask
    public void removeTask(Task task) {
        listOfTask.remove(task);
    }

    //EFFECTS: Returns the number of student assigned.
    public int getNumberOfStudents() {
        return listOfStudent.size();

    }


    //EFFECTS: Returns the number of tasks assigned.
    public int getNumberOfTasks() {
        return listOfTask.size();

    }

    // REQUIRES: hours >= 0
    // MODIFIES: this
    // EFFECTS: removes as many tasks as possible from listOfTask, starting with the most recently added.
    // if one task is completed and the number of hours remaining doesn't complete task, it doesn't continue. Returns a
    // list of the tasks removed.
    public List<Task> work(int hours) {
        List<Task> removedTasks = new ArrayList<>();
        int itask = 0;
        while (itask < listOfTask.size() && hours > 0) {
            Task task = listOfTask.get(itask);
            if (hours - task.getHours() >= 0) {
                listOfTask.remove(task);
                removedTasks.add(task);
                hours -= task.getHours();
            } else {
                return removedTasks;
            }

        }

        return removedTasks;
    }


    // MODIFIES: student
    // EFFECTS: increases the number of homeworks by one if the student is in the ListOfStudents and returns true.
    // returns false otherwise.
    public Boolean assignHomework(Student student) {
        if (listOfStudent.contains(student)) {
            student.increaseHomework();
            return true;
        }
        return false;
    }

    //REQUIRES: n >= 0
    //MODIFIES: this
    //EFFECTS: changes the MaximumHour
    public void setMaximumHour(int maximumHour) {
        this.maximumHours = maximumHour;

    }

    //EFFECTS: returns the volunteer name and phone number.
    @Override
    public String getInformation() {
        String a = "Name: " + name + ", Phone Number: " + phone;
        return a;
    }


    @Override
    //EFFECTS: returns the object's id
    public int getId() {
        return id;
    }

    @Override
    //EFFECTS: returns the object's name
    public String getName() {
        return name;
    }

    //EFFECTS: returns the object's maximum hours
    public int getMaximumHours() {
        return maximumHours;
    }

    //EFFECTS: returns the object's phone number
    public String getPhone() {
        return phone;
    }

    //EFFECTS: returns the object's students
    public List<Student> getListOfStudent() {
        return listOfStudent;
    }

    //EFFECTS: returns the object's tasks
    public List<Task> getListOfTask() {
        return listOfTask;
    }

    //EFFECTS: transforms the object into a jason array.
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("id", id);
        json.put("phone number", phone);
        json.put("max hour", maximumHours);
        json.put("students", studentsToJason());
        json.put("tasks", tasksToJason());
        return json;

    }

    //EFFECTS: transforms the students into a jason array.
    private JSONArray studentsToJason() {
        JSONArray jsonArray = new JSONArray();

        for (Student s : listOfStudent) {
            jsonArray.put(s.toJson());
        }

        return jsonArray;
    }

    //EFFECTS: transforms the tasks into a jason array.
    private JSONArray tasksToJason() {
        JSONArray jsonArray = new JSONArray();

        for (Task s : listOfTask) {
            jsonArray.put(s.toJson());
        }

        return jsonArray;
    }

    //EFFECTS: sets the list of students to the one given.
    public void setListOfStudent(List<Student> students) {
        this.listOfStudent = students;

    }

    //EFFECTS: sets the list of students to the one given.
    public void setListOfTask(List<Task> tasks) {
        this.listOfTask = tasks;

    }




}
