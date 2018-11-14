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

public class MainWindowController {

    final FileChooser fileChooser = new FileChooser();

    ArrayList<TrackWidgetController> audioTracks;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button AddButton;

    @FXML
    private Button PlayButton;

    @FXML
    private Button PauseButton;

    @FXML
    private Button StopButton;

    @FXML
    private HBox trackHBox;

    @FXML
    void initialize() {
        AddButton.setOnAction(event -> {
            System.out.println("Add button clicked!");

            //List<File> files = fileChooser.showOpenMultipleDialog();

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/sample/TrackWidget.fxml"));

            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }

            TrackWidgetController trackWidgetController = loader.getController();
            trackWidgetController.test();
            /*audioTracks.add(trackWidgetController);
            System.out.println(audioTracks.size());
            audioTracks.get(0).test();*/

            Parent track = loader.getRoot();
            trackHBox.getChildren().addAll(track);
        });

    }
}
