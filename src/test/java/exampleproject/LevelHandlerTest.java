package exampleproject;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class LevelHandlerTest {

    LevelHandler levelHandler = new LevelHandler();

    @Test
    public void testSetLevel() {
        
        levelHandler.setLevel(5);

        assertEquals(5, levelHandler.getLevel());
        assertThrows(IllegalArgumentException.class, ()
            -> {
                levelHandler.setLevel(-5);
            });
        
        assertThrows(IllegalArgumentException.class, ()
            -> {
                levelHandler.setLevel(50);
            });
    }

    @Test
    public void testSetLevelLength() {
        levelHandler.setLevelLength(2000);

        assertEquals(2000, levelHandler.getLevelLength());

        assertThrows(IllegalArgumentException.class, ()
            -> {
                levelHandler.setLevelLength(-100);
            });

        assertThrows(IllegalArgumentException.class, ()
            -> {
                levelHandler.setLevelLength(50000);
            });
    }

    @Test
    public void testSetPauseLength() {

        levelHandler.setLevelLength(2000);
        levelHandler.setPauseLength(400);
        
        assertEquals(400, levelHandler.getPauseLength());

        assertThrows(IllegalArgumentException.class, ()
            -> {
                levelHandler.setPauseLength(-300);
            });
        
        assertThrows(IllegalArgumentException.class, ()
            -> {
                levelHandler.setPauseLength(2500);
            });
    }

    @Test
    public void testSetEnemySpeed() {

        levelHandler.setEnemySpeed(2);

        assertEquals(2, levelHandler.getEnemySpeed());

        assertThrows(IllegalArgumentException.class, ()
            -> {
                levelHandler.setEnemySpeed(-2);
            });
        
        assertThrows(IllegalArgumentException.class, ()
            -> {
                levelHandler.setEnemySpeed(50);
            });
        
    }

    @Test
    public void testSetEnemyRate() {

        levelHandler.setEnemyRate(50);

        assertEquals(50, levelHandler.getEnemyRate());

        assertThrows(IllegalArgumentException.class, ()
            -> {
                levelHandler.setEnemyRate(-10);
            });

        assertThrows(IllegalArgumentException.class, ()
            -> {
                levelHandler.setEnemyRate(10000);
            });
    }

    @Test
    public void testSetEnemyLaunchCounter() {

        levelHandler.setEnemyLaunchCounter(500);

        assertEquals(500, levelHandler.getEnemyLaunchCounter());

        assertThrows(IllegalArgumentException.class, ()
            -> {
                levelHandler.setEnemyLaunchCounter(-300);
            });
    }

    @Test
    public void testSetEnemySpawnNumber() {

        levelHandler.setEnemySpawnNumber(10);

        assertEquals(10, levelHandler.getEnemySpawnNumber());

        assertThrows(IllegalArgumentException.class, ()
            -> {
                levelHandler.setEnemySpawnNumber(-10);
            });

        assertThrows(IllegalArgumentException.class, ()
            -> {
                levelHandler.setEnemySpawnNumber(100);
            });
    }

    @Test
    public void testLevelDifficulty() {
        //Trenger ikke å teste alle nivåene da testene ovenfor har testet for ugyldig input, noe som sikrer nogenlunde gyldige nivåer.
        //Nivåene kan også bli endret på dersom det viser seg at de er for enkle. Blir derfor vanskelig å teste alle verdiene. 
        //Tester da bare at levelDifficulty() oppdaterer verdiene for ett nivå. 
        levelHandler.setLevel(3);
        levelHandler.levelDifficulty();

        assertEquals(8, levelHandler.getEnemySpeed());
        assertEquals(2, levelHandler.getEnemySpawnNumber());
        assertEquals(40, levelHandler.getEnemyRate()); 
        assertEquals(2000, levelHandler.getLevelLength());
        assertEquals(200, levelHandler.getPauseLength());
    }
    
    
}
