package exampleproject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;

public class FileManager implements FileManagementInterface {

    private File scores = new File("src/main/java/exampleproject/scores.txt"); 
    private File highScores = new File("src/main/java/exampleproject/highscores.txt"); 
    private ArrayList<String> scoresSorted = new ArrayList<String>();
    private ArrayList<String> highScoresList = new ArrayList<String>();

    public FileManager() {
    }

    @Override
    public void writeToScores(String content) throws IOException {
        
        BufferedWriter bw = new BufferedWriter(new FileWriter(scores, true));

        bw.newLine();
        try {
            bw.write(content);
            
        } catch (IOException e) {
            System.out.println("Cannot write to file");
        }
        bw.close();
    }

    @Override
    public void addScoreToSortedList() throws IOException {
        FileReader reader = new FileReader(scores);
        BufferedReader br = new BufferedReader(reader);
        String line = "";


        while ((line = br.readLine()) != null) {
            scoresSorted.add(line);
        }

        Collections.sort(scoresSorted);
        br.close();
    }

    @Override
    public void writeSortedListToFile() throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(highScores));

        if(scoresSorted.size() > 10) {
            for(int i = scoresSorted.size() - 1; i >= scoresSorted.size() - 10; i--) {
                try {
                    bw.write(scoresSorted.get(i));
                } catch (Exception e) {
                    System.out.println("Cannot write to file");
                }
                bw.newLine();
            }
        } else {
            for(int i = scoresSorted.size() - 1; i >= 0; i--) {
                try {
                    bw.write(scoresSorted.get(i));
                } catch (Exception e) {
                    System.out.println("Cannot write to file");
                }
                bw.newLine();
            }
        }
        bw.close();
    }

    @Override
    public void addHighScoresToList() throws IOException {
        FileReader reader = new FileReader(highScores);
        BufferedReader br = new BufferedReader(reader);

        String line = "";
        while ((line = br.readLine()) != null) {
            highScoresList.add(line);
        }
        br.close();
    }

    @Override
    public String getHighScores(int index) throws IOException{
        return highScoresList.get(index);
    }

    public void writeScoreToFile(int level) throws IOException {

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();

        String score = "Level " + level + " reached. Date: " + dtf.format(now);

        writeToScores(score);
        addScoreToSortedList();
        writeSortedListToFile();
    }

    public int getHighScoresListSize() {
        return highScoresList.size();
    }

    public File getScoresFile() {
        return scores;
    }

    public File getHighScoresFile() {
        return highScores;
    }

    public ArrayList<String> getScoresSorted() {
        return scoresSorted;
    }
    
}
