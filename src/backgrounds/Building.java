package backgrounds;

import geometry.Line;
import geometry.Point;
import interfaces.Sprite;
import setting.GameLevel;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * The type Building.
 */
public class Building implements Sprite {
    /**
     * The Blinks.
     */
    private List<Blink> blinks = new ArrayList<>();
    /**
     * The Blink center.
     */
    private Point blinkCenter;
    /**
     * The First floor width.
     */
    private int firstFloorWidth;
    /**
     * The Frames.
     */
    private int frames;
    /**
     * The Blinks rate.
     */
    private int blinksRate;
    /**
     * The Random window.
     */
    private int randomWindow;
    /**
     * The Drops.
     */
    private List<Line> drops = new ArrayList<>();

    private List<Integer> numbersDrops = new ArrayList<>();

    /**
     * Instantiates a new Building.
     *
     * @param blinkCenter     the blink center
     * @param firstFloorWidth the first floor width
     */
    public Building(Point blinkCenter, int firstFloorWidth) {
        this.blinkCenter = blinkCenter;
        this.firstFloorWidth = firstFloorWidth;
        this.frames = 0;
        this.blinksRate = 50;
        this.randomWindow = -1;
    }

    /**
     * Draw on.
     *
     * @param d the d
     */
    @Override
    public void drawOn(biuoop.DrawSurface d) {
        //color backgrounds
        // d.setColor(new Color(34, 139, 34));
        d.setColor(Color.BLACK);
        d.fillRectangle(0, 0, d.getWidth(), d.getHeight());

        //tallest building
        // circle positions
        int xBlinkCenter = (int) this.blinkCenter.getX();
        int yBlinkCenter = (int) this.blinkCenter.getY();

        d.setColor(new Color(169, 169, 169));

        d.fillRectangle(xBlinkCenter - 5, yBlinkCenter, 10, 600 - yBlinkCenter);

        //buildings
        d.setColor(Color.darkGray);
        int xBuilding = xBlinkCenter - (this.firstFloorWidth / 2);
        int yBuilding = 600 - (this.firstFloorWidth * 2);
        d.fillRectangle(xBuilding, yBuilding, this.firstFloorWidth, this.firstFloorWidth * 2);
        d.setColor(new Color(105, 105, 105));
        d.fillRectangle(xBlinkCenter - (this.firstFloorWidth / 4), 600 - (this.firstFloorWidth * 3),
                (this.firstFloorWidth / 2), this.firstFloorWidth
        );

        //Blinks
        List<Blink> blinksCopy = new ArrayList<>(this.blinks);
        for (Blink blink : blinksCopy) {
            blink.drawOn(d);
        }
        if (this.frames % this.blinksRate == 0) {
            createMoreBlinks();
        }
        // every 300 frames switch window so theres more (every 5 seconds)
        Random rnd = new Random();
        if (frames % 300 == 1) {
            this.randomWindow = rnd.nextInt(25);
        }
        int windowWidth = 15;
        int startWindowsX = xBuilding + 6;
        int startWindowsY = yBuilding + 6;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (this.randomWindow / 5 == i && randomWindow % 5 == j) {
                    d.setColor(Color.BLACK);
                } else {
                    d.setColor(Color.WHITE);
                }
                d.fillRectangle(startWindowsX + (j * (windowWidth + 3)), startWindowsY + (2 * i * (windowWidth + 2)),
                        windowWidth, 2 * windowWidth
                );
            }
        }
        // background  rain
        List<Line> copyDrops = new ArrayList<>(this.drops);
        for (int i = 0; i < copyDrops.size(); i++) {
            d.setColor(Color.white);
            int y = (int) copyDrops.get(i).start().getY();
            int x = (int) this.drops.get(i).start().getX();
            d.fillOval(x, y, 3, 6);
            d.fillCircle(x, y + 3, 3);
        }

        if (this.frames % 5 == 1) {
            for (int i = 0; i < 5; i++) {
                int x = rnd.nextInt(GameLevel.WIDTH) + 25;
                drops.add(new Line(x, 0, x, -5));
                this.numbersDrops.add(rnd.nextInt(10));
            }
        }

    }

    /**
     * Create more blinks.
     */
    private void createMoreBlinks() {
        blinks.add(new Blink(this.blinkCenter, 5, Color.white));

    }

    /**
     * Time passed.
     */
    @Override
    public void timePassed() {
        List<Blink> blinksCopy = new ArrayList<>(this.blinks);
        for (Blink blink : blinksCopy) {
            blink.timePassed();
            if (blink.getSize() > 150) {
                blinks.remove(blink);
            }
        }
        if (this.frames % 50 == 0 && this.blinksRate > 1) {
            this.blinksRate--;
            if (this.blinksRate == 1) {
                this.blinksRate = 50;
            }
        }
        // drop is lines
        List<Line> copyDrop = new ArrayList<>(this.drops);
        for (Line drop : copyDrop) {
            double x = drop.start().getX();
            double y = drop.start().getY();
            int index = this.drops.indexOf(drop);
            this.drops.remove(drop);
            int value = this.numbersDrops.get(index);
            this.numbersDrops.remove(index);
            Line newDrop = new Line(x, y + 1, x, y - 9);
            this.drops.add(newDrop);
            this.numbersDrops.add(value);
            if (newDrop.end().getY() >= GameLevel.HEIGHT) {
                this.drops.remove(newDrop);
                this.numbersDrops.remove(index);
            }
        }
        this.frames++;
    }
}
