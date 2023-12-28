package persistence;

import org.json.JSONObject;

// From the class
// Modified the same interface from: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
public interface Writable {
    // EFFECTS: returns this as JSON object
    JSONObject toJson();
}
