package exampleproject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;

public class GamePanel {
    private static final double tileSize = 48;
    private static final double maxScreenCol = 16;
    private static final double maxScreenRow = 12;
    private static final double screenWidth = tileSize * maxScreenCol;
    private static final double screenHeight = tileSize * maxScreenRow;

    private ArrayList<String> pressedKeysList = new ArrayList<String>();

    private Group root = new Group();
    private Scene scene = new Scene(root, screenWidth, screenHeight);

    private Canvas canvas = new Canvas(screenWidth, screenHeight);
    private GraphicsContext context = canvas.getGraphicsContext2D();

    private Sprite player = new Sprite();
    private static double playerSpeed = 6;

    private ArrayList<Sprite> enemyList = new ArrayList<Sprite>();

    private int levelCounter = 0;
    private int pauseCounter = 0;

    private LevelHandler levelHandler = new LevelHandler();
    private FileManager fileManager = new FileManager();
    private inGameButtons btnText = new inGameButtons();

    private int heartPositionCounter = 0;

    public GamePanel(){ /////////////////////////////////// Konstruktør
        try {
            drawBackground();
        } catch (FileNotFoundException e) {
            System.out.println("Image files not found");
        }
        root.getChildren().add(canvas);
        detectUserInput();
        gameloop.start();
        player.setLives(4);
        drawHearts();
    }

