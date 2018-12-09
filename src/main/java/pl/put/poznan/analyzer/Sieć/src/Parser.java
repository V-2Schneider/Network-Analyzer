package pl.put.poznan.analyzer.Sieć.src;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.boot.autoconfigure.gson.GsonAutoConfiguration;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

public class Parser {

    public static String JSON_HEADER_GRAPH = "graph";
    public static String JSON_HEADER_CONNECTIONS = "connections";

    public static String JSON_HEADER_CONNECTION_ID= "connection_id";
    public static String JSON_HEADER_TO = "to";
    public static String JSON_HEADER_FROM = "from";
    public static String JSON_HEADER_VALUE = "value";

    public static String JSON_HEADER_NODE_ID = "node_id";
    public static String JSON_HEADER_NODE_NAME = "name";
    public static String JSON_HEADER_NODE_TYPE = "type";

    public static String readFile(String path) throws IOException {
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        return new String(encoded, Charset.defaultCharset());
    }

    public static void writeToFile(String path, String toWrite) throws IOException {
        FileWriter fileWriter = new FileWriter(path);
        fileWriter.write(toWrite);
        fileWriter.close();
    }

    public static String parseGraphToJsonString(Graph graph) throws IOException {

        JSONObject jsonGraph = new JSONObject();
        JSONObject jsonConnections = new JSONObject();
        JSONArray jsonArrayConnections = new JSONArray();

        HashMap<Integer,Connection> mapOfConnections = graph.getMapOfConnections();
        for(HashMap.Entry<Integer,Connection> entry: mapOfConnections.entrySet()){
            //wypełnij jsonArrayConnections

            JSONObject jsonConnection       = new JSONObject();
            JSONObject jsonConnectionFrom   = new JSONObject();
            JSONObject jsonConnectionTo     = new JSONObject();

            Node from   = entry.getValue().getFrom();
            Node to     = entry.getValue().getTo();

            jsonConnection.put(JSON_HEADER_CONNECTION_ID,entry.getKey());

            jsonConnectionFrom.put(JSON_HEADER_NODE_ID,from.getId());
            jsonConnectionFrom.put(JSON_HEADER_NODE_NAME,from.getName());
            jsonConnectionFrom.put(JSON_HEADER_NODE_TYPE,from.getTypeOfNode());
            jsonConnection.put(JSON_HEADER_FROM,jsonConnectionFrom);

            jsonConnectionTo.put(JSON_HEADER_NODE_ID,to.getId());
            jsonConnectionTo.put(JSON_HEADER_NODE_NAME,to.getName());
            jsonConnectionTo.put(JSON_HEADER_NODE_TYPE,to.getTypeOfNode());
            jsonConnection.put(JSON_HEADER_TO,jsonConnectionTo);

            jsonConnection.put(JSON_HEADER_VALUE,entry.getValue().getValue());

            jsonArrayConnections.put(jsonConnection);
        }

        jsonConnections.put(JSON_HEADER_CONNECTIONS,jsonArrayConnections);
        jsonGraph.put(JSON_HEADER_GRAPH,jsonConnections);

        String jsonString = jsonGraph.toString();

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonParser jp = new JsonParser();
        JsonElement je = jp.parse(jsonString);
        String prettyJsonString = gson.toJson(je);

        return prettyJsonString;
    }
    public static Graph parseJsonStringToGraph(String jsonString){
        Graph graph = new Graph();

        JSONObject jsonGraph = new JSONObject(jsonString);
        JSONObject jsonConnections = new JSONObject(jsonGraph.get(JSON_HEADER_GRAPH));
        JSONArray jsonConnectionsArray = new JSONArray(jsonConnections);

        for(int i=0;i<jsonConnectionsArray.length();i++){
            JSONObject jsonConnection = new JSONObject(jsonConnectionsArray.get(i));
            jsonConnection.get(JSON_HEADER_CONNECTION_ID);
//            Connection connection = new Connection();
        }
        return null;
    }
}