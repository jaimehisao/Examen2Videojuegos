package spaceinvaders;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author antoniomejorado
 */
import java.awt.EventQueue;
import javax.swing.JFrame;

public class Videogame extends JFrame  {
    
    static Game g;
    
    public static void main(String[] args) {
        g = new Game("Space Invaders", Commons.BOARD_WIDTH, Commons.BOARD_HEIGHT);
        //check this resolution for the correct one.
    }
    
    
    
    
    
/*
    public Videogame() {

        initUI();
    }

    private void initUI() {

        add(new Game());

        setTitle("Space Invaders");
        setSize(Commons.BOARD_WIDTH, Commons.BOARD_HEIGHT);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {

        EventQueue.invokeLater(() -> {

            Videogame ex = new Videogame();
            ex.setVisible(true);
        });
    }

*/
    
    
}
