package exampleproject;

public class Boundary {
    //Inspirasjonen til denne løsningen er hentet fra: https://www.youtube.com/watch?v=9xsT6Z6HQfw&t=1397s

    public double x, y, width, height;

    public Boundary() {
        this.setPosition(0, 0);
        this.setSize(1, 1);
    }

    public Boundary(double x, double y, double width, double height) {
        this.setPosition(x, y);
        this.setSize(width, height);
    }

    public void setPosition(double x, double y) {
        if (y < 0) {
            throw new IllegalArgumentException("y must be positive");
        }
        this.x = x;
        this.y = y;
    }

    public void setSize(double width, double height) {
        if (width < 0 || height < 0) {
            throw new IllegalArgumentException("boundary size: width and height must be positive");
        }
        this.width = width;
        this.height = height;
    }

    public double getX() {
        return this.x;
    }

    public double getY() {
        return this.y;
    }

    public double getWidth() {
        return this.width;
    }

    public double getHeight() {
        return this.height;
    }

    public boolean collide(Boundary otherEntity) {

        boolean noCollision = this.x + this.width < otherEntity.x ||
                otherEntity.x + otherEntity.width < this.x ||
                this.y + this.height < otherEntity.y ||
                otherEntity.y + otherEntity.height < this.y;

        return !noCollision;
    }

}
