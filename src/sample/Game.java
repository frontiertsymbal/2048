package sample;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.text.Font;

import java.util.ArrayList;
import java.util.List;

public class Game {

    private List<Tile> tiles = new ArrayList<Tile>(16);
    private GraphicsContext gc;
    private Board board;


    public Game(GraphicsContext gc, Board board) {
        this.gc = gc;
        this.board = board;
    }

    public void gameStart() {
        newTile();
        newTile();
    }

    public void newTile() {
        tiles.add(new Tile(board.freeList().get(Const.random(board.freeList().size())), gc));
        board.setBusy(tiles);
    }

    public void draw() {
        for (Tile tile : tiles) {
            tile.draw();
        }
    }

    public void move(Direction d) {
        switch (d) {
            case UP:
                if (board.canMove()) {
                    for (Tile tile : tiles) {
                        int[] arr = tile.getPosition();
                        for (int i = 0; i < 4; i++) {
                            if (arr[1] == 0) {
                                break;
                            }
                            arr[1]--;
                            if (!board.isFree(arr)) {
                                arr[1]++;
                                break;
                            }
                        }
                        tile.setPosition(arr);
                    }
                    board.setBusy(tiles);
                    newTile();
                }
                break;
            case RIGHT:

                break;
            case DOWN:

                break;
            case LEFT:

                break;
        }

    }

    public void scorePrint() {
        int score = 0;
        for (Tile tile : tiles) {
            score += tile.getValue();
        }
        int fontSize = 30;
        gc.setFill(ColorFactory.colorSet(1));
        gc.setFont(new Font(fontSize));
        gc.fillText("SCORE", 440, 55);
        gc.fillText("" + score, 440, 85);
    }

}
