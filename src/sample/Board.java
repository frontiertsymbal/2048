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
        boardFreeArrayInit();
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
        return freeList;
    }

    private void boardFreeArrayInit() {
        for (int i = 0; i < free.length; i++) {
            for (int j = 0; j < free[i].length; j++) {
                free[i][j] = true;
            }
        }
    }

    public void gameOverDraw() {
        gc.setFill(ColorFactory.colorSet(0));
        gc.fillRoundRect(20, 320, 560, 205, Const.ANGLE, Const.ANGLE);
        gc.setFill(ColorFactory.colorSet(2));
        gc.fillRoundRect(25, 325, 550, 195, Const.ANGLE, Const.ANGLE);

        int fontSize = 110;
        gc.setFill(ColorFactory.colorSet(1111));
        gc.setFont(new Font(fontSize));
        gc.fillText("Game Over", 25, 425);
        fontSize = 30;
        gc.setFont(new Font(fontSize));
        gc.fillText("To start new game press Enter.", 97, 500);
    }

    public void youWinDraw() {
        gc.setFill(ColorFactory.colorSet(0));
        gc.fillRoundRect(20, 320, 560, 205, Const.ANGLE, Const.ANGLE);
        gc.setFill(ColorFactory.colorSet(2));
        gc.fillRoundRect(25, 325, 550, 195, Const.ANGLE, Const.ANGLE);

        int fontSize = 110;
        gc.setFill(ColorFactory.colorSet(1111));
        gc.setFont(new Font(fontSize));
        gc.fillText("You Win", 100, 425);
        fontSize = 30;
        gc.setFont(new Font(fontSize));
        gc.fillText("To start new game press Enter.", 97, 500);
    }

    public void scorePrint(int score) {
        int fontSize = 30;
        gc.setFill(ColorFactory.colorSet(1));
        gc.setFont(new Font(fontSize));
        gc.fillText("SCORE", 250, 55);
        gc.fillText("" + score, 250, 85);
    }

    public void bestScorePrint(int bestScore) {
        int fontSize = 30;
        gc.setFill(ColorFactory.colorSet(1));
        gc.setFont(new Font(fontSize));
        gc.fillText("BEST SCORE", 405, 55);
        gc.fillText("" + bestScore, 405, 85);
    }

}
