
package sample;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * Класс контроллера главного окна.
 * Содержит элементы управления главного окна, а также обработка их событий
 */
public class MainWindowController {

    /**
     * Окно добавления файлов.
     */
    private FileChooser fileChooser;
    /**
     * Главная сцена приложения.
     * Необходима для вызова окна добавления файлов.
     */
    private Stage mainStage;
    /**
     * Список контроллеров всех аудиозаписей.
     */
    private final ArrayList<TrackWidgetController> audioTracks = new ArrayList<>();

    /**
     * Кнопка добавления файлов.
     */
    @FXML
    private Button addButton;

    /**
     * Кнопка воспроизведения аудиозаписей.
     */
    @FXML
    private Button playButton;

    /**
     * Кнопка паузы.
     */
    @FXML
    private Button pauseButton;

    /**
     * Кнопка остановки воспроизведения.
     */
    @FXML
    private Button stopButton;

    /**
     * Скролл-пэйн, в котором располагаются аудиотреки.
     */
    @FXML
    private ScrollPane trackScrollPane;

    /**
     * Контейнер, в котором располагаются аудиотреки.
     */
    @FXML
    private HBox trackHBox;


    /**
     * Инициализация класса.
     */
    @FXML
    void initialize() {
        fileChooser = new FileChooser();
        trackScrollPane.setContent(trackHBox);

        addButton.setOnAction(event -> {
            List<File> files = fileChooser.showOpenMultipleDialog(mainStage);

            files.forEach(file -> {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/sample/GUI/TrackWidget.fxml"));
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

    /**
     * Инициализация главной сцены приложения.
     * Параметр передается из класса Main.
     *
     * @param stage Главная сцена
     */
    void setMainStage(final Stage stage) {
        this.mainStage = stage;
    }

    /**
     * Метод солирования трека.
     * Солирует выбранный трек, заглушает все остальные.
     *
     * @param number Номер выбранного трека
     */
    void soloTrack(final int number) {
        if (!audioTracks.get(number).isSolo()) {
            audioTracks.parallelStream().forEach(track -> {
                track.mute();
            });
            audioTracks.get(number).unMute();
        } else {
            audioTracks.parallelStream().forEach(track -> {
                track.unMute();
            });
        }
    }
}
