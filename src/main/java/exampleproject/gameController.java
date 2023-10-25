package exampleproject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class gameController {

    @FXML
    private ImageView playButton;

    @FXML
    private ImageView playerImageView;

    private int imageCounter = 0;
    private String playerImageFilePath;

    private FileManager fileManager = new FileManager();

    public void playGameBtn(MouseEvent event) throws Exception {
        GamePanel gamePanel = new GamePanel();

        try {
            Stage window = (Stage) playButton.getScene().getWindow();
            window.setScene(gamePanel.getScene());

            if (imageCounter == 0) {
                playerImageFilePath = "src/main/java/images/playerImages/iron-man.png";
            } else if (imageCounter == 1) {
                playerImageFilePath = "src/main/java/images/playerImages/superman.png";
            } else if (imageCounter == 2) {
                playerImageFilePath = "src/main/java/images/playerImages/star.png";
            }
            gamePanel.setPlayerImageFile(playerImageFilePath);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    

    public void switchToHighScoreScene(MouseEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("Highscores.fxml"));
        Parent root;
        root = loader.load();

        //Oppdaterer til siste highscores
        fileManager.addScoreToSortedList();
        fileManager.writeSortedListToFile();

        highscoreSceneController highscoreSceneController = loader.getController();
        highscoreSceneController.setLabel();

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        window.setResizable(false);
        window.setScene(scene);
        window.show();
    }

    public void switchPlayerImage() throws FileNotFoundException{
        File file = new File("src/main/resources/exampleproject/Big_iron-man.png");
        Image ironMan = new Image(file.toURI().toString());

        File file3 = new File("src/main/resources/exampleproject/Big_superman.png");
        Image superman = new Image(file3.toURI().toString());

        File file4 = new File("src/main/resources/exampleproject/Big_star.png");
        Image star = new Image(file4.toURI().toString());

        if(!file.exists() || !file3.exists() || !file4.exists()) {
            
            throw new FileNotFoundException("Image file not found");
             
        }

        if (imageCounter == 0) {
            playerImageView.setImage(ironMan);
        } else if (imageCounter == 1) {
            playerImageView.setImage(superman);
        } else if (imageCounter == 2) {
            playerImageView.setImage(star);
        }
    }

    public void rightSwitchButton(){
        imageCounter++;

        if (imageCounter == 3) {
            imageCounter = 0;
        }

        try {
            switchPlayerImage();
        } catch (FileNotFoundException e) {
            System.out.println("Player image file not found");
        }
    }

    public void leftSwitchButton(){
        imageCounter--;

        if (imageCounter == -1) {
            imageCounter = 2;
        }

        try {
            switchPlayerImage();
        } catch (FileNotFoundException e) {
            System.out.println("Player image file not found");
        }
    }

    public int getImageCounter() {
        return imageCounter;
    }

    public void rotatePlayerImage() {
        playerImageView.setRotate(playerImageView.getRotate() + 180);
    }

}
