package lib.ln;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class BookDeleteController {
    private BookCardController parentController;

    @FXML
    private Label deleteLabel;

    @FXML
    private JFXButton deleteButton;

    @FXML
    private JFXButton cancelButton;

    public void setData(BookCardController parentController) {
        this.parentController = parentController;
    }

    @FXML
    void cancel(ActionEvent event) {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    void confirmDelete(ActionEvent event) {
        parentController.deleteBook();

        Stage stage = (Stage) deleteButton.getScene().getWindow();
        stage.close();
    }

}
