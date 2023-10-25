package exampleproject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class inGameButtons {

    private Button mainMenuButton = new Button();
    Button restartButton = new Button();

    public inGameButtons() {
    }

    public void restartButtonDefaultValues() throws FileNotFoundException {
        File restartImageFile = new File("src/main/java/images/Buttons/restart.png");
        Image restartImage = new Image(restartImageFile.toURI().toString());
        ImageView restartImageView = new ImageView(restartImage);

        if(!restartImageFile.exists()) {
            throw new FileNotFoundException("Image file not found");
        }

        restartButton.setGraphic(restartImageView);
        restartButton.setStyle("-fx-background-color: transparent;");

        restartButton.setTranslateX(768 / 2 - 110);
        restartButton.setTranslateY(576 / 2 + 40);
        restartImageView.setFitWidth(220);
        restartImageView.setFitHeight(50);
    }

    public Button getRestartButton() throws FileNotFoundException {
        restartButtonDefaultValues();
        return restartButton;
    }

    public Button getMainMenuButton() throws IOException {
        mainMenuButtonDefaultValues();
        mainMenuButtonEvent();
        return mainMenuButton;
    }

    public void mainMenuButtonDefaultValues() throws FileNotFoundException {
        File mainMenuImageFile = new File("src/main/java/images/Buttons/mainMenu.png");
        Image image = new Image(mainMenuImageFile.toURI().toString());
        ImageView mainMenuImageView = new ImageView(image);

        if(!mainMenuImageFile.exists()) {
            throw new FileNotFoundException("Image file not found");
        }

        mainMenuButton.setGraphic(mainMenuImageView);
        mainMenuButton.setStyle("-fx-background-color: transparent;");

        mainMenuButton.setTranslateX(768 / 2 - 110);
        mainMenuButton.setTranslateY(576 / 2 + 120);
        mainMenuImageView.setFitWidth(220);
        mainMenuImageView.setFitHeight(45);
    }

    public void mainMenuButtonEvent() throws IOException {
        Parent mainMenu = FXMLLoader.load(getClass().getResource("gameProject.fxml"));

        mainMenuButton.setOnAction(event -> {
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(mainMenu);
            window.setResizable(false);
            window.setScene(scene);
            window.show();
        });
    }
}