package pl.put.poznan.analyzer.logic;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public  class DataBase {

    static Logger log = LoggerFactory.getLogger(Search.class);

    public static boolean getNetworkFromDatabase (int networkNumber, Graph graph){

        try{
            log.info("rozpoczęcie łączenia z bazą");
          //  Class.forName("com.mysql.jdbc.Driver");
            java.sql.Connection dbConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/networkanalizer","root","password");
            log.info("Nawiązano połączenie z bazą");
            Statement stmt = dbConnection.createStatement();
            log.info("wysyłanie zapytania o nody");
            ResultSet rs = stmt.executeQuery("SELECT * FROM Node WHERE network = "+networkNumber+"");

            log.info("dodawanie nodów do grafu");
            while(rs.next()){
                Node temp = new Node (rs.getString(2));
                graph.addNode(temp);
            }

            log.info("wysyłanie zapytania o connecty");
            rs = stmt.executeQuery("SELECT * FROM Connection WHERE network = "+networkNumber+"");

            log.info("dodawanie connectów do grafu");
            while(rs.next()){
               graph.addConnection(rs.getInt(1), rs.getInt(2), rs.getInt(3));
            }

            dbConnection.close();
            return true;
        } catch (SQLException e){
            log.debug("" + e);
            return false;
        }// catch (ClassNotFoundException e) {
         //   log.debug("" + e);
         //   e.printStackTrace();
         //   return false;
        //}


    }

    public static boolean UpdateDatabase(int networkNumber, Graph graph){
        try {
            log.info("rozpoczęcie łączenia z bazą");
            java.sql.Connection dbConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/networkanalizer","root","password");
            log.info("Nawiązano połączenie z bazą");
            Statement stmt = dbConnection.createStatement();

            int changes = stmt.executeUpdate("DELETE FROM node where network = " + networkNumber + "");
            changes = stmt.executeUpdate("DELETE FROM connection where network = " + networkNumber + "");
            log.info("Usunięto poprzednie dane");

            for(Map.Entry<Integer,Node> h :graph.getMapOfNodes().entrySet()){
                Node value = h.getValue();
                stmt.executeUpdate("INSERT INTO Node (node_id, name, network) VALUES ("+value.getId()+",'"+value.getName()+"',"+networkNumber+")");
            }
            log.info("dodano nody do bazy");
            for(Map.Entry<Integer,Connection> h :graph.getMapOfConnections().entrySet()){
                Connection value = h.getValue();
                stmt.executeUpdate("INSERT INTO connection (id_from, id_to, value, network) VALUES ("+value.getFrom().getId()+","+value.getTo().getId()+","+value.getValue()+","+networkNumber+")");
            }
            log.info("dodano connecty do bazy");
            return true;
        } catch (SQLException e){
            log.debug("" + e);
            return false;
        }
    }

}
