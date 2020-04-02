package videogame;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author antoniomejorado
 */
import java.awt.Graphics;
import javax.swing.ImageIcon;
import java.awt.event.KeyEvent;

public class Player extends Item {
    
    private Game game;
    private int direction;
    private Shot shot;
    
    
    private int dx;
    private boolean alive;
    
    private int lives;
    private int timer;
    
    
    /**
     * Player Class Constructor
     * @author Jaime Hisao & Rodrigo Casale
     * @param x X Coordinate to create Alien
     * @param y Y Coordinate to create Alien
     * @param width Width of the Game
     * @param height height of the Game
     * @param game Game Object
     */
    //New - Considers direction
    public Player(int x, int y, int direction, int width, int height, Game game) {
        super(x, y, width, height);
        this.direction = direction;
        this.game = game;
        this.dx = 1;
        alive = true;
        shot = new Shot(x, y, 1, 6, this, game);
        
        lives = 5;
    }
    
    /**
     * @author Jaime Hisao & Rodrigo Casale
     * @return returns the Shot object
     */
    public Shot getShot(){
        return this.shot;
    }
    
    /**
     * Ticks the Player Object
     * @author Jaime Hisao & Rodrigo Casale
     */
    @Override
    public void tick(){
        if(isAlive()){
            this.shot.tick();

            if (game.getKeyManager().right) {
               setX(this.x+dx);
            }
            if (game.getKeyManager().left) {
               setX(this.x-dx);

            }
            if (game.getKeyManager().space && !shot.isVisible()) {
               shot.shoot(this.x, this.y);

            }
            // reset x position and y position if colision
            if (getX() + 20 >= game.getWidth()) {
                setX(game.getWidth() - 20);
            }
            else if (getX() <= 5) {
                setX(5);
            }
        }else{
            this.timer++;
        }
        
        if(timer>=100 && this.lives >0){
            this.alive = true;
            this.timer = 0;
            setX(Commons.BOARD_WIDTH/2);
        }
    }
   

    /**
     * @author Jaime Hisao & Rodrigo Casale
     * @return returns wether the player is alive or not
     */
    public boolean isAlive(){
        return this.alive;
    }
    
    /**
     * Kills the Player
     * @author Jaime Hisao & Rodrigo Casale
     */
    public void die(){
        this.alive = false;
        this.lives--;
    }
    
    /**
     * Render for the Player
     * If is alive it renders the player if not it renders the explotion
     * @author Jaime Hisao & Rodrigo Casale
     * @param g Graphics Object used in Game Class
     */
    public void render(Graphics g) {
        if(isAlive()){
            g.drawImage(Assets.player, getX(), getY(), getWidth(), getHeight(), null);
            shot.render(g);
        }
        else 
            g.drawImage(Assets.explosion, getX(), getY(), getWidth(), getHeight(), null);
    }
    
    
    /**
     * @author Jaime Hisao & Rodrigo Casale
     * @return returns the Shot object
     */
    public int getLives(){
        return this.lives;
    }
    
    /**
     * @author Jaime Hisao & Rodrigo Casale
     * @param lives int
     */
    public void setLives(int lives){
        this.lives = lives;
    }
    
}