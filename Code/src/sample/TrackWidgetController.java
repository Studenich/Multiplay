package sample;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class TrackWidgetController {

    MainWindowController mainWindow;
    MediaPlayer player;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane trackPane;

    @FXML
    private Label trackNameLabel;

    @FXML
    private Button soloButton;

    @FXML
    private Button muteButton;

    @FXML
    private Slider panSlider;

    @FXML
    private Slider volumeSlider;

    @FXML
    void initialize() {
        soloButton.setOnAction(event -> {
            mainWindow.test();
        });
    }

    void setMainWindow(MainWindowController mainWindow) {
        this.mainWindow = mainWindow;
    }

    void initPlayer(File audioFile) {
        player = new MediaPlayer(new Media(audioFile.toURI().toString()));
        player.play();
    }

    void setName(File audioFile) {
        String name = audioFile.toString().substring(audioFile.toString().lastIndexOf('\\') + 1,
                                                     audioFile.toString().lastIndexOf('.'));
        trackNameLabel.setText(name);
    }

    void test() {
        System.out.println("Track Test");
    }
}
