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

    private boolean visible; //Stores wether the shot has bien fired.
    private Game game;
    private Item item;

    public Shot(int x, int y, int width, int height, Item item, Game game) {
        super(x, y, width, height);
        this.item = item;
        this.visible = false;
        this.game = game;
    }

    @Override
    public void tick() {
        //We check if the shot is fired, if so, we tick, else, nothing.
        if (visible) {
            setY(y -2);
        } else {
            setX(game.getPlayer().getX() + 6);
            setY(game.getPlayer().getY());
        }
        if (y <= 0) {
            visible = false;
        }
    }
    public void shoot(int x, int y){
        setVisibility(true);
        setX(x);
        setY(y);
        
    }
    
    public boolean isVisible(){
        return this.visible;
    }
    public void setVisibility(boolean visible){
        this.visible = visible;
    }
    public void render(Graphics g) {
        if(this.visible)
            g.drawImage(Assets.shot, getX(), getY(), getWidth(), getHeight(), null);
    }

}
