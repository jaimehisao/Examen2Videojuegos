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
    //borrar
    private boolean visible;
    
    private int dx;
    private boolean isAlive;
    
    public Player(int x, int y, int width, int height, Game game) {
        super(x, y, width, height);
        this.game = game;
        isAlive = true;
        dx = 0;
        visible = true;
    }
  

    @Override
    public void tick() {
        
        System.out.println(this.game.getKeyManager().left);
        if(this.game.getKeyManager().left){
            dx = -2;
        }
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
    
    
    public boolean isVisible(){
        return this.visible;
    }
    
    public void die(){
        this.isAlive = false;
    }
    public void render(Graphics g) {
        if(this.visible)
            g.drawImage(Assets.player, getX(), getY(), getWidth(), getHeight(), null);
        else 
            g.drawImage(Assets.explosion, getX(), getY(), getWidth(), getHeight(), null);
    }
}