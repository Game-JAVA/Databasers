import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ControlScreen extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Control Screen");

        // Carregar a imagem
        Image image = new Image("img/controlscreen.png");
        ImageView imageView = new ImageView(image);

        // Configurar o layout
        VBox root = new VBox();
        root.getChildren().add(imageView);

        // Configurar a cena
        Scene scene = new Scene(root, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}