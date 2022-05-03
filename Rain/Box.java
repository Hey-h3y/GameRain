import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;

public class Box extends GameObject {

    private Image boxSprite;

    public Box(int x, int y, int life,int width, int height, ID id) {
        super(x, y, life, width, height, id);
        velY = 0;
        velX = 0;
    }
    public void interaction(GameObject object){
        if(x + width >= object.x - 16 && object.x + object.width + 16 >= x &&
		y + height >= object.y - 16 && object.y + object.height + 16 >= y){
            System.out.print("ta entrando");    
        }
		
    }
	public void collision(GameObject object){
		if(x + width >= object.x && object.x + object.width >= x && y + height >= object.y && object.y + object.height >= y ){

		}
    }  

    @Override
    public void tick() {
        x += velX;
        y += velY;
    }

    @Override
    public void render(Graphics g) {

        ImageIcon ronaldo = new ImageIcon("images\\box.png");
        boxSprite = ronaldo.getImage();

        //g.fillRect(x, y, 32, 64);
        g.drawImage(boxSprite, x, y, width, height, null);
    }
    
}
