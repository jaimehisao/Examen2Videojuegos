package videogame;

/*
 * The Shot is for the player.
 */

/**
 *
 * @author antoniomejorado
 */
import java.awt.Graphics;

public class Bomb extends Item {
    
    private boolean isShot; //Stores wether the shot has bien fired.
    private Game game;
    private Item item;
    
    public Bomb(int x, int y, int width, int height, Item item, Game game) {
        super(x, y, width, height);
        this.item = item;
        this.isShot = false;
        this.game = game;
    }
    
    @Override 
    public void tick(){
        //We check if the shot is fired, if so, we tick, else, nothing.
            if (isShot) {
                setY(y+2);
            } else {
                setX(item.getX()+4);
                setY(item.getY()+5);
            }
            if (y >= 290) {
            isShot = false;
        }
    }
    
    public void render(Graphics g){
        //if(this.visible)
            g.drawImage(Assets.shot, getX(), getY(), getWidth(), getHeight(), null);
    } 
    }