    public AnimationTimer gameloop = new AnimationTimer() { //////////////////////////////// gameloop

        public void handle(long time) {

        if (levelHandler.getIsGameWon()) {
            gameloop.stop();
            try {
                drawWinnerText();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } 
        
        else if (levelHandler.getIsGameOver()) {
            gameloop.stop();
            try {
                drawGameOverText();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            updateLevel();
        
            removeLastFrame();
            processUserInput(getPressedKeysList());
            drawEnemy();
        
        try {
            collisionUpdate();
        } catch (FileNotFoundException e1) {
            System.out.println("Dark heart image file not found");
        }

        player.draw(context);
        }
        
        }
    };

    public Sprite getPlayer() {
        return player;
    }

    public ArrayList<String> getPressedKeysList() {
        return pressedKeysList;
    }

    public Scene getScene() {
        return scene;
    }

    public void drawBackground() throws FileNotFoundException { ////////////////////////// Tegner bakgrunnen

        File space = new File("src/main/java/images/space2.png");
        Image spaceImage = new Image(space.toURI().toString());

        File wall = new File("src/main/java/images/water.png");
        Image wallImage = new Image(wall.toURI().toString());

        if(!space.exists() || !wall.exists()) {
            throw new FileNotFoundException("Background image file not found");
        }

        int counterColumn = 0;
        int counterRow = 0;
        int xCoordinate = 0;
        int yCoordinate = 0;

        while (counterColumn < 18) {

            ImageView imageView = new ImageView(spaceImage);
            imageView.setFitHeight(48);
            imageView.setFitWidth(48);

            imageView.setY(yCoordinate);
            imageView.setX(xCoordinate);

            root.getChildren().add(imageView);

            xCoordinate += 48;
            counterColumn++;

            if (counterColumn == 17) {
                counterColumn = 0;
                counterRow++;
                xCoordinate = 0;
                yCoordinate += 48;
            }

            if (counterRow == 0) {
                imageView.setImage(wallImage);

            }
            if (counterRow == 11) {
                imageView.setImage(wallImage);
            }

            if (counterRow == 12) {
                counterColumn = 18;
            }
        }
    }

    public void removeLastFrame() {
        context.clearRect(0, 0, screenWidth, screenHeight - 46);
    }

    public void detectUserInput() { ////////////////////////// Registrerer input fra spilleren
        // Inspirasjon til denne delen av appen er hentet fra den samme 
        // videoen som i klassen boundary: https://www.youtube.com/watch?v=9xsT6Z6HQfw&t=1397s
        

        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {

            @Override
            public void handle(KeyEvent event) {
                String key = event.getCode().toString();

                if (!pressedKeysList.contains(key)) {
                    pressedKeysList.add(key);
                }
            }
        });

        scene.setOnKeyReleased(new EventHandler<KeyEvent>() {

            @Override
            public void handle(KeyEvent event) {
                String key = event.getCode().toString();

                if (pressedKeysList.contains(key)) {
                    pressedKeysList.remove(key);
                }
            }
        });
    }

    public void processUserInput(ArrayList<String> userInput) { //////////////////////////////// får spilleren til å bevege seg
        player.teleport();
        if (userInput.contains("W")) {
            // top border detection
            if (player.getPositionY() >= tileSize / 2) {
                player.setPositionY(player.getPositionY() - playerSpeed);
            }
        }

        if (userInput.contains("S")) {
            // bottom border detection
            if (player.getPositionY() <= screenHeight - (tileSize + player.getImage().getHeight() + 4)) {
                player.setPositionY(player.getPositionY() + playerSpeed);
            }
        }

        if (userInput.contains("D")) {
            player.setPositionX(player.getPositionX() + playerSpeed);
        }

        if (userInput.contains("A")) {
            player.setPositionX(player.getPositionX() - playerSpeed);
        }
    }

    public void setPlayerImageFile(String imageFilePath){
        try {
            player.setImage(imageFilePath);
        } catch (FileNotFoundException e) {
            System.out.println("Player image file not found");
        }
    }

    public void addEnemyToList() {
        Random rand = new Random();
        int leftRightCounter;

        // legger til fiender i liste med tilfeldig startposisjon
        for (int i = 0; i < levelHandler.getEnemySpawnNumber(); i++) {
            Sprite enemy = new Sprite();
            enemy.setLives(1);
            leftRightCounter = rand.nextInt(2);
            if (leftRightCounter % 2 == 0) {
                enemy.setPositionX(0 - 48 * 2);
                enemy.setPositionY(rand.nextDouble(screenHeight - tileSize * 2.5) + tileSize / 2);
                try {
                    enemy.setImage(levelHandler.getEnemyImagePath());
                } catch (FileNotFoundException e) {
                    System.out.println("Enemy image file not found");
                }
                enemy.setEnemyStartSide("left");
            }
            if (leftRightCounter % 2 != 0) {
                enemy.setPositionX(screenWidth + 48);
                enemy.setPositionY(rand.nextDouble(screenHeight - tileSize * 2.5) + tileSize / 2);
                try {
                    enemy.setImage(levelHandler.getEnemyImagePath());
                } catch (FileNotFoundException e) {
                    System.out.println("Enemy image file not found");
                }
                enemy.setEnemyStartSide("right");
            }
            enemyList.add(enemy);
        }
    }

    public void launchEnemy(){
        // PauseCounter gir spilleren en liten pause mellom nivåene
        if (pauseCounter < 130 || pauseCounter > levelHandler.getLevelLength() - levelHandler.getPauseLength()) {
            levelHandler.setEnemyLaunchCounter(0);
        }
        if (levelHandler.getEnemyLaunchCounter() == levelHandler.getEnemyRate()) {
            addEnemyToList();
            levelHandler.setEnemyLaunchCounter(0);
        }

        levelHandler.setEnemyLaunchCounter(levelHandler.getEnemyLaunchCounter() + 1);

    }

    public void drawEnemy() {
        launchEnemy();

        for (Sprite enemy : enemyList) {
            if (enemy.getEnemyStartSide() == "left") {
                enemy.setPositionX( enemy.getPositionX() + levelHandler.getEnemySpeed());
                enemy.draw(context);
            }
            if (enemy.getEnemyStartSide() == "right") {
                enemy.setPositionX( enemy.getPositionX() - levelHandler.getEnemySpeed());
                enemy.draw(context);
            }
        }
    }

    public void updateLevel(){
        player.draw(context);
        levelHandler.levelDifficulty();

        if (levelCounter == levelHandler.getLevelLength()) {

            levelHandler.setLevel(levelHandler.getLevel() + 1);
            levelCounter = 0;
            pauseCounter = 0;

        }
        if (levelCounter == 0) {
            try {
                drawLevelText();
            } catch (FileNotFoundException e) {
                System.out.println("Level image file not found");
            }

        }

        if (levelHandler.getIsGameOver()) {
            gameloop.stop();
            try {
                drawGameOverText();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (levelHandler.getIsGameWon()) {
            gameloop.stop();
            try {
                drawWinnerText();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        
        levelCounter++;
        pauseCounter++;
    }

    public void drawLevelText() throws FileNotFoundException {

        context.clearRect(50, screenHeight - 38, 150, 30);

        File file = new File("src/main/java/images/levels/level_" + levelHandler.getLevel() + ".png");
        Image image = new Image(file.toURI().toString());

        if(!file.exists()) {
            throw new FileNotFoundException("Image file not found");
        }

        context.drawImage(image, 50, screenHeight - 38, 150, 30);
    }

    public void collisionUpdate() throws FileNotFoundException{
        File file = new File("src/main/java/images/dark_heart.png");
        Image darkHeart = new Image(file.toURI().toString());

        if(!file.exists()) {
            throw new FileNotFoundException("Image file not found");
        }

        for (int enemyNum = 0; enemyNum < enemyList.size(); enemyNum++) {
            Sprite enemy = enemyList.get(enemyNum);
            if (player.collide(enemy)) {
                enemy.setLives(enemy.getLives() - 1);
                context.drawImage(darkHeart, screenWidth - 200 + heartPositionCounter, screenHeight - 48, 48, 48);
                heartPositionCounter += 48;
                player.setLives(player.getLives() - 1);

                if (player.getLives() == 0) {
                    levelHandler.setIsGameOver(true);
                }
            }
            if(enemy.getLives() == 0) {
                enemyList.remove(enemyNum);
            }
        }
    }

    public void drawHearts(){

        Sprite heart = new Sprite("src/main/java/images/Heart.png");
        int counter = 0;
        for (int i = 0; i < player.getLives(); i++) {
            heart.setPositionX(screenWidth - 200 + counter);
            heart.setPositionY(screenHeight - 48);
            heart.draw(context);
            counter += 48;
        }
    }

    public void restartButtonEvent()  {
        EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                try {
                    restart();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        };
        try {
            btnText.getRestartButton().setOnAction(event);
        } catch (FileNotFoundException e1) {
            System.out.println("Restart button image file not found");
        }
    }

    public void drawGameOverText() throws IOException {
        File file = new File("src/main/java/images/game_over.png");
        Image image = new Image(file.toURI().toString());

        if(!file.exists()) {
            throw new FileNotFoundException("Image file not found");
        }
        context.drawImage(image, 80, screenHeight / 2 - 150, 600, 120);

        fileManager.writeScoreToFile(levelHandler.getLevel());

        restartButtonEvent();
        root.getChildren().add(btnText.getRestartButton());
        root.getChildren().add(btnText.getMainMenuButton());
    }

    public void drawWinnerText() throws IOException {
        File file = new File("src/main/java/images/winner.png");
        Image image = new Image(file.toURI().toString());

        if(!file.exists()) {
            throw new FileNotFoundException("Image file not found");
        }

        context.drawImage(image, 80, screenHeight / 2 - 150, 600, 120);

        fileManager.writeScoreToFile(levelHandler.getLevel());

        restartButtonEvent();
        root.getChildren().add(btnText.getRestartButton());
        root.getChildren().add(btnText.getMainMenuButton());
    }

    public void restart() throws IOException {
        context.clearRect(0, 0, screenWidth, screenHeight);
        player.setLives(4);
        heartPositionCounter = 0;
        drawHearts();
        levelHandler.setLevel(1);
        levelHandler.setIsGameOver(false);
        levelHandler.setIsGameWon(false);
        enemyList.clear();
        levelHandler.setEnemyLaunchCounter(0);
        pauseCounter = 0;
        levelCounter = 0;
        root.getChildren().remove(btnText.getRestartButton());
        root.getChildren().remove(btnText.getMainMenuButton());
        gameloop.start();
    }

}
