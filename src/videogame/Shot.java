package videogame;

/*
 * The Shot is for the player.
 */

/**
 *
 * @author antoniomejorado
 */
import java.awt.Graphics;

public class Shot extends Item {
    
    private boolean visible; //Stores wether the shot has bien fired.
    private Game game;
    private Item item;
    
    public Shot(int x, int y, int width, int height, Item item, Game game) {
        super(x, y, width, height);
        this.item = item;
        this.isShot = false;
        this.game = game;
    }
    public void setVisibility(boolean visible){
        this.visible = visible;
    }
    private void initShot(int x, int y) {

        int H_SPACE = 6;
        setX(x + H_SPACE);

        int V_SPACE = 1;
        setY(y - V_SPACE);
    }
    @Override 
    public void tick(){
        //We check if the shot is fired, if so, we tick, else, nothing.
            if (isShot) {
                setY(y+dy);
            } else {
                setX(item.getX()+4);
                setY(item.getY()+5);
            }
            if (y >= 290) {
            isShot = false;
        }
    }
    
    
    
    
    
    
    
    
    
    public void render(Graphics g){
            g.drawImage(Assets.shot, getX(), getY(), getWidth(), getHeight(), null);
    }
    
    public void die(){
        visible = false;
    }
    
    }
