package model;


import org.json.JSONObject;

//tasks class with a title and number of hours needed for the task to be completed.
public class Task {
    private String title;
    private int hours;

    //REQUIRES: hours >= 0
    //EFFECTS: constructs a Task object with the title and hours given.
    public Task(String title, int hours) {
        this.title = title;
        this.hours = hours;
    }


    //EFFECTS: returns the title
    public String getTitle() {
        return title;
    }

    //EFFECTS: returns the hours
    public int getHours() {

        return hours;
    }

    //EFFECTS: transforms the object into a jason array.
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("title", title);
        json.put("hours", hours);
        return json;
    }


}
