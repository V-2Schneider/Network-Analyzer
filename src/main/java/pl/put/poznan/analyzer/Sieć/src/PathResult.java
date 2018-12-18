package pl.put.poznan.analyzer.Sieć.src;

import java.util.ArrayList;

public class PathResult {
    private int sumValue;
    private ArrayList<Node> path;

    public PathResult(int sumValue, ArrayList<Node> path) {
        this.sumValue = sumValue;
        this.path = path;
    }

    public int getSumValue() {
        return sumValue;
    }

    public ArrayList<Node> getPath() {
        return path;
    }
}
