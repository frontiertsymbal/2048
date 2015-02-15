package sample;

import javafx.scene.paint.Color;

public class ColorFactory {
    public static Color colorSet(int index) {
        switch (index) {
            case 0:
                return Color.web("#cdc1b4");
            case 1:
                return Color.web("#bbada0");
            case 2:
                return Color.web("#eee4da");
            case 4:
                return Color.web("#ede0c8");
            case 8:
                return Color.web("#f2b179");
            case 10:
                return Color.web("#776e65");
            case 16:
                return Color.web("#f59563");
            case 20:
                return Color.web("#f9f6f2");
            case 32:
                return Color.web("#f67c5f");
            case 64:
                return Color.web("#f65e3b");
            case 128:
                return Color.web("#edcf72");
            case 256:
                return Color.web("#edcc61");
            case 512:
                return Color.web("#edc850");
            case 1024:
                return Color.web("#edc53f");
            case 2048:
                return Color.web("#edc22e");
            case 1111:
                return Color.web("#ff6347");
        }
        return Color.web("#cdc1b4");
    }
}
