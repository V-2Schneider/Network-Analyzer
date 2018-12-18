package pl.put.poznan.analyzer.logic;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * This class is used to manage a single node
 */
public class Node implements Serializable {

    /**
    * 
    */
    private static final long serialVersionUID = 1L;
    /**
    * 
    */
    private static AtomicInteger sequence   = new AtomicInteger(0);
    /**
     * A node inside network, which isn't an end or start of the network
     */
    private static java.lang.String NODE_TYPE_REGULAR = "regular";
    /**
     * A node where network starts, doesn't have any incoming connections.
     */
    private static java.lang.String NODE_TYPE_ENTRY   = "entry";
    /**
     * A node where network ends, doesn't have any outcoming connections.
     */
    private static java.lang.String NODE_TYPE_EXIT    = "exit";
    
    /**
    * identifier number (id) of the node
    */
    private int node_id;
    /**
    * description of the node
    */
    private String name;
    /**
    * type of the node( regular/entry/exit)
    */
    private String type;
    /**
    * list of incoming connections to the node
    */
    private ArrayList<Connection> Incoming;
    /**
    * list of outgoing connections from the node
    */
    private ArrayList<Connection> Outgoing;
    
    /**
    * 
    */
    private static int nextInSequence(){
        return sequence.incrementAndGet();
    }

    /**
     * Override toString method
     *
     * @return string with information about the object
     */
    @Override
    public String toString() {
        return "node_id_" + node_id + "_name_" + name + "_type_" + type;
    }

    /**
     * Class constructor for nodes without connections
     *
     * @param name          description of the node
     */
    public Node(String name) {
        this.name = name;
        this.node_id = nextInSequence();
        this.Incoming = new ArrayList<>();
        this.Outgoing = new ArrayList<>();
        setType();
    }
    
    /**
     * Class constructor for nodes without connections
     *
     * @param node_id       node id
     * @param name          description of the node
     * @param typeOfNode    node type can be entry, exit or regular
     */
    public Node(int node_id, String name, String typeOfNode) {
        this.node_id = node_id;
        this.name = name;
        this.type = typeOfNode;
        this.Incoming = new ArrayList<>();
        this.Outgoing = new ArrayList<>();
        setType();
    }

    public void setType() {
        if(Incoming.isEmpty())
            this.type = Node.NODE_TYPE_ENTRY;
        if(Outgoing.isEmpty())
            this.type = Node.NODE_TYPE_EXIT;
        if(!Incoming.isEmpty() && !Outgoing.isEmpty())
            this.type = Node.NODE_TYPE_REGULAR;
    }
    
    /**
    * method adds new connection to incoming list
    */
    public void addToIncoming(Connection connection){
        this.Incoming.add(connection);
    }
    
    /**
    * method adds new connection to outgoing list
    */
    public void addToOutgoing(Connection connection){
        this.Outgoing.add(connection);
    }

    public int getId() {
        return node_id;
    }
    public java.lang.String getName() {
        return name;
    }
    public String getType(){
        return this.type;
    }
    public ArrayList<Connection> getIncoming() {
        return this.Incoming;
    }
    public ArrayList<Connection> getOutgoing() {
        return this.Outgoing;
    }
    //    public void readIncoming(int n) {
//                Scanner s = new Scanner(System.in);
//                for (int i = 0; i < n ; i++) {
//                    Node pom = new Node();
//                    Connection con = new Connection();
//
//                    con.setTo(this);                                //następnikiem jest this
//                    System.out.println("node_id poprzednika:");          //poprzednika trzeba podać
//                    pom.setId(s.nextInt());
//                    con.setFrom(pom);
//
//                    System.out.println("koszt połaczenia:");
//                    con.setValue(s.nextInt());
//                    Incoming.add(con);
//                }
//                setType();
//            }
//    public void readOutgoing(int n) {
//        Scanner s = new Scanner(System.in);
//        for (int i = 0; i < n ; i++) {
//            Node pom = new Node();
//            Connection con = new Connection();
//
//            con.setFrom(this);                               //poprzednikiem jest this
//            System.out.println("node_id nastepnika:");            //następnika trzeba podać
//            pom.setId(s.nextInt());
//            con.setTo(pom);
//
//            System.out.println("koszt połaczenia:");
//            con.setValue(s.nextInt());
//            Outgoing.add(con);
//        }
//    }
}
