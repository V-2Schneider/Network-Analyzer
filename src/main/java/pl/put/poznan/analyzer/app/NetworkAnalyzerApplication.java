package pl.put.poznan.analyzer.app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;
import pl.put.poznan.analyzer.logic.Main;

/**
 * Main class that run the application
 */
@SpringBootApplication
public class NetworkAnalyzerApplication extends Application {

    @Autowired
    private Environment environment;

    /**
     * Main function that starts the application
     * @param args command line arguments
     */
    public static void main(String[] args) {
        SpringApplication.run(NetworkAnalyzerApplication.class, args);
        launch(args);
    }


    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("sample.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 789, 605));
        primaryStage.show();
    }



}
