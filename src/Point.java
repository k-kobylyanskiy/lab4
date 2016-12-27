public class Point {
    private int pixel_x;
    private int pixel_y;

    private float real_x;
    private float real_y;

    private int R;

    public boolean wasIn;

    Point(int x, int y, int r){
        this.pixel_x = x;
        this.pixel_y = y;

        R = r;

        real_x = (float)(x-(MainWindow.fieldSize/2))*MainWindow.getR()/(int)(MainWindow.fieldSize/2.2222);
        real_y = (float)((MainWindow.fieldSize/2)-y)*MainWindow.getR()/(int)(MainWindow.fieldSize/2.2222);

        setWasIn();
    }

    public void setWasIn(){
        wasIn = Forma.isInside(real_x, real_y, R);
    }

    public void setR(int r){
        R = r;
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
