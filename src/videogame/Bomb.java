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
    
    public Bomb(int x, int y, int width, int height, Alien alien, Game game, int speed) {
        super(x, y, width, height);
        this.alien = alien;
        this.visible = true;
        this.game = game;
        
        this.speed = speed;
    }
    public void setSpeed(int speed){
        this.speed = speed;
    }
    public int getSpeed(){
        return speed;
    }
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
    public boolean isVisible(){
        return this.visible;
    }
    public void render(Graphics g){
        if(this.isVisible())
            g.drawImage(Assets.shot, getX(), getY(), getWidth(), getHeight(), null);
    } 
    }
