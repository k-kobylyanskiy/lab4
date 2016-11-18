public class Point {
    private int pixel_x;
    private int pixel_y;

    private float real_x;
    private float real_y;

    Point(int x, int y){
        this.pixel_x = x;
        this.pixel_y = y;

        real_x = (float)(x-300)*MainWindow.getR()/270;
        real_y = (float)(300-y)*MainWindow.getR()/270;
    }

    public void setPixel_x(int newX){
        pixel_x = newX;
    }

    public void setPixel_y(int newY){
        pixel_y = newY;
    }

    public int getPixel_x(){
        return pixel_x;
    }

    public int getPixel_y(){
        return pixel_y;
    }

    public float getX(){
        return real_x;
    }

    public float getY(){
        return real_y;
    }
}