package friday;

import friday.task.Deadline;
import friday.task.DeadlineArgsException;
import friday.task.Event;
import friday.task.EventArgsException;
import friday.task.Task;
import friday.task.ToDo;

public class Parser {
    private final Ui ui;

    /**
     * Constructor
     * @param ui
     */
    public Parser(Ui ui) {
        this.ui = ui;
    }

    /**
     * Parses the input command and executes the corresponding action
     * @param input
     */
    public String parse(String input) {
        Command cmd = Command.getCommand(input);
        String out = "";
        switch (cmd) {
        case BYE -> this.ui.stop();
        case LIST -> {
            out += "Here are your tasks:\n";
            for (int i = 0; i < this.ui.getTaskCount(); i++) {
                out += (i + 1) + ". " + this.ui.getTask(i) + "\n";
            }
        }
        case FIND -> {
            String[] parts = input.split(" ");
            if (parts.length < 2) {
                out += "Please specify the keyword to find";
                return out;
            }

            String keyword = parts[1].trim();
            TaskList matchedTasks = this.ui.find(keyword);
            out += "Here are the matching tasks in your list:\n";
            for (int i = 0; i < matchedTasks.count(); i++) {
                System.out.println((i + 1) + ". " + matchedTasks.get(i));
                out += (i + 1) + ". " + matchedTasks.get(i) + "\n";
            }
        }
        case UNMARK, MARK, DELETE -> {
            String[] parts = input.split(" ");
            if (parts.length < 2) {
                System.out.println("Please also specify the task number.");
                out += "Please also specify the task number.\n";
                return out;
            }
            try {
                int taskNumber = Integer.parseInt(parts[1]) - 1;
                if (taskNumber < 0 || taskNumber >= this.ui.getTaskCount()) {
                    System.out.println("Ah... This task number does not exist!");
                    out += "Ah... This task number does not exist!\n";
                } else {
                    if (cmd == Command.UNMARK) {
                        this.ui.getTask(taskNumber).markAsNotDone();
                        System.out.println("OK, I've marked this task as not done: " + this.ui.getTask(taskNumber));
                        out += "OK, I've marked this task as not done: " + this.ui.getTask(taskNumber) + "\n";
                    } else if (cmd == Command.MARK) {
                        this.ui.getTask(taskNumber).markAsDone();
                        System.out.println("Nice! I've marked this task as done: " + this.ui.getTask(taskNumber));
                        out += "Nice! I've marked this task as done: " + this.ui.getTask(taskNumber) + "\n";
                    } else { // cmd == Command.DELETE
                        Task removedTask = this.ui.deleteTask(taskNumber);
                        System.out.println("Noted. I've removed this task: " + removedTask);
                        System.out.println("Now you have " + this.ui.getTaskCount() + " task(s) in the list.");
                        out += "Noted. I've removed this task: " + removedTask + "\n";
                        out += "Now you have " + this.ui.getTaskCount() + " task(s) in the list.\n";
                    }
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid task number.");
                out += "Please enter a valid task number.\n";
            }
        }
        case TODO, DEADLINE, EVENT -> {
            String[] parts = input.split(" ", 2);
            Task task;
            if (parts.length < 2) {
                System.out.println("Please provide a valid task description.");
                out += "Please provide a valid task description.\n";
                return out;
            }

            switch (cmd) {
            case TODO -> task = new ToDo(parts[1]);
            case DEADLINE -> {
                try {
                    task = new Deadline(parts[1]);
                } catch (DeadlineArgsException e) {
                    System.out.println(e.getMessage());
                    return out;
                }
            }
            case EVENT -> {
                try {
                    task = new Event(parts[1]);
                } catch (EventArgsException e) {
                    System.out.println(e.getMessage());
                    return out;
                }
            }
            default -> {
                System.out.println("Unknown command. Please use 'todo', 'deadline', or 'event'.");
                out += "Unknown command. Please use 'todo', 'deadline', or 'event'.\n";
                return out;
            }
            }

            this.ui.addTask(task);
            out += "Got it. I've added this task:\n";
            out += task + "\n";
            out += "Now you have " + this.ui.getTaskCount() + " task(s) in the list.\n";
        }
        }
        return out;
    }
}
