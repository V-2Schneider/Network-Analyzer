package pl.put.poznan.analyzer.app;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;

import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import pl.put.poznan.analyzer.logic.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;


public class Controller {

    @FXML
    private Button editRecordButton;
    @FXML
    private ListView<String> searchList = new ListView<>();
    @FXML
    private TextField filterField;
    @FXML
    private ScrollPane infoField;
    @FXML
    private TextFlow infoTextFlow;
    @FXML
    private Text infoText = new Text();
    @FXML
    private TextField addNode;
    @FXML
    private ComboBox nodeto;
    @FXML
    private TextField nodevalue;
    @FXML
    private ComboBox nodefrom;
    @FXML
    private ComboBox start;
    @FXML
    private ComboBox end;

    static Logger log = LoggerFactory.getLogger(Search.class);

    public static List<Node> nudesy = new ArrayList<>();

    Graph graph = new Graph();

    private NetworkAnalyzer networkAnalyzer;


    private String presentedType;

    @FXML
    private void handleAddEmployeesButton(ActionEvent event) throws IOException {
        System.out.println("addConnectionButton!");
        String value = nodevalue.textProperty().getValue();
        int from = 0;
        int to = 0;

        try{
            if(!value.equals("") && nodefrom.getValue() != null && nodeto.getValue() != null){
               if(nodefrom.getValue().toString() != nodeto.getValue().toString()){
                    for (int i = 0; i < nudesy.size(); i++){
                        if( nudesy.get(i).getName() == nodefrom.getValue().toString()){
                            from = nudesy.get(i).getId();
                        }
                        if(nudesy.get(i).getName() == nodeto.getValue().toString()){
                            to = nudesy.get(i).getId();
                        }
                    }
                    int valuee = Integer.parseInt(value);
                    graph.addConnection(from,to,valuee);

                   start.getItems().removeAll(start.getItems());
                   end.getItems().removeAll(end.getItems());

                   for (int i = 0; i < nudesy.size(); i++){
                       if(nudesy.get(i).getType() == "entry"){
                           start.getItems().addAll(nudesy.get(i).getName());
                       }
                       if(nudesy.get(i).getType() == "exit"){
                           end.getItems().addAll(nudesy.get(i).getName());
                       }

                   }

                    System.out.println("Dodano połączenie");
                }
                else{
                    System.out.println("Krawedz do siebie!");
                }
            }
            else{
                System.out.println("Puste pole!");
            }
        }catch(Exception e){
            System.out.println("Niepoprawnie wypełnione pola!");
        }

        nodevalue.clear();


    }



    @FXML
    private void handleAddClientsButton(ActionEvent event) throws IOException {
        System.out.println("addNodesButton!");
        String editedName = addNode.textProperty().getValue();
        try{
            if(!editedName.equals("")){
                Node node = new Node(editedName);
                graph.addNode(node);
                nudesy.add(node);
                nodefrom.getItems().addAll(node.getName());
                nodeto.getItems().addAll(node.getName());
                System.out.println("Dodano wierzchołek");

                start.getItems().removeAll(start.getItems());
                end.getItems().removeAll(end.getItems());

                for (int i = 0; i < nudesy.size(); i++){
                    if(nudesy.get(i).getType() == "entry"){
                        start.getItems().addAll(nudesy.get(i).getName());
                    }
                    if(nudesy.get(i).getType() == "exit"){
                        end.getItems().addAll(nudesy.get(i).getName());
                    }

                }

            }
            else{
                System.out.println("Puste pole! Proszę nadać wierzchołkowi nazwe.");
            }
        }catch(Exception e){
            System.out.println("Niepoprawnie wypełnione pola!");
        }

        addNode.clear();

    }

    @FXML
    private void handlebfsButton(ActionEvent event) throws IOException {
        System.out.println("bfsButton!");

        int from = 0;
        int to = 0;

        try{
            if( start.getValue() != null && end.getValue() != null){
                if(start.getValue().toString() != end.getValue().toString()){
                    for (int i = 0; i < nudesy.size(); i++){
                        if( nudesy.get(i).getName() == start.getValue().toString()){
                            from = nudesy.get(i).getId();
                        }
                        if(nudesy.get(i).getName() == end.getValue().toString()){
                            to = nudesy.get(i).getId();
                        }
                    }

                    PathResult szukanie = Search.BFS(from,to,graph.getMapOfNodes());
                    float sum =0;
                    sum= szukanie.getValue();
                    //sum = szukaniee.getValue();

                    System.out.println(sum);



                }
                else{
                    System.out.println("Sciezka do siebie!");
                }
            }
            else{
                System.out.println("Puste pole!");
            }
        }catch(Exception e){
            System.out.println("Niepoprawnie wypełnione pola!");
        }

    }

