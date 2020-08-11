package pl.mwgrogowo;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

public class Main extends Application {

  private List<String> pigeons;
  private List<String> equalsPigeons;
  private List<String> pigeonsFound;

  @Override
  public void start(Stage primaryStage) throws Exception {
    Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("sample.fxml"));
    primaryStage.setTitle("Pigeon compare");
    primaryStage.setScene(new Scene(root, 1000, 600));
    primaryStage.show();

    pigeons = new ArrayList<>();
    equalsPigeons = new ArrayList<>();
    pigeonsFound = new ArrayList<>();

    ListView<String> listEqualsPigeons = (ListView<String>) root.lookup("#pigeonsEquals");
    ListView<String> listView = (ListView<String>) root.lookup("#listView");

    listView.setCellFactory(param -> new PigeonsCell(pigeonsFound));
    //listView.getCellFactory().call(null);
    Label labelPigeonCount = (Label) root.lookup("#pigeonCount");
    Label labelPigeonsFound = (Label) root.lookup("#pigeonsFound");
    TextArea textArea = (TextArea) root.lookup("#textArea");
    Button btnCompare = (Button) root.lookup("#btnCompare");
    btnCompare.setAlignment(Pos.CENTER);
    btnCompare.setOnAction(event -> {
      equalsPigeons.clear();

      BufferedReader bufferedReader = new BufferedReader(new StringReader(textArea.getText()));
      String line;
      int linesCount = 0;
      try {
        line = bufferedReader.readLine();
        while ((line = bufferedReader.readLine()) != null) {
          String tmp = line.substring(29);
          int tabIndex = tmp.indexOf("\t");
          String finalString = tmp.substring(0, tabIndex);
          System.out.println(finalString);
          for (int i = 0; i < pigeons.size(); i++) {
            if (pigeons.get(i).equals(finalString)) {
              equalsPigeons.add(finalString);
              pigeonsFound.add(finalString);
              listView.refresh();
              break;
            }
          }
          linesCount++;
        }
        labelPigeonCount.setText(String.valueOf(linesCount));
        labelPigeonsFound.setText(String.valueOf(equalsPigeons.size()));

        ObservableList<String> observableListEquals = FXCollections.observableArrayList(equalsPigeons);
        listEqualsPigeons.setItems(observableListEquals);

        textArea.selectAll();
        textArea.requestFocus();
      } catch (IOException ex) {
        System.out.println(ex.getMessage());
      }

    });

    try {
      File file = new File("C:\\pigeons.txt");
      BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8));

      String st;
      while ((st = br.readLine()) != null) {
        pigeons.add(st);
      }
      ObservableList<String> observableList = FXCollections.observableArrayList(pigeons);
      listView.setItems(observableList);

    } catch (IOException ex) {
      System.err.println(ex.getMessage());
    }

  }


  public static void main(String[] args) {
    launch(args);
  }
}
