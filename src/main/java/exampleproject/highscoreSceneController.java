package exampleproject;

import java.io.IOException;
import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class highscoreSceneController {
    
    @FXML
    Label label0, label1, label2, label3, label4, label5, label6, label7, label8, label9;

    ArrayList<Label> labels = new ArrayList<Label>();

    public void setLabel() throws IOException {
        labels.add(label0);
        labels.add(label1);
        labels.add(label2);
        labels.add(label3);
        labels.add(label4);
        labels.add(label5);
        labels.add(label6);
        labels.add(label7);
        labels.add(label8);
        labels.add(label9);

        FileManager fileManager = new FileManager();

        fileManager.addHighScoresToList();

        int counter = 0;
        for(Label label : labels) {
            if(counter < fileManager.getHighScoresListSize()) {
                label.setText(fileManager.getHighScores(counter));
                counter++;
                }
            else {
                label.setText("");
            }
            } 
        }
    

    public void switchBackToHome(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("gameProject.fxml"));
        Stage window = (Stage)((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        window.setResizable(false);
        window.setScene(scene);
        window.show();
    }
}
