package sample;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.text.Font;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Game {

    private List<Tile> tiles = new ArrayList<Tile>(16);
    private GraphicsContext gc;
    private Board board;
    private Random rand = new Random();


    public Game(GraphicsContext gc, Board board) {
        this.gc = gc;
        this.board = board;
    }

    public void gameStart() {
        newTile();
        newTile();
    }

    public void newTile() {
        //TODO review;

        tiles.add(new Tile(board.freeList().get(rand.nextInt(board.freeList().size())), gc));
        board.setBusy(tiles);
//        while(true) {
//            int[] arr = new int[2];
//            arr[0] = rand.nextInt(4);
//            arr[1] = rand.nextInt(4);
//            if (board.isFree(arr)) {
//                tiles.add(new Tile(arr, gc));
//                board.setBusy(tiles);
//                break;
//            }else return;
//        }
    }

    public void draw() {
        for (Tile tile : tiles) {
            tile.draw();
        }
    }

    public void move(Direction d) {
        switch (d) {
            case UP:
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

    public List<Tile> getList() {
        return tiles;
    }
}
