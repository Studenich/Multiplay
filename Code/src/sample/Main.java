package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    private MainWindowController mainWindowController;

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader mainLoader = new FXMLLoader(getClass().getResource("MainWindow.fxml"));
        Parent root = mainLoader.load();
        mainWindowController = mainLoader.getController();
        mainWindowController.setMainStage(primaryStage);
        primaryStage.setTitle("Multiplay");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
