package ui;

import model.Organisation;
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
public class OrganisationInteraction {
    private static final String JSON_STORE = "./data/workroom.json";
    private boolean running = true; // Flag to control the loop
    private String name;
    private Organisation organisation;
    private Scanner scanner = new Scanner(System.in);
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;


    //EFFECTS: constructs an Organisation class with the name given and empty lists for takss, students, and volunteers.
    public OrganisationInteraction(String name) {
        this.name = name;
        organisation = new Organisation(name);
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
    }


    // EFFECTS: the main function of the user interaction, shows the options and directs to choosing function
    void organisationInteraction() {
        System.out.println("Welcome " + name + "'s leadership!");

        while (true) {
            System.out.println("Select Options; 1: add volunteer, 2: add Student, 3: get volunteer information,");
            System.out.println("4: get Student Information 5: create and assign task. 6: assign student");
            System.out.println("7: view volunteer's students, 8: view volunteer's tasks");
            System.out.println("9: save your work , 10: retrieve last work saved, -1: exit");
            int code = scanner.nextInt();
            scanner.nextLine();

            if (code == -1) {
                break; // Exit the loop
            }

            handleUserInput(code);
        }
    }

    //MODIFIES: this
    //EFFECTS: direct you to the functions needed for each option
    void handleUserInput(int code) {
        switch (code) {
            case 1:
                addVolunteerInterface();
                break;
            case 2:
                addStudentInterface();
                break;
            case 3:
                displayVolunteerInformation();
                break;
            case 4:
                displayStudentInformation();
                break;
            case 5:
                createAssignTask();
                break;
            case 6:
                assignStudents();
                break;
            default:
                more(code);
        }
    }

    // MODIFIES: this
    // EFFECTS: continuation to the previous function
    private void more(int code) {
        switch (code) {
            case 7:
                viewStudents();
                break;
            case 8:
                viewTasks();
                break;
            case 9:
                saveOrg();
                break;
            case 10:
                try {
                    readOrg();
                    System.out.println("Information Retrieved Successfully!");
                } catch (IOException e) {
                    System.out.println("sorry error retrieving data");
                }
                break;
            default:
                System.out.println("invalid option");
                break;

        }

    }

    //EFFECTS: shows a list of students assigned to the volunteer wanted.
    private void viewStudents() {
        System.out.println("Write the id of the volunteer:");
        int volId = scanner.nextInt();
        int studentFound = 0;
        for (Volunteer vol : organisation.getVolunteers()) {
            if (volId == vol.getId()) {
                System.out.println("Here are " + vol.getName() + "'s students: ");
                studentFound = 1;
                for (Student student : vol.getListOfStudent()) {
                    System.out.println(student.getInformation());
                }

            }

        }
        if (studentFound != 1) {
            System.out.println("wrong ID, volunteer not in the system");
        }
    }

    //EFFECTS: shows a list of tasks assigned to the volunteer wanted.
    private void viewTasks() {
        System.out.println("Write the id of the volunteer:");
        int volId = scanner.nextInt();
        int taskFound = 0;
        for (Volunteer vol : organisation.getVolunteers()) {
            if (volId == vol.getId()) {
                System.out.println("Here are " + vol.getName() + "'s students: ");
                taskFound = 1;
                for (Task task : vol.getListOfTask()) {
                    System.out.println(task.getTitle());
                }

            }
        }
        if (taskFound != 1) {
            System.out.println("wrong ID, volunteer not in the system");
        }
    }

    //EFFECTS: shows the information of all volunteers in the system
    void displayVolunteerInformation() {
        System.out.println("Here are our amazing volunteers: ");
        for (Volunteer volunteer : organisation.getVolunteers()) {
            System.out.println(volunteer.getInformation());
        }
    }

    //EFFECTS: shows the information of all students in the system
    void displayStudentInformation() {
        System.out.println("Here are our lovely students: ");
        for (Student student : organisation.getStudents()) {
            System.out.println(student.getInformation());
        }
    }


    //MODIFIES: volunteer.getListOfStudents
    //EFFECTS: assigns the student to the volunteer
    private void assignStudents() {
        System.out.println("Enter Student Id: ");
        int stId = scanner.nextInt();
        System.out.println("Enter Volunteer Id: ");
        int volId = scanner.nextInt();
        Student student = new Student("", 0);
        for (Student s :  organisation.getStudents()) {
            if (s.getId() == stId) {
                student = s;
            }
        }
        for (Volunteer volunteer : organisation.getVolunteers()) {
            if (volunteer.getId() == volId) {
                volunteer.addStudent(student);
                System.out.println("student added to volunteer successfully!");
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: adds a new student to the system
    private void addStudentInterface() {
        System.out.println("Enter student name: ");
        String name = scanner.nextLine();
        System.out.println("Enter student id: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        Student newStudent = new Student(name, id);
        organisation.addStudent(newStudent);
        System.out.println("Student created successfully!");
    }

    // MODIFIES: this
    // EFFECTS: adds a new volunteer to the system
    private void addVolunteerInterface() {
        System.out.println("Enter volunteer name: ");
        String name = scanner.nextLine();
        System.out.println("Enter volunteer id: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character
        System.out.println("Enter volunteer's phone number: ");
        String phone = scanner.nextLine();
        System.out.println("Enter volunteer's maximum hours: ");
        int max = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        Volunteer newVolunteer = new Volunteer(name, id, phone, max);
        organisation.addVolunteer(newVolunteer);
        System.out.println("Volunteer created successfully!");
    }

    // MODIFIES: this
    // EFFECTS: adds a new task to the system and assigns it to a volunteer
    private void createAssignTask() {
        System.out.println("Enter task name: ");
        String name = scanner.nextLine();
        System.out.println("To which volunteer would you like to assign tasks? Enter their id ");
        int id = scanner.nextInt();
        System.out.println("How many hours does this task require? ");
        int hours = scanner.nextInt();
        Task newTask = new Task(name, hours);
        Volunteer vol;
        for (Volunteer volunteer : organisation.getVolunteers()) {
            if (volunteer.getId() == id) {
                volunteer.addTask(newTask);
                vol = volunteer;
                System.out.println(newTask.getTitle() + " has been added to volunteer: " + vol.getName());
            } else {
                System.out.println("Volunteer doesn't exist");
            }

        }


    }

    //EFFECTS: saves the organisation in a json file for later use.
    void saveOrg() {
        try {
            jsonWriter.open();
            jsonWriter.write(organisation);
            jsonWriter.close();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }

    }

    // REQUIRES: saveOrg should have been done before
    //MODIFIES: this
    //EFFECTS: reads the organisation in a json file saved from before.
    void readOrg() throws IOException {
        Organisation org = jsonReader.read();
        this.organisation = org;
    }


    //EFFECTS: returns the students in the object
    public List<Student> getStudents() {
        return organisation.getStudents();
    }

    //EFFECTS: returns the volunteers in the object
    public List<Volunteer> getVolunteers() {
        return organisation.getVolunteers();
    }

    //EFFECTS: returns the tasks in the object
    public List<Task> getTasks() {
        return organisation.getTasks();
    }

    //EFFECTS: returns the organisation's name
    public String getName() {
        return this.name;
    }


    public void addStudent(Student newStudent) {
        this.organisation.addStudent(newStudent);
    }

    public void addTask(Task newTask) {
        this.organisation.addTask(newTask);
    }

    public void addVolunteer(Volunteer newVolunteer) {
        this.organisation.addVolunteer(newVolunteer);
    }

    public Organisation getOrganisation() {
        return this.organisation;
    }
}
