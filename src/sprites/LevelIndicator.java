package sprites;

import biuoop.DrawSurface;
import interfaces.Sprite;
import setting.GameLevel;

import java.awt.Color;

public class LevelIndicator implements Sprite {

    private String levelName;

    public LevelIndicator(String levelName) {
        this.levelName = levelName;
    }

    public void drawOn(DrawSurface d) {
        d.setColor(Color.WHITE);
        d.fillRectangle(GameLevel.WIDTH * 2  / 3, 10, GameLevel.WIDTH / 3, 20);
        d.setColor(Color.BLACK);
        d.drawText(GameLevel.WIDTH * 2 / 3 + 10, 25, "Level Name:" + this.levelName, 16);
    }

    @Override
    public void timePassed() {

    }


}
