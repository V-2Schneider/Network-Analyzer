package pl.put.poznan.analyzer.logic;

/**
 * This class is used to manage a single connection
 */
class Connection {
    /**
     * Node from which the connection starts
     */
    private Node from;
    /**
     * Node in which the connection ends
     */
    private Node to;
    /**
     * Value of the connection
     */
    private int value;
    
    /**
     * Class constructor
     *
     * @param from  node from which the connection starts
     * @param to    node in which the connection ends
     * @param value value of the connection
     */
    public Connection(Node from, Node to, int value) {
        this.from = from;
        this.to = to;
        this.value = value;
    }
    
    /**
     * Override toString method
     *
     * @return string with all information about the object
     */

    @Override
    public String toString() {
        return "connection_from_" + from.getName() + "_to_" + to.getName() + "_value_" + value;
    }

    public Node getTo() {
        return to;
    }

    public void setTo(Node to) {
        this.to = to;
    }

    public Node getFrom() {
        return from;
    }

    public void setFrom(Node from) {
        this.from = from;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
