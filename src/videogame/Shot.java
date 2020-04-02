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

public class Shot extends Item {
    private boolean visible;
    

    public Shot(int x, int y, int width, int heigth) {
        super(x + 6 , y - 1 , width, heigth);
        this.x = x;
        this.y = y;
        this.visible = true;
    }
    public boolean isVisible(){
        return this.visible;
    }
    private void initShot(int x, int y) {

        int H_SPACE = 6;
        setX(x + H_SPACE);

        int V_SPACE = 1;
        setY(y - V_SPACE);
    }
    public void render(Graphics g){
        if(this.visible)
            g.drawImage(Assets.shot, getX(), getY(), getWidth(), getHeight(), null);
    }
    public void die(){
        visible = false;
    }
    @Override
    public void tick() {
        this.y -= 4;
        if(visible){
            if(this.y<0){
                this.die();
            }else{
                this.y = y;
            }
        }
    }
}