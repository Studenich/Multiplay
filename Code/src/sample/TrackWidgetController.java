
package sample;

import java.io.File;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

/**
 * Класс контроллера аудиотрека.
 */
public class TrackWidgetController {

    /**
     * Контроллер главного окна.
     * Необходим для передачи данных в класс главного окна.
     */
    private MainWindowController mainWindow;
    /**
     * Плеер аудиозаписей.
     * Управление параметрами звука.
     */
    private MediaPlayer player;
    /**
     * Номер аудиотрека.
     */
    private int number;
    /**
     * Флаг заглушения.
     *
     * 0 - Аудиозапись заглушена.
     * 1 - Аудиозапись не заглушена.
     */
    private int isNotMuted = 1;
    /**
     * Флаг солирования.
     *
     * 0 - Аудиозапись солирована.
     * 1 - Аудиозапись не солирована.
     */
    private int isNotSolo = 1;

    /**
     * Название аудиофайла.
     */
    @FXML
    private Label trackNameLabel;

    /**
     * Кнопка солирования.
     */
    @FXML
    private Button soloButton;

    /**
     * Кнопка заглушения.
     */
    @FXML
    private Button muteButton;

    /**
     * Слайдер панорамирования.
     */
    @FXML
    private Slider panSlider;

    /**
     * Слайдер громкости.
     */
    @FXML
    private Slider volumeSlider;

    /**
     * Значение громкости аудиозаписи.
     */
    @FXML
    private Label volumeLabel;

    /**
     * Значение панорамы аудиозаписи.
     */
    @FXML
    private Label panLabel;

    /**
     * Инициализация класса.
     */
    @FXML
    void initialize() {
        volumeSlider.getStylesheets().add("sample/GUI/Styles.css");
        volumeSlider.setId("mySlider");
        panSlider.setId("myPanSlider");

        volumeSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            player.setVolume(newValue.doubleValue() / 100 * isNotMuted);
            volumeLabel.setText("V: " + (int) (player.getVolume() * 100) + "%");

        });

        panSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            player.setBalance(newValue.doubleValue() / 50 - 1);
            panLabel.setText(String.format("P: %.2f",  player.getBalance()));
        });

        soloButton.setOnAction(event -> {
            mainWindow.soloTrack(number);
            if (isNotSolo == 1) {
                isNotSolo = 0;
                soloButton.setStyle("-fx-background-color: #e8e03b");
            } else {
                isNotSolo = 1;
                soloButton.setStyle("-fx-background-color: #ebebeb");
            }
        });

        muteButton.setOnAction(event -> {
            if (isNotMuted == 1) {
                player.setVolume(0);
                isNotMuted = 0;
                muteButton.setStyle("-fx-background-color: #f73838");
            } else {
                player.setVolume(volumeSlider.getValue() / 100);
                isNotMuted = 1;
                muteButton.setStyle("-fx-background-color: #ebebeb");
            }
        });
    }

    /**
     * Установка контроллера главного окна.
     *
     * @param mainWindowController Контроллер главного окна.
     */
    void setMainWindow(final MainWindowController mainWindowController) {
        this.mainWindow = mainWindowController;
    }

    /**
     * Установка номера аудиотрека.
     *
     * @param trackNumber Номер аудиотрека.
     */
    void setNumber(final int trackNumber) {
        this.number = trackNumber;
    }

    /**
     * Инициализация плеера аудиозаписи.
     *
     * @param audioFile Аудиофайл.
     */
    void initPlayer(final File audioFile) {
        player = new MediaPlayer(new Media(audioFile.toURI().toString()));
        player.setVolume(0.8);
    }

    /**
     * Установка названия аудиотрека.
     *
     * @param audioFile Аудиофайл.
     */
    void setName(final File audioFile) {
        String name = audioFile.toString().substring(audioFile.toString().lastIndexOf('\\') + 1,
                                                     audioFile.toString().lastIndexOf('.'));
        trackNameLabel.setText(name);
    }

    /**
     * Воспроизведение.
     */
    void play() {
        player.play();
    }

    /**
     * Пауза.
     */
    void pause() {
        player.pause();
    }

    /**
     * Остановка воспроизведени.
     */
    void stop() {
        player.stop();
    }

    /**
     * Заглушение.
     */
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

    /**
     * Снятие заглушения.
     */
    void unMute() {
        if (isNotMuted == 0) {
            player.setVolume(volumeSlider.getValue()/100);
            isNotMuted = 1;
            muteButton.setStyle("-fx-background-color: #ebebeb");
        }
    }

    /**
     * Проверка, солирован ли трек.
     *
     * @return true - солирован, false - не солирован.
     */
    boolean isSolo() {
        return isNotSolo == 0 ? true : false;
    }
}
