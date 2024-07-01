import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import java.util.Objects;

public class BackButtonHandler {

    private final Runnable backAction;

    public BackButtonHandler(Runnable backAction) {
        this.backAction = backAction;
    }

    public void addBackButton(StackPane layout) {
        // Botão de voltar ao menu principal
        Image backButtonImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("img/home.png")));
        ImageView backButtonImageView = new ImageView(backButtonImage);
        backButtonImageView.setFitWidth(50);
        backButtonImageView.setFitHeight(50);
        Button backButton = new Button("", backButtonImageView);
        backButton.setStyle("-fx-background-color: transparent; -fx-padding: 0;"); // Remove background and padding
        backButton.setOnAction(e -> backAction.run());

        StackPane.setAlignment(backButton, Pos.TOP_LEFT);
        layout.getChildren().add(backButton);
    }
}
