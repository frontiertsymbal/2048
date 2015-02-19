package sample;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.text.Font;

public class Tile {
    private int x;
    private int y;
    private int[] pos = new int[2];
    private GraphicsContext gc;
    private int value;

    public Tile(int[] pos, GraphicsContext gc) {
        this.gc = gc;
        this.pos = pos;
        setPosition(pos);
        newTileRandomValueSet();
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

    public void setPosition(int[] pos) {
        this.pos = pos;
        x = Const.arrX[pos[0]];
        y = Const.arrY[pos[1]];
    }

    public int[] getPosition () {
        return pos;
    }

    public void newTileRandomValueSet() {
        value = Const.valueArray[Const.random(Const.valueArray.length)];
    }

    public void draw() {
        gc.setFill(ColorFactory.colorSet(value));
        gc.fillRoundRect(x, y, Const.TILE_SIZE, Const.TILE_SIZE, Const.ANGLE, Const.ANGLE);
        drawValue();
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

}
