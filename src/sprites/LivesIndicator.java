package sprites;

import biuoop.DrawSurface;
import hitlisteners.Counter;
import interfaces.Sprite;
import setting.GameLevel;
import java.awt.Color;

public class LivesIndicator implements Sprite {

    private Counter lives;

    public LivesIndicator(Counter lives) {
        this.lives = lives;
    }

    public void drawOn(DrawSurface d) {
        d.setColor(Color.WHITE);
        d.fillRectangle(0, 10, GameLevel.WIDTH / 3, 20);
        d.setColor(Color.BLACK);
        d.drawText(30, 25, "Lives:" + String.valueOf(this.lives.getValue()), 16);
    }

    @Override
    public void timePassed() {

    }
}
