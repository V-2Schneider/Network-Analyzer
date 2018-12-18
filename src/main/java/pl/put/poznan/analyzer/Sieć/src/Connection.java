package pl.put.poznan.analyzer.Sieć.src;

class Connection {
    private Node from;
    private Node to;
    private int value;

    public Connection(Node from, Node to, int value) {
        this.from = from;
        this.to = to;
        this.value = value;
    }

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
