package sample;

import javafx.scene.paint.Color;

public class ColorFactory {
    public static Color colorSet(int index) {
        switch (index) {
            case 0:
                return Color.web("0xcdc1b4");
            case 1:
                return Color.web("0xbbada0");
            case 2:
                return Color.web("0xeee4da");
            case 4:
                return Color.web("0xede0c8");
            case 8:
                return Color.web("0xf2b179");
            case 10:
                return Color.web("0x776e65");
            case 16:
                return Color.web("0xf59563");
            case 20:
                return Color.web("0xf9f6f2");
            case 32:
                return Color.web("0xf67c5f");
            case 64:
                return Color.web("0xf65e3b");
            case 128:
                return Color.web("0xedcf72");
            case 256:
                return Color.web("0xedcc61");
            case 512:
                return Color.web("0xedc850");
            case 1024:
                return Color.web("0xedc53f");
            case 2048:
                return Color.web("0xedc22e");
        }
        return Color.web("0xcdc1b4");
    }
}
