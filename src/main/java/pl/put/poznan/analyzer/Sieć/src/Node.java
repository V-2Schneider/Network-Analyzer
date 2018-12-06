package pl.put.poznan.analyzer.Sieć.src;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Node implements Serializable {

    private static final long serialVersionUID = 1L;
    private int id;
    private String name;
    private TypeOfNode typeOfNode;
    private ArrayList<Connection> Incoming = new ArrayList<>();
    private ArrayList<Connection> Outgoing = new ArrayList<>();

    public Node(int id) {
        this.id = id;
        this.Incoming = new ArrayList<>();
        this.Outgoing = new ArrayList<>();
        setName();
        setType();
    }

    public Node() {
        this.Incoming = new ArrayList<>();
        this.Outgoing = new ArrayList<>();
    }

    public void setId(int id) {
        this.id=id;
    }

    public void setName() {
        this.name = "node_"+this.id;
    }

    public void setType() {
        if(Incoming.isEmpty())
            this.typeOfNode = TypeOfNode.entry;
        else if(Outgoing.isEmpty())
            this.typeOfNode = TypeOfNode.exit;
        else
            this.typeOfNode = TypeOfNode.regular;
    }

    public void addToIncoming(Connection connection){
        this.Incoming.add(connection);
    }

    public void addToOutgoing(Connection connection){
        this.Outgoing.add(connection);
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

    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public TypeOfNode getTypeOfNode(){
        return this.typeOfNode;
    }
    public ArrayList getIncoming() {
        if(Incoming.isEmpty()){
            System.out.println("Nie ma wchodzących");
            return null;
        }
        else {
            System.out.println("Wchodzace");
            for (Connection aIncoming : Incoming) {

                System.out.println("Z " + aIncoming.getFrom() + " do " + aIncoming.getTo());
                System.out.println("Koszt polaczenia " + aIncoming.getValue());
            }
            return Incoming;
        }
    }
    public void getOutgoing() {
        if(Outgoing.isEmpty())
            System.out.println("Nie ma wychodzących");
        else {
            System.out.println("Wychodzace");
            for (Connection aOutgoing : Outgoing) {
                System.out.println("Z " + aOutgoing.getFrom() + " do " + aOutgoing.getTo());
                System.out.println("Koszt polaczenia " + aOutgoing.getValue());
            }
        }
    }
}
