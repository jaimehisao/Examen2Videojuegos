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
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class Alien extends Item {
    
    private Game game;
    private int direction;
    private Shot shot;
    
    //New constructor
    public Alien(int x, int y, int width, int height, Game game, int direction) {
        super(x, y, width, height);
        this.game = game;
        this.direction = direction;
        shot = new Shot(x,y,3,3,2,1,game);
    }
    
    public Alien(int x, int y, int width, int height, Game game) {
        super(x, y, width, height);
        bomb = new Bomb(x, y, 2, 10);
        this.direction = 1;
        this.game = game;
        this.visible = true;
        this.isAlive = true;
    }
    public void setDirection(int direction){
        this.direction = direction;
    }
    public Bomb getBomb() {
        return bomb;
    }

    @Override
    public void tick() {
        //could it work??
        this.bomb.tick();
        if (this.x >= Commons.BOARD_WIDTH - Commons.BORDER_RIGHT && direction != -1) {
            direction = -1;
            this.y += 15;
        }else if(x <= Commons.BORDER_LEFT && direction != 1){
            direction = 1;
            this.y += 15;
        }
        this.x += direction;
    }

    public boolean isVisible(){
        return this.visible;
    }
    public void die(){
        this.isAlive = false;
    }
    @Override
    public void render(Graphics g) {
        if(isAlive)
            g.drawImage(Assets.alien, getX(), getY(), getWidth(), getHeight(), null);
        else
            g.drawImage(Assets.explosion, getX(), getY(), getWidth(), getHeight(), null);

    }
}
