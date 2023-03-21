//Imports
import javax.swing.*;
import javax.swing.plaf.basic.BasicScrollBarUI;
import java.awt.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Collections;
//GUI menu
public class GUIMenu {
    //Main frame variables
    private JFrame frame;
    JLabel label;
    private JList list;
    private JPanel listPanel;
    private JPanel buttonPanel;
    private JButton addButton;
    private JButton updateButton;
    private JButton deleteButton;
    private DefaultListModel<Todo> model = new DefaultListModel<>();
    //Todo arraylist
    public ArrayList<Todo> todoList = new ArrayList<Todo>();
    //Variable used for updating todos
    int updateChoice;
    //Constructor
    GUIMenu(){
        //Try and set look and feel that looks the same on all platforms
        try{
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        //Create main frame
        frame = new JFrame("Todo List App"); //Frame title
        frame.setLayout(new FlowLayout()); //Set flow layout for the frame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Stop running program when close
        frame.setVisible(true); //Make frame visible
        frame.setBackground(Color.ORANGE); //Set color behind title
        frame.getContentPane().setBackground(Color.darkGray); //Set color of window
        frame.setResizable(false); //Make frame so it's not resizeable
        frame.setLocationRelativeTo(null); //Make frame appear in the centre of the screen
        //SAMPLE LIST OF TODOS
        Todo t1 = new Todo("Maths homework", LocalDateTime.now(), Category.BLUE, Importance.HIGH, Status.PARTIAL);
        Todo t2 = new Todo("Wash clothes", LocalDateTime.now(), Category.RED, Importance.NORMAL, Status.COMPLETED);
        Todo t3 = new Todo("Clean room", LocalDateTime.now(), Category.GREEN, Importance.LOW, Status.PENDING);
        Todo t4 = new Todo("Wash car", LocalDateTime.now(), Category.WHITE, Importance.NORMAL, Status.PENDING);
        Todo t5 = new Todo("Make bed", LocalDateTime.now(), Category.PURPLE, Importance.HIGH, Status.COMPLETED);
        Todo t6 = new Todo("Mop the living room floor", LocalDateTime.now(), Category.YELLOW, Importance.NORMAL, Status.PARTIAL);
        //Add sample list to todo arraylist
        todoList.add(t1);
        todoList.add(t2);
        todoList.add(t3);
        todoList.add(t4);
        todoList.add(t5);
        todoList.add(t6);
        //Create new JList
        list = new JList();
        list.setModel(model); //Set default JList model
        for (Todo todo : todoList) { //Populate JList using contents from the todo arraylist
            model.addElement(todo);
        }
        //Create a panel to hold JList component
        listPanel = new JPanel(); //Call it the list panel
        listPanel.setBackground(Color.ORANGE); //Set background to orange
        list.setVisibleRowCount(4); //Show max 4 elements of the JList before scrolling
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); //Single selection only
        list.setCellRenderer(new CustomRenderer()); //Use of a custom renderer to draw JList
        JScrollPane sp = new JScrollPane(list); //Wrap JList in a JScrollPane
        sp.setBorder(BorderFactory.createLineBorder(Color.darkGray)); //Draw dark gray border
        sp.setBackground(Color.ORANGE); //Set background to orange
        sp.getVerticalScrollBar().setBackground(Color.ORANGE); //Set background of scroll bar to orange
        sp.getVerticalScrollBar().setBorder(BorderFactory.createMatteBorder(0, 1, 0, 0, Color.darkGray)); //Draw border to the left of scroll bar
        sp.getHorizontalScrollBar().setBackground(Color.ORANGE); //Set background of scroll bar to orange
        sp.getHorizontalScrollBar().setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, Color.darkGray)); //Draw border on top of scroll bar
        sp.getVerticalScrollBar().setUI(new BasicScrollBarUI() { //Style vertical scroll bar
            @Override
            protected void configureScrollBarColors() {
                this.thumbColor = new Color(22, 24, 25); //Change scroll bar color
            }
        });
        sp.getHorizontalScrollBar().setUI(new BasicScrollBarUI() { //Style horizontal scroll bar
            @Override
            protected void configureScrollBarColors() {
                this.thumbColor = new Color(22, 24, 25); //Change scroll bar color
            }
        });
        listPanel.add(sp); //Add wrapped JList to list panel
        frame.add(listPanel); //Add list panel to the main frame
        //Create a panel to hold JButton components
        buttonPanel = new JPanel(); //Call it the button panel
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS)); //Set layout to box layout and to display components from top to bottom of panel
        buttonPanel.setBackground(Color.darkGray); //Set background of button panel to dark gray
        addButton = new JButton("Add Todo!"); //Button title
        addButton.setOpaque(true); //Make button opaque
        addButton.setBackground(Color.ORANGE); //Set background of button to orange
        addButton.setBorderPainted(false); //Disable border
        addButton.setPreferredSize(new Dimension(120, 25)); //Set button size
        addButton.setMaximumSize(new Dimension(Short.MAX_VALUE, Short.MAX_VALUE)); //Set max button size as back-up
        addButton.setFocusPainted(false); //Disable focus
        addButton.setForeground(new Color(22, 24, 25)); //Set color of button text
        buttonPanel.add(addButton); //Add JButton to button panel

        buttonPanel.add(Box.createRigidArea(new Dimension(0, 10))); //Create space between button components
        updateButton = new JButton("Update Todo!"); //Button title
        updateButton.setOpaque(true); //Make button opaque
        updateButton.setBackground(Color.ORANGE); //Set background of button to orange
        updateButton.setPreferredSize(new Dimension(120, 25)); //Set button size
        updateButton.setMaximumSize(new Dimension(Short.MAX_VALUE, Short.MAX_VALUE)); //Set max button size as back-up
        updateButton.setBorderPainted(false); //Disable border
        updateButton.setFocusPainted(false); //Disable focus
        updateButton.setForeground(new Color(22, 24, 25)); //Set color of button text
        buttonPanel.add(updateButton); //Add JButton to button panel

        buttonPanel.add(Box.createRigidArea(new Dimension(0, 10))); //Create space between button components
        deleteButton = new JButton("Delete Todo!"); //Button title
        deleteButton.setOpaque(true); //Make button opaque
        deleteButton.setBackground(Color.ORANGE); //Set background of button to orange
        deleteButton.setPreferredSize(new Dimension(120, 25)); //Set button size
        deleteButton.setMaximumSize(new Dimension(Short.MAX_VALUE, Short.MAX_VALUE)); //Set max button size as back-up
        deleteButton.setBorderPainted(false); //Disable border
        deleteButton.setFocusPainted(false); //Disable focus
        deleteButton.setForeground(new Color(22, 24, 25)); //Set color of button text
        buttonPanel.add(deleteButton); //Add JButton to button panel

        frame.add(buttonPanel); //Add button panel to main frame
        frame.pack(); //Pack frame
        
        addButton.addActionListener(e -> addTodoFrame()); //When add button is pressed, open add todo frame

        updateButton.addActionListener(e -> { //When update button is pressed, open todo number frame
            if (todoList.size() < 1) //If todo list is empty, open no todo frame
            {
                noTodoFrame();
                return;
            }
            todoNumberFrame(); //Otherwise open todo number frame
        });

        deleteButton.addActionListener(e -> { //When delete button is pressed, open delete todo frame
            if (todoList.size() < 1) //If todo list is empty, open no todo frame
            {
                noTodoFrame();
                return;
            }
            deleteTodoFrame();
        });
    }

    private void addTodoFrame() {
        EventQueue.invokeLater(() -> {
            JFrame newFrame = new JFrame("Add Todo!"); //Frame title
            newFrame.setSize(400, 300); //Set frame size
            newFrame.setVisible(true); //Make frame visible
            newFrame.setResizable(false); //Make it so the frame isn't resizable
            newFrame.setLocationRelativeTo(frame); //Draw in centre of main frame
            newFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); //Close frame when close operation is triggered
            newFrame.setBackground(Color.ORANGE); //Set color behind title
            newFrame.getContentPane().setBackground(Color.darkGray); //Set color of window
            //Try and set look and feel that looks the same on all platforms
            try
            {
                UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
            } catch (Exception e) {
                e.printStackTrace();
            }
            //Create panel to hold components
            JPanel panel = new JPanel(); //Call it panel
            panel.setLayout(new GridBagLayout()); //Set layout as GridBagLayout
            panel.setBackground(Color.darkGray); //Set background as dark gray
            GridBagConstraints c = new GridBagConstraints(); //Add constraints
            panel.setOpaque(true); //Make panel opaque

            JLabel title = new JLabel("Title: "); //JLabel title
            title.setFont(new Font("Arial", Font.BOLD, 16)); //Set font for label
            title.setForeground(Color.ORANGE); //Set text color to orange
            c.gridx = 0; //x co-ordinate
            c.gridy = 0; //y co-ordinate
            c.anchor = GridBagConstraints.WEST; //Place component on west-side
            c.insets = new Insets(0, 0, 10, 0); //Inset spacing
            title.setVisible(true); //Make label visible
            panel.add(title, c); //Add label to panel

            JTextField todoTitle = new JTextField("Enter title here"); //JTextField title
            todoTitle.setBorder(BorderFactory.createLineBorder(Color.ORANGE)); //Set orange line border
            c.gridx = 1; //x co-ordinate
            c.gridy = 0; //y co-ordinate
            c.fill = GridBagConstraints.HORIZONTAL; //Fill allocated space
            panel.add(todoTitle, c); //Add text field to panel

            JLabel due = new JLabel("Due date: "); //JLabel title
            due.setFont(new Font("Arial", Font.BOLD, 16)); //Set font for label
            due.setForeground(Color.ORANGE); //Set text color to orange
            c.gridx = 0; //x co-ordinate
            c.gridy = 1; //y co-ordinate
            c.anchor = GridBagConstraints.WEST; //Place component on west-side
            due.setVisible(true); //Make label visible
            panel.add(due, c); //Add label to panel

            JTextField todoDue = new JTextField("Enter date in format: YYYY-MM-DDTHH:MM"); //JTextField title
            todoDue.setBorder(BorderFactory.createLineBorder(Color.ORANGE)); //Set orange line border
            c.gridx = 1; //x co-ordinate
            c.gridy = 1; //y co-ordinate
            c.fill = GridBagConstraints.HORIZONTAL; //Fill allocated space
            panel.add(todoDue, c); //Add text field to panel

            JLabel category = new JLabel("Category: "); //JLabel title
            category.setFont(new Font("Arial", Font.BOLD, 16)); //Set font for label
            category.setForeground(Color.ORANGE); //Set text color to orange
            c.gridx = 0; //x co-ordinate
            c.gridy = 2; //y co-ordinate
            c.anchor = GridBagConstraints.WEST; //Place component on west-side
            category.setVisible(true); //Make label visible
            panel.add(category, c); //Add label to panel

            String[] categories = {"RED", "WHITE", "BLUE", "PURPLE", "YELLOW", "GREEN"}; //String array
            JComboBox<String> todoCategories = new JComboBox<String>(categories); //Use array as options for JComboBox
            todoCategories.setBorder(BorderFactory.createLineBorder(Color.ORANGE)); //Set orange line border
            c.gridx = 1; //x co-ordinate
            c.gridy = 2; //y co-ordinate
            c.fill = GridBagConstraints.HORIZONTAL; //Fill allocated space
            todoCategories.setVisible(true); //Make JComboBox visible
            panel.add(todoCategories, c); //Add combo box to panel

            JLabel importance = new JLabel("Importance: "); //JLabel title
            importance.setFont(new Font("Arial", Font.BOLD, 16)); //Set font for label
            importance.setForeground(Color.ORANGE); //Set text color to orange
            c.gridx = 0; //x co-ordinate
            c.gridy = 3; //y co-ordinate
            c.anchor = GridBagConstraints.WEST; //Place component on west-side
            importance.setVisible(true); //Make label visible
            panel.add(importance, c); //Add label to panel

            String[] importances = {"LOW", "NORMAL", "HIGH"}; //String array
            JComboBox<String> todoImportance = new JComboBox<String>(importances); //Use array as options for JComboBox
            todoImportance.setBorder(BorderFactory.createLineBorder(Color.ORANGE)); //Set orange line border
            c.gridx = 1; //x co-ordinate
            c.gridy = 3; //y co-ordinate
            c.fill = GridBagConstraints.HORIZONTAL; //Fill allocated space
            todoImportance.setVisible(true); //Make JComboBox visible
            panel.add(todoImportance, c); //Add combo box to panel

            JLabel completion = new JLabel("Completion: "); //JLabel title
            completion.setFont(new Font("Arial", Font.BOLD, 16)); //Set font for label
            completion.setForeground(Color.ORANGE); //Set text color to orange
            c.gridx = 0; //x co-ordinate
            c.gridy = 4; //y co-ordinate
            c.anchor = GridBagConstraints.WEST; //Place component on west-side
            completion.setVisible(true); //Make label visible
            panel.add(completion, c); //Add label to panel

            String[] status = {"PENDING", "STARTED", "PARTIAL", "COMPLETED"}; //String array
            JComboBox<String> todoCompletion = new JComboBox<String>(status); //Use array as options for JComboBox
            todoCompletion.setBorder(BorderFactory.createLineBorder(Color.ORANGE)); //Set orange line border
            c.gridx = 1; //x co-ordinate
            c.gridy = 4; //y co-ordinate
            c.fill = GridBagConstraints.HORIZONTAL; //Fill allocated space
            todoCompletion.setVisible(true); //Make JComboBox visible
            panel.add(todoCompletion, c); //Add combo box to panel

            JButton confirm = new JButton("Confirm"); //JButton title
            c.gridx = 1; //x co-ordinate
            c.gridy = 5; //y co-ordinate
            c.insets = new Insets(0, 50, 0, 50); //Inset spacing
            confirm.setOpaque(true); //Make button visible
            confirm.setBackground(Color.ORANGE); //Set background color orange
            confirm.setBorderPainted(false); //Disable border
            confirm.setFocusPainted(false); //Disable focus
            confirm.setForeground(new Color(22, 24, 25)); //Set text color
            newFrame.getRootPane().setDefaultButton(confirm); //Enter key shortcut
            panel.add(confirm, c); //Add button to panel
            //When pressed, gather data and create todo
            confirm.addActionListener(e -> {
                String addTitle = todoTitle.getText(); //Get inputted title
                String addStringDue = todoDue.getText(); //Get inputted due date
                LocalDateTime addDue;
                Category addCat = null;
                Importance addImportance = null;
                Status addStatus = null;

                if (addTitle.trim().isEmpty()) //Catch empty title/title with only spaces
                {
                    errorFrame(); //Open error frame
                    return; //End
                }

                try {
                    addDue = LocalDateTime.parse(addStringDue); //Parse inputted due into LocalDateTime format
                } catch (DateTimeParseException err){
                   errorFrame(); //Open error frame
                   return; //End
                }
                //Get selected category by user and set corresponding enum
                if (todoCategories.getSelectedItem().equals("RED")) addCat = Category.RED;
                if (todoCategories.getSelectedItem().equals("WHITE")) addCat = Category.WHITE;
                if (todoCategories.getSelectedItem().equals("BLUE")) addCat = Category.BLUE;
                if (todoCategories.getSelectedItem().equals("PURPLE")) addCat = Category.PURPLE;
                if (todoCategories.getSelectedItem().equals("YELLOW")) addCat = Category.YELLOW;
                if (todoCategories.getSelectedItem().equals("GREEN")) addCat = Category.GREEN;
                //Get selected importance by user and set corresponding enum
                if (todoImportance.getSelectedItem().equals("LOW")) addImportance = Importance.LOW;
                if (todoImportance.getSelectedItem().equals("NORMAL")) addImportance = Importance.NORMAL;
                if (todoImportance.getSelectedItem().equals("HIGH")) addImportance = Importance.HIGH;
                //Get selected status by user and set corresponding enum
                if (todoCompletion.getSelectedItem().equals("PENDING")) addStatus = Status.PENDING;
                if (todoCompletion.getSelectedItem().equals("STARTED")) addStatus = Status.STARTED;
                if (todoCompletion.getSelectedItem().equals("PARTIAL")) addStatus = Status.PARTIAL;
                if (todoCompletion.getSelectedItem().equals("COMPLETED")) addStatus = Status.COMPLETED;
                //Using gathered information, create new Todo object
                Todo addTodo = new Todo(addTitle, addDue, addCat, addImportance, addStatus);
                //Add new object to todo arraylist
                todoList.add(addTodo);
                //Get original JList and empty it to repopulate it with updated contents
                DefaultListModel listModel = (DefaultListModel) list.getModel();
                listModel.clear(); //Clear JList
                for (int i = 0; i < todoList.size(); i++)
                {
                    listModel.addElement(todoList.get(i)); //Repopulate JList
                }

                newFrame.dispose(); //Close frame
            });


            newFrame.add(panel); //Add panel to frame
        });
    }

    private void todoNumberFrame() {
        EventQueue.invokeLater(() -> {
            JFrame newFrame = new JFrame("Which todo would you like to update?"); //Create frame
            newFrame.setSize(400, 100); //Set frame size
            newFrame.setVisible(true); //Make frame visible
            newFrame.setResizable(false); //Make it so the frame isn't resizable
            newFrame.setLocationRelativeTo(frame); //Create in centre of main frame
            newFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); //Close frame when close operation is triggered
            newFrame.setBackground(Color.ORANGE); //Set color behind title
            newFrame.getContentPane().setBackground(Color.darkGray); //Set color of window
            //Try and set the look and feel that looks the same on all platforms
            try
            {
                UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
            } catch (Exception e) {
                e.printStackTrace();
            }
            //Create panel
            JPanel panel = new JPanel(); //Call it panel
            panel.setLayout(new GridBagLayout()); //Set GridBagLayout
            panel.setBackground(Color.darkGray); //Set background color as dark gray
            GridBagConstraints c = new GridBagConstraints(); //Add constraints
            panel.setOpaque(true); //Make panel opaque

            JLabel update = new JLabel("Todo to update: "); //JLabel title
            update.setFont(new Font("Arial", Font.BOLD, 16)); //Set label font
            update.setForeground(Color.ORANGE); //Set background as orange
            c.gridx = 0; //x co-ordinate
            c.gridy = 0; //y co-ordinate
            c.anchor = GridBagConstraints.WEST; //Place component on west-side
            c.insets = new Insets(0, 0, 10, 0); //Inset spacing
            update.setVisible(true); //Make label visible
            panel.add(update, c); //Add label to panel

            JTextField todoNumber = new JTextField("Enter todo number here"); //JTextField title
            todoNumber.setBorder(BorderFactory.createLineBorder(Color.ORANGE)); //Set orange line border
            c.gridx = 1; //x co-ordinate
            c.gridy = 0; //y co-ordinate
            c.fill = GridBagConstraints.HORIZONTAL; //Fill allocated space
            panel.add(todoNumber, c); //Add text field to panel

            JButton confirm = new JButton("Confirm"); //JButton title
            c.gridx = 1; //x co-ordinate
            c.gridy = 1; //y co-ordinate
            c.insets = new Insets(0, 50, 0, 50); //Inset spacing
            confirm.setOpaque(true); //Make button opaque
            confirm.setBackground(Color.ORANGE); //Set button background as orange
            confirm.setBorderPainted(false); //Disable border
            confirm.setFocusPainted(false); //Disable focus
            confirm.setForeground(new Color(22, 24, 25)); //Set text color
            newFrame.getRootPane().setDefaultButton(confirm); //Enter key shortcut
            panel.add(confirm, c); //Add button to panel
            //When pressed, gather data used to update todo
            confirm.addActionListener(e -> {
                try {
                    updateChoice = Integer.parseInt(todoNumber.getText()); //Get integer from text field
                } catch (NumberFormatException err){
                    errorFrame();
                    return;
                }
                //If input is not within bounds
                if (updateChoice > todoList.size() || updateChoice <= 0)
                {
                    errorFrame(); //Open error frame
                    return; //End
                }

                updateChoice--; //Align input with array indexing
                newFrame.dispose(); //Close frame
                updateTodoFrame(); //Open update todo frame



            });

            newFrame.add(panel); //Add panel to frame

        });
    }

    private void updateTodoFrame() {
        EventQueue.invokeLater(() -> {
            JFrame newFrame = new JFrame("Update Todo!"); //Frame title
            newFrame.setSize(400, 300); //Set frame size
            newFrame.setVisible(true); //Make frame visible
            newFrame.setResizable(false); //Make it so the frame isn't resizable
            newFrame.setLocationRelativeTo(frame); //Draw in centre of main frame
            newFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); //Close frame when close operation is triggered
            newFrame.setBackground(Color.ORANGE); //Set color behind title
            newFrame.getContentPane().setBackground(Color.darkGray); //Set color of window
            //Try and set look and feel so it looks the same on every platform
            try
            {
                UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
            } catch (Exception e) {
                e.printStackTrace();
            }
            //Create panel
            JPanel panel = new JPanel(); //Call it panel
            panel.setLayout(new GridBagLayout()); //Set GridBagLayout
            panel.setBackground(Color.darkGray); //Set background color as dark gray
            GridBagConstraints c = new GridBagConstraints(); //Add constraints
            panel.setOpaque(true); //Make panel opaque

            JLabel title = new JLabel("Title: "); //JLabel title
            title.setFont(new Font("Arial", Font.BOLD, 16)); //Add label font
            title.setForeground(Color.ORANGE); //Set text color as orange
            c.gridx = 0; //x co-ordinate
            c.gridy = 1; //y co-ordinate
            c.anchor = GridBagConstraints.WEST; //Place component on the west-side
            c.insets = new Insets(0, 0, 10, 0); //Inset spacing
            title.setVisible(true); //Make label visible
            panel.add(title, c); //Add label to panel

            JTextField todoTitle = new JTextField(todoList.get(updateChoice).getText()); //JTextField with saved title
            todoTitle.setBorder(BorderFactory.createLineBorder(Color.ORANGE)); //Create orange line border
            c.gridx = 1; //x co-ordinate
            c.gridy = 1; //y co-ordinate
            c.fill = GridBagConstraints.HORIZONTAL; //Fill allocated space
            panel.add(todoTitle, c); //Add label to panel

            JLabel due = new JLabel("Due date: "); //JLabel title
            due.setFont(new Font("Arial", Font.BOLD, 16)); //Add label font
            due.setForeground(Color.ORANGE); //Set text color as orange
            c.gridx = 0; //x co-ordinate
            c.gridy = 2; //y co-ordinate
            c.anchor = GridBagConstraints.WEST; //Place component on the west-side
            due.setVisible(true); //Make label visible
            panel.add(due, c); //Add label to panel

            JTextField todoDue = new JTextField(String.valueOf(todoList.get(updateChoice).getDue())); //JTextField with saved due date
            todoDue.setBorder(BorderFactory.createLineBorder(Color.ORANGE)); //Create orange line border
            c.gridx = 1; //x co-ordinate
            c.gridy = 2; //y co-ordinate
            c.fill = GridBagConstraints.HORIZONTAL; //Fill allocated space
            panel.add(todoDue, c); //Add label to panel

            JLabel category = new JLabel("Category: "); //JLabel title
            category.setFont(new Font("Arial", Font.BOLD, 16)); //Add label font
            category.setForeground(Color.ORANGE); //Set text color as orange
            c.gridx = 0; //x co-ordinate
            c.gridy = 3; //y co-ordinate
            c.anchor = GridBagConstraints.WEST; //Place component on the west-side
            category.setVisible(true); //Make label visible
            panel.add(category, c); //Add label to panel
            //Use of arraylist to make 0 index the saved option when updating
            ArrayList<String> categories = new ArrayList<String>(); //Arraylist for category options
            categories.add("RED");
            categories.add("WHITE");
            categories.add("BLUE");
            categories.add("PURPLE");
            categories.add("YELLOW");
            categories.add("GREEN");

            int prevCategory = 0; //Previous category index
            //Set prevCategory index based on set category constant
            if(todoList.get(updateChoice).getCat().equals(Category.WHITE)) prevCategory = 1;
            if(todoList.get(updateChoice).getCat().equals(Category.BLUE)) prevCategory = 2;
            if(todoList.get(updateChoice).getCat().equals(Category.PURPLE)) prevCategory = 3;
            if(todoList.get(updateChoice).getCat().equals(Category.YELLOW)) prevCategory = 4;
            if(todoList.get(updateChoice).getCat().equals(Category.GREEN)) prevCategory = 5;
            //Swap 0 index with index of saved category
            Collections.swap(categories, 0, prevCategory);
            //Convert arraylist into array to be used by combo box
            String[] categoryArray = new String[categories.size()];
            for (int i = 0; i < categoryArray.length; i++)
            {
                categoryArray[i] = categories.get(i); //Populate array
            }

            JComboBox<String> todoCategories = new JComboBox<String>(categoryArray); //Use array as options for JComboBox
            todoCategories.setBorder(BorderFactory.createLineBorder(Color.ORANGE)); //Make orange line border
            c.gridx = 1; //x co-ordinate
            c.gridy = 3; //y co-ordinate
            c.fill = GridBagConstraints.HORIZONTAL; //Fill allocated space
            todoCategories.setVisible(true); //Make combo box visible
            panel.add(todoCategories, c); //Add combo box to panel

            JLabel importance = new JLabel("Importance: "); //JLabel title
            importance.setFont(new Font("Arial", Font.BOLD, 16)); //Set label font
            importance.setForeground(Color.ORANGE); //Set text color as orange
            c.gridx = 0; //x co-ordinate
            c.gridy = 4; //y co-ordinate
            c.anchor = GridBagConstraints.WEST; //Place component on the west-side
            importance.setVisible(true); //Make label visible
            panel.add(importance, c); //Add label to panel
            //Use of arraylist to make 0 index the saved option when updating
            ArrayList<String> importances = new ArrayList<String>(); //Arraylist for importance options
            importances.add("LOW");
            importances.add("NORMAL");
            importances.add("HIGH");

            int prevImportance = 0; //Previous importance index
            //Set prevImportance index based on set importance constant
            if(todoList.get(updateChoice).getImportance().equals(Importance.NORMAL)) prevImportance = 1;
            if(todoList.get(updateChoice).getImportance().equals(Importance.HIGH)) prevImportance = 2;
            //Swap 0 index with index of saved importance
            Collections.swap(importances, 0, prevImportance);
            //Convert arraylist into array to be used by combo box
            String[] importanceArray = new String[importances.size()];
            for (int i = 0; i < importanceArray.length; i++)
            {
                importanceArray[i] = importances.get(i); //Populate array
            }

            JComboBox<String> todoImportance = new JComboBox<String>(importanceArray); //Use array as options for JComboBox
            todoImportance.setBorder(BorderFactory.createLineBorder(Color.ORANGE)); //Make orange line border
            c.gridx = 1; //x co-ordinate
            c.gridy = 4; //y co-ordinate
            c.fill = GridBagConstraints.HORIZONTAL; //Place component on the west-side
            todoImportance.setVisible(true); //Make combo box visible
            panel.add(todoImportance, c); //Add combo box to panel

            JLabel completion = new JLabel("Completion: "); //JLabel title
            completion.setFont(new Font("Arial", Font.BOLD, 16)); //Set label font
            completion.setForeground(Color.ORANGE); //Set text color as orange
            c.gridx = 0; //x co-ordinate
            c.gridy = 5; //y co-ordinate
            c.anchor = GridBagConstraints.WEST; //Place component on the west-side
            completion.setVisible(true); //Make label visible
            panel.add(completion, c); //Add label to panel
            //Use of arraylist to make 0 index the saved option when updating
            ArrayList<String> status = new ArrayList<String>(); //Arraylist for status options
            status.add("PENDING");
            status.add("STARTED");
            status.add("PARTIAL");
            status.add("COMPLETED");

            int prevStatus = 0; //Previous status index
            //Set prevStatus index based on set status constant
            if(todoList.get(updateChoice).getCompletion().equals(Status.STARTED)) prevStatus = 1;
            if(todoList.get(updateChoice).getCompletion().equals(Status.PARTIAL)) prevStatus = 2;
            if(todoList.get(updateChoice).getCompletion().equals(Status.COMPLETED)) prevStatus = 3;
            //Swap 0 index with index of saved status
            Collections.swap(status, 0, prevStatus);
            //Convert arraylist into array to be used by combo box
            String[] statusArray = new String[status.size()];
            for (int i = 0; i < statusArray.length; i++)
            {
                statusArray[i] = status.get(i); //Populate array
            }

            JComboBox<String> todoCompletion = new JComboBox<String>(statusArray); //Use array as options for JComboBox
            todoCompletion.setBorder(BorderFactory.createLineBorder(Color.ORANGE)); //Make orange line border
            c.gridx = 1; //x co-ordinate
            c.gridy = 5; //y co-ordinate
            c.fill = GridBagConstraints.HORIZONTAL; //Fill allocated space
            todoCompletion.setVisible(true); //Make combo box visible
            panel.add(todoCompletion, c); //Add combo box to panel

            JButton confirm = new JButton("Confirm"); //JButton title
            c.gridx = 1; //x co-ordinate
            c.gridy = 6; //y co-ordinate
            c.insets = new Insets(0, 50, 0, 50); //Inset spacing
            confirm.setOpaque(true); //Make button opaque
            confirm.setBackground(Color.ORANGE); //Set button background color as orange
            confirm.setBorderPainted(false); //Disable border
            confirm.setFocusPainted(false); //Disable focus
            confirm.setForeground(new Color(22, 24, 25)); //Set text color
            newFrame.getRootPane().setDefaultButton(confirm); //Enter key shortcut
            panel.add(confirm, c); //Add button to panel
            //When pressed, gather data used to update todo
            confirm.addActionListener(e -> {
                String updateTitle = todoTitle.getText(); //Get inputted title
                String updateStringDue = todoDue.getText(); //Get inputted due date
                LocalDateTime updateDue;
                Category updateCat = null;
                Importance updateImportance = null;
                Status updateStatus = null;

                if (updateTitle.trim().isEmpty()) //Catch empty title/title with only spaces
                {
                    errorFrame(); //Open error frame
                    return; //End
                }

                try {
                    updateDue = LocalDateTime.parse(updateStringDue); //Convert inputted due date into LocalDateTime format
                } catch (DateTimeParseException err){
                    errorFrame(); //Open error frame
                    return; //End
                }
                //Get selected category by user and set corresponding enum
                if (todoCategories.getSelectedItem().equals("RED")) updateCat = Category.RED;
                if (todoCategories.getSelectedItem().equals("WHITE")) updateCat = Category.WHITE;
                if (todoCategories.getSelectedItem().equals("BLUE")) updateCat = Category.BLUE;
                if (todoCategories.getSelectedItem().equals("PURPLE")) updateCat = Category.PURPLE;
                if (todoCategories.getSelectedItem().equals("YELLOW")) updateCat = Category.YELLOW;
                if (todoCategories.getSelectedItem().equals("GREEN")) updateCat = Category.GREEN;
                //Get selected importance by user and set corresponding enum
                if (todoImportance.getSelectedItem().equals("LOW")) updateImportance = Importance.LOW;
                if (todoImportance.getSelectedItem().equals("NORMAL")) updateImportance = Importance.NORMAL;
                if (todoImportance.getSelectedItem().equals("HIGH")) updateImportance = Importance.HIGH;
                //Get selected status by user and set corresponding enum
                if (todoCompletion.getSelectedItem().equals("PENDING")) updateStatus = Status.PENDING;
                if (todoCompletion.getSelectedItem().equals("STARTED")) updateStatus = Status.STARTED;
                if (todoCompletion.getSelectedItem().equals("PARTIAL")) updateStatus = Status.PARTIAL;
                if (todoCompletion.getSelectedItem().equals("COMPLETED")) updateStatus = Status.COMPLETED;
                //Update chosen todo entry
                todoList.get(updateChoice).setText(updateTitle);
                todoList.get(updateChoice).setDue(updateDue);
                todoList.get(updateChoice).setCat(updateCat);
                todoList.get(updateChoice).setImportance(updateImportance);
                todoList.get(updateChoice).setCompletion(updateStatus);

                DefaultListModel listModel = (DefaultListModel) list.getModel(); //Get list
                listModel.clear(); //Clear list contents
                for (int i = 0; i < todoList.size(); i++) //Repopulate list using for loop
                {
                    listModel.addElement(todoList.get(i)); //Create an updated list
                }

                newFrame.dispose(); //Close frame

            });

            newFrame.add(panel); //Add panel to frame
        });
    }

    private void deleteTodoFrame() {
        EventQueue.invokeLater(() -> {
            JFrame newFrame = new JFrame("Delete Todo!"); //Frame title
            newFrame.setSize(400, 100); //Set frame size
            newFrame.setVisible(true); //Make frame visible
            newFrame.setResizable(false); //Make it so the frame isn't resizable
            newFrame.setLocationRelativeTo(frame); //Draw in centre of frame
            newFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); //Close frame when close operation is triggered
            newFrame.setBackground(Color.ORANGE); //Set color behind title
            newFrame.getContentPane().setBackground(Color.darkGray); //Set color of window
            //Try and set look and feel so it looks the same on every platform
            try
            {
                UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
            } catch (Exception e) {
                e.printStackTrace();
            }
            //Create panel
            JPanel panel = new JPanel(); //Call it panel
            panel.setLayout(new GridBagLayout()); //Set GridBagLayout
            panel.setBackground(Color.darkGray); //Set background color as dark gray
            GridBagConstraints c = new GridBagConstraints(); //Add constraints
            panel.setOpaque(true); //Make panel opaque

            JLabel delete = new JLabel("Todo to delete: "); //JLabel title
            delete.setFont(new Font("Arial", Font.BOLD, 16)); //Set label font
            delete.setForeground(Color.ORANGE); //Set text color as orange
            c.gridx = 0; //x co-ordinate
            c.gridy = 0; //y co-ordinate
            c.anchor = GridBagConstraints.WEST; //Place component on the west-side
            c.insets = new Insets(0, 0, 10, 0); //Inset spacing
            delete.setVisible(true); //Make label visible
            panel.add(delete, c); //Add label to panel

            JTextField todoNumber = new JTextField("Enter todo number here"); //JTextField title
            todoNumber.setBorder(BorderFactory.createLineBorder(Color.ORANGE)); //Make orange line border
            c.gridx = 1; //x co-ordinate
            c.gridy = 0; //y co-ordinate
            c.fill = GridBagConstraints.HORIZONTAL; //Fill allocated space
            panel.add(todoNumber, c); //Add text field to panel

            JButton confirm = new JButton("Confirm"); //JButton title
            c.gridx = 1; //x co-ordinate
            c.gridy = 2; //y co-ordinate
            c.insets = new Insets(0, 50, 0, 50); //Inset spacing
            confirm.setOpaque(true); //Make button opaque
            confirm.setBackground(Color.ORANGE); //Set button background as orange
            confirm.setBorderPainted(false); //Disable border
            confirm.setFocusPainted(false); //Disable focus
            confirm.setForeground(new Color(22, 24, 25)); //Set text color
            newFrame.getRootPane().setDefaultButton(confirm); //Enter key shortcut
            panel.add(confirm, c); //Add button to panel
            //When pressed, gather data to delete todo
            confirm.addActionListener(e -> {
                int deleteChoice; //Store delete choice

                try {
                    deleteChoice = Integer.parseInt(todoNumber.getText()); //Get integer from text field
                } catch (NumberFormatException err){
                    errorFrame(); //Open error frame
                    return; //End
                }
                //If user input isn't within bounds
                if (deleteChoice > todoList.size() || deleteChoice <= 0)
                {
                    errorFrame(); //Open error frame
                    return; //End
                }
                //Align choice with array indexing
                deleteChoice--;
                //Remove user's choice
                todoList.remove(deleteChoice);

                DefaultListModel listModel = (DefaultListModel) list.getModel(); //Get list
                listModel.clear(); //Clear list contents
                for (int i = 0; i < todoList.size(); i++) //Repopulate list using for loop
                {
                    listModel.addElement(todoList.get(i)); //Create an updated list
                }

                newFrame.dispose(); //Close frame



            });

            newFrame.add(panel); //Add panel to frame

        });
    }

    private void errorFrame() {
        JFrame errorFrame = new JFrame("Error!"); //Error frame
        errorFrame.setVisible(true); //Make frame visible
        errorFrame.setResizable(false); //Make frame so it's not resizable
        errorFrame.setLocationRelativeTo(frame); //Window placed centre of screen
        errorFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); //Close frame when close operation is triggered
        errorFrame.setBackground(Color.ORANGE); //Set color behind title
        errorFrame.getContentPane().setBackground(Color.darkGray); //Set color of window

        JPanel errorPanel = new JPanel(); //Error panel
        errorPanel.setBackground(Color.darkGray); //Set background color of panel
        errorPanel.setOpaque(true); //Make panel opaque
        errorPanel.setLayout(new BoxLayout(errorPanel, BoxLayout.Y_AXIS)); //Box Layout
        errorFrame.add(errorPanel); //Add panel to frame

        JLabel error = new JLabel("Invalid Todo entry, please try again!", JLabel.CENTER); //Add error message
        error.setAlignmentX(JLabel.CENTER_ALIGNMENT); //Align label
        error.setAlignmentY(JLabel.CENTER_ALIGNMENT); //Align label
        error.setForeground(Color.ORANGE); //Make label text orange
        errorPanel.add(error); //Add label to panel

        JButton ok = new JButton("OK"); //Ok button
        ok.setAlignmentX(JButton.CENTER_ALIGNMENT); //Align button
        ok.setOpaque(true); //Make button opaque
        ok.setBackground(Color.ORANGE); //Make button background orange
        ok.setBorderPainted(false); //No border
        ok.setFocusPainted(false); //No focus
        ok.setForeground(new Color(22, 24, 25)); //Set text color

        errorFrame.getRootPane().setDefaultButton(ok); //Enter shortcut set to button
        errorPanel.add(ok); //Add button to panel

        ok.addActionListener(e -> errorFrame.dispose()); //When button is pressed

        errorFrame.pack(); //Pack frame

    }

    public void noTodoFrame(){
        JFrame errorFrame = new JFrame("No todos!"); //Error frame
        errorFrame.setVisible(true); //Make frame visible
        errorFrame.setResizable(false); //Make frame so it's not resizable
        errorFrame.setLocationRelativeTo(frame); //Window placed centre of screen
        errorFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); //Close frame when close operation is triggered
        errorFrame.setBackground(Color.ORANGE); //Set color behind title
        errorFrame.getContentPane().setBackground(Color.darkGray); //Set color of window

        JPanel errorPanel = new JPanel(); //Error panel
        errorPanel.setBackground(Color.darkGray); //Set background color of panel
        errorPanel.setOpaque(true); //Make panel opaque
        errorPanel.setLayout(new BoxLayout(errorPanel, BoxLayout.Y_AXIS)); //Box Layout
        errorFrame.add(errorPanel); //Add panel to frame

        JLabel error = new JLabel("There are currently no todos in the list!", JLabel.CENTER); //Add error message
        error.setAlignmentX(JLabel.CENTER_ALIGNMENT); //Align label
        error.setAlignmentY(JLabel.CENTER_ALIGNMENT); //Align label
        error.setForeground(Color.ORANGE); //Make label text orange
        errorPanel.add(error); //Add label to panel

        JButton ok = new JButton("OK"); //Ok button
        ok.setAlignmentX(JButton.CENTER_ALIGNMENT); //Align button
        ok.setOpaque(true); //Make button opaque
        ok.setBackground(Color.ORANGE); //Make button background orange
        ok.setBorderPainted(false); //No border
        ok.setFocusPainted(false); //No focus
        ok.setForeground(new Color(22, 24, 25)); //Set text color

        errorFrame.getRootPane().setDefaultButton(ok); //Enter shortcut set to button
        errorPanel.add(ok); //Add button to panel

        ok.addActionListener(e -> errorFrame.dispose()); //When button is pressed

        errorFrame.pack(); //Pack frame

    }
}
