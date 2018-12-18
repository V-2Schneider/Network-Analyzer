package pl.put.poznan.analyzer.logic;

import java.util.ArrayList;

/**
 * This class is used to store and manage paths
 */
public class PathResult {
    /**
     * Total value of the path
     */
    private float value;
    /**
     * List of nodes from the path
     */
    private ArrayList<Node> nodes;

    /**
     * Empty class contructor
     */
    public PathResult() {
    }

    /**
     * Class constructor
     *
     * @param value total value of the path
     * @param nodes list of nodes from the path
     */
    public PathResult(float value, ArrayList<Node> nodes) {
        this.value = value;
        this.nodes = nodes;
    }
    
        public ArrayList<Node> getNodes() {
        return nodes;
    }

    public void setNodes(ArrayList<Node> nodes) {
        this.nodes = nodes;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }
}
