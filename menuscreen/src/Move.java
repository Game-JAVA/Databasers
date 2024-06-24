import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.image.Image;

import java.util.Objects;

public class Move extends Application {

    private static final double WIDTH = 1000;
    private static final double HEIGHT = 600;

    @Override
    public void start(Stage primaryStage) {
        Pane pane = new Pane();
        Scene scene = new Scene(pane, WIDTH, HEIGHT);

        // Instância do mapa e do player
        GameMap gameMap = new GameMap(WIDTH, HEIGHT);
        Player player = new Player(200, HEIGHT/2);
        pane.getChildren().add(gameMap.getBackgroundImageView());
        pane.getChildren().add(player.getImageView());


       // Instância da classe GameLoop para acessar o método start()
        GameLoop gameLoop = new GameLoop(scene, player, pane);
        gameLoop.start();


        // Configuração da tela 'primaryStage'
        primaryStage.setTitle("Atemporal");
        primaryStage.setScene(scene);
        primaryStage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("res/logo.png"))));
        primaryStage.show();

        // Atribuir foco aos eventos do painel
        pane.requestFocus();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
