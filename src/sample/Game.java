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
        Const.gameOver = false;
        gameStart();
    }

    public void gameStart() {
        newTile();
        newTile();
    }

    public void newTile() {
        if (board.freeList().size() == 0 && isMovePossible()) {
            Const.gameOver = true;
        } else {
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

    public void move(Direction d) {
        Tile[][] tilesArray = getTilesArray();
        switch (d) {
            case UP:
                if (isMovePossible()) {
                    tiles = moveUp(tilesArray);
                    board.setBusy(tiles);
                    newTile();
                }
                break;
            case RIGHT:
                if (isMovePossible()) {
                    tiles = moveRight(tilesArray);
                    board.setBusy(tiles);
                    newTile();
                }
                break;
            case DOWN:
                if (isMovePossible()) {
                    tiles = moveDown(tilesArray);
                    board.setBusy(tiles);
                    newTile();
                }
                break;
            case LEFT:
                if (isMovePossible()) {
                    tiles = moveLeft(tilesArray);
                    board.setBusy(tiles);
                    newTile();
                }
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

    public Tile getTile(int[] arr) {
        for (Tile tile : tiles) {
            if (tile.getPosition()[0] == arr[0] && tile.getPosition()[1] == arr[1]) {
                return tile;
            }
        }
        return null;
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
