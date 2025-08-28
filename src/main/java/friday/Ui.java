package friday;

import friday.task.Task;

import java.util.Scanner;

public class Ui {

    private Scanner scanner;
    private TaskList tasks;
    private boolean isRunning;

    public Ui() {
        this.isRunning = true;
        this.tasks = new TaskList();
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        Parser parser = new Parser(this);

        System.out.println("-".repeat(50));
        System.out.println("Hello! I'm friday.Friday");
        System.out.println("What can I do for you?");
        System.out.println("You have " + this.getTaskCount() + " friday.task(s) in the list.");

        while (this.isRunning) {
            System.out.println("-".repeat(50));
            String input = scanner.nextLine();

            System.out.println("-".repeat(50));
            parser.parse(input);
        }
    }

    public Integer getTaskCount() {
        return this.tasks.count();
    }

    public Task getTask(int index) {
        return this.tasks.get(index);
    }

    public void addTask(Task task) {
        this.tasks.add(task);
    }

    public Task deleteTask(int index) {
        return this.tasks.delete(index);
    }

    public void stop() {
        scanner.close();
        this.isRunning = false;
        this.tasks.save();
        System.out.println("Bye. Hope to see you again soon!");
    }
}
