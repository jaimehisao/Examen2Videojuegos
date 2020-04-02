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
    
    //borrar
    private boolean visible;
    
    private int dx;
    private boolean isAlive;
    
    //OLD - Does not consider direction
    public Player(int x, int y, int width, int height, Game game) {
        super(x, y, width, height);
        this.game = game;
        
        dx = 0;
        visible = true;
    }
    
    //New - Considers direction
    public Player(int x, int y, int direction, int width, int height, Game game) {
        super(x, y, width, height);
        this.direction = direction;
        this.game = game;
        this.dx = 2;
        
        shot = new Shot(x, y, width, height, this, game);
    }
  
    @Override
    public void tick(){
        
        this.shot.tick();
        
        if (game.getKeyManager().right) {
           setX(this.x+dx);
          
        }
        if (game.getKeyManager().left) {
           setX(this.x-dx);
         
        }
    
        // reset x position and y position if colision
        if (getX() + 20 >= game.getWidth()) {
            setX(game.getWidth() - 20);
        }
        else if (getX() <= 5) {
            setX(5);
        }
    }
   

/*
    @Override
    public void tick() {
        
        if(this.game.getKeyManager().left)
            dx = -2;
        if(this.game.getKeyManager().right)
            dx = 2;
            
        x += dx;

        if (x <= 2) 
            x = 2;

        if (x >= Commons.BOARD_WIDTH - 2 * width) 
            x = Commons.BOARD_WIDTH - 2 * width;
    }
    
    public void keyReleased(KeyEvent e) {

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {

            dx = 0;
        }

        if (key == KeyEvent.VK_RIGHT) {

            dx = 0;
        }
    }
    */

    
    public boolean isVisible(){
        return this.visible;
    }
    
    public void die(){
        this.isAlive = false;
    }
    public void render(Graphics g) {
        if(isAlive){
            g.drawImage(Assets.player, getX(), getY(), getWidth(), getHeight(), null);
            shot.render(g);
        }
        /*
        else 
            g.drawImage(Assets.explosion, getX(), getY(), getWidth(), getHeight(), null);
*/
    }
}