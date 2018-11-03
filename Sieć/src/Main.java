import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static List<Node> nudesy = new ArrayList<>();
    public static int p,ent,outt;

    public static void main(String[] args) throws IOException {
        System.out.println("Elo robię sieć");
        Scanner s = new Scanner(System.in);
            System.out.println("Gimme ilość nodes");
            p = s.nextInt();
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
                    n.setOutgoing(outt);
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




            }

    }


