package persistence;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import model.*;
import org.json.*;
import model.Organisation;
import ui.OrganisationInteraction;

// Represents a reader that reads workroom from JSON data stored in file
// Modified the same class from: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
public class JsonReader {


    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads workroom from file and returns it;
    // throws IOException if an error occurs reading data from file
    public Organisation read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseOrg(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses the whole Organisation from JSON object and returns it
    private Organisation parseOrg(JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        Organisation org = new Organisation(name);
        org.setStudentsRead(parseStudents(org, jsonObject.getJSONArray("Students")));
        org.setTasksRead(parseTasks(org, jsonObject.getJSONArray("Tasks")));
        org.setVolunteerRead(parseVolunteers(org, jsonObject.getJSONArray("volunteers")));
        return org;
    }

    //EFFECTS: parses the tasks from JSON object and returns it
    private List<Task> parseTasks(Organisation org, JSONArray tasks) {
        List<Task> allTasks = new ArrayList<>();
        for (Object json : tasks) {
            JSONObject taskJson = (JSONObject) json;
            String title = taskJson.getString("title");
            int hours = taskJson.getInt("hours");

            Task task = new Task(title, hours);
            allTasks.add(task);
        }
        return allTasks;
    }

    //EFFECTS: parses the students from JSON object and returns it
    private List<Student> parseStudents(Organisation org, JSONArray students) {
        List<Student> allStudents = new ArrayList<>();
        for (Object studentJson : students) {
            JSONObject studentObject = (JSONObject) studentJson;
            String studentName = studentObject.getString("name");
            int studentId = studentObject.getInt("id");
            int studentHomework = studentObject.getInt("homework");

            Student student = new Student(studentName, studentId);
            student.setHomework(studentHomework);
            allStudents.add(student);
        }
        return allStudents;
    }

    //EFFECTS: parses the volunteers from JSON object and returns it
    private List<Volunteer> parseVolunteers(Organisation org, JSONArray jsonArray) {
        List<Volunteer> volunteers = new ArrayList<>();
        for (Object json : jsonArray) {
            JSONObject volunteerJson = (JSONObject) json;
            String name = volunteerJson.getString("name");
            int id = volunteerJson.getInt("id");
            String phone = volunteerJson.getString("phone number");
            int maximumHours = volunteerJson.getInt("max hour");

            Volunteer volunteer = new Volunteer(name, id, phone, maximumHours);

            JSONArray studentsJson = volunteerJson.getJSONArray("students");
            volunteer.setListOfStudent(parseStudents(org, studentsJson));

            JSONArray tasksJson = volunteerJson.getJSONArray("tasks");
            volunteer.setListOfTask(parseTasks(org, tasksJson));
            volunteers.add(volunteer);

        }
        return volunteers;

    }

    public String getSource() {
        return source;
    }


}
