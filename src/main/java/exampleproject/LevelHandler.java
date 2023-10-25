package exampleproject;

public class LevelHandler {

    private int level = 1;
    private int levelLength;
    private int pauseLength;

    private double enemySpeed;
    private int enemyRate;
    private int enemyLaunchCounter = 0;
    private int enemySpawnNumber;
    private String enemyImagePath;

    private Boolean isGameOver = false;
    private Boolean isGameWon = false;

    public LevelHandler() {
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        if(level < 0 || level >= 12) {
            throw new IllegalArgumentException("level must be greater than 0 and less than 12");
        } else {
            this.level = level;
        }
    }

    public int getLevelLength() {
        return levelLength;
    }



    public void setLevelLength(int levelLength) {
        if(levelLength < 0 || levelLength > 20000) {
            throw new IllegalArgumentException("levelLenght must be between 0 and 20 000");
        } else {
            this.levelLength = levelLength;
        }
        
    }

    public int getPauseLength() {
        return pauseLength;
    }

    public void setPauseLength(int pauseLength) {
        if(pauseLength < 0 || pauseLength > getLevelLength()) {
            throw new IllegalArgumentException("pauseLength must be greater than 0 and less than levelLength");
        } else {
            this.pauseLength = pauseLength;
        }
    }

    public double getEnemySpeed() {
        return enemySpeed;
    }

    public void setEnemySpeed(double enemySpeed) {
        if(enemySpeed < 0 || enemySpeed > 30) {
            throw new IllegalArgumentException("enemySpeed must be between 0 and 30");
        }
        this.enemySpeed = enemySpeed;
    }

    public int getEnemyRate() {
        return enemyRate;
    }

    public void setEnemyRate(int enemyRate) {
        if(enemyRate < 0 || enemyRate > 1000) {
            throw new IllegalArgumentException("enemyRate must be between 0 and 1000 and");
        }
        this.enemyRate = enemyRate;
    }

    public int getEnemyLaunchCounter() {
        return enemyLaunchCounter;
    }

    public void setEnemyLaunchCounter(int enemyLaunchCounter) {
        if(enemyLaunchCounter < 0) {
            throw new IllegalArgumentException("enemyLaunchCounter must be positive");
        }
        this.enemyLaunchCounter = enemyLaunchCounter;
    }

    public int getEnemySpawnNumber() {
        return enemySpawnNumber;
    }

    public void setEnemySpawnNumber(int enemySpawnNumber) {
        if(enemySpawnNumber < 0 || enemySpawnNumber > 30) {
            throw new IllegalArgumentException("EnemySpawnNumber must be between 0 and 30");
        } else {
            this.enemySpawnNumber = enemySpawnNumber;
        }
    }

    public String getEnemyImagePath() {
        return enemyImagePath;
    }

    public void setEnemyImagePath(String enemyImagePath) {
        if(enemyImagePath == null) {
            throw new IllegalArgumentException("enemyImagePath cannot be null");
        } 
        this.enemyImagePath = enemyImagePath;
    }

    

    public Boolean getIsGameOver() {
        return isGameOver;
    }

    public void setIsGameOver(Boolean isGameOver) {
        if(isGameOver == null) {
            throw new IllegalArgumentException("Must set isGameOver to be either true or false");
        }
        this.isGameOver = isGameOver;
    }

    public Boolean getIsGameWon() {
        return isGameWon;
    }

    public void setIsGameWon(Boolean isGameWon) {
        if(isGameOver == null) {
            throw new IllegalArgumentException("Must set isGameWon to be either true or false");
        }
        this.isGameWon = isGameWon;
    }

    

    public void levelDifficulty() {
        //Level 1
        if(level == 1) {
            setEnemyRate(60);
            setEnemySpeed(3);
            setEnemySpawnNumber(2);
            setLevelLength(2000);
            setPauseLength(380);
            setEnemyImagePath("src/main/java/images/Ghosts/Ghost_green.png");
        }
        //Level 2
        else if(level == 2) {
            setEnemyRate(30);
            setEnemySpeed(3);
            setEnemySpawnNumber(2);
            setLevelLength(1800);
            setPauseLength(380);
            setEnemyImagePath("src/main/java/images/Ghosts/Ghost_green.png");
            
        }
        //Level 3
        else if(level == 3) {
            setEnemyRate(60);
            setEnemySpeed(8);
            setEnemySpawnNumber(2);
            setLevelLength(2000);
            setPauseLength(200);
            setEnemyImagePath("src/main/java/images/Ghosts/Ghost_yellow.png");
        }
        //Level 4
        else if(level == 4) {
            setEnemyRate(110);
            setEnemySpeed(4);
            setEnemySpawnNumber(10);
            setLevelLength(2200);
            setPauseLength(340);
            setEnemyImagePath("src/main/java/images/Ghosts/Ghost_red.png");
        }
        //Level 5
        else if(level == 5) {
            setEnemyRate(30);
            setEnemySpeed(1);
            setEnemySpawnNumber(2);
            setLevelLength(2800);
            setPauseLength(930);
            setEnemyImagePath("src/main/java/images/Ghosts/Ghost_purple.png");
        }
        //Level 6
        else if(level == 6) {
            setEnemyRate(30);
            setEnemySpeed(8);
            setEnemySpawnNumber(3);
            setLevelLength(1800);
            setPauseLength(200);
            setEnemyImagePath("src/main/java/images/Ghosts/Ghost_yellow.png");
        }
        //Level 7
        else if(level == 7) {
            setEnemyRate(60);
            setEnemySpeed(4);
            setEnemySpawnNumber(10);
            setLevelLength(3000);
            setPauseLength(980);
            setEnemyImagePath("src/main/java/images/Ghosts/Ghost_red.png");
        }
        //Level 8
        else if(level == 8) {
            setEnemyRate(30);
            setEnemySpeed(10);
            setEnemySpawnNumber(3);
            setLevelLength(2500);
            setPauseLength(200);
            setEnemyImagePath("src/main/java/images/Ghosts/Ghost_yellow.png");
        }
        //Level 9
        else if(level == 9) {
            setIsGameWon(true);
        }
    }
    
}
