package pl.mwgrogowo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
public class Main extends Application {

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
    pigeonDatabaseRepository.startConnection();
    PigeonService pigeonService = new PigeonService(pigeonDatabaseRepository);
    controller.setService(pigeonService);
    launch(args);
  }
}
