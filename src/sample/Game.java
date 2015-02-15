package sample;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.text.Font;

import java.util.ArrayList;
import java.util.List;

public class Game {

    private List<Tile> tiles = new ArrayList<Tile>(16);
    private GraphicsContext gc;
    private Board board;

    public int count = 0;
    public boolean[] need = {true, true, true, true};


    public Game(GraphicsContext gc, Board board) {
        this.gc = gc;
        this.board = board;
    }

    public void newGame() {
        tiles = new ArrayList<Tile>(16);
        board.setBusy(tiles);
        gameStart();
    }

    public void gameStart() {
        newTile();
        newTile();
    }

    public void newTile() {
        if (board.freeList().size() != 0) {
            tiles.add(new Tile(board.freeList().get(Const.random(board.freeList().size())), gc));
            board.setBusy(tiles);
        }
    }

    public void draw() {
        for (Tile tile : tiles) {
            tile.draw();
        }
        scorePrint();
    }

    public boolean isMovePossible() {
        Tile[][] tilesArray = getTilesArray();
        int[][] arr = new int[4][4];
        for (int i = 0; i < tilesArray.length; i++) {
            for (int j = 0; j < tilesArray[i].length; j++) {
                if (tilesArray[i][j] == null) {
                    arr[i][j] = 0;
                } else {
                    arr[i][j] = tilesArray[i][j].getValue();
                }
            }
        }

        for (int i = 0; i <= 3; i++) {
            for (int j = 0; j <= 3; j++) {
                if (i != 3 && j != 3) {
                    if (arr[i][j] == 0 || arr[i + 1][j] == 0 || arr[i][j + 1] == 0) {
                        return true;
                    }
                    if (arr[i][j] == arr[i + 1][j] || arr[i][j] == arr[i][j + 1]) {
                        return true;
                    }
                } else {
                    if (i == 3 && j != 3) {
                        if (arr[i][j + 1] == 0 || arr[i][j] == arr[i][j + 1]) {
                            return true;
                        }
                    }
                    if (j == 3 && i != 3) {
                        if (arr[i + 1][j] == 0 || arr[i][j] == arr[i + 1][j]) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    public void move(Direction d) {
        Tile[][] tilesArray = getTilesArray();
        switch (d) {
            case UP:
                tiles = moveUp(tilesArray);
                board.setBusy(tiles);
                newTile();
                break;
            case RIGHT:
                tiles = moveRight(tilesArray);
                board.setBusy(tiles);
                newTile();
                break;
            case DOWN:
                tiles = moveDown(tilesArray);
                board.setBusy(tiles);
                newTile();
                break;
            case LEFT:
                tiles = moveLeft(tilesArray);
                board.setBusy(tiles);
                newTile();
                break;
        }
    }

    public List<Tile> moveLeft(Tile[][] tilesArray) {
        for (int j = 0; j < tilesArray.length; j++) {
            for (int i = 1; i < tilesArray[j].length; i++) {
                if (tilesArray[i][j] != null) {
                    if (tilesArray[i - 1][j] != null) {
                        if (tilesArray[i][j].getValue() == tilesArray[i - 1][j].getValue() && need[j]) {
                            tilesArray[i - 1][j].setValue(tilesArray[i - 1][j].getValue() * 2);
                            tilesArray[i][j] = null;
                            need[j] = false;
                        }
                    } else {
                        tilesArray[i][j].setPosition(new int[]{i - 1, j});
                        tilesArray[i - 1][j] = tilesArray[i][j];
                        tilesArray[i][j] = null;
                    }
                }
            }
        }
        count++;
        if (count <= 3) {
            tiles = moveLeft(tilesArray);
        } else {
            count = 0;
        }
        for (int i = 0; i < need.length; i++) {
            need[i] = true;
        }

        return tilesArrayToList(tilesArray);
    }

    public List<Tile> moveRight(Tile[][] tilesArray) {
        tilesArray = arrayRotate(tilesArray);
        tilesArray = arrayRotate(tilesArray);
        tilesArray = getTilesArray(moveLeft(tilesArray));
        tilesArray = arrayRotate(tilesArray);
        tilesArray = arrayRotate(tilesArray);

        return tilesArrayToList(tilesArray);

    }

    public List<Tile> moveUp(Tile[][] tilesArray) {
        tilesArray = arrayRotate(tilesArray);
        tilesArray = getTilesArray(moveLeft(tilesArray));
        tilesArray = arrayRotate(tilesArray);
        tilesArray = arrayRotate(tilesArray);
        tilesArray = arrayRotate(tilesArray);

        return tilesArrayToList(tilesArray);

    }

    public List<Tile> moveDown(Tile[][] tilesArray) {
        tilesArray = arrayRotate(tilesArray);
        tilesArray = arrayRotate(tilesArray);
        tilesArray = arrayRotate(tilesArray);
        tilesArray = getTilesArray(moveLeft(tilesArray));
        tilesArray = arrayRotate(tilesArray);

        return tilesArrayToList(tilesArray);

    }

    public Tile[][] arrayRotate(Tile[][] tiles) {
        Tile[][] result = new Tile[4][4];
        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles[i].length; j++) {
                result[j][tiles.length - 1 - i] = tiles[i][j];
                int[] arr = {j, tiles.length - 1 - i};
                if (result[j][tiles.length - 1 - i] != null) {
                    result[j][tiles.length - 1 - i].setPosition(arr);
                }
            }
        }
        return result;
    }

    private List<Tile> tilesArrayToList(Tile[][] tilesArray) {
        List<Tile> list = new ArrayList<Tile>();
        for (Tile[] aTilesArray : tilesArray) {
            for (Tile anATilesArray : aTilesArray) {
                if (anATilesArray != null) {
                    list.add(anATilesArray);
                }
            }
        }
        return list;
    }

    public Tile[][] getTilesArray() {
        Tile[][] tileArray = new Tile[4][4];
        for (Tile tile : tiles) {
            tileArray[tile.getPosition()[0]][tile.getPosition()[1]] = tile;
        }
        return tileArray;
    }

    public Tile[][] getTilesArray(List<Tile> list) {
        Tile[][] tileArray = new Tile[4][4];
        for (Tile tile : list) {
            tileArray[tile.getPosition()[0]][tile.getPosition()[1]] = tile;
        }
        return tileArray;
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
