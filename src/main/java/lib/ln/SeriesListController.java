package lib.ln;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import lib.ln.model.Series;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class SeriesListController implements Initializable {

    private Stage mainStage;
    private List<Series> serieses;

    @FXML
    private JFXButton viewCSVsButton;

    @FXML
    private VBox seriesList;

    @FXML
    private Label seriesCount;

    @FXML
    private StackPane root;

    @FXML
    public void initialize(URL url, ResourceBundle resources) {
        serieses = new ArrayList<>();
        updatePage();
    }

    public void setMainStage(Stage mainStage) {
        this.mainStage = mainStage;
    }

    public void updatePage() {
        serieses.clear();
        seriesList.getChildren().clear();

        readLibList();

        for (Series series : serieses) {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("seriesCard.fxml"));
                HBox seriesCard = fxmlLoader.load();
                SeriesCardController seriesCardController = fxmlLoader.getController();
                seriesCardController.setData(series, this);
                seriesList.getChildren().add(seriesCard);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        updateCount();
    }

    @FXML
    void addSeries(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("seriesAdd.fxml"));
            Parent parent = fxmlLoader.load();
            SeriesAddController seriesAddController = fxmlLoader.getController();
            seriesAddController.setParentController(this);
            Stage stage = new Stage();
            stage.setTitle("Add New Series");
            stage.setScene(new Scene(parent));
            stage.setAlwaysOnTop(true);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showNewSeries(Series series) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("seriesCard.fxml"));
            HBox seriesCard = fxmlLoader.load();
            SeriesCardController seriesCardController = fxmlLoader.getController();
            seriesCardController.setData(series, this);
            seriesList.getChildren().add(seriesCard);
            updateCount();
            showScene(seriesCardController.getScene());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void openSeries(Series series) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("bookList.fxml"));
            Parent bookList = fxmlLoader.load();
            BookListController bookListController = fxmlLoader.getController();
            bookListController.setData(series);

            root.getChildren().add(bookList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showScene(Parent scene) {
        root.getChildren().add(scene);
//        mainStage.getScene().setCursor(Cursor.DEFAULT);
    }

    public void loading() {
//        mainStage.getScene().setCursor(Cursor.WAIT);
        System.out.println("Loading");
    }

    public void back() {
        root.getChildren().remove(1);
    }

    public void updateCount() {
        seriesCount.setText(String.valueOf(seriesList.getChildren().size()));
    }

    private void readLibList() {
        try (BufferedReader libList = new BufferedReader(new FileReader("lib.txt"))) {
            String csvFileName;
            while ((csvFileName = libList.readLine()) != null) {
                csvFileName = csvFileName.strip();
                File csvFile = new File(csvFileName);
                if (csvFile.isFile()) {
                    serieses.add(Series.load(csvFileName));
                } else {
                    System.out.println(csvFileName + " not found");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void viewCSVs(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("viewCSV.fxml"));
            Parent parent = fxmlLoader.load();
            CSVEditController csvEditController = fxmlLoader.getController();
            csvEditController.setParentController(this);
            Stage stage = new Stage();
            stage.setTitle("View CSVs");
            stage.setScene(new Scene(parent));
            stage.setAlwaysOnTop(true);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
