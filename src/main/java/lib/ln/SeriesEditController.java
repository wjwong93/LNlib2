package lib.ln;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.Stage;
import lib.ln.model.Series;

import java.net.URL;
import java.util.ResourceBundle;

public class SeriesEditController {
    private Series series;
    private BookListController bookListController;

    @FXML
    private JFXTextField seriesName;

    @FXML
    private JFXTextField authorName;

    @FXML
    private JFXTextField illustratorName;

    @FXML
    private JFXButton saveButton;

    @FXML
    private JFXButton cancelButton;

    public SeriesEditController() {
    }

    public void setData(BookListController bookListController) {
        this.bookListController = bookListController;
        this.series = bookListController.getSeries();

        seriesName.setText(series.getName());
        authorName.setText(series.getAuthor());
        illustratorName.setText(series.getIllustrator());
    }

    @FXML
    void cancel(ActionEvent event) {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    void save(ActionEvent event) {
        series.setName(seriesName.getText());
        series.setAuthor(authorName.getText());
        series.setIllustrator(illustratorName.getText());

        bookListController.updateInfo();

        Stage stage = (Stage) saveButton.getScene().getWindow();
        stage.close();
    }
}
