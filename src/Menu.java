import javax.swing.*;
import java.awt.*;

public class Menu extends JPanel {

    public void paintComponent(Graphics g){
        MainWindow.menuHeight = MainWindow.fieldSize;
        this.setPreferredSize(new Dimension(MainWindow.menuWidth, MainWindow.menuHeight));
    }

    Menu(GridLayout e){
        this.setLayout(e);
        this.setPreferredSize(new Dimension(MainWindow.menuWidth, MainWindow.menuHeight));
        System.out.println(123);
    }
}
