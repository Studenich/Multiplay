package sample;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
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
            List<File> files = fileChooser.showOpenMultipleDialog(mainStage);

            files.forEach(file -> {
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
                trackWidgetController.setNumber(audioTracks.size());
                audioTracks.add(trackWidgetController);
            });
        });

        playButton.setOnAction(event -> {
            audioTracks.parallelStream().forEach(track -> {
                track.play();
            });
        });

        pauseButton.setOnAction(event -> {
            audioTracks.parallelStream().forEach(track -> {
                track.pause();
            });
        });

        stopButton.setOnAction(event -> {
            audioTracks.parallelStream().forEach(track -> {
                track.stop();
            });
        });
    }

    void setMainStage(Stage stage) {
        this.mainStage = stage;
    }

    void soloTrack(int number) {
        if (!audioTracks.get(number).isSolo()) {
            audioTracks.parallelStream().forEach(track -> {
                track.mute();
            });
            audioTracks.get(number).unMute();
        }
        else {
            audioTracks.parallelStream().forEach(track -> {
                track.unMute();
            });
        }
    }
}
