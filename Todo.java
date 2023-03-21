import java.time.LocalDateTime;

public class Todo {
    private String text;
    private LocalDateTime due;
    private Category cat;
    private Importance importance;
    private Status completion;
    //Constructor
    public Todo(String text, LocalDateTime due, Category cat, Importance importance, Status completion) {
        this.text = text;
        this.due = due;
        this.cat = cat;
        this.importance = importance;
        this.completion = completion;
    }
    //Getters and Setters
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public LocalDateTime getDue() {
        return due;
    }

    public void setDue(LocalDateTime due) {
        this.due = due;
    }

    public Category getCat() {
        return cat;
    }

    public void setCat(Category cat) {
        this.cat = cat;
    }

    public Importance getImportance() {
        return importance;
    }

    public void setImportance(Importance importance) {
        this.importance = importance;
    }

    public Status getCompletion() {
        return completion;
    }

    public void setCompletion(Status completion) {
        this.completion = completion;
    }
    //Override object calls with string version
    @Override
    public String toString() {
        return "<html>Text: " + text +
                "<br>Due: " + due +
                "<br>Category: " + cat +
                "<br>Importance: " + importance +
                "<br>Completion: " + completion +
                "</html>";

    }
}
