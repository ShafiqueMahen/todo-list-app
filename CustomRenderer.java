import javax.swing.*;
import java.awt.*;

public class CustomRenderer extends JLabel implements ListCellRenderer<Todo> {
    //Constructor
    public CustomRenderer() {
        setOpaque(true);
    }
    //Custom renderer for JList
    @Override
    public Component getListCellRendererComponent(JList<? extends Todo> list, Todo todo, int index, boolean isSelected, boolean cellHasFocus) {
        Color background = new Color(22, 24, 25); //Set background color
        Color foreground = null; //Initialise foreground
        Category cat = todo.getCat(); //Get category for item
        //Determine foreground color based on category enum constant
        if (cat == Category.RED) foreground = Color.RED;
        if (cat == Category.WHITE) foreground = Color.WHITE;
        if (cat == Category.BLUE) foreground = Color.BLUE;
        if (cat == Category.PURPLE) foreground = new Color(102,0,153); //Purple
        if (cat == Category.YELLOW) foreground = Color.YELLOW;
        if (cat == Category.GREEN) foreground = Color.GREEN;
        //Styling
        setBorder(BorderFactory.createLineBorder(Color.ORANGE)); //Create orange line border
        setForeground(foreground); //Set list item text as foreground
        setBackground(background); //Set list item background
        setText(todo.toString()); //Set list item text as string version of object
        setFont(new Font("Arial", Font.BOLD, 16)); //Set list font
        setSize(400,400); //Set list cell size

        return this; //Return
    }
}
