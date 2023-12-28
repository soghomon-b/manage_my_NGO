package persistence;

import model.Organisation;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;


import ui.OrganisationInteraction;

// Represents a writer that writes JSON representation of workroom to file
// Modified the same class from: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
public class JsonWriter {
    private static final int TAB = 4;
    private PrintWriter writer;
    private String destination;

    // EFFECTS: constructs writer to write to destination file
    public JsonWriter(String destination) {
        this.destination = destination;
    }

    // MODIFIES: this
    // EFFECTS: opens writer; throws FileNotFoundException if destination file cannot
    // be opened for writing
    public void open() throws FileNotFoundException {
        writer = new PrintWriter(new File(destination));
    }

    // MODIFIES: this
    // EFFECTS: writes JSON representation of workroom to file
    public void write(Organisation org) {
        JSONObject json = org.orgtoJson();
        saveToFile(json.toString(TAB));
    }

    // MODIFIES: this
    // EFFECTS: closes writer
    public int close() {
        writer.close();
        return 1;
    }

    // MODIFIES: this
    // EFFECTS: writes string to file
    public int saveToFile(String json) {
        writer.print(json);
        return 1;

    }

    public String getDestination() {
        return this.destination;
    }
}
