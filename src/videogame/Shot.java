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

    private boolean isShot; //Stores wether the shot has bien fired.
    private Game game;
    private Item item;

    public Shot(int x, int y, int width, int height, Item item, Game game) {
        super(x, y, width, height);
        this.item = item;
        this.isShot = false;
        this.game = game;
    }

    @Override
    public void tick() {
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
    public void shoot(int x, int y){
        this.isShot = true;
        setX(x);
        setY(y);
        
    }
    public void render(Graphics g) {
        if(this.isShot)
            g.drawImage(Assets.shot, getX(), getY(), getWidth(), getHeight(), null);
    }

}
