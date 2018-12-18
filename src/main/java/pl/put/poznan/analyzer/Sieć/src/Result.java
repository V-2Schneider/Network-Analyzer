package pl.put.poznan.analyzer.Sieć.src;

import java.util.List;

/**
 * This class is used to store and manage shortest paths from traversing graphs.
 */
public class Result {
    /**
     * Total value of the shortest path
     */
    private float value;
    /**
     * List of nodes from the shortest path
     */
    private List<Node> nodes;

    /**
     * Empty class contructor
     */
    public Result() {
    }

    /**
     * Class constructor
     *
     * @param value total value of the shortest path
     * @param nodes list of nodes from the shortest path
     */
    public Result(float value, List<Node> nodes) {
        this.value = value;
        this.nodes = nodes;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public List<Node> getNodes() {
        return nodes;
    }

    public void setNodes(List<Node> nodes) {
        this.nodes = nodes;
    }
}
