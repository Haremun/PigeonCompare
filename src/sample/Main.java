package sample;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main extends Application {

    private List<String> pigeons;

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 1000, 600));
        primaryStage.show();

        pigeons = new ArrayList<>();

        ListView listView = (ListView) root.lookup("#listView");
        TextArea textArea = (TextArea) root.lookup("#textArea");
        Button btnCompare = (Button) root.lookup("#btnCompare");
        btnCompare.setAlignment(Pos.CENTER);
        btnCompare.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println(textArea.getText());
            }
        });


        try {
            File file = new File("C:\\pigeons.txt");
            BufferedReader br = new BufferedReader(new FileReader(file));

            String st;
            StringBuilder builder = new StringBuilder();
            while ((st = br.readLine()) != null) {
                builder.append(st);
                builder.append("\n");
                pigeons.add(st);
            }
            ObservableList<String> observableList = FXCollections.observableArrayList(pigeons);
            listView.setItems(observableList);

        } catch (IOException e) {

        }

    }


    public static void main(String[] args) {
        launch(args);
    }
}
