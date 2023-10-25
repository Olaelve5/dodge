package exampleproject;

import java.io.IOException;

public interface FileManagementInterface {

    public void writeToScores(String content) throws IOException;
    public void addScoreToSortedList() throws IOException;
    public void writeSortedListToFile() throws IOException;
    public void addHighScoresToList() throws IOException;
    public String getHighScores(int index) throws IOException;
}
