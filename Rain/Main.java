import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.Random;

public class Main extends Canvas implements Runnable {

    public static final int WIDTH = 1312;
    public static final int HEIGHT = 748;
    final int TICKS_PER_SECOND = 60;
    final int SKIP_TICKS = 1000 / TICKS_PER_SECOND;
    final int MAX_FRAMESKIP = 5;

    private Thread thread;
    private boolean running = false;

    private Random r;

    private Handler handler;
    private Player player;
    private Box box;

    public Main(){

        
        handler = new Handler();

        Window window = new Window(WIDTH, HEIGHT, "Rain", this);
        window.requestFocus();
        
        box = new Box(500, 300, 0, 32,32, ID.Box);
        
        player = new Player(100, 300, 100, 64, 64, ID.Player, handler);
        
        this.addKeyListener(new KeyInput(handler));
        
        r = new Random();
        handler.addObject(box);
        handler.addObject(player);

        for(int i = 0; i < 200; i++){
            handler.addObject(new Rain(r.nextInt(WIDTH), r.nextInt(1500)-1532, 0, 2, 32, ID.Rain));
            handler.addObject(new RainThic(r.nextInt(WIDTH), r.nextInt(1500)-1532, 0, 4, 32, ID.RainThic));
        }
    }

    public synchronized void start(){
        thread = new Thread(this);
        thread.start();
        running = true;
    }

    public synchronized void stop(){
        try{
            thread.join();
            running = false;
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void run(){

        double next_game_tick = System.currentTimeMillis();
        int loops;

        while (running) {
            loops = 0;
            while (System.currentTimeMillis() > next_game_tick && loops < MAX_FRAMESKIP) {

                tick();

                next_game_tick += SKIP_TICKS;
                loops++;
			}
        render();
        }
    }

    private void tick(){
        handler.tick();;
        
        for(int i = 0; i < handler.object.size(); i++){

            GameObject tempObject = handler.object.get(i);

            if(tempObject.getId() ==  ID.Player){
                box.interaction(tempObject);
            }
            if(tempObject.getId() ==  ID.Box){
                player.collision(tempObject);
            }
        }

    }

    private void render(){
        BufferStrategy bs = this.getBufferStrategy();
        if(bs == null){
            this.createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();

        g.setColor(new Color(240, 200, 240));
        g.fillRect(0, 0, WIDTH, HEIGHT);

        handler.render(g);

        g.dispose();
        bs.show();
    }

    public static int Clamp(int var, int min, int max){
        if(var >= max)
            return var = max;
        else if( var <= min)
            return var = min;
        else
            return var;
    }
    public static void main(String[] args) {
        new Main();
    }
}
