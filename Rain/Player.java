import java.awt.Graphics;
import java.awt.Image;
import java.awt.Color;
import java.awt.Font;
import javax.swing.ImageIcon;

public class Player extends GameObject {

    private Image playerSprite;

    Handler handler;

    public Player(int x, int y, int life,int width, int height, ID id, Handler handler) {
        super(x, y, life, width, height, id);
        setLife(life);
    }

    public void collision(GameObject object){
            if(x + width >= object.getX() && object.getX() + object.getWidth() >= x && 
            y + height >= object.getY() && object.getY() + object.getHeight() >= y && this.getLife() > 0){
                removeLife(life, 2);
               
            }
    }
    

    @Override
    public void tick() {
        this.x += velX * acelleration;
        this.y += velY * acelleration;

        x = Main.Clamp(x, 0, Main.WIDTH-64);
        y = Main.Clamp(y, 0, Main.HEIGHT-64);

    }

    @Override
    public void render(Graphics g) {

        ImageIcon ronaldo = new ImageIcon("images\\ronaldo.png");
        playerSprite = ronaldo.getImage();

        //g.fillRect(x, y, 32, 64);
        g.drawImage(playerSprite, x, y, width, height, null);

        Font font = new Font("Arial", Font.BOLD, 30);

        g.setFont(font);
        g.setColor(new Color(10, 10, 10));
    
        // Draw a string such that its base line is at x, y
        g.setColor(Color.GREEN);
        g.fillRect(10, 10, Main.Clamp(life,0,100)*2, 50);
        g.setColor(Color.GRAY);
        g.drawRect(10, 10, 200, 50);
        g.setColor(Color.BLACK);
        g.drawString(("Ronaldo's life: " + life), 10, 100);
    
    }
}
