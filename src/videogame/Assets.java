/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package videogame;

import java.awt.image.BufferedImage;

/**
 *
 * @author Jaime Hisao
 * @author Rodrigo Casale
 */
public class Assets {
    public static BufferedImage background; 
    public static BufferedImage player;
    public static BufferedImage explosion;
    public static BufferedImage alien;
    public static BufferedImage shot;
    public static BufferedImage bomb;
    
    public static SoundClip pew;
    public static SoundClip explosionSC;
    public static SoundClip audio;
    
    public static void init(){
        player = ImageLoader.loadImage("/resources/player.png");
        explosion = ImageLoader.loadImage("/resources/explosion.png");
        background = ImageLoader.loadImage("/resources/stars.jpg");
        alien = ImageLoader.loadImage("/resources/alien.png");
        shot = ImageLoader.loadImage("/resources/shot.png");
        bomb = ImageLoader.loadImage("/resources/bomb.png");
        
        pew = new SoundClip("/sounds/pew.wav");
        explosionSC = new SoundClip("/sounds/bomb.wav");
        audio = new SoundClip("/sounds/audio.wav");
    }
}
