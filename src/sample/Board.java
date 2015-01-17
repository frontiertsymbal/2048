package sample;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.text.Font;

import java.util.ArrayList;
import java.util.List;

public class Board {

    private GraphicsContext gc;
    private boolean[][] free = new boolean[4][4];
    private boolean canMove = true;


    public Board(GraphicsContext gc) {
        this.gc = gc;
    }

    public void draw() {
        gc.setFill(ColorFactory.colorSet(1));
        int fontSize = 80;
        gc.setFont(new Font(fontSize));
        gc.fillText("2048", 25, 90);
        gc.fillRoundRect(Const.CASE_X, Const.CASE_Y, Const.CASE_SIZE, Const.CASE_SIZE, Const.ANGLE, Const.ANGLE);
        gc.setFill(ColorFactory.colorSet(0));
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                gc.fillRoundRect(Const.arrX[i], Const.arrY[j], Const.TILE_SIZE, Const.TILE_SIZE, Const.ANGLE, Const.ANGLE);
            }
        }
    }

    public boolean isFree(int[] arr) {
        return free[arr[0]][arr[1]];
    }

    public void setBusy(List<Tile> list) {
        boardFreeArrayInit();
        for (Tile aList : list) {
            free[aList.getPosition()[0]][aList.getPosition()[1]] = false;
        }
    }

    public List<int[]> freeList() {
        List<int[]> freeList = new ArrayList<int[]>();
        for (int i = 0; i < free.length; i++) {
            for (int j = 0; j < free[i].length; j++) {
                if (free[i][j]) {
                    freeList.add(new int[]{i, j});
                }
            }
        }
        if (freeList.size() == 1) {
            canMove = false;
        }
        return freeList;
    }

    public void boardFreeArrayInit() {
        for (int i = 0; i < free.length; i++) {
            for (int j = 0; j < free[i].length; j++) {
                free[i][j] = true;
            }
        }
    }

    public boolean canMove(){
        return canMove;
    }
}
