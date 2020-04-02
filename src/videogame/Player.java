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
    
    //OLD - Does not consider direction
    public Player(int x, int y, int width, int height, Game game) {
        super(x, y, width, height);
        this.game = game;
        
        dx = 0;
        alive = true;
        
    }
    
    //New - Considers direction
    public Player(int x, int y, int direction, int width, int height, Game game) {
        super(x, y, width, height);
        this.direction = direction;
        this.game = game;
        this.dx = 1;
        alive = true;
        shot = new Shot(x, y, 2, 10, this, game);
    }
    public Shot getShot(){
        return this.shot;
    }
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
        }
    }
   

    public boolean isAlive(){
        return this.alive;
    }
    
    public void die(){
        this.alive = false;
    }
    public void render(Graphics g) {
        if(isAlive()){
            g.drawImage(Assets.player, getX(), getY(), getWidth(), getHeight(), null);
            shot.render(g);
        }
        else 
            g.drawImage(Assets.explosion, getX(), getY(), getWidth(), getHeight(), null);
    }
}