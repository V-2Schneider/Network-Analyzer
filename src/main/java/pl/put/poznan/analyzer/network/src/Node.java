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

    private int id;
    private String name;
    private String typeOfNode;
    private ArrayList<Connection> Incoming;
    private ArrayList<Connection> Outgoing;

    private static int nextInSequence(){
        return sequence.incrementAndGet();
    }

    @Override
    public String toString() {
        return "node_id_" + id + "_name_" + name + "_type_" + typeOfNode;
    }

    public Node(String name) {
        this.name = name;
        this.id = nextInSequence();
        this.Incoming = new ArrayList<>();
        this.Outgoing = new ArrayList<>();
        setType();
    }

    public Node(int id, String name, String typeOfNode) {
        this.id = id;
        this.name = name;
        this.typeOfNode = typeOfNode;
        this.Incoming = new ArrayList<>();
        this.Outgoing = new ArrayList<>();
        setType();
    }

    public void setType() {
        if(Incoming.isEmpty())
            this.typeOfNode = Node.NODE_TYPE_ENTRY;
        if(Outgoing.isEmpty())
            this.typeOfNode = Node.NODE_TYPE_EXIT;
        if(!Incoming.isEmpty() && !Outgoing.isEmpty())
            this.typeOfNode = Node.NODE_TYPE_REGULAR;
    }

    public void addToIncoming(Connection connection){
        this.Incoming.add(connection);
    }

    public void addToOutgoing(Connection connection){
        this.Outgoing.add(connection);
    }

    public int getId() {
        return id;
    }
    public java.lang.String getName() {
        return name;
    }
    public String getTypeOfNode(){
        return this.typeOfNode;
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
//                    System.out.println("id poprzednika:");          //poprzednika trzeba podać
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
//            System.out.println("id nastepnika:");            //następnika trzeba podać
//            pom.setId(s.nextInt());
//            con.setTo(pom);
//
//            System.out.println("koszt połaczenia:");
//            con.setValue(s.nextInt());
//            Outgoing.add(con);
//        }
//    }
}
