package videogame;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Jaime Hisao, Casale w/antoniomejorado
 */
public class Videogame {
    
    static Game g;
    
    public static void main(String[] args) {
        g = new Game("Space Invaders", Commons.BOARD_WIDTH, Commons.BOARD_HEIGHT);
        g.start();
    }
    
}
