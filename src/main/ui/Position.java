package ui;


// Class representing a position on the screen
public class Position {

    private int xposition;
    private int yposition;
    private int width;
    private int height;

    Position(int x, int y, int width, int height) {
        this.xposition = x;
        this.yposition = y;
        this.height = height;
        this.width = width;
    }

    public int getX() {
        return xposition;
    }

    public int getY() {
        return yposition;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
