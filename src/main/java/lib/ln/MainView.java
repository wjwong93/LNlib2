package lib.ln;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainView extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("seriesList.fxml"));
        Parent parent = fxmlLoader.load();
        SeriesListController seriesListController = fxmlLoader.getController();
        seriesListController.setMainStage(stage);
        stage.setTitle("Light Novel Library");
        stage.setScene(new Scene(parent));
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}