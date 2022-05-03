import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import java.util.Random;


public class Enemy extends GameObject {

    private Image enemySprite;

    Random r = new Random();


    public Enemy(int x, int y, int life,int width, int height, ID id) {
        super(x, y, life, width, height, id);

        velX = -5;

    }

    @Override
    public void tick() {
        x += velX;
        y += velY;
        if(getX() < -80){
            setY(r.nextInt(748-height));
            setX(r.nextInt(1000)+1312);
        }
        
    }

    @Override
    public void render(Graphics g) {

        ImageIcon knife = new ImageIcon("images\\faca.png");
        enemySprite = knife.getImage();

        g.drawImage(enemySprite, x, y, width, height, null);
        
    }
    
}
