package pl.put.poznan.analyzer.Sieć.src;
/**
 * Available types of nodes
 */
public enum TypeOfNode {
    /**
     * A node where network starts, doesn't have any incoming connections.
     */
    entry, 
    /**
     * A node where network ends, doesn't have any outcoming connections.
     */
    exit, 
    /**
     * A node inside network, which isn't an end or start of the network
     */
    regular
}
