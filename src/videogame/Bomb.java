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
    
    private boolean visible; //Stores wether the shot has bien fired.
    private Game game;
    private Alien alien;
    public int speed;
    
    /**
     * Constructor for Bomb
     * @author Jaime Hisao & Rodrigo Casale
     * @param x X Coordinate for Shot
     * @param y Y Coordinate for Shot
     * @param width Width of the Game
     * @param height Height of the Game
     * @param alien Alien Object
     * @param game Current Game Object
     * @param speed The speed for shot
     */
    public Bomb(int x, int y, int width, int height, Alien alien, Game game, int speed) {
        super(x, y, width, height);
        this.alien = alien;
        this.visible = true;
        this.game = game;
        
        this.speed = speed;
    }
    
        
    /**
     * Sets the speed of the Bomb Object
     * @param speed int value
     */
    public void setSpeed(int speed){
        this.speed = speed;
    }
    
    /**
     * @return Int value for the speed
     */
    public int getSpeed(){
        return speed;
    }
    
    /**
     * @return Int value for the speed
     */
    public void setAlien(Alien alien){
        this.alien = alien;
    }
    
    /**
     * Tick for Bomb Object
     * @author Jaime Hisao & Rodrigo Casale
     */
    @Override 
    public void tick(){
        //We check if the shot is fired, if so, we tick, else, nothing.
        setY(getY()+speed);
        if (!visible && alien.isVisible()) {
            this.visible = true;
            setX(alien.getX()+4);
            setY(alien.getY()+5);
        }
        if (y >= 290) {
            setX(alien.getX()+4);
            setY(alien.getY()+5);
        }
    }
    
    
    /**
     * Returns wether the object is visible
     * @return Boolean value if the object is visible or not.
     */
    public boolean isVisible(){
        return this.visible;
    }
    
    /**
     * Render for Bomb Object if it's visible
     * @param g Graphics object used in Game Class
     */
    public void render(Graphics g){
        if(this.isVisible())
            g.drawImage(Assets.bomb, getX(), getY(), getWidth(), getHeight(), null);
    } 
}
