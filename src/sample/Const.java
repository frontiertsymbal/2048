package sample;

public class Const {
    public static final int CANVAS_X = 600;
    public static final int CANVAS_Y = 700;
    public static final int TILE_SIZE = 125;
    public static final int INTERVAL = 10;
    public static final int ANGLE = 15;
    public static final int CASE_X = 25;
    public static final int CASE_Y = 125;
    public static final int CASE_SIZE = 550;
    public static int[] arrX = new int[4];
    public static int[] arrY = new int[4];
    public static int[] valueArray = new int[]{4,2,2,4,2,2,4,2,2,4};

    public static void arrayInit() {
        int y = CASE_Y + Const.INTERVAL;
        int x = CASE_X + Const.INTERVAL;
        for (int i = 0; i < 4; i++) {
            arrX[i] = x;
            arrY[i] = y;
            x += Const.INTERVAL + Const.TILE_SIZE;
            y += Const.INTERVAL + Const.TILE_SIZE;
        }
    }

}
