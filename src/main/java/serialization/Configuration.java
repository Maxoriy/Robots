package serialization;

import org.json.JSONObject;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class Configuration {
    private final String homeDirectory = System.getProperty("user.home");
    private final File robotDirectory = new File(homeDirectory + "/Robots");
    private final File configurationFile = new File(robotDirectory + "/config.json");

    public void save(JSONObject configuration) {
        if (!robotDirectory.exists()) robotDirectory.mkdir();
        try (FileWriter fileWriter = new FileWriter(configurationFile.getAbsolutePath(), StandardCharsets.UTF_8)) {
            fileWriter.write(configuration.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public JSONObject load() {
        if (configurationFile.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(configurationFile, StandardCharsets.UTF_8))) {
                StringBuilder stringBuilder = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) stringBuilder.append(line);
                return new JSONObject(stringBuilder.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return new JSONObject();
    }
}
