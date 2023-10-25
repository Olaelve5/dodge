package exampleproject;

import java.io.File;
import java.io.FileNotFoundException;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Sprite {
    private double positionX;
    private double positionY;
    private Image image;
    private String enemyStartSide;
    private Boundary boundary;
    private int lives;
    

    public Sprite() {
        this.positionX = 150;
        this.positionY = 150;
        this.boundary = new Boundary();
        this.boundary.setSize(30, 40);
    }

    public Sprite(String imageFileName) {
        this();
        try {
            setImage(imageFileName);
        } catch (FileNotFoundException e) {
            System.out.println("Image file not found");
        }
    }

    public void setImage(String imageFileName) throws FileNotFoundException {

        File file = new File(imageFileName);
        Image newImage = new Image(file.toURI().toString());
        this.image = newImage;

        if(!file.exists()) {
            throw new FileNotFoundException("Image file not found");
        }
    }

    public Image getImage() {
        return this.image;
    }

    public void setPositionX(double positionX) {
        if(positionX > 0 - 49 || positionX < 768 + 49) {
            this.positionX = positionX;
        } else {
            throw new IllegalArgumentException("PositionX must be between -48 and screenwidth + 49");
        }
    }

    public double getPositionX() {
        return this.positionX;
    }

    public void setPositionY(double positionY) {
        if(positionY < 0 || positionY > 576) {
            throw new IllegalArgumentException("Illegal y position");
        }
        this.positionY = positionY;
    }

    public double getPositionY() {
        return this.positionY;
    }

    public void setEnemyStartSide(String side) {
        if(side == "left" || side == "right") {
            this.enemyStartSide = side;
        } else {
            throw new IllegalArgumentException("Illegal side argument; must be 'left' or 'right'");
        }
        
    }

    public String getEnemyStartSide() {
        return this.enemyStartSide;
    }

    public void setLives(int lives) {
        this.lives = lives;
    }

    public int getLives() {
        return this.lives;
    }

    public void draw(GraphicsContext context) {

        context.save();

        context.translate(this.positionX, this.positionY);
        context.drawImage(this.image, 0, 0);

        context.restore();

    }

    public void teleport() {
        if (this.positionX + 48 < 0) {
            this.positionX = 768 - 1;
        }
        if (this.positionX > 768) {
            this.positionX = 0 - 48;
        }
    }

    public Boundary getBoundary() {

        this.boundary.setPosition(this.positionX, this.positionY);
        return this.boundary;
    }

    public boolean collide(Sprite otherEntity) {

        return this.getBoundary().collide(otherEntity.getBoundary());
    }
}
