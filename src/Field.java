import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;


public class Field extends JPanel implements Runnable {

    public void recalculate(){
        for(Point point: pointArrayList) {
            point.setPixel_x((int)((MainWindow.fieldSize)*point.getX()/MainWindow.getR()/2.2222 + MainWindow.fieldSize/2));
            point.setPixel_y((int)((MainWindow.fieldSize/2 - (MainWindow.fieldSize)*point.getY()/MainWindow.getR()/2.2222)));
            System.out.println(point.getPixel_y());
        }
    }

    Thread t;
    int radiusPointAnimation = 10;

    public void run(){
        for (int i = 1; i < 15; i++){
            radiusPointAnimation = i;
            try{
                repaint();
                Thread.sleep(20);
            } catch (InterruptedException e){

            }
        }

        for (int i = 15; i > 9; i--){
            radiusPointAnimation = i;
            try{
                repaint();
                Thread.sleep(20);
            } catch (InterruptedException e){

            }
        }

    }

    private ArrayList<Point> pointArrayList;
    private JLabel coordinates;

    private Point point;

    public void paintComponent(Graphics g) {

        this.setPreferredSize(new Dimension(MainWindow.fieldSize, MainWindow.fieldSize));

        Graphics2D g2 = (Graphics2D)g;
        BasicStroke pen1 = new BasicStroke(3);
        g2.setStroke(pen1);

        super.paintComponent(g);
        int[] xPoints = {MainWindow.fieldSize/2, MainWindow.fieldSize/2, (int)(MainWindow.fieldSize/20)};
        int[] yPoints = {(int)(MainWindow.fieldSize/1.38), MainWindow.fieldSize/2, MainWindow.fieldSize/2};
        g.fillPolygon(xPoints, yPoints, 3);

        g.fillRect((int)(MainWindow.fieldSize/3.6363636), MainWindow.fieldSize/20, (int)(MainWindow.fieldSize/4.444444), (int)(MainWindow.fieldSize/2.222222));
        g.fillArc((int)(MainWindow.fieldSize/3.6364), (int)(MainWindow.fieldSize/3.6364), (int)(MainWindow.fieldSize/2.2222),(int)(MainWindow.fieldSize/2.2222), 270, 90);

        // drawing axis
        g2.drawLine(0,MainWindow.fieldSize/2,MainWindow.fieldSize,MainWindow.fieldSize/2);
        g2.drawLine(MainWindow.fieldSize/2,0,MainWindow.fieldSize/2,MainWindow.fieldSize);

        // add R labels
        g2.drawLine((int)(MainWindow.fieldSize/2.0339), MainWindow.fieldSize/20, (int)(MainWindow.fieldSize/1.9672), MainWindow.fieldSize/20);
        g2.drawLine(MainWindow.fieldSize/20, (int)(MainWindow.fieldSize/1.9672), MainWindow.fieldSize/20, (int)(MainWindow.fieldSize/2.0339));
        g2.drawLine((int)(MainWindow.fieldSize/2.0339), (int)(MainWindow.fieldSize/1.05263),(int)(MainWindow.fieldSize/1.9672), (int)(MainWindow.fieldSize/1.05263));
        g2.drawLine((int)(MainWindow.fieldSize/1.05263), (int)(MainWindow.fieldSize/2.0339), (int)(MainWindow.fieldSize/1.05263), (int)(MainWindow.fieldSize/1.9672));


        boolean animation = false;
        if(!pointArrayList.isEmpty()) {

            for(Point point: pointArrayList) {

                if (CheckPoint.checkPoint(point) == 0 && point.wasIn) {
                    animation = true;
                    break;
                }

                point.setR(MainWindow.getR());
                point.setWasIn();
            }

            System.out.println(animation);

                for(Point point: pointArrayList) {

                    int result = CheckPoint.checkPoint(point);

                    if (animation) {
                        g.setColor(Color.RED);
                        if (result == 1)
                            g.setColor(Color.GREEN);
                        g.fillOval(point.getPixel_x() - radiusPointAnimation / 2, point.getPixel_y() - radiusPointAnimation / 2, radiusPointAnimation, radiusPointAnimation);
                    } else {
                        g.setColor(Color.RED);
                        if (result == 1) {
                            g.setColor(Color.GREEN);
                        }  else if (result == 3){
                            g.setColor(Color.YELLOW);
                        }
                        g.fillOval(point.getPixel_x() - 5, point.getPixel_y() - 5, 10, 10);
                    }
                }
        }
    }

    public void changeR(){

        for (Point point: pointArrayList){
            point.setPixel_x((int) ((MainWindow.fieldSize/2) + (int)(MainWindow.fieldSize/2.2222) * point.getX() / MainWindow.getR()));
            point.setPixel_y((int) ((MainWindow.fieldSize/2) - (int)(MainWindow.fieldSize/2.2222) * point.getY() / MainWindow.getR()));
        }

        try {
            if(t.isAlive()){
                t.stop();
            }
        } catch (NullPointerException e){

        }
        t = new Thread(this);

        t.run();
        t.start();

    }

    public void addPoint(Point p){
        pointArrayList.add(p);
        repaint();
    }

    public void clear(){
        pointArrayList.clear();
        repaint();
    }

    Field(){

        coordinates = new JLabel("(0;0)");
        this.add(coordinates);
        coordinates.setLocation(0,0);
        coordinates.setSize(500,25);
        pointArrayList = new ArrayList<>();
        this.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                point = new Point(e.getX(), e.getY(), MainWindow.getR());
                coordinates.setText("(" + point.getX() + ";" + point.getY() + ")");
                addPoint(point);
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
    }
}
