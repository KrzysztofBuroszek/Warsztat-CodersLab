package pl.coderslab.Warsztat_1;

public class Task {
    private String task;
    private String date;
    private String important;

    public Task(String task, String date, String important) {
        this.task = task;
        this.date = date;
        this.important = important;
    }

    public String getTask() {
        return task;
    }

    public String getDate() {
        return date;
    }

    public String isImportant() {
        return important;
    }

    @Override
    public String toString() {
        return
                task + " " + date +" "+ important;
    }
}
