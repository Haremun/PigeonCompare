package pl.mwgrogowo;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
public class Main extends Application {

  private final PigeonService pigeonService = new PigeonService(null);
  private static final Controller controller = new Controller();

  @Override
  public void start(Stage primaryStage) throws Exception {
    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("sample.fxml"));
    fxmlLoader.setController(controller);
    Parent root = fxmlLoader.load();
    primaryStage.setTitle("Pigeon compare");
    primaryStage.setScene(new Scene(root, 1000, 600));
    primaryStage.show();
  }

  public static void main(String[] args) {
    PigeonDatabaseRepository pigeonDatabaseRepository = new PigeonDatabaseRepository();
    //pigeonRepository.startConnection();
    PigeonService pigeonService = new PigeonService(new PigeonInMemoryRepository());
    controller.setService(pigeonService);
    launch(args);
  }
}
