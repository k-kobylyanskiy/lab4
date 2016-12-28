import javax.swing.*;

public class Main {

    static String locale;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                locale = new String(args[0]);
                new MainWindow();
            }
        });


    }
}
