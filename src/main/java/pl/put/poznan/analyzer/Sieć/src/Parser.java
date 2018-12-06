package pl.put.poznan.analyzer.Sieć.src;

import org.json.JSONObject;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;

public class Parser {

    public static void parseGraphToJson(Graph graph){
        JSONObject json1 = new JSONObject(graph.getMapOfConnections());
        JSONObject json2 = new JSONObject(graph.getMapOfNodes());

//        json.put("Nodes:",graph.getMapOfNodes());
//        json.put("Connections:", );

        String jsonString1 = json1.toString();
        String jsonString2 = json2.toString();

        //System.out.println(jsonString1);
        System.out.println(jsonString2);

        try(FileWriter file = new FileWriter("json.txt")){
            file.write(jsonString2);
            System.out.println("Successfully Copied JSON Object to File...");
            System.out.println("\nJSON Object: " + jsonString2);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}