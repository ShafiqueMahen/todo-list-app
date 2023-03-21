import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

public class CLIMenu {
    public ArrayList<Todo> todoList;

    public CLIMenu(ArrayList<Todo> todoList) {
        this.todoList = todoList;

        int choice;
        Scanner sc = new Scanner(System.in);
        System.out.println("Select an item between 1 and 5 and press enter" + "\n" +
                "1. List todo" + "\n" +
                "2. Add todo" + "\n" +
                "3. Update todo" + "\n" +
                "4. Delete todo" + "\n" +
                "5. Quit");
        choice = sc.nextInt();

        while (choice <= 0 || choice > 5) {
            System.out.println("The integer you have entered is invalid, Please enter another one");
            choice = sc.nextInt();
        }

        switch (choice) {
            case 1:
                listTodos();
                break;
            case 2:
                addTodo();
                break;
            case 3:
                updateTodo();
                break;
            case 4:
                deleteTodo();
                break;
            case 5:
                //Quit program
                break;
            default:
                //If an error occurs, re-run program
                System.out.println("An error has occurred, restarting program.");
                CLIMenu menu5 = new CLIMenu(todoList);

        }

    }

    //Methods
    public void listTodos() {
        //Print out todo list
        for (int i = 0; i < todoList.size(); i++) {
            System.out.println((i + 1) + ". " + todoList.get(i));
        }
        //Re-run program with current todo list.
        CLIMenu menu = new CLIMenu(todoList);
    }

    public void addTodo() {
        Scanner sc1 = new Scanner(System.in);
        System.out.println("Enter a title for the todo");
        String newTodoTitle = sc1.nextLine();
        System.out.println(newTodoTitle);

        Scanner sc2 = new Scanner(System.in);
        System.out.println("Enter a due date for the todo in the format YYYY-MM-DDTHH:MM");
        String dateInput = sc2.nextLine();
        LocalDateTime newTodoDate = LocalDateTime.parse(dateInput);
        System.out.println(newTodoDate);

        Scanner sc3 = new Scanner(System.in);
        System.out.println("Select a category for the todo" + "\n"
                + "Select an item between 1 and 6 and press enter" + "\n"
                + "1. red" + "\n"
                + "2. white" + "\n"
                + "3. blue" + "\n"
                + "4. purple" + "\n"
                + "5. yellow" + "\n"
                + "6. green");
        int colourInput = sc3.nextInt();

        while (colourInput <= 0 || colourInput > 6) {
            System.out.println("The integer you have entered is invalid, Please enter another one");
            colourInput = sc3.nextInt();
        }
        Category newTodoColor;
        if (colourInput == 1) {
            newTodoColor = Category.RED;
        } else if (colourInput == 2) {
            newTodoColor = Category.WHITE;
        } else if (colourInput == 3) {
            newTodoColor = Category.BLUE;
        } else if (colourInput == 4) {
            newTodoColor = Category.PURPLE;
        } else if (colourInput == 5) {
            newTodoColor = Category.YELLOW;
        } else {
            newTodoColor = Category.GREEN;
        }

        System.out.println(newTodoColor);

        Scanner sc4 = new Scanner(System.in);
        System.out.println("Select an importance for the todo" + "\n"
                + "Select an item between 1 and 3 and press enter" + "\n"
                + "1. LOW" + "\n"
                + "2. NORMAL" + "\n"
                + "3. HIGH");
        int importanceInput = sc4.nextInt();

        while (importanceInput <= 0 || importanceInput > 3) {
            System.out.println("The integer you have entered is invalid, Please enter another one");
            importanceInput = sc4.nextInt();
        }

        Importance newTodoImportance;

        if (importanceInput == 1) {
            newTodoImportance = Importance.LOW;
        } else if (importanceInput == 2) {
            newTodoImportance = Importance.NORMAL;
        } else {
            newTodoImportance = Importance.HIGH;
        }

        System.out.println(newTodoImportance);

        Todo newTodo = new Todo(newTodoTitle, newTodoDate, newTodoColor, newTodoImportance, Status.PENDING);
        todoList.add(newTodo);
        CLIMenu menu2 = new CLIMenu(todoList);
    }

