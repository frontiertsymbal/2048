package sample;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.text.Font;

import java.util.Random;

public class Tile {
    private int x;
    private int y;
    int[] pos = new int[2];
    private GraphicsContext gc;
    private int value;
    private Random rand = new Random();

    public Tile(int[] pos, GraphicsContext gc) {
        this.gc = gc;
        this.pos = pos;
        setPosition(pos);
        newTileRandomValueSet();
        //TODO review;
    }

    public void setPosition(int[] pos) {
        x = Const.arrX[pos[0]];
        y = Const.arrY[pos[1]];
    }

    public int[] getPosition () {
        return pos;
    }

    public void newTileRandomValueSet() {
        int index = rand.nextInt(2);
        switch (index) {
            case 0:
                value = 2;
                break;
            case 1:
                value = 4;
                break;
        }
    }

    public void draw() {
        gc.setFill(ColorFactory.colorSet(value));
        gc.fillRoundRect(x, y, Const.TILE_SIZE, Const.TILE_SIZE, Const.ANGLE, Const.ANGLE);
        drawValue();
    }

    private void drawValue() {
        int textX = x;
        String val = "" + value;
        int fontSize = 55;
        if (value < 16) {
            gc.setFill(ColorFactory.colorSet(10));
        } else {
            gc.setFill(ColorFactory.colorSet(20));
        }
        switch (val.length()) {
            case 1:
                textX = x + 47;
                break;
            case 2:
                textX = x + 35;
                break;
            case 3:
                textX = x + 13;
                break;
            case 4:
                textX = x + 3;
                break;
        }
        gc.setFont(new Font(fontSize));
        gc.fillText(val, textX, y + 83);
    }

    public int getValue() {
        return value;
    }
}
