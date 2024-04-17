package PullAndBear;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class JSONHandler {

    private static final String JSON_FILE = "inventario.json";

    public static JSONArray readJSON() {
        JSONParser parser = new JSONParser();
        try (FileReader reader = new FileReader(JSON_FILE)) {
            Object obj = parser.parse(reader);
            return (JSONArray) obj;
        } catch (IOException | ParseException e) {
            e.printStackTrace();
            return new JSONArray();
        }
    }

    public static void writeJSON(JSONArray jsonArray) {
        try (FileWriter file = new FileWriter(JSON_FILE)) {
            file.write(jsonArray.toJSONString());
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void updateJSON(int rowIndex, JSONObject newData) {
        JSONArray jsonArray = readJSON();
        jsonArray.set(rowIndex, newData);
        writeJSON(jsonArray);
    }
}
