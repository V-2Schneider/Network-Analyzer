package pl.put.poznan.analyzer.network.src;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class Node implements Serializable {

    private static final long serialVersionUID = 1L;
    private static AtomicInteger sequence   = new AtomicInteger(0);
    private static java.lang.String NODE_TYPE_REGULAR = "regular";
    private static java.lang.String NODE_TYPE_ENTRY   = "entry";
    private static java.lang.String NODE_TYPE_EXIT    = "exit";

    private int node_id;
    private String name;
    private String type;
    private ArrayList<Connection> Incoming;
    private ArrayList<Connection> Outgoing;

    private static int nextInSequence(){
        return sequence.incrementAndGet();
    }

    @Override
    public String toString() {
        return "node_id_" + node_id + "_name_" + name + "_type_" + type;
    }

    public Node(String name) {
        this.name = name;
        this.node_id = nextInSequence();
        this.Incoming = new ArrayList<>();
        this.Outgoing = new ArrayList<>();
        setType();
    }

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

    public void addToIncoming(Connection connection){
        this.Incoming.add(connection);
    }

    public void addToOutgoing(Connection connection){
        this.Outgoing.add(connection);
    }

    public int getNode_id() {
        return node_id;
    }
    public java.lang.String getName() {
        return name;
    }
    public String getType(){
        return this.type;
    }
    public ArrayList getIncoming() {
        return Incoming;
    }
    public ArrayList getOutgoing() {
        return Outgoing;
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
