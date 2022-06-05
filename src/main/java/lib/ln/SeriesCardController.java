package lib.ln;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import lib.ln.model.Series;

import java.io.IOException;

public class SeriesCardController {

    private Series series;

    private Parent scene;

    private SeriesListController parentController;

    @FXML
    private HBox card;

    @FXML
    private ImageView cover;

    @FXML
    private Label seriesName;

    @FXML
    private Label authorName;

    @FXML
    private Label illustratorName;

    @FXML
    private Label bookCount;

    @FXML
    private Button editButton;

    @FXML
    private Button deleteButton;

    public void setData(Series series, SeriesListController parentController) {
        this.series = series;
        this.parentController = parentController;

        seriesName.setText(series.getName());
        authorName.setText(series.getAuthor());
        illustratorName.setText(series.getIllustrator());
        bookCount.setText(String.valueOf(series.getBooks().size()));

        if (series.getBooks().size() > 0) {
            Image image = new Image(series.getBooks().get(0).getBookCoverSrc());
            cover.setImage(image);
        }


    }

    public Parent getScene() {
        return scene;
    }

    @FXML
    void confirmDelete(ActionEvent event) {

    }

    @FXML
    void editSeries(ActionEvent event) {

    }

    @FXML
    void expandImage(MouseEvent event) {

    }

    @FXML
    void openSeries(MouseEvent event) {
        parentController.loading();

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("bookList.fxml"));
            scene = fxmlLoader.load();
            BookListController bookListController = fxmlLoader.getController();
            bookListController.setData(series);
            bookListController.setParentController(parentController);

        } catch (IOException e) {
            e.printStackTrace();
        }

        parentController.showScene(scene);
    }

}


