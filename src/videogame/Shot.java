package videogame;
/**
 * Shot Class - Works with Player Class to Shoot Aliens
 * @author Jaime Hisao & Rodrigo Casale
 */
import java.awt.Graphics;

public class Shot extends Item {
    
    //Shot Class Values
    private boolean visible; //Stores wether the shot has bien fired.
    private final Game game;
    private final Item item;

    /**
     * Constructor for Shot
     * @author Jaime Hisao & Rodrigo Casale
     * @param x X Coordinate for Shot
     * @param y Y Coordinate for Shot
     * @param width Width of the Game
     * @param height Height of the Game
     * @param item Player Object
     * @param game Current Game Object
     */
    public Shot(int x, int y, int width, int height, Item item, Game game) {
        super(x, y, width, height);
        this.item = item;
        this.visible = false;
        this.game = game;
    }
    
    /**
     * Tick for Shot Object
     * @author Jaime Hisao & Rodrigo Casale
     */
    @Override
    public void tick() {
        //We check if the shot is fired, if so, we tick, else, nothing.
        if (visible) {
            setY(y -4);
        } else {
            setX(game.getPlayer().getX() + 6);
            setY(game.getPlayer().getY());
        }
        if (y <= 0) {
            visible = false;
        }
    }
    
    /**
     * Shoot for Shot
     * Basically, shoots
     * @author Jaime Hisao & Rodrigo Casale
     * @param x X Coordinate of where the shot will fire
     * @param y Y Coordinate of where the shot will fire
     */
    public void shoot(int x, int y){
        setVisibility(true);
        setX(x);
        setY(y);
        
    }
    
    /**
     * Returns wether the object is visible
     * @return Boolean value if the object is visible or not.
     */
    public boolean isVisible(){
        return this.visible;
    }
    
    /**
     * Sets the visibility of the Shot Object
     * @param visible boolean value, if object is visible or not
     */
    public void setVisibility(boolean visible){
        this.visible = visible;
    }
    
    /**
     * Render for Shot Object if it's visible
     * @param g Graphics object used in Game Class
     */
    public void render(Graphics g) {
        if(isVisible())
            g.drawImage(Assets.shot, getX(), getY(), getWidth(), getHeight(), null);
    }

}
