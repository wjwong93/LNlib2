package lib.ln;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lib.ln.model.Series;

import java.io.IOException;

public class BookListView extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("bookList.fxml"));
        Parent parent = fxmlLoader.load();
        BookListController bookListController = fxmlLoader.getController();
        bookListController.setData(Series.load("DAL.csv"));
        stage.setTitle("Light Novel Library");
        stage.setScene(new Scene(parent));
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}