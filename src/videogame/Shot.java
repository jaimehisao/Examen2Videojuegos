package videogame;

/*
 * The Shot is for the player.
 */

/**
 *
 * @author antoniomejorado
 */
import java.awt.Graphics;

public class Shot extends Item {
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
    
    private boolean visible; //Stores wether the shot has bien fired.
=======

=======
    
>>>>>>> parent of 9505bd2... fixed compile issues and has basic functionality
=======
    
>>>>>>> parent of 9505bd2... fixed compile issues and has basic functionality
    private boolean isShot; //Stores wether the shot has bien fired.
>>>>>>> 9505bd2921254cfd709a6fecd8c0c5a65d035ca7
    private Game game;
    private Item item;
    
    public Shot(int x, int y, int width, int height, Item item, Game game) {
        super(x, y, width, height);
        this.item = item;
        this.isShot = false;
        this.game = game;
    }
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
    public void setVisibility(boolean visible){
        this.visible = visible;
    }
    private void initShot(int x, int y) {

        int H_SPACE = 6;
        setX(x + H_SPACE);

        int V_SPACE = 1;
        setY(y - V_SPACE);
    }
    @Override 
    public void tick(){
=======

    @Override
    public void tick() {
>>>>>>> 9505bd2921254cfd709a6fecd8c0c5a65d035ca7
=======
    
    @Override 
    public void tick(){
>>>>>>> parent of 9505bd2... fixed compile issues and has basic functionality
=======
    
    @Override 
    public void tick(){
>>>>>>> parent of 9505bd2... fixed compile issues and has basic functionality
        //We check if the shot is fired, if so, we tick, else, nothing.
            if (isShot) {
                setY(y+dy);
            } else {
                setX(item.getX()+4);
                setY(item.getY()+5);
            }
            if (y >= 290) {
            isShot = false;
        }
    }
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
=======
>>>>>>> parent of 9505bd2... fixed compile issues and has basic functionality
=======
>>>>>>> parent of 9505bd2... fixed compile issues and has basic functionality
    
    
    
    
    
    
    
    
    
    public void render(Graphics g){
<<<<<<< HEAD
<<<<<<< HEAD
=======
        if(this.visible)
>>>>>>> parent of 9505bd2... fixed compile issues and has basic functionality
=======
        if(this.visible)
>>>>>>> parent of 9505bd2... fixed compile issues and has basic functionality
            g.drawImage(Assets.shot, getX(), getY(), getWidth(), getHeight(), null);
    }
    
    public void die(){
        visible = false;
    }
    
<<<<<<< HEAD
<<<<<<< HEAD
=======

    public void render(Graphics g) {
        //if(this.visible)
        g.drawImage(Assets.shot, getX(), getY(), getWidth(), getHeight(), null);
>>>>>>> 9505bd2921254cfd709a6fecd8c0c5a65d035ca7
=======
>>>>>>> parent of 9505bd2... fixed compile issues and has basic functionality
=======
>>>>>>> parent of 9505bd2... fixed compile issues and has basic functionality
    }
