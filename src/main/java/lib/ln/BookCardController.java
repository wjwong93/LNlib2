package lib.ln;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import lib.ln.model.Book;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class BookCardController implements Initializable, Controller {

    private Book book;
    private BookListController parentController;

    @FXML
    private ImageView cover;

    @FXML
    private Label title;

    @FXML
    private Label releaseDate;

    @FXML
    private JFXButton inLibrary;

    @FXML
    private JFXButton completed;

    @FXML
    private Button editButton;

    @Override
    public void initialize(URL url, ResourceBundle resources) {
//        this.bookListener = new BookListener() {
//            @Override
//            public void onClickListener() {
//                updateData();
//            }
//        };
    }

    public void setData(Book book, BookListController parentController) {
        this.book = book;
        this.parentController = parentController;

        Image image = new Image(book.getBookCoverSrc());
        cover.setImage(image);
        title.setText(book.getTitle());
        releaseDate.setText(book.printReleaseDate());

        if (book.isInLibrary()) {
            inLibrary.setText("In Library");
            inLibrary.setStyle("-fx-background-color: lawngreen");
        } else {
            inLibrary.setText("Not In Library");
            inLibrary.setStyle("-fx-background-color: firebrick");
        }

        if (book.isCompleted()) {
            completed.setText("Completed");
            completed.setStyle("-fx-background-color: lawngreen");
        } else {
            completed.setText("Not Completed");
            completed.setStyle("-fx-background-color: firebrick");
        }
    }

    Book getBook() {
        return this.book;
    }

    void updateData() {
        Image image = new Image(book.getBookCoverSrc());
        cover.setImage(image);
        title.setText(book.getTitle());
        releaseDate.setText(book.printReleaseDate());

        if (book.isInLibrary()) {
            inLibrary.setText("In Library");
            inLibrary.setStyle("-fx-background-color: lawngreen");
        } else {
            inLibrary.setText("Not In Library");
            inLibrary.setStyle("-fx-background-color: firebrick");
        }

        if (book.isCompleted()) {
            completed.setText("Completed");
            completed.setStyle("-fx-background-color: lawngreen");
        } else {
            completed.setText("Not Completed");
            completed.setStyle("-fx-background-color: firebrick");
        }

        parentController.updateCount();
    }

    @FXML
    void editBook(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("bookEdit.fxml"));
            Parent parent = fxmlLoader.load();
            BookEditController bookEditController = fxmlLoader.getController();
            bookEditController.setData(this);
            Stage stage = new Stage(StageStyle.DECORATED);
            stage.setTitle("Edit Book Information");
            stage.setScene(new Scene(parent));
            stage.setAlwaysOnTop(true);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    void updateCard(MouseEvent event) {

    }

    @FXML
    void confirmDelete(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("deleteBookConfirmation.fxml"));
            Parent parent = fxmlLoader.load();
            BookDeleteController bookDeleteController = fxmlLoader.getController();
            bookDeleteController.setData(this);
            Stage stage = new Stage(StageStyle.UTILITY);
            stage.setTitle("Confirm Delete");
            stage.setScene(new Scene(parent));
            stage.setAlwaysOnTop(true);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void deleteBook() {
        System.out.println("Book " + book.getTitle() + " removed");
        parentController.getSeries().getBooks().remove(book);
        parentController.refreshPage();
        parentController.updateCount();
    }

    @FXML
    void expandImage(MouseEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("expandImage.fxml"));
            Parent parent = fxmlLoader.load();
            ExpandImageController expandImageController = fxmlLoader.getController();
            expandImageController.setImageSrc(book.getBookCoverSrc());
            Stage stage = new Stage();
            stage.setFullScreen(true);
            stage.setFullScreenExitHint("");
            stage.setScene(new Scene(parent));
            stage.setAlwaysOnTop(true);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
