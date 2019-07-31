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
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Main extends Application {

    private List<String> pigeons;
    private List<String> equalsPigeons;

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 1000, 600));
        primaryStage.show();

        pigeons = new ArrayList<>();
        equalsPigeons = new ArrayList<>();

        ListView<String> listEqualsPigeons = (ListView<String>) root.lookup("#pigeonsEquals");
        ListView<String> listView = (ListView<String>) root.lookup("#listView");
        
        TextArea textArea = (TextArea) root.lookup("#textArea");
        Button btnCompare = (Button) root.lookup("#btnCompare");
        btnCompare.setAlignment(Pos.CENTER);
        btnCompare.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                equalsPigeons.clear();

                BufferedReader bufferedReader = new BufferedReader(new StringReader(textArea.getText()));
                String line;
                int equalsCount = 0;
                try {
                    while ((line = bufferedReader.readLine()) != null) {
                        for (int i = 0; i < pigeons.size(); i++) {
                            if (pigeons.get(i).equals(line)) {
                                equalsPigeons.add(line);
                                equalsCount++;
                                break;
                            }
                        }
                    }

                    ObservableList<String> observableListEquals = FXCollections.observableArrayList(equalsPigeons);
                    listEqualsPigeons.setItems(observableListEquals);
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                }

            }
        });


        try {
            File file = new File("C:\\pigeons.txt");
            BufferedReader br = new BufferedReader(new FileReader(file));

            String st;
            while ((st = br.readLine()) != null) {
                pigeons.add(st);
            }
            ObservableList<String> observableList = FXCollections.observableArrayList(pigeons);
            listView.setItems(observableList);

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

    }


    public static void main(String[] args) {
        launch(args);
    }
}
