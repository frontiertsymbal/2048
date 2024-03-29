package sample;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Const {
    private static  Random rand = new Random();
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
    public static int[] valueArray = new int[20];
    private static boolean[][] free = new boolean[4][4];

    public static void arraysInit() {
        positionArrayInit();
        valueArrayInit();
        boardFreeArrayInit();
    }

    private static void positionArrayInit() {
        int y = CASE_Y + Const.INTERVAL;
        int x = CASE_X + Const.INTERVAL;
        for (int i = 0; i < 4; i++) {
            arrX[i] = x;
            arrY[i] = y;
            x += Const.INTERVAL + Const.TILE_SIZE;
            y += Const.INTERVAL + Const.TILE_SIZE;
        }
    }

    private static void valueArrayInit() {
        int rand1 = rand.nextInt(20);
        int rand2 = rand.nextInt(20);
        for (int i = 0; i < valueArray.length; i++) {
            if (i == rand1 || i == rand2) {
                valueArray[i] = 4;
            } else {
                valueArray[i] = 2;
            }
        }
    }

    public static int random (int a) {
        return rand.nextInt(a);
    }

    public static void setBusy(List<Tile> list) {
        boardFreeArrayInit();
        for (Tile aList : list) {
            free[aList.getPosition()[0]][aList.getPosition()[1]] = false;
        }
    }

    public static List<int[]> freeList() {
        List<int[]> freeList = new ArrayList<int[]>();
        for (int i = 0; i < free.length; i++) {
            for (int j = 0; j < free[i].length; j++) {
                if (free[i][j]) {
                    freeList.add(new int[]{i, j});
                }
            }
        }
        return freeList;
    }

    private static void boardFreeArrayInit() {
        for (int i = 0; i < free.length; i++) {
            for (int j = 0; j < free[i].length; j++) {
                free[i][j] = true;
            }
        }
    }

}
