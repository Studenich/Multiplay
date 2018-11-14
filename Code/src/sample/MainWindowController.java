package sample;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class MainWindowController {

    private FileChooser fileChooser;
    private Stage mainStage;
    private ArrayList<TrackWidgetController> audioTracks = new ArrayList<TrackWidgetController>();
    private int count = 0;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button addButton;

    @FXML
    private Button playButton;

    @FXML
    private Button pauseButton;

    @FXML
    private Button stopButton;

    @FXML
    private HBox trackHBox;

    @FXML
    void initialize() {
        fileChooser = new FileChooser();




        addButton.setOnAction(event -> {
            System.out.println("Add button clicked!");

            List<File> files = fileChooser.showOpenMultipleDialog(mainStage);
            files.forEach(file -> {
                System.out.println(file);

                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/sample/TrackWidget.fxml"));
                try {
                    loader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Parent track = loader.getRoot();
                trackHBox.getChildren().addAll(track);

                TrackWidgetController trackWidgetController = loader.getController();
                trackWidgetController.setMainWindow(this);
                trackWidgetController.initPlayer(file);
                trackWidgetController.setName(file);
                audioTracks.add(trackWidgetController);
            });

            System.out.println(audioTracks.size());
        });

    }

    void test() {
        System.out.println("Main Test");
    }

    void setMainStage(Stage stage) {
        this.mainStage = stage;
    }
}
