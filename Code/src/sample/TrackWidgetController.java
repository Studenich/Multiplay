package sample;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.AnchorPane;

public class TrackWidgetController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane TrackPane;

    @FXML
    private Label TrackNameLabel;

    @FXML
    private Button SoloButton;

    @FXML
    private Button MuteButton;

    @FXML
    private Slider PanSlider;

    @FXML
    private Slider VolumeSlider;

    @FXML
    void initialize() {

    }

    void test() {
        System.out.println("Test");
    }
}
