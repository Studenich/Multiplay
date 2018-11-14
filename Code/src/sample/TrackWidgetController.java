package sample;

import java.io.File;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;


public class TrackWidgetController {

    private MainWindowController mainWindow;
    private MediaPlayer player;
    private int number;
    private int isNotMuted = 1;
    private int isNotSolo = 1;

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

        volumeSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            player.setVolume(newValue.doubleValue()/100 * isNotMuted);
        });

        panSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            player.setBalance(newValue.doubleValue()/50 - 1);
        });

        soloButton.setOnAction(event -> {
            mainWindow.soloTrack(number);
            if (isNotSolo == 1) {
                isNotSolo = 0;
                soloButton.setStyle("-fx-background-color: #e8e03b");
            }
            else {
                isNotSolo = 1;
                soloButton.setStyle("-fx-background-color: #ebebeb");
            }
        });

        muteButton.setOnAction(event -> {
            if (isNotMuted == 1) {
                player.setVolume(0);
                isNotMuted = 0;
                muteButton.setStyle("-fx-background-color: #f73838");
            }
            else {
                player.setVolume(volumeSlider.getValue()/100);
                isNotMuted = 1;
                muteButton.setStyle("-fx-background-color: #ebebeb");
            }
        });
    }

    void setMainWindow(MainWindowController mainWindow) {
        this.mainWindow = mainWindow;
    }

    void setNumber(int number) {
        this.number = number;
    }

    void initPlayer(File audioFile) {
        player = new MediaPlayer(new Media(audioFile.toURI().toString()));
        player.setVolume(0.8);
    }

    void setName(File audioFile) {
        String name = audioFile.toString().substring(audioFile.toString().lastIndexOf('\\') + 1,
                                                     audioFile.toString().lastIndexOf('.'));
        trackNameLabel.setText(name);
    }

    void play() {
        player.play();
    }

    void pause() {
        player.pause();
    }

    void stop() {
        player.stop();
    }

    void mute() {
        if (isNotSolo == 0) {
            isNotSolo = 1;
            soloButton.setStyle("-fx-background-color: #ebebeb");
        }
        if (isNotMuted == 1) {
            player.setVolume(0);
            isNotMuted = 0;
            muteButton.setStyle("-fx-background-color: #f73838");
        }
    }

    void unMute() {
        if (isNotMuted == 0) {
            player.setVolume(volumeSlider.getValue()/100);
            isNotMuted = 1;
            muteButton.setStyle("-fx-background-color: #ebebeb");
        }
    }

    boolean isSolo() {
        if (isNotSolo == 0) return true;
        else return false;
    }
}
