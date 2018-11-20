
package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Главный класс приложения.
 */
public class Main extends Application {

    /**
     * Контроллер главного окна.
     * Позволяет использовать методы данного класса.
     */
    private MainWindowController mainWindowController;

    /**
     * Стартовый метод приложения.
     *
     * @param primaryStage Главная сцена окна приложения.
     * @throws Exception Исключения (Возможный исключения не обрабатываются).
     */
    @Override
    public void start(final Stage primaryStage) throws Exception {
        FXMLLoader mainLoader = new FXMLLoader(getClass().getResource("GUI/MainWindow.fxml"));
        Parent root = mainLoader.load();
        mainWindowController = mainLoader.getController();
        mainWindowController.setMainStage(primaryStage);
        primaryStage.setTitle("Multiplay");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    /**
     * Начальная точка приложения.
     *
     * @param args Аргументы командной строки
     */
    public static void main(final String[] args) {
        launch(args);
    }
}
