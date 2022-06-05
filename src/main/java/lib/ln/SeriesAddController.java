package lib.ln;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.stage.Stage;
import lib.ln.model.Series;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class SeriesAddController {
    private Series series;
    private SeriesListController parentController;

    @FXML
    private JFXTextField seriesName;

    @FXML
    private JFXTextField authorName;

    @FXML
    private JFXTextField illustratorName;

    @FXML
    private JFXTextField fileName;

    @FXML
    private JFXButton addButton;

    @FXML
    private JFXButton cancelButton;

    @FXML
    void cancel(ActionEvent event) {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    public void setParentController(SeriesListController parentController) {
        this.parentController = parentController;
    }

    @FXML
    void addSeries(ActionEvent event) {
        String name = seriesName.getText().isBlank() ? "No Name" : seriesName.getText();
        String author = authorName.getText().isBlank() ? "-" : authorName.getText();
        String illustrator = illustratorName.getText().isBlank() ? "-" : illustratorName.getText();
        String file = fileName.getText().isBlank() ? "newseries.csv" : fileName.getText()+".csv";

        try (BufferedWriter out = new BufferedWriter(new FileWriter("lib.txt", true))) {
            out.write(file);
            out.newLine();
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

        series = new Series(name, author, illustrator, file);

        parentController.showNewSeries(series);

        Stage stage = (Stage) addButton.getScene().getWindow();
        stage.close();
    }
}
