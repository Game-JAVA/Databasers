import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

public class menu extends Application {

    private Stage primaryStage;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        primaryStage.setTitle("Atemporal");

        // Logo de Atemporal
        Image logo = new Image("file:src/img/logo.png");
        ImageView logoView = new ImageView(logo);
        logoView.setFitWidth(300); // Ajuste de largura da logo
        logoView.setPreserveRatio(true); // Mantém a proporção da imagem

        // Configurar a animação de transição para movimentar o logo
        TranslateTransition transition = new TranslateTransition(Duration.seconds(3), logoView);
        transition.setToY(-20); // Mover para cima 20 pixels
        transition.setCycleCount(TranslateTransition.INDEFINITE); // Repetir infinitamente
        transition.setAutoReverse(true); // Alternar a direção

        // Iniciar a animação
        transition.play();

        // Botão Iniciar Jogo
        Button startButton = new Button("Iniciar Jogo");
        startButton.getStyleClass().add("menu-button"); // Recebe a classe css do menu-button
        startButton.setOnAction(e -> showCharacterSelection());

        // Botão Configurações
        Button settingsButton = new Button("Opções");
        settingsButton.getStyleClass().add("menu-button");
        settingsButton.setOnAction(e -> openSettings());

        // Botão Sair
        Button exitButton = new Button("Sair");
        exitButton.getStyleClass().add("menu-button");
        exitButton.setOnAction(e -> primaryStage.close());

        // Layout do menu
        VBox menuLayout = new VBox(20);
        menuLayout.getChildren().addAll(logoView, startButton, settingsButton, exitButton); // posição dos botões
        menuLayout.setAlignment(Pos.CENTER);
        menuLayout.getStyleClass().add("menu-layout"); // recebe o estilo css do menu-layout

        // Cena principal
        Scene mainScene = new Scene(menuLayout, 800, 600);
        mainScene.getStylesheets().add("./CSS/style.css"); // arquivo css com as estilizações
        primaryStage.setScene(mainScene);
        primaryStage.show();
    }

    private void showCharacterSelection() {
        // Knight
        VBox knightBox = createCharacterBox("file:src/img/knight.png", "Knight");

        // Soldier
        VBox soldierBox = createCharacterBox("file:src/img/soldado.png", "Soldier");

        // Robot
        VBox robotBox = createCharacterBox("file:src/img/robot.png", "Robot");

        // Layout de seleção de personagem (Knight, Soldier e Robot)
        HBox characterSelectionLayout = new HBox(25);
        characterSelectionLayout.getChildren().addAll(knightBox, soldierBox, robotBox);
        characterSelectionLayout.setAlignment(Pos.CENTER);
        characterSelectionLayout.getStyleClass().add("character-selection-layout");

        // Cena de seleção de personagem
        Scene selectCharacterScene = new Scene(characterSelectionLayout, 800, 600);
        selectCharacterScene.getStylesheets().add("./CSS/styleSelect.css"); // Recebe o CSS para a cena de seleção

        primaryStage.setScene(selectCharacterScene);
    }

    private VBox createCharacterBox(String imagePath, String characterName) {
        Button characterButton = new Button(characterName);
        characterButton.getStyleClass().add("character-button");
        characterButton.setOnAction(e -> selectCharacter(characterName));

        ImageView characterImageView = new ImageView(new Image(imagePath));
        characterImageView.setFitHeight(200);
        characterImageView.setPreserveRatio(true);

        VBox characterBox = new VBox(10);
        characterBox.setAlignment(Pos.CENTER);
        characterBox.getChildren().addAll(characterButton, characterImageView);
        characterBox.getStyleClass().add("character-box");

        return characterBox;
    }

    private void selectCharacter(String characterName) {
        System.out.println(characterName + " selected!");
        // Código do Kauan jogabilidade:
    }


    private void openSettings() {
        System.out.println("Abrindo configurações");
    }

    public static void main(String[] args) {
        launch(args);
    }
}