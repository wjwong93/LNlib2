package lib.ln;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import lib.ln.model.Book;

import java.io.File;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class BookAddController implements Controller, Initializable {

    private Book book;
    private BookListController parentController;

    @FXML
    private ImageView coverImage;

    @FXML
    private JFXTextField bookTitle;

    @FXML
    private JFXDatePicker releaseDate;

    @FXML
    private JFXCheckBox inLibrary;

    @FXML
    private JFXCheckBox completed;

    @FXML
    private JFXButton addButton;

    @FXML
    private JFXButton cancelButton;

    @FXML
    private Button chooseCoverImageButton;

    @FXML
    public void initialize(URL url, ResourceBundle resources) {
        this.releaseDate.setValue(LocalDate.now());
    }

    @FXML
    void add(ActionEvent event) {
        if (bookTitle.getText().equals("")) {
            System.out.println("Info is not filled");
            return;
        }

        if (bookTitle.getText().contains(",")) {
            System.out.println("Book Title cannot contain comma");
            return;
        }

        this.book = new Book(
                bookTitle.getText(),
                releaseDate.getValue().toString(),
                String.valueOf(inLibrary.isSelected()),
                String.valueOf(completed.isSelected()),
                coverImage.getImage().getUrl()
        );

        parentController.getSeries().addBook(book);
        parentController.refreshPage();
        parentController.updateCount();

        Stage stage = (Stage) addButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    void cancel(ActionEvent event) {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    void changeImage(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        Stage stage = (Stage) chooseCoverImageButton.getScene().getWindow();
        fileChooser.setTitle("Choose Cover Image");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("PNG", "*.png"),
                new FileChooser.ExtensionFilter("JPG", "*.jpg")
        );
        File file = fileChooser.showOpenDialog(stage);
        if (file != null) {
            System.out.println(file);

            Image image = new Image(file.toString());
            coverImage.setImage(image);
        }
    }

    public void setData(BookListController parentController) {
        this.parentController = parentController;
    }

}
