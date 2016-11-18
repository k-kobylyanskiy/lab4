import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainWindow {

    public JFrame mainWindow;
    public JLabel x_label;
    public JLabel y_label;
    public Field field;
    public static JSpinner spinner;
    public JButton add;
    public JButton clear;
    public Elements elements;

    MainWindow(){
        buildGUI();
    }

    private void buildGUI(){
        x_label = new JLabel("Выберите координату х: ");
        y_label = new JLabel("Выберите координату y: ");

        mainWindow = new JFrame("Лабораторная работа №4");
        mainWindow.setSize(800, 600);
        mainWindow.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
        mainWindow.setResizable(false);
        mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel menu = new JPanel(new GridLayout(14, 1));
        menu.setPreferredSize(new Dimension(200, 600));


        // Field place

        field = new Field();
        field.setPreferredSize(new Dimension(600, 600));
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
                elements.radioButtons.stream().filter(jRadioButton -> jRadioButton.isSelected()).forEach(jRadioButton -> field.addPoint(new Point(300 + 270 / MainWindow.getR() * Integer.parseInt(elements.x_coordinate.getSelectedItem().toString()), (300 - Integer.parseInt(jRadioButton.getLabel())*270/MainWindow.getR()))));
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
    }

    public static int getR(){
        return (Integer)spinner.getValue();
    }

}
