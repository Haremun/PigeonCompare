package pl.mwgrogowo;

import java.io.*;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

public class Controller implements Initializable {
  @FXML
  public ListView<String> listPigeonToFind;
  @FXML
  public ListView<String> listBasketPigeons;
  @FXML
  public ListView<String> listPigeonsFound;
  @FXML
  public Button btnLoad;
  @FXML
  public Button btnCompare;
  @FXML
  public Label labelPigeonsFound;
  @FXML
  public Label labelPigeonCount;

  private PigeonService pigeonService;
  private final List<String> allPigeonsFound = new ArrayList<>();
  private final List<String> pigeonsFoundInCurrentBasket = new ArrayList<>();
  private List<String> pigeonsToFind = new ArrayList<>();
  private List<PigeonDto> pigeonDtos = new ArrayList<>();

  public void setService(PigeonService pigeonService) {
    this.pigeonService = pigeonService;
  }

  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
    listPigeonToFind.setCellFactory(param -> new PigeonsCell(allPigeonsFound));
    pigeonsToFind = loadPigeons();
    ObservableList<String> observableList = FXCollections.observableArrayList(pigeonsToFind);
    listPigeonToFind.setItems(observableList);
    btnCompare.setOnAction(this::onCompareButtonClick);
    btnLoad.setOnAction(this::onLoadButtonCLick);
  }

  @FXML
  private void onLoadButtonCLick(ActionEvent event) {
    try {
      pigeonDtos = pigeonService.getPigeonInBasket();
      ObservableList<String> observableListEquals = FXCollections.observableArrayList(
          pigeonDtos.stream()
              .map(PigeonDto::getName)
              .collect(Collectors.toList()));
      listBasketPigeons.setItems(observableListEquals);
    } catch (SQLException ex) {
      System.err.println(ex.getMessage());
    }
  }

  @FXML
  private void onCompareButtonClick(ActionEvent event) {
    pigeonsFoundInCurrentBasket.clear();
    pigeonDtos.forEach(pigeonDto ->
        pigeonsToFind.stream()
            .filter(pigeon -> pigeonDto.getName().equals(pigeon))
            .findFirst()
            .ifPresent(pigeonName -> {
              pigeonsFoundInCurrentBasket.add(pigeonDto.toString());
              allPigeonsFound.add(pigeonName);
              listPigeonToFind.refresh();
            }));
    labelPigeonCount.setText(String.valueOf(pigeonDtos.size()));
    labelPigeonsFound.setText(String.valueOf(pigeonsFoundInCurrentBasket.size()));

    ObservableList<String> observableListEquals = FXCollections.observableArrayList(pigeonsFoundInCurrentBasket);
    listPigeonsFound.setItems(observableListEquals);
  }

  private List<String> loadPigeons() {
    try {
      return loadPigeonsFromFile();
    } catch (IOException ex) {
      System.err.println(ex.getMessage());
      return new ArrayList<>();
    }
  }

  private List<String> loadPigeonsFromFile() throws IOException {
    List<String> pigeons = new ArrayList<>();
    File file = new File("C:\\pigeons.txt");
    BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8));

    String st;
    while ((st = br.readLine()) != null) {
      pigeons.add(st);
    }
    return pigeons;
  }
}
