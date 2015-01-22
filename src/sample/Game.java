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
        scorePrint();
    }

    //TODO move logic;

    public void move(Direction d) {
        switch (d) {
            case UP:
                if (isMovePossible()) {
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
                    arrayPrint();
                }
                break;
            case RIGHT:

                break;
            case DOWN:

                break;
            case LEFT:
                if (isMovePossible()) {
                    tiles = moveLeft();
                    board.setBusy(tiles);
                    newTile();
                    arrayPrint();
                }
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

    public boolean isMovePossible() {
        for (Tile tile : tiles) {
            int[] arr = tile.getPosition();
            if (arr[0] == 3 && arr[1] == 3) {
                continue;
            }

            if (arr[0] == 3) {
                Tile tmp = getTile(new int[]{arr[0], arr[1] + 1});
                if (tmp == null) {
                    return true;
                } else {
                    if (tile.getValue() == tmp.getValue()) {
                        return true;
                    }
                }
            }

            if (arr[1] == 3) {
                Tile tmp = getTile(new int[]{arr[0] + 1, arr[1]});
                if (tmp == null) {
                    return true;
                } else {
                    if (tile.getValue() == tmp.getValue()) {
                        return true;
                    }
                }
            }

            Tile tmp = getTile(new int[]{arr[0] + 1, arr[1]});
            if (tmp == null) {
                return true;
            } else {
                if (tile.getValue() == tmp.getValue()) {
                    return true;
                } else {
                    Tile tmp1 = getTile(new int[]{arr[0], arr[1] + 1});
                    if (tmp1 == null) {
                        return true;
                    } else {
                        if (tile.getValue() == tmp1.getValue()) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    public Tile[][] getTilesArray() {
        Tile[][] tileArray = new Tile[4][4];
        for (int i = 0; i < tiles.size(); i++) {
            tileArray[tiles.get(i).getPosition()[0]][tiles.get(i).getPosition()[1]] = tiles.get(i);
        }
        return tileArray;
    }

    public void arrayPrint() { //FOT TEST
        Tile[][] tilesArray = getTilesArray();
        for (int i = 0; i < tilesArray.length; i++) {
            for (int j = 0; j < tilesArray[i].length; j++) {
                if (tilesArray[i][j] != null) {
                    System.out.println(tilesArray[i][j].toString());
                }
            }
        }
        System.out.println("=========================");
    }

    public List<Tile> moveLeft() {
        List<Tile> list = new ArrayList<Tile>();
        Tile[][] tilesArray = getTilesArray();
        for (int i = 0; i < tilesArray.length; i++) {
            for (int j = 0; j < tilesArray[i].length; j++) {
                if (tilesArray[i][j] != null && j < 3) {
                    if (tilesArray[i][j + 1] != null) {
                        if (tilesArray[i][j].getValue() != tilesArray[i][j + 1].getValue()) {
                            continue;
                        } else {
                            tilesArray[i][j].setValue(tilesArray[i][j].getValue()*2);
                            tilesArray[i][j + 1] = null;
                        }
                    }
                } else {
                    if (tilesArray[i][j] == null && tilesArray[i][j+1] != null) {
                        tilesArray[i][j] = tilesArray[i][j+1];
                        tilesArray[i][j+1] = null;
                    }
                }
            }
        }
        for (int i = 0; i < tilesArray.length; i++) {
            for (int j = 0; j < tilesArray[i].length; j++) {
                if (tilesArray[i][j] != null) {
                   list.add(tilesArray[i][j]);
                }
            }
        }
        return list;
    }

    public Tile getTile(int[] arr) {
        for (Tile tile : tiles) {
            if (tile.getPosition()[0] == arr[0] && tile.getPosition()[1] == arr[1]) {
                return tile;
            }
        }
        return null;
    }

}
