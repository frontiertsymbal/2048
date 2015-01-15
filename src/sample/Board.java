package sample;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.text.Font;

import java.util.ArrayList;
import java.util.List;

public class Board {

    private GraphicsContext gc;
    private boolean[][] free = new boolean[4][4];


    public Board(GraphicsContext gc) {
        this.gc = gc;
    }

    public void init() {
        gc.setFill(ColorFactory.colorSet(1));
        int fontSize = 80;
        gc.setFont(new Font(fontSize));
        gc.fillText("2048", 25, 90);
        gc.fillRoundRect(Const.CASE_X, Const.CASE_Y, Const.CASE_SIZE, Const.CASE_SIZE, Const.ANGLE, Const.ANGLE);
    }

    public void draw() {
        gc.setFill(ColorFactory.colorSet(0));
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                gc.fillRoundRect(Const.arrX[i], Const.arrY[j], Const.TILE_SIZE, Const.TILE_SIZE, Const.ANGLE, Const.ANGLE);
            }
        }
    }

//    TODO rewrite boolean;
    public boolean isFree(int[] arr) {
        return !free[arr[0]][arr[1]];
    }

    public void setBusy(int[] arr) {
        free[arr[0]][arr[1]] = true;
    }

    public void setFree(int[] arr) {
        free[arr[0]][arr[1]] = false;
    }



    //for test
    public void printBoolean() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                System.out.println(i + " " + j + " " + free[i][j]);
            }
        }
    }
}
