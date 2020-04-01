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
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Sprite {

    private boolean visible;
    private BufferedImage image;
    private boolean dying;

    int x;
    int y;
    int dx;

    public Sprite() {

        visible = true;
    }

    public void die() {

        visible = false;
    }

    public boolean isVisible() {

        return visible;
    }

    protected void setVisible(boolean visible) {

        this.visible = visible;
    }

    public void setImage(BufferedImage image) {

        this.image = image;
    }

    public BufferedImage getImage() {

        return image;
    }

    protected BufferedImage loadImage(String path) {
        BufferedImage img = null;
        try {
            img = ImageIO.read(getClass().getResource(path));
        } catch (IOException ioe) {
            System.out.println("Error loading image " + path + ioe.toString());
            System.exit(1);
        }
        return img;
    }

    public void setX(int x) {

        this.x = x;
    }

    public void setY(int y) {

        this.y = y;
    }

    public int getY() {

        return y;
    }

    public int getX() {

        return x;
    }

    public void setDying(boolean dying) {

        this.dying = dying;
    }

    public boolean isDying() {

        return this.dying;
    }
}
