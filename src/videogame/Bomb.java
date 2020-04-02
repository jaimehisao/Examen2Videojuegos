/*
 *The bomb is for the Aliens
 */
package videogame;

import java.awt.Graphics;

/**
 *
 * @author rodrigocasale
 */
public class Bomb extends Item{
    
    private boolean destroyed;

    public Bomb(int x, int y, int width, int height) {
        super(x, y, width, height);
        //setDestroyed(true);
    }


    public void die(){
        this.destroyed = true;
    }
    public void setDestroyed(boolean destroyed) {

        this.destroyed = destroyed;
    }
 
    public boolean isDestroyed() {

        return destroyed;
    }
        
    public void render(Graphics g) {
        g.drawImage(Assets.bomb, getX(), getY(), getWidth(), getHeight(), null);
    }

    @Override
    public void tick() {
        this.y += 1;
        if(this.y >= Commons.GROUND - this.height){
            this.die();
        }
    }
}
