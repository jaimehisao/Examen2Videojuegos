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
<<<<<<< HEAD
        setDestroyed(true);
        
=======
        //setDestroyed(true);
>>>>>>> ea9f2cc8f5c701b1f130f63f43626e5e586770a5
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
