package spaceinvaders;

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

    //borrar
    private boolean visible;
    
    private int width;
    private int dx;
    private boolean isAlive;
    public Player(int x, int y, int width, int height, Game game) {
        initPlayer();
    }
  
    private void initPlayer() {

 
        setImage(ImageLoader.loadImage("/resources/player.png"));
        width = getImage().getWidth();

        int START_X = 270;
        setX(START_X);

        int START_Y = 280;
        setY(START_Y);

        super(x, 280, width, height);
        dx = 0;
        
        visible = true;
    }

    @Override
    public void tick() {

        x += dx;

        if (x <= 2) {

            x = 2;
        }

        if (x >= Commons.BOARD_WIDTH - 2 * width) {

            x = Commons.BOARD_WIDTH - 2 * width;
        }
    }

    public void keyPressed(KeyEvent e) {

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {

            dx = -2;
        }

        if (key == KeyEvent.VK_RIGHT) {

            dx = 2;
        }
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
        if(isAlive)
            g.drawImage(Assets.player, getX(), getY(), getWidth(), getHeight(), null);
        else 
            g.drawImage(Assets.explosion, getX(), getY(), getWidth(), getHeight(), null);
    }
}