    public void updateTodo() {
        int updateChoice = 1;

        //If there is a possible todo to delete
        if (todoList.size() >= 1) {
            System.out.println("Which todo do you want to update?");
        }
        //Otherwise re-run program
        else {
            System.out.println("There are no todos to update");
            CLIMenu menu4 = new CLIMenu(todoList);
            return; //END CASE HERE TO PREVENT BUGS
        }

        inputValidator(updateChoice);

        //Align requested choice with ArrayList index
        updateChoice--;

        Scanner sc6 = new Scanner(System.in);
        System.out.println("Select an item between 1 and 5 and press enter" + "\n" +
                "1. title" + "\n" +
                "2. due date" + "\n" +
                "3. category" + "\n" +
                "4. importance" + "\n" +
                "5. completion");
        int fieldChoice = sc6.nextInt();

        while (fieldChoice <= 0 || fieldChoice > 5) {
            System.out.println("The integer you have entered is invalid, Please enter another one");
            fieldChoice = sc6.nextInt();
        }

        if (fieldChoice == 1) {
            Scanner sc7 = new Scanner(System.in);
            System.out.println("Enter new title for the todo");
            String updatedTitle = sc7.nextLine();

            todoList.get(updateChoice).setText(updatedTitle);
            CLIMenu menu3 = new CLIMenu(todoList);
        } else if (fieldChoice == 2) {
            Scanner sc8 = new Scanner(System.in);
            System.out.println("Enter a new due date for the todo in the format YYYY-MM-DDTHH:MM");
            String updatedDateString = sc8.nextLine();
            LocalDateTime updatedDate = LocalDateTime.parse(updatedDateString);

            todoList.get(updateChoice).setDue(updatedDate);
            CLIMenu menu3 = new CLIMenu(todoList);
        } else if (fieldChoice == 3) {
            Scanner sc9 = new Scanner(System.in);
            System.out.println("Select new category for the todo" + "\n"
                    + "Select an item between 1 and 6 and press enter" + "\n"
                    + "1. red" + "\n"
                    + "2. white" + "\n"
                    + "3. blue" + "\n"
                    + "4. purple" + "\n"
                    + "5. yellow" + "\n"
                    + "6. green");
            int updatedColor = sc9.nextInt();

            while (updatedColor <= 0 || updatedColor > 6) {
                System.out.println("The integer you have entered is invalid, Please enter another one");
                updatedColor = sc9.nextInt();
            }

            Category updatedCat;
            if (updatedColor == 1) {
                updatedCat = Category.RED;
            } else if (updatedColor == 2) {
                updatedCat = Category.WHITE;
            } else if (updatedColor == 3) {
                updatedCat = Category.BLUE;
            } else if (updatedColor == 4) {
                updatedCat = Category.PURPLE;
            } else if (updatedColor == 5) {
                updatedCat = Category.YELLOW;
            } else {
                updatedCat = Category.GREEN;
            }

            todoList.get(updateChoice).setCat(updatedCat);
            CLIMenu menu3 = new CLIMenu(todoList);
        } else if (fieldChoice == 4) {
            Scanner sc10 = new Scanner(System.in);
            System.out.println("Select new importance for the todo" + "\n"
                    + "Select an item between 1 and 3 and press enter" + "\n"
                    + "1. LOW" + "\n"
                    + "2. NORMAL" + "\n"
                    + "3. HIGH");
            int updatedLevel = sc10.nextInt();

            while (updatedLevel <= 0 || updatedLevel > 3) {
                System.out.println("The integer you have entered is invalid, Please enter another one");
                updatedLevel = sc10.nextInt();
            }

            Importance updatedImportance;

            if (updatedLevel == 1) {
                updatedImportance = Importance.LOW;
            } else if (updatedLevel == 2) {
                updatedImportance = Importance.NORMAL;
            } else {
                updatedImportance = Importance.HIGH;
            }

            todoList.get(updateChoice).setImportance(updatedImportance);
            CLIMenu menu3 = new CLIMenu(todoList);
        } else {
            Scanner sc11 = new Scanner(System.in);
            System.out.println("Select a new status for the todo" + "\n"
                    + "Select an item between 1 and 4 and press enter" + "\n"
                    + "1. PENDING" + "\n"
                    + "2. STARTED" + "\n"
                    + "3. PARTIAL" + "\n"
                    + "4. COMPLETED");
            int updatedPriority = sc11.nextInt();

            while (updatedPriority <= 0 || updatedPriority > 4) {
                System.out.println("The integer you have entered is invalid, Please enter another one");
                updatedPriority = sc11.nextInt();
            }

            Status updatedStatus;

            if (updatedPriority == 1) {
                updatedStatus = Status.PENDING;
            } else if (updatedPriority == 2) {
                updatedStatus = Status.STARTED;
            } else if (updatedPriority == 3) {
                updatedStatus = Status.PARTIAL;
            } else {
                updatedStatus = Status.COMPLETED;
            }

            todoList.get(updateChoice).setCompletion(updatedStatus);
            CLIMenu menu3 = new CLIMenu(todoList);
        }
    }

    public void deleteTodo() {
        int deleteChoice = 1; //Has to start positive to prevent positive integer message from showing

        //If there is a possible todo to delete
        if (todoList.size() >= 1) {
            System.out.println("Which todo do you want to delete?");
        }
        //Otherwise re-run program
        else {
            System.out.println("There are no todos to delete");
            CLIMenu menu4 = new CLIMenu(todoList);
            return; //END CASE HERE TO PREVENT BUGS
        }

        inputValidator(deleteChoice);

        //Align with ArrayList indexing
        deleteChoice--;

        todoList.remove(deleteChoice);
        CLIMenu menu4 = new CLIMenu(todoList);
    }

    //Method to validate user input when updating/deleting
    public void inputValidator(int inputVal) {
        Scanner sc = new Scanner(System.in);
        do {
            //If integer entered is greater than bounds
            if (inputVal > todoList.size()) {
                System.out.println("The integer you have entered is out of bounds, Please enter another one");
            }
            //If integer entered is lower than bounds
            if (inputVal <= 0) {
                System.out.println("Please enter a positive integer");
            }
            //If input entered is not an integer
            while (!sc.hasNextInt()) {
                sc.nextLine(); //clear invalid input
                System.out.println("Please enter an integer");
            }
            inputVal = sc.nextInt();
            sc.nextLine(); //clear scanner so error message doesn't duplicate
        } while (inputVal > todoList.size() || inputVal <= 0);
    }

    //NOTE: MAYBE MAKE SEPARATE VALIDATOR FOR FIELDS (MAYBE NOT AS METHODS)
}
