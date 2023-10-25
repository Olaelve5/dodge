package exampleproject;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class FileManagerTest {

    private static FileManager fileManager = new FileManager();
    ArrayList<String> originalScores = new ArrayList<String>();
    ArrayList<String> originalHighScores = new ArrayList<String>();

    @BeforeEach
    public void saveOriginalScoresInList() throws IOException {
        FileReader reader = new FileReader("src/main/java/exampleproject/scores.txt");

        BufferedReader br = new BufferedReader(reader);
        String line = "";

        while ((line = br.readLine()) != null) {
            originalScores.add(line);
        }

        br.close();
    }


    @Test
    public void checkIfScoresAreWrittenToFile() throws IOException {
        String score = "Level 9 reached. Date: 2022/04/26 09:47:46 (Test)";
        fileManager.writeToScores(score);

        ArrayList<String> testList = new ArrayList<String>();

        FileReader reader = new FileReader("src/main/java/exampleproject/scores.txt");
        BufferedReader br = new BufferedReader(reader);
        String line = "";


        while ((line = br.readLine()) != null) {
            testList.add(line);
        }
        br.close();

        assertEquals("Level 9 reached. Date: 2022/04/26 09:47:46 (Test)", testList.get(testList.size() - 1));
    }

    @Test
    public void testSortedList() throws IOException {
        String score = "Level 90 reached. Date: 2022/04/26 09:47:46 (Test)";
        fileManager.writeToScores(score);
        fileManager.addScoreToSortedList();

        String string = fileManager.getScoresSorted().get(fileManager.getScoresSorted().size() - 1);

        assertEquals(score, string);
    }

    @Test
    @DisplayName("Check if highscores are updated correctly")
    public void testHighScoreUpdate() throws IOException {

        String score = "Level 90 reached. Date: 2022/04/26 09:47:46 (Test)";
        fileManager.writeToScores(score);
        fileManager.addScoreToSortedList();
        fileManager.writeSortedListToFile();
        fileManager.addHighScoresToList();

        String highScore = fileManager.getHighScores(0);

        assertEquals(score, highScore);

    }

    @AfterEach
    public void restoreScores() throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(fileManager.getScoresFile()));
        for (int i = 0; i < originalScores.size(); i++) {
            bw.write(originalScores.get(i));
            bw.newLine();
        }
        bw.close();
    }

}
