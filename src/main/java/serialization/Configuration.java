package serialization;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;

public class Configuration implements ObjectState {
    private final String homeDirectory = System.getProperty("user.home");
    private final File robotDirectory = new File(homeDirectory + "/Robots");
    private final File configurationFile = new File(robotDirectory + "/config.json");

    @Override
    public void save(JSONObject configuration) {
        if (!robotDirectory.exists()) robotDirectory.mkdir();
        try (FileWriter fileWriter = new FileWriter(configurationFile.getAbsolutePath())) {
            fileWriter.write(configuration.toString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public JSONObject load() {
        JSONParser parser = new JSONParser();
        if (configurationFile.exists()) {
            try {
                return (JSONObject) parser.parse(new FileReader(configurationFile));
            } catch (IOException | ParseException e) {
                throw new RuntimeException(e);
            }
        }
        return null;
    }
}
