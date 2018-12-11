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

    public static String JSON_HEADER_REQUEST = "path_request";

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
            JSONObject jsonNodeFrom   = new JSONObject();
            JSONObject jsonNodeTo     = new JSONObject();

            Node from   = entry.getValue().getFrom();
            Node to     = entry.getValue().getTo();

            jsonConnection.put(JSON_HEADER_CONNECTION_ID,entry.getKey());

            jsonNodeFrom.put(JSON_HEADER_NODE_ID,from.getId());
            jsonNodeFrom.put(JSON_HEADER_NODE_NAME,from.getName());
            jsonNodeFrom.put(JSON_HEADER_NODE_TYPE,from.getTypeOfNode());
            jsonConnection.put(JSON_HEADER_FROM,jsonNodeFrom);

            jsonNodeTo.put(JSON_HEADER_NODE_ID,to.getId());
            jsonNodeTo.put(JSON_HEADER_NODE_NAME,to.getName());
            jsonNodeTo.put(JSON_HEADER_NODE_TYPE,to.getTypeOfNode());
            jsonConnection.put(JSON_HEADER_TO,jsonNodeTo);

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
        JSONArray jsonConnectionsArray = jsonGraph.getJSONObject(JSON_HEADER_GRAPH)
                .getJSONArray(JSON_HEADER_CONNECTIONS);

        for(int i = 0; i < jsonConnectionsArray.length(); i++){
            JSONObject jsonConnection = jsonConnectionsArray.getJSONObject(i);
            JSONObject jsonFrom = jsonConnection.getJSONObject(JSON_HEADER_FROM);
            JSONObject jsonTo = jsonConnection.getJSONObject(JSON_HEADER_TO);

            Node from   = new Node(jsonFrom.getInt(JSON_HEADER_NODE_ID), jsonFrom.getString(JSON_HEADER_NODE_NAME), jsonFrom.getString(JSON_HEADER_NODE_TYPE));
            Node to     = new Node(jsonTo.getInt(JSON_HEADER_NODE_ID), jsonTo.getString(JSON_HEADER_NODE_NAME), jsonTo.getString(JSON_HEADER_NODE_TYPE));

            graph.addNode(from);
            graph.addNode(to);
            graph.addConnection(from.getId(),to.getId(),jsonConnection.getInt(JSON_HEADER_VALUE));
        }
        return graph;
    }
    public static String parseNodesToRequest(Node from, Node to){
        JSONObject jsonRequest = new JSONObject();
        JSONObject jsonNodes = new JSONObject();
        JSONObject jsonNodeFrom = new JSONObject();
        JSONObject jsonNodeTo = new JSONObject();

        jsonNodeFrom.put(JSON_HEADER_NODE_NAME,from.getName());
        jsonNodeFrom.put(JSON_HEADER_NODE_TYPE,from.getTypeOfNode());
        jsonNodeFrom.put(JSON_HEADER_NODE_ID,from.getId());

        jsonNodes.put(JSON_HEADER_FROM,jsonNodeFrom);

        jsonNodeTo.put(JSON_HEADER_NODE_NAME,to.getName());
        jsonNodeTo.put(JSON_HEADER_NODE_TYPE,to.getTypeOfNode());
        jsonNodeTo.put(JSON_HEADER_NODE_ID,to.getId());

        jsonNodes.put(JSON_HEADER_TO,jsonNodeTo);

        jsonRequest.put(JSON_HEADER_REQUEST,jsonNodes);

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonParser jp = new JsonParser();
        JsonElement je = jp.parse(jsonRequest.toString());
        String prettyJsonString = gson.toJson(je);

        return prettyJsonString;
    }
//    public static ArrayList<Node> parseRequestToNodesList(String jsonString){
//        JSONObject jsonRequest = new JSONObject(jsonString);
//        JSONObject jsonNodes = jsonRequest.getJSONObject(JSON_HEADER_REQUEST);
//
//        JSONObject jsonNodeFrom = jsonNodes.getJSONObject(JSON_HEADER_FROM);
//        JSONObject jsonNodeTo = jsonNodes.getJSONObject(JSON_HEADER_TO);
//
//        Node from = new Node(jsonNodeFrom.getInt(JSON_HEADER_NODE_ID),jsonNodeFrom.getString(JSON_HEADER_NODE_NAME),jsonNodeFrom.getString(JSON_HEADER_NODE_TYPE));
//        Node to = new Node(jsonNodeTo.getInt(JSON_HEADER_NODE_ID),jsonNodeTo.getString(JSON_HEADER_NODE_NAME),jsonNodeTo.getString(JSON_HEADER_NODE_TYPE));
//
//        ArrayList<Node> list = new ArrayList<>();
//        list.add(from);
//        list.add(to);
//    }
}