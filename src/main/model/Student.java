package model;

// student class with a name, id, a volunteer, and number of homeworks the student has.
import org.json.JSONObject;

public class Student implements Entity  {
    private String name;
    private int id;
    private Volunteer volunteer;
    private int homework;


    //MODIFIES: this
    //EFFECTS: constructs a student class with a name, an id and zero homeworks and no volunteer.
    public Student(String name, int id) {
        this.name = name;
        this.id = id;
        this.homework = 0;

    }

    // REQUIRES: homework >= 0
    // MODIFIES: this
    // EFFECTS: brings homework down by one.
    public Boolean doHomework() {
        if (this.homework != 0) {
            this.homework -= 1;
            return true;
        } else {
            return false;
        }
    }

    // REQUIRES: volunteer not null;
    //EFFECTS: returns the name and phone number of the volunteer. If no volunteer has been assigned, returns "No
    //volunteer has been assigned yet"
    public String contactVolunteer() {
        return volunteer.getInformation();
    }

    //EFFECTS: returns the name and the id and the number of homework of the student
    @Override
    public String getInformation() {
        String a = "Name: " + this.name + ", Student Number: " + this.id;

        return a;

    }

    //EFFECTS: returns the ID of the student
    @Override
    public int getId() {
        return id;
    }

    //EFFECTS: returns the name of the student
    @Override
    public String getName() {
        return name;
    }

    //EFFECTS: returns the volunteer of the student
    public Volunteer getVolunteer() {
        return volunteer;
    }

    //EFFECTS: returns the homework of the student
    public int getHomework() {
        return homework;
    }

    //EFFECTS: increases the number of homework by 1
    public void increaseHomework() {
        this.homework++;
    }

    //MODIFIES: this
    //EFFECTS: sets the volunteer given
    public void setVolunteer(Volunteer volunteer) {
        this.volunteer = volunteer;
    }

    //EFFECTS: transforms the object into a jason array.
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("id", id);
        json.put("volunteer", getVolName());
        json.put("homework", homework);

        return json;
    }

    // EFFECTS: returns the volunteer's name if exists, or null if not
    public String getVolName() {
        if (volunteer != null) {
            return volunteer.getName();
        } else {
            return "null";
        }
    }

    //EFFECTS: sets the homework of students to the one given.
    public void setHomework(int studentHomework) {
        this.homework = studentHomework;
    }
}
