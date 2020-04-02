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
    
    private boolean visible; //Stores wether the shot has bien fired.
=======

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
        //We check if the shot is fired, if so, we tick, else, nothing.
        if (isShot) {
            setY(y + 2);
        } else {
            setX(game.getPlayer().getX() + 6);
            setY(game.getPlayer().getY());
        }
        if (y <= 0) {
            isShot = false;
        }
    }
<<<<<<< HEAD
    
    
    
    
    
    
    
    
    
    public void render(Graphics g){
            g.drawImage(Assets.shot, getX(), getY(), getWidth(), getHeight(), null);
    }
    
    public void die(){
        visible = false;
    }
    
=======

    public void render(Graphics g) {
        //if(this.visible)
        g.drawImage(Assets.shot, getX(), getY(), getWidth(), getHeight(), null);
>>>>>>> 9505bd2921254cfd709a6fecd8c0c5a65d035ca7
    }

}
