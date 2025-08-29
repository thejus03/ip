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

    /**
     * Starts Friday's UI interactions
     */
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

    /**
     * Returns the number of tasks in the task list
     * @return
     */
    public Integer getTaskCount() {
        return this.tasks.count();
    }

    /**
     * Returns the task at the given index
     * @param index
     * @return
     */
    public Task getTask(int index) {
        return this.tasks.get(index);
    }

    /**
     * Adds a task to the task list
     * @param task
     */
    public void addTask(Task task) {
        this.tasks.add(task);
    }

    /**
     * Deletes a task from the task list
     * @param index
     * @return
     */
    public Task deleteTask(int index) {
        return this.tasks.delete(index);
    }

    /**
     * Stops Friday's UI interactions
     */
    public void stop() {
        this.scanner.close();
        this.isRunning = false;
        this.tasks.save();
        System.out.println("Bye. Hope to see you again soon!");
    }
}
