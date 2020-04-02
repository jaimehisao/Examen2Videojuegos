package videogame;

/**
 * Alien Class
 * This Class Extends Item
 * @author Jaime Hisao & Rodrigo Casale
 */
import java.awt.Graphics;
import java.util.Random;

public class Alien extends Item {
    
    //Class Variables
    private final Game game;
    private int direction;
    private final Bomb bomb;
    
    private int status; // 1 = alive, 2 = explotion, 3 = gone
    private int timer;
    Random generator;
    
    /**
     * Alien Class Constructor
     * @author Jaime Hisao & Rodrigo Casale
     * @param x X Coordinate to create Alien
     * @param y Y Coordinate to create Alien
     * @param width Width of the Game
     * @param height height of the Game
     * @param game Game Object
     * @param direction Direction of the Alien
     */
    public Alien(int x, int y, int width, int height, Game game, int direction) {
        super(x, y, width, height);
        this.game = game;
        this.direction = direction;
        status = 1; 
        timer = 0;
        
        generator = new Random();
        bomb = new Bomb(x, y, 3, 3,this, game, generator.nextInt(2)+1);
    }
    
    /**
     * Alien Class Constructor
     * @author Jaime Hisao & Rodrigo Casale
     * @param x X Coordinate to create Alien
     * @param y Y Coordinate to create Alien
     * @param width Width of the Game
     * @param height height of the Game
     * @param game Game Object
     * @param direction Direction of the Alien
     * @param bomb Bomb Object
     */
    public Alien(int x, int y, int width, int height, Game game, int direction,
            Bomb bomb) {
        super(x, y, width, height);
        this.game = game;
        this.direction = direction;
        status = 1; 
        timer = 0;
        
        generator = new Random();
        this.bomb = bomb;
    }
    
    /**
     * Ticks the Alien Object
     * @author Jaime Hisao & Rodrigo Casale
     */
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
    
    /**
     * Kills the Alien
     * @author Jaime Hisao & Rodrigo Casale
     */
    public void die(){
        this.status = 2;
    }
    
    /**
     * Sets the direction of the Alien Object
     * @author Jaime Hisao & Rodrigo Casale
     * @param direction int that sets the direction of the object
     */
    public void setDirection(int direction){
        this.direction = direction;
    }

    /**
     * Returns Alien Status
     * @author Jaime Hisao & Rodrigo Casale
     * @return returns the Status of the Alien, int value: 1 = alive, 2 = explosion, 3 = gone
     */
    public int getStatus(){
        return this.status;
    }

    /**
     * Returns Alien visibility
     * @author Jaime Hisao & Rodrigo Casale
     * @return returns wether the Alien is visible or not.
     */
    public boolean isVisible(){
        //return this.visible;
        return false;
    }
    
    /**
     * Returns the Bomb object from an Alien instance
     * @author Jaime Hisao & Rodrigo Casale
     * @return returns the Bomb object 
     */
    public Bomb getBomb(){
        return this.bomb;
    }
    
    public int getDirection(){
        return direction;
    }
    
    /**
     * Render for Alien Object
     * Renders the alien and also the bomb (if fired)
     * @author Jaime Hisao & Rodrigo Casale
     * @param g Graphics Object used in Game Class
     */
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
