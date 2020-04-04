package videogame;

/**
 * Game Class
 * Connects most of the code together.
 * @author Jaime Hisao & Rodrigo Casale w/antoniomejorado
 */
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Game implements Runnable {

    //Game Components
    private BufferStrategy bs;//Contains the display buffers
    private Graphics g; //Provides ability to paint objects
    private Display display; //To Display the game, duh!
    private KeyManager keyManager;

    //Game Information
    String title; //Title of the Game
    private int width, height; //Screen Resolution
    private Thread thread; //Separate thread for game execution
    private boolean running; //To see if the game is running
    private final String fileName = "spaceInvaders.txt"; //Path to the game file txt
    //private final String highScoreFile = "highestScore.txt";
    
    //Game Data & Score Keeping
    int score, lives, hits, alienHits;
    boolean gamePaused;
    
    int direction;

    //Objects contained in the Game
    private List<Alien> aliens;
    //private int highScore;
    private Player player;
    private Shot shot;
    private int gameStatus;
    private String endMessage;
    private String scoreMessage;
    
    Random generator;
    /**
     * Game Constructor
     * @param title Title of the Game
     * @param width Width of the Game
     * @param height Height of the Game
     */
    public Game(String title, int width, int height) {
        this.title = title;
        this.width = width;
        this.height = height;
        running = false;
        keyManager = new KeyManager();
        score = 0;
        lives = 0;
        hits = 0;
        alienHits = 0;
        direction = 1;
        gamePaused = false;
        gameStatus = 0;
        
        generator = new Random();
    }
    
    /*
    private void saveHighScore(){
        if(score>highScore){
            try {
                //Declares PrintWriter object, to write to file
                BufferedWriter wrt = new BufferedWriter(new FileWriter(highScoreFile));

                //Wrties the number of entries

       
                    System.out.println("New highscore saved: " + score);
                    wrt.write(score);

                //Closes the Writer.
                wrt.close();
            } catch (Exception e) {
                System.out.println("No se encontro el archivo y no se pudo guardar!");
            }

            System.out.println("high score saved!");
        }else{
            System.out.println("Old score kept!");
        }
    }
    */
    
    /*
    private void loadHighScore(){
         //Opens the file
         try{
            BufferedReader bReader = new BufferedReader(new FileReader(highScoreFile));
            String read = bReader.readLine(); //Reads the file
            
            try{
                highScore = Character.getNumericValue(read.charAt(0));
            }catch(Exception e){
                System.out.println("File was unreadable, interpreting other thing.");
                highScore = 30;
            }
            System.out.println("Highscore: " + highScore);
         } catch (IOException e) {
            System.out.println("No se encontro el archivo y no se pudo guardar!");
        }
         System.out.println("high score loaded!");
    }
    */

    /**
     * Saves the Game to a TXT File
     * @param fileName Filename and path of the TXT File
     */
    private void save(String path) {
        try {
            //Declares PrintWriter object, to write to file
            PrintWriter wrt = new PrintWriter(new FileWriter(path));

            //Check wether the game is paused or not
            int isPaused = (gamePaused ? 1 : 0);
            //Writes lives, score, isPaused
            wrt.print(lives + " " + score + " " + isPaused + " ");
            
            //Writes Player Data
            wrt.print(player.getX() + " " + player.getY() + " ");
            
            //Write Alien & Bomb Data - # of aliens, x, y, direction, bombX, bombY, bombSpeed
            wrt.print(aliens.size() + " ");
            for(Alien alien: aliens){
                wrt.print(alien.getX() + " " + alien.getY() + " " + 
                        alien.getDirection() + " " + alien.getBomb().getX() 
                        + " " + alien.getBomb().getY() + " " + 
                        alien.getBomb().getSpeed() + " ");
            }
            //Closes the Writer.
            wrt.close();
        } catch (IOException e) {
            System.out.println("No se encontro el archivo y no se pudo guardar!");
        }

        System.out.println("Juego Guardado!");
    }

    /**
     * Loads the Game from a TXT File
     * @param fileName Filename and path of the TXT File
     */
    private void load(String path) {
        try {
            //Opens the file
            BufferedReader bReader = new BufferedReader(new FileReader(path));
            String read = bReader.readLine(); //Reads the file
            String readArr[] = read.split(" "); //Divides it into an array
            int readData = 0; //Var to go over the array.

            //Using the values in the array, we set the game values.
            
            //Set the lives and score
            lives = Integer.parseInt(readArr[readData++]);
            score = Integer.parseInt(readArr[readData++]);

            //Set the paused status
            gamePaused = Integer.parseInt(readArr[readData++]) == 1;
            
            //Read Data for Player
            player.setX(Integer.parseInt(readArr[readData++]));
            player.setY(Integer.parseInt(readArr[readData++]));
            
            //Get # of Aliens
            int numberOfAliens = Integer.parseInt(readArr[readData++]);
            int num = numberOfAliens+readData;
            List<Alien> importedAliens = new ArrayList<>();
            for(int i = readData; i<num; i++){
                Alien tmp = new Alien(Integer.parseInt(readArr[readData++]), 
                        Integer.parseInt(readArr[readData++]),
                Commons.ALIEN_WIDTH, Commons.ALIEN_HEIGHT, this, 
                        Integer.parseInt(readArr[readData++]),
                new Bomb(Integer.parseInt(readArr[readData++]), 
                        Integer.parseInt(readArr[readData++]), 
                        2, 6, null, this, 
                        Integer.parseInt(readArr[readData++])));
                tmp.getBomb().setAlien(tmp);
                importedAliens.add(tmp);
            }
            
            //Equal imported list to loaded list
            aliens = importedAliens;
            
            //Closes the reader resource.
            bReader.close();

        } catch (IOException e) {
            System.out.println("No se encontro el archivo, entonces no podemos cargar el juego");
        }

        System.out.println("Juego Cargado!");
    }

    /**
     * Initializes the game with many Aliens, single player and shot.
     * @author Jaime Hisao based on Mejorado's Code
     */
    private void init() {
        display = new Display(title, getWidth(), getHeight());
        Assets.init();
        
        //loadHighScore();
        
        //Initialize the Player
        player = new Player(getWidth()/2,getHeight()-60, 0, Commons.PLAYER_WIDTH, 
                Commons.PLAYER_HEIGHT,this);

        //Initialize value for Aliens Array
        aliens = new ArrayList<>();

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 6; j++) {
                Alien alien = new Alien(Commons.ALIEN_INIT_X + 18 * j,
                        Commons.ALIEN_INIT_Y + 18 * i, Commons.ALIEN_WIDTH, 
                        Commons.ALIEN_HEIGHT, this, 1);
                aliens.add(alien);
            }
        }
        
        display.getJframe().addKeyListener(keyManager);
        Assets.audio.setLooping(true);
        Assets.audio.play();

    }

    /**
     * Tick for Game
     * Mostly calls other Ticks and handles the KeyManager
     * @author Jaime Hisao & Rodrigo Casale
     */
    private void tick() {
        
        keyManager.tick();
        
        //Check if the user wants to pause
        if(keyManager.pause){
            keyManager.release(KeyEvent.VK_P);
            gamePaused = !gamePaused;
            if(gamePaused){
                System.out.println("Game is paused");
                Assets.audio.stop();
            }else{
                System.out.println("Game is running");
                Assets.audio.play();
            }
        }
        
        //Pauses the game
        if(!gamePaused){
        
        //Tick the Player
        player.tick();
   
        //Ticks the KeyManager to have the updated keys.
        //Check if user wants to Load game
        if (keyManager.load) {
            keyManager.release(KeyEvent.VK_C);
            try{
                load(fileName);
            }catch(Exception e){
                System.out.print("Unexpected Error...");
                //e.printStackTrace();
            }
        }

        //Check if user wants to Save game
        if (keyManager.save) {
            keyManager.release(KeyEvent.VK_G);
            try{
                save(fileName);
            }catch(Exception e){
                 System.out.print("Unexpected Error...");
                 //e.printStackTrace();
            }
        }
        
        
        if (score == Commons.NUMBER_OF_ALIENS_TO_DESTROY) {
            //saveHighScore();
            running = false;
            //timer.stop();
            //message = "Game won!";
        }
        
        Shot shot = player.getShot();
        for(Alien alien : aliens){
            alien.tick();
            //is there is a collision and the alien is alive
            if(shot.collision(alien) && alien.getStatus() == 1){
                shot.setVisibility(false);
                alien.die();
                this.hits++;
            }
            if(alien.getBomb().collision(player) && player.isAlive()){
                player.die();
            }
        }
        if(this.hits == Commons.NUMBER_OF_ALIENS_TO_DESTROY || keyManager.a){
            this.gameStatus = 1;
            this.endMessage = "You won!";
            this.scoreMessage = "Score: " + this.score;
            //saveHighScore();
        }
        if(this.player.getLives() == 0){
            this.gameStatus = 1;
            this.endMessage = "Game Over";
            this.scoreMessage = "Score: " + this.score;
            //saveHighScore();
        }
        this.score = this.hits * 9;
    }
    }
    
    /**
     * Method that returns the player object
     * @return Returns the player object.
     */
    public Player getPlayer(){
        return player;
    }

    /**
     * Renders the Game and its components
     * @author Jaime Hisao & Rodrigo Casale
     */
    private void render() {
        //Retrieves the BS from Display
        bs = display.getCanvas().getBufferStrategy();

        if (bs == null) {
            display.getCanvas().createBufferStrategy(3);
        } else {
            g = bs.getDrawGraphics();
            g.drawImage(Assets.background, 0, 0, getWidth(), getHeight()-50, null);
            g.setColor(Color.DARK_GRAY);
            g.fillRect(0, getHeight()-50, getWidth(), getHeight());
            //g.drawImage(Assets.background, 0, 0, width, height, null);
            
            //Show Text for hits, lives and score
            g.setColor(Color.white);
            g.setFont(new Font("Arial", Font.PLAIN, 15));
            g.drawString("Lives: " + this.player.getLives(), 10, getHeight() - 30);
            g.drawString("Score: " + this.score, 10, getHeight() - 10);
            //Render Individual Game Components
            player.render(g);

            for (Alien alien : aliens) {
                alien.render(g);
            }

            g.setColor(Color.green);
            g.drawLine(0, getHeight()-50, getWidth(), getHeight()-50);
            bs.show();
            g.dispose();
        }
    }
    
    private void startScreen(){
        bs = display.getCanvas().getBufferStrategy();
        
        if(bs == null){
            display.getCanvas().createBufferStrategy(3);
        }else{
            g = bs.getDrawGraphics();
            
            g.setColor(Color.black);
            g.fillRect(0, 0, getWidth(), getHeight());
            
        }
    }

    /**
     * Render and Process game after loosing
     * @param g Graphics, the graphics object of the game
     */
    private void gameOver() {

        bs = display.getCanvas().getBufferStrategy();

        if (bs == null) {
            display.getCanvas().createBufferStrategy(3);
        } else {
            g = bs.getDrawGraphics();

            g.setColor(Color.black);
            g.fillRect(0, 0, getWidth(), getHeight());
            g.setColor(new Color(0, 32, 48));
            g.fillRect(50, getWidth() / 2 - 30, getWidth() - 100, 50);
            g.setColor(Color.white);
            g.drawRect(50, getWidth() / 2 - 30, getWidth() - 100, 50);

            Font small = new Font("Helvetica", Font.BOLD, 14);
            FontMetrics fontMetrics = g.getFontMetrics(small);

            g.setColor(Color.white);
            g.setFont(small);
            
            g.drawString(this.endMessage, (getWidth() - fontMetrics.stringWidth(this.endMessage)) / 2, getHeight() / 2 - 6);
            g.drawString(this.scoreMessage, (getWidth() - fontMetrics.stringWidth(this.scoreMessage)) / 2, getHeight() / 2 + 12);
            bs.show();
            g.dispose();
        }        
    }

    /**
     * Starts the game with a new Thread for separate execution
     * @author Jaime Hisao & Rodrigo Casale
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
     * @author Jaime Hisao & Rodrigo Casale
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
     * run
     * Runs the game and calls the Tick and Render, part of Runnable Interface-
     * @author Jaime Hisao & Rodrigo Casale
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
                if(this.gameStatus == 0){
                    tick();
                    render();
                }else if(this.gameStatus == 1){
                    gameOver();
                }
                delta--;
            }
        }
        //stop();
    }
    
    /**
     * Returns the KeyManager
     * @return KeyManager Object
     */
    public KeyManager getKeyManager(){
        return this.keyManager;
    }
   
    /**
     * Get Title of the Game
     * @return Title of the Game
     */
    public String getTitle() {
        return title;
    }

    /**
     * Set the Title of the Game
     * @param title Title of the Game
     */
    public void setTitle(String title) {
        this.title = title;
    }
    
    /**
     * Get Width of the Game
     * @return width of the Game
     */
    public int getWidth() {
        return width;
    }

    /**
     * Set the width
     * @param width Width of the Game
     */
    public void setWidth(int width) {
        this.width = width;
    }

    /**
     * Get the Height
     * @return Height of the Game
     */
    public int getHeight() {
        return height;
    }

    /**
     * Sets the Height
     * @param height Height of the Game
     */
    public void setHeight(int height) {
        this.height = height;
    }
}