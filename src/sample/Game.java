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
        if (board.freeList().size() == 0) {
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
        switch (d) {
            case UP:

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
                }
                break;
        }

    }

    //TODO verifications, move loops.

    public List<Tile> moveLeft() {
        List<Tile> list = new ArrayList<Tile>();
        Tile[][] tilesArray = getTilesArray();


        for (int i = 1; i < tilesArray.length; i++) {
            for (int j = 0; j < tilesArray[i].length; j++) {
                if (tilesArray[i][j] != null) {
                    if (tilesArray[i - 1][j] != null) {
                        if (tilesArray[i][j].getValue() == tilesArray[i - 1][j].getValue()) {
                            tilesArray[i - 1][j].setValue(tilesArray[i - 1][j].getValue() * 2);
                            tilesArray[i][j] = null;
                        }
                    } else {
                        tilesArray[i][j].setPosition(new int[]{i - 1, j});
                        tilesArray[i - 1][j] = tilesArray[i][j];
                        tilesArray[i][j] = null;
                    }
                }
            }
        }
        tilesArrayToList(list, tilesArray);
        return list;
    }

    private void tilesArrayToList(List<Tile> list, Tile[][] tilesArray) {
        for (Tile[] aTilesArray : tilesArray) {
            for (Tile anATilesArray : aTilesArray) {
                if (anATilesArray != null) {
                    list.add(anATilesArray);
                }
            }
        }
    }

    public Tile[][] getTilesArray() {
        Tile[][] tileArray = new Tile[4][4];
        for (Tile tile : tiles) {
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
