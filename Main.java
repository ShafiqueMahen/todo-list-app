import java.time.LocalDateTime;
import java.util.ArrayList;
import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new GUIMenu();
            }
        });



    }
}
