package pl.put.poznan.analyzer.Sieć.src;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.json.JSONObject;
import org.springframework.boot.autoconfigure.gson.GsonAutoConfiguration;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;

public class Parser {

    public static void parseGraphToJson(Graph graph){

        JSONObject json = new JSONObject();

        json.put("Graph:",graph.getMapOfConnections());
//        json.put("Connections:","uhh");

        String jsonString = json.toString();

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonParser jp = new JsonParser();
        JsonElement je = jp.parse(jsonString);
        String prettyJsonString = gson.toJson(je);

        System.out.println(prettyJsonString);

//        try(FileWriter file = new FileWriter("json.txt")){
//            file.write(jsonString2);
//            System.out.println("Successfully Copied JSON Object to File...");
//            System.out.println("\nJSON Object: " + jsonString2);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }
}