package pl.put.poznan.transformer.Sieć.src;

import java.io.Console;
import java.io.IOException;
import java.io.Serializable;
import java.lang.invoke.MethodHandleInfo;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Node implements Serializable {
    public enum type {
        entry, exit, regular
    }
        private static final long serialVersionUID = 1L;
        private int id;
        private String name;
        private Type type;
        private List<Connection> Incoming = new ArrayList<>();
        private List<Connection> Outgoing = new ArrayList<>();

            public void setId(int id){
                this.id=id;
            }

            public void setName(){
                this.name = "node_"+this.id;
            }
            public void setType(){
                if(Incoming.isEmpty())
                    this.type = Type.entry;
                else if(Outgoing.isEmpty())
                    this.type = Type.exit;
                else
                    this.type = Type.regular;
            }
            public void setIncoming(int n) throws IOException {
                Scanner s = new Scanner(System.in);
                for (int i = 0; i < n ; i++) {
                    int pom;
                    Connection con = new Connection();
                    con.setTo(this.id);
                    System.out.println("Gimme node poprzednik");
                    pom = s.nextInt();
                    con.setFrom(pom);
                    System.out.println("Gimme koszt");
                    pom = s.nextInt();
                    con.setValue(pom);
                    Incoming.add(con);
                }
            }
            public void setOutgoing(int n) throws IOException {
                Scanner s = new Scanner(System.in);
                for (int i = 0; i < n ; i++) {
                    int pom;
                    Connection con = new Connection();
                    System.out.println("Gimme node nastepnik");
                    pom = s.nextInt();
                    con.setTo(pom);
                    con.setFrom(this.id);
                    System.out.println("Gimme koszt");
                    pom = s.nextInt();
                    con.setValue(pom);
                    Outgoing.add(con);

                }
            }
             public int getId() {
                return id;
            }
            public String getName() {
                return name;
            }
            public void getType() { System.out.println("Typ nudesa "+this.type); }
            public void getIncoming() {
                if(Incoming.isEmpty())
                System.out.println("Nie ma wchodzących");
                else {
                    System.out.println("Wchodzace");
                    for (int i = 0; i < Incoming.size(); i++) {

                        System.out.println("Z " + Incoming.get(i).getFrom() + " do " + Incoming.get(i).getTo());
                        System.out.println("Koszt polaczenia " + Incoming.get(i).getValue());
                    }
                }
            }
            public void getOutgoing() {
                if(Outgoing.isEmpty())
                    System.out.println("Nie ma wychodzących");
                else {
                    System.out.println("Wychodzace");
                    for (int i = 0; i < Outgoing.size(); i++) {
                        System.out.println("Z " + Outgoing.get(i).getFrom() + " do " + Outgoing.get(i).getTo());
                        System.out.println("Koszt polaczenia " + Outgoing.get(i).getValue());
                    }
                }
             }
}
