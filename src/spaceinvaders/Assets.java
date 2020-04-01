/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spaceinvaders;

import java.awt.image.BufferedImage;

/**
 *
 * @author Jaime Hisao
 * @author Rodrigo Casale
 */
public class Assets {
<<<<<<< HEAD
<<<<<<< HEAD
    public static BufferedImage background;
=======

>>>>>>> fa9a7c175c6ce065229ccac057b3056aedc98dbf
=======
    
    public static BufferedImage background;
>>>>>>> 053de131dd44b9292e8b373ca92f3bc21b5fc5e8
    public static BufferedImage player;
    public static BufferedImage explosion;
    public static BufferedImage alien;
    public static BufferedImage shot;
    
    public static void init(){
        player = ImageLoader.loadImage("/resources/player.png");
        explosion = ImageLoader.loadImage("/resources/explosion.png");
        background = ImageLoader.loadImage("/resources/background.png");
<<<<<<< HEAD
        alien = ImageLoader.loadImage("/resources/alien.png");
=======
>>>>>>> 053de131dd44b9292e8b373ca92f3bc21b5fc5e8
    }
}
