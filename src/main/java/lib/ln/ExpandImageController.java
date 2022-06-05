package lib.ln;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class ExpandImageController {
    @FXML
    private ImageView image;

    public void setImageSrc(String imgSrc) {
        image.setImage(new Image(imgSrc));
    }

    @FXML
    void close(MouseEvent event) {
        Stage stage = (Stage) image.getScene().getWindow();
        stage.close();
    }
}
