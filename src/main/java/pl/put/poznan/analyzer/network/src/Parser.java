package pl.put.poznan.analyzer.network.src;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Parser class is a container for constants and static utility methods used in NetworkAnalyzer.
 * These methods are used for parsing objects of types Graph, Node and Connection to JSONObject and vice versa.
 * They are used in our REST API.
 */
public class Parser {

    // String constants
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

    /**
     * Parser class exists solely to store static methods used for parsing objects,
     * therefore it should not be instantiated, so its constructor is private.
     */
    private Parser() {
    }

    /**
     * Returns a {@link String} object with the contents of a file.
     * @param path path to the file (root is the project folder).
     * @return {@link String} with file contents.
     * @throws IOException .
     */
    public static String readFile(String path) throws IOException {
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        return new String(encoded, Charset.defaultCharset());
    }

    /**
     * Writes a {@link String} to a text file.
     * @param path path to the file (root is the project folder).
     * @param toWrite {@link String} to be written to the file.
     * @throws IOException .
     */
    public static void writeToFile(String path, String toWrite) throws IOException {
        FileWriter fileWriter = new FileWriter(path);
        fileWriter.write(toWrite);
        fileWriter.close();
    }

    /**
     * Returns a {@link String} containing a JSON representation of an object of type {@link Graph}.
     * @param graph object of type {@link Graph} that is to be parsed.
     * @return {@link String} containing a JSON representation of parsed Graph.
     * @throws IOException .
     */
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
            jsonNodeFrom.put(JSON_HEADER_NODE_TYPE,from.getType());
            jsonConnection.put(JSON_HEADER_FROM,jsonNodeFrom);

            jsonNodeTo.put(JSON_HEADER_NODE_ID,to.getId());
            jsonNodeTo.put(JSON_HEADER_NODE_NAME,to.getName());
            jsonNodeTo.put(JSON_HEADER_NODE_TYPE,to.getType());
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

    /**
     * Returns an object of type {@link Graph} from a {@link String} containing a JSON representation of said graph.
     * @param jsonString object of type {@link String} that is to be parsed.
     * @return extracted {@link Graph}
     * @throws IOException .
     */
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

    /**
     * Returns an object of type {@link String} containing a JSON representation of a client's request for a path connecting two objects of type {@link Node}.
     * @param from object of type {@link Node} representing the entry node for the sought path.
     * @param to object of type {@link Node} representing the exit node for the sought path.
     * @return {@link String} containing a JSON representation of a client's request for a path connecting two objects of type {@link Node}.
     * @throws IOException .
     */
    public static String parseNodesToRequest(Node from, Node to){
        JSONObject jsonRequest = new JSONObject();
        JSONObject jsonNodes = new JSONObject();
        JSONObject jsonNodeFrom = new JSONObject();
        JSONObject jsonNodeTo = new JSONObject();

        jsonNodeFrom.put(JSON_HEADER_NODE_NAME,from.getName());
        jsonNodeFrom.put(JSON_HEADER_NODE_TYPE,from.getType());
        jsonNodeFrom.put(JSON_HEADER_NODE_ID,from.getId());

        jsonNodes.put(JSON_HEADER_FROM,jsonNodeFrom);

        jsonNodeTo.put(JSON_HEADER_NODE_NAME,to.getName());
        jsonNodeTo.put(JSON_HEADER_NODE_TYPE,to.getType());
        jsonNodeTo.put(JSON_HEADER_NODE_ID,to.getId());

        jsonNodes.put(JSON_HEADER_TO,jsonNodeTo);

        jsonRequest.put(JSON_HEADER_REQUEST,jsonNodes);

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonParser jp = new JsonParser();
        JsonElement je = jp.parse(jsonRequest.toString());
        String prettyJsonString = gson.toJson(je);

        return prettyJsonString;
    }

    /**
     * Returns an {@link ArrayList<Node>} containing the nodes from client's JSON request.
     * @param jsonString object of type {@link String} containing the JSON request sent from a client.
     * @return {@link ArrayList<Node>} containing the nodes from client's JSON request.
     * @throws IOException .
     */
    public static ArrayList<Node> parseRequestToNodesList(String jsonString){
        JSONObject jsonRequest = new JSONObject(jsonString);
        JSONObject jsonNodes = jsonRequest.getJSONObject(JSON_HEADER_REQUEST);

        JSONObject jsonNodeFrom = jsonNodes.getJSONObject(JSON_HEADER_FROM);
        JSONObject jsonNodeTo = jsonNodes.getJSONObject(JSON_HEADER_TO);

        Node from = new Node(jsonNodeFrom.getInt(JSON_HEADER_NODE_ID),jsonNodeFrom.getString(JSON_HEADER_NODE_NAME),jsonNodeFrom.getString(JSON_HEADER_NODE_TYPE));
        Node to = new Node(jsonNodeTo.getInt(JSON_HEADER_NODE_ID),jsonNodeTo.getString(JSON_HEADER_NODE_NAME),jsonNodeTo.getString(JSON_HEADER_NODE_TYPE));

        ArrayList<Node> list = new ArrayList<>();
        list.add(from);
        list.add(to);

        return list;
    }
}