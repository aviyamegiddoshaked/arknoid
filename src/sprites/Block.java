package sprites;

import biuoop.DrawSurface;
import geometry.Line;
import geometry.Point;
import geometry.Rectangle;
import geometry.Velocity;
import interfaces.Collidable;
import interfaces.HitListener;
import interfaces.HitNotifier;
import interfaces.Sprite;
import setting.GameLevel;


import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Block.
 */
public class Block implements Collidable, Sprite, HitNotifier {

    private Rectangle rect;
    private Color col;
    private List<HitListener> listeners;

    /**
     * Instantiates a new Block.
     *
     * @param rect the rect
     * @param col  the col
     */
    public Block(Rectangle rect, Color col) {
        this.rect = rect;
        this.col = col;
        this.listeners= new ArrayList<>();
    }

    @Override
    public Rectangle getCollisionRectangle() {
        return this.rect;
    }

    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        // array of lines of rectangle
        Line[] lines = this.rect.getLines();
        double dx = currentVelocity.getDx();
        double dy = currentVelocity.getDy();

        this.notifyHit(hitter);

        // if ball gets to line bounces other way
        for (int i = 0; i < lines.length; i++) {
            if (lines[i].isPointOnLine(collisionPoint)) {
                if (i % 2 == 0) {
                    dy = dy * -1; }
                else {
                    dx = dx * -1;
                }
            }
        }
        // if either changes a ball bounced and hit
        return new Velocity(dx, dy);
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.BLACK);
        d.drawRectangle((int) this.rect.getUpperLeft().getX(), (int) this.rect.getUpperLeft().getY(), (int) this.rect.getWidth(), (int) this.rect.getHeight());
        d.setColor(this.col);
        d.fillRectangle((int) this.rect.getUpperLeft().getX(), (int) this.rect.getUpperLeft().getY(), (int) this.rect.getWidth(), (int) this.rect.getHeight());
    }

    @Override
    public void timePassed() {

    }


    /**
     * Add the paddle to the game.
     *
     * @param g the g
     */
// Add this paddle to the game.
    public void addToGame(GameLevel g) {
        g.addCollidable(this);
        g.addSprite(this);
    }


    @Override
    public void addHitListener(HitListener hl) {
        this.listeners.add(hl);
    }

    @Override
    public void removeHitListener(HitListener hl) {
        this.listeners.remove(hl);
    }

    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<HitListener>(this.listeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }


    /**
     * Remove from game.
     *
     * @param game the game
     */
    public void removeFromGame(GameLevel game){
        game.removeCollidable(this);
        game.removeSprite(this);
    }
}

