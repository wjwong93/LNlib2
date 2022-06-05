package lib.ln;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import lib.ln.model.Book;
import lib.ln.model.Series;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class BookListController implements Controller, Initializable {

    private Series series;
    private BookListener bookListener;
    private SeriesListController parentController;

    @FXML
    private VBox bookList;

    @FXML
    private Label seriesName;

    @FXML
    private Label authorName;

    @FXML
    private Label illustratorName;

    @FXML
    private Label inLibCount;

    @FXML
    private Label completeCount;

    @FXML
    private Label back;

    public void setData(Series series) {
        this.series = series;

        try {
            seriesName.setText(series.getName());
            authorName.setText(series.getAuthor());
            illustratorName.setText(series.getIllustrator());

            inLibCount.setText(series.inLibCount() + "/" + series.getBooks().size());
            completeCount.setText(series.completeCount() + "/" + series.getBooks().size());

            for (Book book : series.getBooks()) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("bookCard.fxml"));
                HBox bookCard = fxmlLoader.load();
                BookCardController bookCardController = fxmlLoader.getController();
                bookCardController.setData(book, this);
                bookList.getChildren().add(bookCard);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    Series getSeries() {
        return this.series;
    }

    public void refreshPage() {

        bookList.getChildren().clear();

        try {
            for (Book book : series.getBooks()) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("bookCard.fxml"));
                HBox bookCard = fxmlLoader.load();
                BookCardController bookCardController = fxmlLoader.getController();
                bookCardController.setData(book, this);
                bookList.getChildren().add(bookCard);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void saveChanges(ActionEvent event) {
        series.toFile();
    }

    @FXML
    void editSeriesInfo(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("seriesEdit.fxml"));
            Parent parent = fxmlLoader.load();
            SeriesEditController seriesEditController = fxmlLoader.getController();
            seriesEditController.setData(this);
            Stage stage = new Stage(StageStyle.DECORATED);
            stage.setTitle("Edit Series Information");
            stage.setScene(new Scene(parent));
            stage.setAlwaysOnTop(true);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void updateInfo() {
        seriesName.setText(series.getName());
        authorName.setText(series.getAuthor());
        illustratorName.setText(series.getIllustrator());
    }

    void updateCount() {
        inLibCount.setText(series.inLibCount() + "/" + series.getBooks().size());
        completeCount.setText(series.completeCount() + "/" + series.getBooks().size());
    }

    @FXML
    void addBook(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass(). getResource("bookAdd.fxml"));
            Parent parent = fxmlLoader.load();
            BookAddController bookAddController = fxmlLoader.getController();
            bookAddController.setData(this);
            Stage stage = new Stage(StageStyle.DECORATED);
            stage.setTitle("Add New Book");
            stage.setScene(new Scene(parent));
            stage.setAlwaysOnTop(true);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void backToSeries(MouseEvent event) {
        parentController.back();
    }

    public void setParentController(SeriesListController parentController) {
        this.parentController = parentController;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        back.setCursor(Cursor.HAND);
    }
}