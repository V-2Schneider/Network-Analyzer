package pl.put.poznan.analyzer.Sieć.src;

import org.apache.jasper.tagplugins.jstl.core.Out;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

public class Node implements Serializable {

    private static final long serialVersionUID = 1L;
    private static AtomicInteger sequence = new AtomicInteger(0);

    private int id;
    private String name;
    private TypeOfNode typeOfNode;
    private ArrayList<Connection> Incoming;
    private ArrayList<Connection> Outgoing;

    private static int nextInSequence(){
        return sequence.incrementAndGet();
    }

    public Node(String name) {
        this.name = name;
        this.id = nextInSequence();
        this.Incoming = new ArrayList<>();
        this.Outgoing = new ArrayList<>();
        setType();
    }

    public void setType() {
        if(Incoming.isEmpty())
            this.typeOfNode = TypeOfNode.entry;
        if(Outgoing.isEmpty())
            this.typeOfNode = TypeOfNode.exit;
        if(!Incoming.isEmpty() && !Outgoing.isEmpty())
            this.typeOfNode = TypeOfNode.regular;
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
    public String getName() {
        return name;
    }
    public TypeOfNode getTypeOfNode(){
        return this.typeOfNode;
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
