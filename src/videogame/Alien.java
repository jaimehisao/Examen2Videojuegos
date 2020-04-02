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
import java.util.Random;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class Alien extends Item {
    
    private Game game;
    private int direction;
    private Bomb bomb;
    
    private int status; // 1 = alive, 2 = explotion, 3 = gone
    private int timer;
    
    Random generator;
    //New constructor
    public Alien(int x, int y, int width, int height, Game game, int direction) {
        super(x, y, width, height);
        this.game = game;
        this.direction = direction;
        status = 1; 
        timer = 0;
        
        generator = new Random();
        bomb = new Bomb(x, y, 3, 3,this, game, generator.nextInt(2)+1);
    }
    
    @Override
    public void tick() {
        //si el alien esta vivo acutara nomal
        if(status == 1){
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
        //si esta en explosion cuenta 100 segundos y cuando acabe cambia a estatus 3
        else if(status == 2){
            timer++;
            if(timer >= 100){
                status = 3;
            }
        }
    }
    
    public void die(){
        this.status = 2;
    }
    
    public void setDirection(int direction){
        this.direction = direction;
    }

    public int getStatus(){
        return this.status;
    }

    public boolean isVisible(){
        //return this.visible;
        return false;
    }
    public Bomb getBomb(){
        return this.bomb;
    }
    @Override
    public void render(Graphics g) {
        //si esta en estatus 1 enseña el alien
        if(status == 1){
            g.drawImage(Assets.alien, getX(), getY(), getWidth(), getHeight(), null);
            bomb.render(g);
        }
        //si esta en el 2 explosion
        else if(status == 2)
            g.drawImage(Assets.explosion, getX(), getY(), getWidth(), getHeight(), null);
        

    }
}
