package lib.ln;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import lib.ln.model.Book;
import java.io.File;
import java.time.LocalDate;

public class BookEditController implements Controller {

    private Book book;
    private BookCardController bookCardController;

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
    private JFXButton saveButton;

    @FXML
    private JFXButton cancelButton;

    @FXML
    private Button chooseCoverImageButton;

    @FXML
    void save(ActionEvent event) {
        book.setTitle(bookTitle.getText());
        book.setReleaseDate(releaseDate.getValue().toString());
        book.setInLibrary(inLibrary.isSelected());
        book.setCompleted(completed.isSelected());

        bookCardController.updateData();

        Stage stage = (Stage) saveButton.getScene().getWindow();
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
            book.setBookCoverSrc(file.toString());

            Image image = new Image(book.getBookCoverSrc());
            coverImage.setImage(image);
        }
    }

    public void setData(BookCardController bookCardController) {
        this.bookCardController = bookCardController;
        this.book = bookCardController.getBook();

        Image image = new Image(book.getBookCoverSrc());
        coverImage.setImage(image);
        bookTitle.setText(book.getTitle());
        releaseDate.setValue(book.getReleaseDate());
        inLibrary.setSelected(book.isInLibrary());
        completed.setSelected(book.isCompleted());
    }

}
