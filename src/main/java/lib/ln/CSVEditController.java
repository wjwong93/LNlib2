package lib.ln;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;

public class CSVEditController implements Initializable {
    private SeriesListController parentController;
    @FXML
    private JFXTextArea textArea;

    @FXML
    private JFXButton saveButton;

    @FXML
    private JFXButton cancelButton;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        StringBuilder libFileContents = new StringBuilder();
        try (BufferedReader in = new BufferedReader(new FileReader("lib.txt"))) {
            String inString;
            while ((inString = in.readLine()) != null) {
                libFileContents.append(inString);
                libFileContents.append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        textArea.setText(libFileContents.toString());
    }

    public void setParentController(SeriesListController parentController) {
        this.parentController = parentController;
    }

    public void setData() {

    }

    @FXML
    void cancel(ActionEvent event) {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    void save(ActionEvent event) {
        try (BufferedWriter out = new BufferedWriter(new FileWriter("lib.txt"))) {
            out.write(textArea.getText().strip());
            out.newLine();
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

        parentController.updatePage();

        Stage stage = (Stage) saveButton.getScene().getWindow();
        stage.close();
    }
}
