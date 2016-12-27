import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.*;

public class MainWindow {

    public JFrame mainWindow;
    public JLabel x_label;
    public JLabel y_label;
    public Field field = new Field();
    public static JSpinner spinner;
    public JButton add;
    public JButton clear;
    public Elements elements;

    private double width;
    private double height;

    private int minWidth;
    private int minHeight;

    public static int menuWidth;
    public static int menuHeight;

    public static Integer fieldSize;

    MainWindow(){
        buildGUI();
    }

    private JFrame initializeMainWindow(JFrame mainWindow){

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        width = screenSize.getWidth();
        height = screenSize.getHeight();

        minWidth = (int)(width/1.7075);
        minHeight = (int)(height/1.28);

        menuWidth = (int)(width/6.83);
        menuHeight = minHeight;

        mainWindow.setSize(minWidth, minHeight);
        fieldSize = Math.min(mainWindow.getHeight(), mainWindow.getWidth() - menuWidth);
        mainWindow.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
        mainWindow.setResizable(true);
        mainWindow.setMinimumSize(new Dimension(minWidth, minHeight));
        mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        return mainWindow;
    }

    private void buildGUI(){

        x_label = new JLabel("Выберите координату х: ");
        y_label = new JLabel("Выберите координату y: ");
        mainWindow = new JFrame("Лабораторная работа №5");

        mainWindow = initializeMainWindow(mainWindow);
        Menu menu = new Menu(new GridLayout(14, 1));
        field.setPreferredSize(new Dimension(fieldSize, fieldSize));
        field.setBackground(Color.decode("#F0E68C"));
        field.setLayout(null);

        JPanel x_coordinates = new JPanel(new GridLayout(2,1));
        JPanel y_coordinates = new JPanel(new GridLayout(1,5));

        spinner = new JSpinner(new SpinnerNumberModel(1,1,Integer.MAX_VALUE,1));
        spinner.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                field.changeR();
            }
        });

        elements = new Elements();
        x_coordinates.add(x_label);
        x_coordinates.add(elements.x_coordinate);
        elements.radioButtons.stream().forEach(t -> y_coordinates.add(t));

        menu.add(x_coordinates);
        menu.add(y_label);
        menu.add(y_coordinates);
        menu.add(spinner);

        add = new JButton("Add");
        add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                elements.radioButtons.stream().filter(jRadioButton -> jRadioButton.isSelected()).forEach(jRadioButton -> field.addPoint(new Point(MainWindow.fieldSize/2 + (int)(MainWindow.fieldSize/2.2222) / MainWindow.getR() * Integer.parseInt(elements.x_coordinate.getSelectedItem().toString()), (MainWindow.fieldSize/2 - Integer.parseInt(jRadioButton.getLabel())*(int)(MainWindow.fieldSize/2.2222)/MainWindow.getR()), MainWindow.getR())));
            }
        });
        clear = new JButton("Clear");
        clear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                field.clear();
            }
        });

        menu.add(add);
        menu.add(clear);

        mainWindow.add(menu);
        mainWindow.add(field);
        mainWindow.setVisible(true);

        mainWindow.addComponentListener(new ComponentListener() {
            @Override
            public void componentResized(ComponentEvent e) {
                fieldSize = Math.min(mainWindow.getHeight(), mainWindow.getWidth() - menuWidth - 2);
                field.repaint();
                menu.repaint();
                field.recalculate();
            }

            @Override
            public void componentMoved(ComponentEvent e) {
            }

            @Override
            public void componentShown(ComponentEvent e) {
                fieldSize = mainWindow.getHeight();
                field.repaint();
                System.out.println(fieldSize);
            }

            @Override
            public void componentHidden(ComponentEvent e) {
                fieldSize = mainWindow.getHeight();
                field.repaint();
                System.out.println(fieldSize);
            }
        });

    }

    public static int getR(){
        return (Integer)spinner.getValue();
    }

}
