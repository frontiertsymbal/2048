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
        board.draw();
        game.gameStart();
        game.draw();

    }

    private void keyListener(Scene scene) {
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                gc.clearRect(0, 0, Const.CANVAS_X, Const.CANVAS_Y);
                switch (event.getCode()) {
                    case UP:
                        game.move(Direction.UP);
                        break;
                    case RIGHT:
                        game.move(Direction.RIGHT);
                        break;
                    case DOWN:
                        game.move(Direction.DOWN);
                        break;
                    case LEFT:
                        game.move(Direction.LEFT);
                        break;
                    case ENTER:
                        game.newGame();
                }
                board.draw();
                game.draw();
                if (Const.gameOver) {
                    board.gameOverDraw();
                }
            }
        });
    }

    public static void main(String[] args) {
        launch(args);
    }
}