    @FXML
    private void handledfsButton(ActionEvent event) throws IOException {
        System.out.println("dfsButton!");

        int from = 0;
        int to = 0;

        try{
            if( start.getValue() != null && end.getValue() != null){
                if(start.getValue().toString() != end.getValue().toString()){
                    for (int i = 0; i < nudesy.size(); i++){
                        if( nudesy.get(i).getName() == start.getValue().toString()){
                            from = nudesy.get(i).getId();
                        }
                        if(nudesy.get(i).getName() == end.getValue().toString()){
                            to = nudesy.get(i).getId();
                        }
                    }

                    PathResult szukanie = Search.DFS(from,to,graph.getMapOfNodes());
                    float sum =0;
                    sum= szukanie.getValue();
                    //sum = szukaniee.getValue();

                    System.out.println(sum);
                }
                else{
                    System.out.println("Sciezka do siebie!");
                }
            }
            else{
                System.out.println("Puste pole!");
            }
        }catch(Exception e){
            System.out.println("Niepoprawnie wypełnione pola!");
        }

    }

    @FXML
    private void handlegreedyButton(ActionEvent event) throws IOException {
        System.out.println("greedyButton!");

        int from = 0;
        int to = 0;

        try{
            if( start.getValue() != null && end.getValue() != null){
                if(start.getValue().toString() != end.getValue().toString()){
                    for (int i = 0; i < nudesy.size(); i++){
                        if( nudesy.get(i).getName() == start.getValue().toString()){
                            from = nudesy.get(i).getId();
                        }
                        if(nudesy.get(i).getName() == end.getValue().toString()){
                            to = nudesy.get(i).getId();
                        }
                    }

                    PathResult szukanie = Search.GreedySeach(1,4,graph.getMapOfNodes());
                    float sum =0;
                    sum= szukanie.getValue();
                    //sum = szukaniee.getValue();

                    System.out.println(sum);
                }
                else{
                    System.out.println("Sciezka do siebie!");
                }
            }
            else{
                System.out.println("Puste pole!");
            }
        }catch(Exception e){
            System.out.println("Niepoprawnie wypełnione pola!");
        }

    }




    @FXML
    private void initialize() {
        for (int i = 0; i < nudesy.size(); i++){
            nodefrom.getItems().addAll(nudesy.get(i).getName());
            nodeto.getItems().addAll(nudesy.get(i).getName());

        }
       /* // Initialize the person table
        idColumn.setCellValueFactory(
                new PropertyValueFactory<RecordToShow, String>("id"));
        dataColumn.setCellValueFactory(
                new PropertyValueFactory<RecordToShow, String>("data"));
        recordsTable.setPlaceholder(new Label("Nie wybrano żadnych rekordów"));
        // Add filtered data to the table
        recordsTable.setItems(filteredData);
        // Listen for text changes in the filter text field
        filterField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable,
                                String oldValue, String newValue) {
                updateFilteredData();
            }
        });
        dataToShow.addListener(new ListChangeListener<RecordToShow>() {
            @Override
            public void onChanged(ListChangeListener.Change<? extends RecordToShow> change) {
                updateFilteredData();
            }
        });

        recordsTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                if (presentedType.equals("Employees")){
                   /* PracownikDAO prdao = new PracownikDAO(cc);
                    List<Pracownik> lista = prdao.getPracownicy();
                    Pracownik tempEmployee = lista.get(dataToShow.indexOf(newSelection));
                    infoText.setText(tempEmployee.toString());
                    prdao.closeStatements();*/
              /*  }
                else if (presentedType.equals("Clients")){
                  /*  KlientDAO prdao = new KlientDAO(cc);
                    List<Klient> lista = prdao.getKlienci();
                    Klient tempClient = lista.get(dataToShow.indexOf(newSelection));
                    infoText.setText(tempClient.toString());
                    prdao.closeStatements();*/
             /*   }

            }
        });*/
    }




}
