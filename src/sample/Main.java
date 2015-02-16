package sample;

import javafx.application.Application;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application {

    public static GraphicsContext gc;
    public Board board;
    public Game game;

    @Override
    public void start(Stage primaryStage) throws Exception {
        final Canvas canvas = new Canvas(Const.CANVAS_X, Const.CANVAS_Y);
        final BorderPane group = new BorderPane();
        group.setCenter(canvas);
        final Scene scene = new Scene(group);
        gc = canvas.getGraphicsContext2D();
        primaryStage.setTitle("2048");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();

        Const.arrayInit();

        board = new Board(gc);
        game = new Game(gc, board);
        keyListener(scene);
        game.gameStart();
        drawGame();
    }

    private void keyListener(Scene scene) {
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                gc.clearRect(0, 0, Const.CANVAS_X, Const.CANVAS_Y);
                //TODO isPossibleAddiction
                switch (event.getCode()) {
                    case UP:
                        if (game.isMovePossible()) {
                            game.move(Direction.UP);
                        }
                        break;
                    case RIGHT:
                        if (game.isMovePossible()) {
                            game.move(Direction.RIGHT);
                        }
                        break;
                    case DOWN:
                        if (game.isMovePossible()) {
                            game.move(Direction.DOWN);
                        }
                        break;
                    case LEFT:
                        if (game.isMovePossible()) {
                            game.move(Direction.LEFT);
                        }
                        break;
                    case ENTER:
                        game.newGame();
                        break;
                }
                drawGame();

                if (game.getScore() > game.getBestScore()) {
                    game.setBestScore(game.getScore());
                }
                
                if (game.isWin()) {
                    board.youWinDraw();
                } else {
                    if (!game.isMovePossible()) {
                        board.gameOverDraw();
                    }
                }

            }
        });
    }

    public void drawGame() {
        board.draw();
        game.draw();
        board.scorePrint(game.getScore());
        board.bestScorePrint(game.getBestScore());
    }

    public static void main(String[] args) {
        launch(args);
    }
}
