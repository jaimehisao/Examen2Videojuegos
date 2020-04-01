package videogame;

/**
 * 
 * @author Jaime Hisao w/antoniomejorado
 * @author Rodrigo Casale
 */
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import javax.swing.Timer;

public class Game implements Runnable {

    //Game Components
    private BufferStrategy bs;//Contains the display buffers
    private Graphics g; //Provides ability to paint objects
    private Display display; //To Display the game, duh!
    private final KeyManager keyManager;

    //Game Information
    String title; //Title of the Game
    private int width, height; //Screen Resolution
    private Thread thread; //Separate thread for game execution
    private boolean running; //To see if the game is running
    private String fileName; //Path to the game file txt

    //Game Data & Score Keeping
    int score, lives, hits, alienHits;
    
    int direction;

    //Objects contained in the Game
    private List<Alien> aliens;
    private Player player;
    private Shot shot;
    

    public Game(String title, int width, int height) {
        this.title = title;
        this.width = width;
        this.height = height;
        running = false;
        keyManager = new KeyManager();
    }

    private void save(String fileName) {

    }

    private void load(String fileName) {

    }

    /**
     * Initializes the game with many Aliens, single player and shot.
     *
     * @author Jaime Hisao based on Mejorado's Code
     */
    private void init() {
        display = new Display(title, getWidth(), getHeight());
        Assets.init();
        display.getJframe().addKeyListener(keyManager);
        score = 0;
        lives = 0;
        hits = 0;
        alienHits = 0;
        direction = 1;
        
        //Initialize the Player
        player = new Player(270,280, getWidth(),getHeight(),this);

        //Initialize value for Aliens Array
        aliens = new ArrayList<>();

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 6; j++) {
                Alien alien = new Alien(Commons.ALIEN_INIT_X + 18 * j,
                        Commons.ALIEN_INIT_Y + 18 * i, Commons.ALIEN_WIDTH, Commons.ALIEN_HEIGHT, this);
                aliens.add(alien);
            }
        }

        shot = new Shot();
    }

    private void tick() {
        //Ticks the KeyManager to have the updated keys.
        keyManager.tick();

        //Check if user wants to Load game
        if (keyManager.load) {
            keyManager.release(KeyEvent.VK_C);
            load(fileName);
        }

        //Check if user wants to Save game
        if (keyManager.save) {
            keyManager.release(KeyEvent.VK_S);
            load(fileName);
        }

        if (score == Commons.NUMBER_OF_ALIENS_TO_DESTROY) {

            running = false;
            //timer.stop();
            //message = "Game won!";
        }

        //Tick the Player
        player.tick();

        //Tick the Shot
        shot.tick();
<<<<<<< HEAD:src/spaceinvaders/Game.java
=======

>>>>>>> 562b63bf52054a10bb00c8bf329a5797776162d9:src/videogame/Game.java

        // shot
        if (shot.isVisible()) {

            int shotX = shot.getX();
            int shotY = shot.getY();

            for (Alien alien : aliens) {

                int alienX = alien.getX();
                int alienY = alien.getY();

                if (alien.isVisible() && shot.isVisible()) {
                    if (shotX >= (alienX)
                            && shotX <= (alienX + Commons.ALIEN_WIDTH)
                            && shotY >= (alienY)
                            && shotY <= (alienY + Commons.ALIEN_HEIGHT)) {

<<<<<<< HEAD:src/spaceinvaders/Game.java
                        alien.die();
                        deaths++;
=======
                        alien.setImage(Assets.explosion);
                        alien.setDying(true);
                        alienHits++;
>>>>>>> 562b63bf52054a10bb00c8bf329a5797776162d9:src/videogame/Game.java
                        shot.die();
                    }
                }
            }

            int y = shot.getY();
            y -= 4;

            if (y < 0) {
                shot.die();
            } else {
                shot.setY(y);
            }
        }

        //Tick the Aliens
        for (Alien alien : aliens) {
            alien.tick();
        }

        Iterator<Alien> it = aliens.iterator();

        while (it.hasNext()) {

            Alien alien = it.next();

            if (alien.isVisible()) {

                int y = alien.getY();

                if (y > Commons.GROUND - Commons.ALIEN_HEIGHT) {
                    inGame = false;
                    message = "Invasion!";
                }

                //alien.setDirection(0-alien.getDirection());
            }
        }

        // bombs
        Random generator = new Random();

        for (Alien alien : aliens) {

            int shot = generator.nextInt(15);
            Bomb bomb = alien.getBomb();

            if (shot == Commons.CHANCE && alien.isVisible() && bomb.isDestroyed()) {

                bomb.setDestroyed(false);
                bomb.setX(alien.getX());
                bomb.setY(alien.getY());
            }

            if (player.isVisible() && !bomb.isDestroyed()) {
                if(bomb.collision(player)){
                    player.die();
                    bomb.die();
                }
            }

            if (!bomb.isDestroyed()) {
                bomb.tick();
            }
        }
    }

    /**
     * Renders the Game and its components
     *
     * @author Jaime Hisao
     */
    private void render() {
        //Retrieves the BS from Display
        bs = display.getCanvas().getBufferStrategy();

        if (bs == null) {
            display.getCanvas().createBufferStrategy(3);
        } else {
            g = bs.getDrawGraphics();
            g.drawImage(Assets.background, 0, 0, width, height, null);
        }

        //Show Text for hits, lives and score
        g.setColor(Color.red);

        //Render Individual Game Components
        player.render(g);

        for (Alien alien : aliens) {
            alien.render(g);
            alien.getBomb().render(g);
        }
        
        shot.render();

        bs.show();
        g.dispose();
    }

    /**
     * Process game after loosing
     *
     * @param g Graphics, the graphics object of the game
     */
    private void gameOver(Graphics g) {

        g.setColor(Color.black);
        g.fillRect(0, 0, Commons.BOARD_WIDTH, Commons.BOARD_HEIGHT);

        g.setColor(new Color(0, 32, 48));
        g.fillRect(50, Commons.BOARD_WIDTH / 2 - 30, Commons.BOARD_WIDTH - 100, 50);
        g.setColor(Color.white);
        g.drawRect(50, Commons.BOARD_WIDTH / 2 - 30, Commons.BOARD_WIDTH - 100, 50);

        Font small = new Font("Helvetica", Font.BOLD, 14);
        FontMetrics fontMetrics = this.getFontMetrics(small);

        g.setColor(Color.white);
        g.setFont(small);
        g.drawString("Game Over", (Commons.BOARD_WIDTH - fontMetrics.stringWidth("Game Over")) / 2,
                Commons.BOARD_WIDTH / 2);
    }

    /**
     * Starts the game with a new Thread for separate execution
     *
     * @author Jaime Hisao
     */
    public synchronized void start() {
        if (!running) {
            running = true;
            thread = new Thread(this);
            thread.start();
        }
    }

    /**
     * Stops the thread process.
     *
     * @author Jaime Hisao
     */
    public synchronized void stop() {
        if (running) {
            running = false;
            try {
                thread.join();
            } catch (InterruptedException ie) {
                System.out.println("Program was interrupted!");
            }
        }

    }

    /**
     *
     */
    @Override
    public void run() {
        init();
        // frames per second
        int fps = 50;
        // time for each tick in nano segs
        double timeTick = 1000000000 / fps;
        // initializing delta
        double delta = 0;
        // define now to use inside the loop
        long now;
        // initializing last time to the computer time in nanosecs
        long lastTime = System.nanoTime();
        while (running) {
            // setting the time now to the actual time
            now = System.nanoTime();
            // acumulating to delta the difference between times in timeTick units
            delta += (now - lastTime) / timeTick;
            // updating the last time
            lastTime = now;

            // if delta is positive we tick the game
            // tambien si el estatus del juego es 0, si no, enseñamos la pantalla que se acabo el juego
            if (delta >= 1) {
                tick();
                render();
                delta--;
            } 
        }
        stop();
    }
    public KeyManager getKeyManager(){
        return this.keyManager;
    }
    /*
    private Dimension d;
    private List<Alien> aliens;
    private Player player;
    private Shot shot;

    private int direction = -1;
    private int deaths = 0;

    private boolean inGame = true;

    private String message = "Game Over";

    private Timer timer;


    public Game(){
        initBoard();
        gameInit();
    }

    private void initBoard() {

        addKeyListener(new TAdapter());
        setFocusable(true);
        d = new Dimension(Commons.BOARD_WIDTH, Commons.BOARD_HEIGHT);
        setBackground(Color.black);

        timer = new Timer(Commons.DELAY, new GameCycle());
        timer.start();

        gameInit();
    }



    private void drawAliens(Graphics g) {

        for (Alien alien : aliens) {

            if (alien.isVisible()) {

                g.drawImage(alien.getImage(), alien.getX(), alien.getY(), this);
            }

            if (alien.isDying()) {

                alien.die();
            }
        }
    }

    private void drawPlayer(Graphics g) {

        if (player.isVisible()) {

            g.drawImage(player.getImage(), player.getX(), player.getY(), this);
        }

        if (player.isDying()) {

            player.die();
            inGame = false;
        }
    }

    private void drawShot(Graphics g) {

        if (shot.isVisible()) {

            g.drawImage(shot.getImage(), shot.getX(), shot.getY(), this);
        }
    }

    private void drawBombing(Graphics g) {

        for (Alien a : aliens) {

            Alien.Bomb b = a.getBomb();

            if (!b.isDestroyed()) {

                g.drawImage(b.getImage(), b.getX(), b.getY(), this);
            }
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        doDrawing(g);
    }

    private void doDrawing(Graphics g) {

        g.setColor(Color.black);
        g.fillRect(0, 0, d.width, d.height);
        g.setColor(Color.green);

        if (inGame) {

            g.drawLine(0, Commons.GROUND,
                    Commons.BOARD_WIDTH, Commons.GROUND);

            drawAliens(g);
            drawPlayer(g);
            drawShot(g);
            drawBombing(g);

        } else {

            if (timer.isRunning()) {
                timer.stop();
            }

            gameOver(g);
        }

        Toolkit.getDefaultToolkit().sync();
    }



    private void update() {

        if (deaths == Commons.NUMBER_OF_ALIENS_TO_DESTROY) {

            inGame = false;
            timer.stop();
            message = "Game won!";
        }

        // player
        player.act();

        // shot
        if (shot.isVisible()) {

            int shotX = shot.getX();
            int shotY = shot.getY();

            for (Alien alien : aliens) {

                int alienX = alien.getX();
                int alienY = alien.getY();

                if (alien.isVisible() && shot.isVisible()) {
                    if (shotX >= (alienX)
                            && shotX <= (alienX + Commons.ALIEN_WIDTH)
                            && shotY >= (alienY)
                            && shotY <= (alienY + Commons.ALIEN_HEIGHT)) {

                        alien.setImage(alien.loadImage("/resources/explosion.png"));
                        alien.setDying(true);
                        deaths++;
                        shot.die();
                    }
                }
            }

            int y = shot.getY();
            y -= 4;

            if (y < 0) {
                shot.die();
            } else {
                shot.setY(y);
            }
        }

        // aliens

        for (Alien alien : aliens) {
            alien.tick();
        }

        Iterator<Alien> it = aliens.iterator();

        while (it.hasNext()) {

            Alien alien = it.next();

            if (alien.isVisible()) {

                int y = alien.getY();

                if (y > Commons.GROUND - Commons.ALIEN_HEIGHT) {
                    inGame = false;
                    message = "Invasion!";
                }

                alien.setDirection(direction);
            }
        }

        // bombs
        Random generator = new Random();

        for (Alien alien : aliens) {

            int shot = generator.nextInt(15);
            Bomb bomb = alien.getBomb();

            if (shot == Commons.CHANCE && alien.isVisible() && bomb.isDestroyed()) {

                bomb.setDestroyed(false);
                bomb.setX(alien.getX());
                bomb.setY(alien.getY());
            }

            int bombX = bomb.getX();
            int bombY = bomb.getY();
            int playerX = player.getX();
            int playerY = player.getY();

            if (player.isVisible() && !bomb.isDestroyed()) {
                
                if(bomb.collision(player)){
                    
                    player.die();
                    bomb.setDestroyed(true);
                }
                if (bombX >= (playerX)
                        && bombX <= (playerX + Commons.PLAYER_WIDTH)
                        && bombY >= (playerY)
                        && bombY <= (playerY + Commons.PLAYER_HEIGHT)) {
                    //nada
                }
            }

            if (!bomb.isDestroyed()) {

                bomb.setY(bomb.getY() + 1);

                if (bomb.getY() >= Commons.GROUND - Commons.BOMB_HEIGHT) {

                    bomb.setDestroyed(true);
                }
            }
        }
    }

   

























    private class TAdapter extends KeyAdapter {

        @Override
        public void keyReleased(KeyEvent e) {

            player.keyReleased(e);
        }

        @Override
        public void keyPressed(KeyEvent e) {

            player.keyPressed(e);

            int x = player.getX();
            int y = player.getY();

            int key = e.getKeyCode();

            if (key == KeyEvent.VK_SPACE) {

                if (inGame) {

                    if (!shot.isVisible()) {

                        shot = new Shot(x, y);
                    }
                }
            }
        }
    }
     */

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
