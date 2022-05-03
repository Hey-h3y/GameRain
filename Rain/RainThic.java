import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;


public class RainThic extends GameObject {

    Random r = new Random();

    public RainThic(int x, int y, int life,int width, int height, ID id) {
        super(x, y, life, width, height, id);
        velY = 10;
        velX = -1;
    }

    @Override
    public void tick() {
        x += velX;
        y += velY;
        if(getY() > 1032){
            setY(r.nextInt(800)-832);
            setX(r.nextInt(1312));
        }
    }

    @Override
    public void render(Graphics g) {

        g.setColor(new Color(200, 50, 220));
        g.fillRect(x, y, width, height);
        
    }
    
}