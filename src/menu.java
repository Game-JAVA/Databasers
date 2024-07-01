import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.Objects;

public class menu extends Application {

    private Stage primaryStage;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        primaryStage.setTitle("Atemporal");

        showMainMenu();
    }

    private void showMainMenu() {
        // Logo de Atemporal
        Image logo = new Image(Objects.requireNonNull(getClass().getResourceAsStream("img/logo.png")));
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
        mainScene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("CSS/style.css")).toExternalForm()); // arquivo css com as estilizações
        primaryStage.setScene(mainScene);
        primaryStage.show();
    }

    private void showCharacterSelection() {
        // Knight
        VBox knightBox = createCharacterBox("img/knight.png", "Knight");

        // Soldier
        VBox soldierBox = createCharacterBox("img/soldado.png", "Soldier");

        // Robot
        VBox robotBox = createCharacterBox("img/robot.png", "Robot");

        // Layout de seleção de personagem (Knight, Soldier e Robot)
        HBox characterSelectionLayout = new HBox(25);
        characterSelectionLayout.getChildren().addAll(knightBox, soldierBox, robotBox);
        characterSelectionLayout.setAlignment(Pos.CENTER);
        characterSelectionLayout.getStyleClass().add("character-selection-layout");

        // Adicionar botão de voltar ao layout principal
        StackPane mainLayout = new StackPane();
        mainLayout.getChildren().add(characterSelectionLayout);

        // Cena de seleção de personagem
        Scene selectCharacterScene = new Scene(mainLayout, 800, 600);
        selectCharacterScene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("CSS/styleSelect.css")).toExternalForm()); // Recebe o CSS para a cena de seleção

        primaryStage.setScene(selectCharacterScene);
    }

    private void showControlScreen() {
        // Carregar a imagem
        Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream("img/controlscreen.png")));
        ImageView imageView = new ImageView(image);

        // Configurar o layout
        VBox root = new VBox();
        root.getChildren().add(imageView);
        root.setAlignment(Pos.CENTER);

        // Adicionar botão de voltar ao layout principal
        StackPane mainLayout = new StackPane();
        mainLayout.getChildren().add(root);
        new BackButtonHandler(this::showMainMenu).addBackButton(mainLayout);

        // Configurar a cena
        Scene scene = new Scene(mainLayout, 800, 600);
        scene.setOnKeyPressed(this::handleKeyPress);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Control Screen");
    }

    private void showGameIntro() {
        // Criar a imagem do portal
        Image portalImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("img/logo.png")));
        ImageView portalImageView = new ImageView(portalImage);
        portalImageView.setFitWidth(200); // Ajuste conforme necessário
        portalImageView.setPreserveRatio(true); // Mantém a proporção da imagem

        // Criar o texto
        Text infoText = new Text("Caldeum\nO povo precisa de um herói!\nAjude Dante a eliminar as ameaças esqueléticas e\n"
                + "derrotar o Lorde das Trevas, Malakar.");
        infoText.setFont(Font.font("Monospaced", 20));
        infoText.setFill(Color.WHITE);
        infoText.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);

        // Layout para a imagem e o texto
        VBox contentLayout = new VBox(20);
        contentLayout.getChildren().addAll(portalImageView, infoText);
        contentLayout.setAlignment(Pos.CENTER);
        contentLayout.getStyleClass().add("content-layout");

        // Adicionar botão de voltar ao layout principal
        StackPane mainLayout = new StackPane();
        mainLayout.getChildren().add(contentLayout);
        new BackButtonHandler(this::showMainMenu).addBackButton(mainLayout);

        // Configurar a cena
        Scene displayScene = new Scene(mainLayout, 800, 600);
        displayScene.setOnKeyPressed(this::handleKeyPress);
        displayScene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("CSS/styleSelect.css")).toExternalForm()); // Arquivo CSS com as estilizações

        primaryStage.setScene(displayScene);
        primaryStage.setTitle("Game Intro");
    }

    private void handleKeyPress(KeyEvent event) {
        switch (event.getCode()) {
            case SPACE:
                if (primaryStage.getTitle().equals("Control Screen")) {
                    showGameIntro();
                }
                break;
            default:
                break;
        }
    }

    private VBox createCharacterBox(String imagePath, String characterName) {
        Button characterButton = new Button(characterName);
        characterButton.getStyleClass().add("character-button");
        characterButton.setOnAction(e -> {
            selectCharacter(characterName);
            showControlScreen();
        });

        ImageView characterImageView = new ImageView(new Image(Objects.requireNonNull(getClass().getResourceAsStream(imagePath))));
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
        // Aqui você pode adicionar o código para iniciar o jogo com o personagem selecionado
    }

    private void openSettings() {
        System.out.println("Abrindo configurações");
        // Aqui você pode adicionar o código para abrir as configurações
    }

    public static void main(String[] args) {
        launch(args);
    }
}
