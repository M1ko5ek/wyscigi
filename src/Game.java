import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.concurrent.ThreadLocalRandom;

public class Game extends JComponent implements KeyListener {

    private int width = 480;
    private int hight = 640;
    private boolean out_of_map = false;
    private boolean game_over = false;
    private boolean start = false;
    private int speed = 6;
    private int points = 0;
    private Car player;

    Thread t1;

    public Game ()
    {
        t1 = new Thread(new Thread1());
        t1.start();
        addKeyListener(this);
        player= new Car(width/2,hight-(hight/5),25,50);
    }

    private Car enemy1 = new Car(170,0,25,50);
    private Car enemy2 = new Car(285,0,25,50);


    @Override
    public boolean isFocusTraversable(){
        return true;
    }

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {
            if(player.get_x_pos() >= 125 && start == true) {
                player.set_x_pos(player.get_x_pos() - 5);
            }
        }

        if (key == KeyEvent.VK_RIGHT) {
            if(player.get_x_pos()<=330 && start == true) {
                player.set_x_pos(player.get_x_pos() + 5);
            }
        }
        if (key == KeyEvent.VK_UP) {
            start = true;
        }
    }


    public void keyReleased(KeyEvent e) {

    }

    public void keyTyped(KeyEvent e){

    }

    @Override
    public void paintComponent(Graphics g) {


        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        Toolkit.getDefaultToolkit().sync();

        g2d.setColor(Color.GREEN);
        g2d.fillRect(0,0,width,hight);
        g2d.setColor(Color.BLACK);
        g2d.fillRect(120,0,240,hight);

        g2d.setColor(Color.BLUE);
        g2d.fillRect(player.get_x_pos(), player.get_y_pos(), 25, 50); // (pozycja x, pozycja y, szerokość, wysokość)



        if(out_of_map == true) {
            int x = ThreadLocalRandom.current().nextInt(120, 240 + 1);
            int y = ThreadLocalRandom.current().nextInt(265, 335 + 1);
            enemy1.set_x_pos(x);
            enemy2.set_x_pos(y);
        }

        g2d.setColor(Color.RED);
        g2d.fillRect(enemy1.get_x_pos(), enemy1.get_y_pos(), 25, 50);
        g2d.fillRect(enemy2.get_x_pos(), enemy2.get_y_pos(), 25, 50);


        if (start == false) {
            g2d.setColor(Color.WHITE);
            g2d.setFont(new Font("TimesRoman", Font.PLAIN, 30));
            g2d.drawString("Press UP_ARROW to START", 30, 320);
        }

        if(start == true) {

            colision(enemy1);
            colision(enemy2);
            enemy_movement(enemy1, enemy2);

            if(points == 10)
            {
                speed = 2;
            }

            if(game_over == true)
            {
                g2d.setColor(Color.WHITE);
                g2d.setFont(new Font("TimesRoman", Font.PLAIN, 50));
                g2d.drawString("Points: " + points,120,320);
                t1.stop();
            }
        }



    }

    void enemy_movement(Car e1, Car e2)
    {
        Car enemy1 = e1;
        Car enemy2 = e2;

        if (out_of_map == false) {
            int x = enemy1.get_y_pos();
            int y = x + 1;
            enemy1.set_y_pos(y);
            enemy2.set_y_pos(y);

            if (enemy1.get_y_pos() == hight) {
                out_of_map = true;
            }

        } else if (out_of_map == true) {
            enemy1.set_y_pos(1);
            enemy2.set_y_pos(1);
            points++;
            if (speed > 3) {
                speed--;
            }
            out_of_map = false;


        }
    }

    void colision(Car e) {
        Car enemy = e;

        int x_player = player.get_x_pos();
        int y_player = player.get_y_pos();
        int x_enemy = enemy.get_x_pos();
        int y_enemy = enemy.get_y_pos();


        if (x_player >= x_enemy && x_player <= x_enemy + enemy.get_car_width() && y_player >= y_enemy && y_player <= y_enemy + enemy.get_car_hight() ||
                x_player + player.get_car_width() >= x_enemy && x_player <= x_enemy + enemy.get_car_width() && y_player + player.get_car_hight()>= y_enemy && y_player <= y_enemy + enemy.get_car_hight()) {
            game_over = true;
        }
    }

    class Thread1 implements Runnable
    {
        public void run(){
            while(true){
                try{
                    Thread.sleep(speed);
                    repaint();
                }catch(Exception ex){
                    ex.printStackTrace();
                }
            }
        }
    }
}
