package serialization;


import org.json.simple.JSONObject;

public interface ObjectState {
    void save(JSONObject configuration);

    JSONObject load();
}
