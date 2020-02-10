import javax.swing.*;

public class Car {

    private int x_pos;
    private int y_pos;
    private int car_width;
    private int car_hight;

    public Car(int x, int y, int w, int h) {
        this.x_pos = x;
        this.y_pos = y;
        this.car_width = w;
        this.car_hight = h;
    }

    public int get_x_pos() {
        return x_pos;
   }

    public int get_y_pos() {
        return y_pos;
    }

    public int get_car_width(){return car_width;}

    public int get_car_hight(){return car_hight;}

    public void set_x_pos(int x) {
        this.x_pos = x;
    }

    public void set_y_pos(int y) {
        this.y_pos = y;
    }
}
