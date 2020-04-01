/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package videogame;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 *
 * @author rodrigocasale
 */
public class KeyManager implements KeyListener{
    
    public boolean up;      // flag to move up the player
    public boolean down;    // flag to move down the player
    public boolean left;    // flag to move left the player
    public boolean right;   // flag to move right the player
    public boolean q;   // flag to move right the player
    public boolean a;   // flag to move right the player
    public boolean p;   // flag to move right the player
    public boolean l;   // flag to move right the player
    public boolean load, save;

    private boolean keys[];  // to store all the flags for every key
    
    public KeyManager() {
        keys = new boolean[256];
    }
    
    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        // set true to every key pressed
        keys[e.getKeyCode()] = true;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // set false to every key released
        keys[e.getKeyCode()] = false;
    }
    
     /**
     * @desc This method "forces" the release of the given key
     * @author Jaime Hisao
     * @param keyNum The key to release
    */
    public void release(int keyNum){
        keys[keyNum] = false;
    }
    
    /**
     * to enable or disable moves on every tick
     */
    public void tick() {
        up = keys[KeyEvent.VK_UP];
        down = keys[KeyEvent.VK_DOWN];
        left = keys[KeyEvent.VK_LEFT];
        right = keys[KeyEvent.VK_RIGHT];
        q = keys[KeyEvent.VK_Q];
        a = keys[KeyEvent.VK_A];
        p = keys[KeyEvent.VK_P];
        l = keys[KeyEvent.VK_L];
        load = keys[KeyEvent.VK_C];
        save = keys[KeyEvent.VK_G];
    }
}
