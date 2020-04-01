package spaceinvaders;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Jaime Hisao, Casale w/antoniomejorado
 */
import javax.swing.JFrame;

public class Videogame extends JFrame {
    
    static Game g;
    
    public static void main(String[] args) {
        g = new Game("Space Invaders", Commons.BOARD_WIDTH, Commons.BOARD_HEIGHT);
    }
    
}
