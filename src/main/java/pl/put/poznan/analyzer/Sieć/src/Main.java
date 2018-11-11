/*

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static List<Node> nudesy = new ArrayList<>();
    public static int p,ent,outt, val, kosztd, kosztb;

    public static void main(String[] args) throws IOException {
        System.out.println("Elo robię sieć");
        Scanner s = new Scanner(System.in);
            System.out.println("Gimme ilość nodes");
            p = s.nextInt();
            final int v = p;
            System.out.println("Maksymalny koszt");
            val = s.nextInt();
            final int value = val;
            Graph graph = new Graph(v, value);
            for (int i = 1; i <= p; i++) {
                System.out.println("Oto jest nudes nr " + i);
                Node n = new Node();
                n.setId(i);
                n.setName();
                System.out.println("Gimme ilość wchodzace");
                ent = s.nextInt();
                if (ent != 0) {
                    n.setIncoming(ent);
                }
                System.out.println("Gimme ilość wychodzace");
                outt = s.nextInt();
                if (outt != 0) {
                    //Scanner s = new Scanner(System.in);
                    for (int j = 0; j < outt ; j++) {
                        int pom;
                        Connection con = new Connection();
                        System.out.println("Gimme node nastepnik");
                        pom = s.nextInt();
                        //adjList.get(n.getId()).add(pom);
                        //graph.addEdge(n.getId()-1, pom-1);
                        int source = n.getId()-1;
                        int dest = pom-1;
                        con.setTo(pom);
                        con.setFrom(n.getId());
                        System.out.println("Gimme koszt");
                        pom = s.nextInt();
                        int d = source;
                        if(pom>1){
                            for(int m=1; m< pom; m++){
                                graph.addEdge(d, d+v-1);
                                d=d+v-1;
                            }
                        }
                        graph.addEdge(d, dest);
                        con.setValue(pom);
                        n.setOutgoing(con);
                    }
                }
                n.setType();
                nudesy.add(n);


            }


            for (int i = 0; i < nudesy.size(); i++) {
                System.out.println("Oto jest " + nudesy.get(i).getName());
                nudesy.get(i).getIncoming();
                nudesy.get(i).getOutgoing();
                nudesy.get(i).getType();
            }
            
            //System.out.println("\n\nGraf skierowany: " + graph);
            
            System.out.println("DFS");  
            DFS dfs3 = new DFS(graph, (1-1)); //nudes 2
            for (int it : dfs3.getPathTo(3-1)) { //nudes 3
                 kosztd++;                                
                   if (it < (v - 1)) {                      
                    System.out.print((it + 1) + " ");}   
                   }                                        
            System.out.println("Koszt DFS " + (kosztd - 1));  

            System.out.println("BFS"); 
            BFS bfs3 = new BFS(graph, (1-1)); //nudes 1
            for (int it : bfs3.getPathTo((3-1))) { //nudes 3
                kosztb++
                if(it<(v-1)){
                System.out.print((it+1) + " ");}
             }
             System.out.println("Koszt BFS " + (kosztb-1));  


            }

    }
*/

