import javax.swing.*;
import java.util.ArrayList;

public class Elements {
    JComboBox<Integer> x_coordinate;

    ArrayList<JRadioButton> radioButtons = new ArrayList<>();

    Elements(){
        x_coordinate = new JComboBox<>();
        for(int i = 0; i < 5; i++){
            x_coordinate.addItem(i*5);
            radioButtons.add(new JRadioButton(""+i));
        }
    }
}
