import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class Field extends JPanel implements Runnable {

    Thread t;
    int radiusPointAnimation = 10;

    public void run(){
        for (int i = 1; i < 11; i++){
            radiusPointAnimation = i;
            try{
                repaint();
                Thread.sleep(80);
            } catch (InterruptedException e){

            }
        }
    }

    private ArrayList<Point> pointArrayList;
    private JLabel coordinates;

    private JLabel r1;
    private JLabel r2;
    private JLabel r3;
    private JLabel r4;

    ArrayList<JLabel> rLabels;

    private Point point;

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        int[] xPoints = {300, 300, 435};
        int[] yPoints = {435, 300, 300};
        g.fillPolygon(xPoints, yPoints, 3);

        g.fillRect(165, 30, 135, 270);
        g.fillArc(165, 165, 270, 270, 180, 90);

        // drawing axis
        g.drawLine(0,300,600,300);
        g.drawLine(300,0,300,600);


        // add R labels
        g.drawLine(295, 30, 305, 30);
        g.drawLine(30, 305, 30, 295);
        g.drawLine(295, 570, 305, 570);
        g.drawLine(570, 295, 570, 305);


        if(!pointArrayList.isEmpty()) {

            for(Point point: pointArrayList) {

                boolean wasIn = false;
                boolean animation = false;

                if(point.getPixel_x() > 135 && point.getPixel_x() < 300 &&
                        point.getPixel_y() > 30 && point.getPixel_y() < 300){
                    wasIn = true;
                    System.out.println("YEEEEA " + point.getPixel_x());
                }

                if(point.getPixel_x() * point.getPixel_x() +
                        point.getPixel_y() * point.getPixel_y() < 135*135 &&
                        point.getPixel_x() > 135 && point.getPixel_x() < 300 &&
                        point.getPixel_y() > 300 && point.getPixel_y() < 435){
                    wasIn = true;
                }

                if(point.getPixel_x() > 300 && point.getPixel_x() < 435 &&
                        point.getPixel_y() > 300 && point.getPixel_y() < 435 &&
                        point.getPixel_y() > point.getPixel_x() - 135){
                    wasIn = true;
                }


                point.setPixel_x((int)(300 + 270 * point.getX() / MainWindow.getR()));
                point.setPixel_y((int)(300 - 270 * point.getY() / MainWindow.getR()));

                if(!(point.getPixel_x() > 135 && point.getPixel_x() < 300 &&
                        point.getPixel_y() > 30 && point.getPixel_y() < 300) && wasIn){
                    animation = true;
                }

                if (animation) {

                    g.setColor(Color.RED);
                    if (Forma.isInside(point.getX(), point.getY(), MainWindow.getR()))
                        g.setColor(Color.GREEN);
                    g.fillOval(point.getPixel_x() - radiusPointAnimation / 2, point.getPixel_y() - radiusPointAnimation / 2, radiusPointAnimation, radiusPointAnimation);
                } else {
                    g.setColor(Color.RED);
                    if (Forma.isInside(point.getX(), point.getY(), MainWindow.getR()))
                        g.setColor(Color.GREEN);
                    g.fillOval(point.getPixel_x() - 5, point.getPixel_y() - 5, 10, 10);
                }
            }
        }
    }

    public void changeR(){
        try {
            if(t.isAlive()){
                t.stop();
            }
        } catch (NullPointerException e){

        }
        t = new Thread(this);
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

        rLabels = new ArrayList<>();

        r1 = new JLabel("-R");
        r1.setLocation(25,270);
        r1.setSize(40,25);

        r2 = new JLabel("-R");
        r2.setLocation(315, 557);
        r2.setSize(40,25);

        r4 = new JLabel("R");
        r4.setLocation(315,17);
        r4.setSize(40,25);

        r3 = new JLabel("R");
        r3.setLocation(565, 270);
        r3.setSize(40,25);

        rLabels.add(r1);
        rLabels.add(r2);
        rLabels.add(r3);
        rLabels.add(r4);

        this.add(r1);
        this.add(r2);
        this.add(r3);
        this.add(r4);

        coordinates = new JLabel("(0;0)");
        this.add(coordinates);
        coordinates.setLocation(0,0);
        coordinates.setSize(500,25);
        pointArrayList = new ArrayList<>();
        this.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                point = new Point(e.getX(), e.getY());
                addPoint(point);
                coordinates.setText("(" + point.getX() + ";" + point.getY() + ")");
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
        repaint();
    }
}
