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
        tiles.add(new Tile(gc));
        board.setBusy(tiles.get(tiles.size() - 1).getPosition());
    }

    public void draw() {
        for (int i = 0; i < tiles.size(); i++) {
            tiles.get(i).draw();
        }
    }

    public void move(Direction d) {
        switch (d) {
            case UP:
                for (Tile tile : tiles) {
                    int[] arr = tile.getPosition();
                    board.setFree(arr);
                    if (arr[1] == 0) {
                        board.setBusy(arr);
                        return;
                    }
                    arr[1]--;
                    if (board.isFree(arr)) {
                        tile.setPosition(arr);
                        board.setBusy(arr);
                    } else return;
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
        for (int i = 0; i < tiles.size(); i++) {
            score += tiles.get(i).getValue();
        }
        int fontSize = 30;
        gc.setFill(ColorFactory.colorSet(1));
        gc.setFont(new Font(fontSize));
        gc.fillText("SCORE", 440, 55);
        gc.fillText("" + score, 440, 85);
    }
}
