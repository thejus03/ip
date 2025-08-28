package friday;

import friday.task.*;

public class Parser {
    private final Ui ui;
    public Parser(Ui ui) {
        this.ui = ui;
    }

    public void parse(String input) {
        Command cmd = Command.getCommand(input);

        switch (cmd) {
            case BYE -> this.ui.stop();
            case LIST -> {
                System.out.println("Here are your tasks:");
                for (int i = 0; i < this.ui.getTaskCount(); i++) {
                    System.out.println((i + 1) + ". " + this.ui.getTask(i));
                }

            }
            case UNMARK, MARK, DELETE -> {
                String[] parts = input.split(" ");
                if (parts.length < 2) {
                    System.out.println("Please also specify the task number.");
                    return;
                }
                try {
                    int taskNumber = Integer.parseInt(parts[1]) - 1;
                    if (taskNumber < 0 || taskNumber >= this.ui.getTaskCount()) {
                        System.out.println("Ah... This task number does not exist!");
                    } else {
                        if (cmd == Command.UNMARK) {
                            this.ui.getTask(taskNumber).markAsNotDone();
                            System.out.println("OK, I've marked this task as not done: " + this.ui.getTask(taskNumber));
                        } else if (cmd == Command.MARK){
                            this.ui.getTask(taskNumber).markAsDone();
                            System.out.println("Nice! I've marked this task as done: " + this.ui.getTask(taskNumber));
                        } else { // cmd == Command.DELETE
                            Task removedTask = this.ui.deleteTask(taskNumber);
                            System.out.println("Noted. I've removed this task: " + removedTask);
                            System.out.println("Now you have " + this.ui.getTaskCount() + " task(s) in the list.");
                        }
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Please enter a valid task number.");
                }

            }
            case TODO, DEADLINE, EVENT -> {

                String[] parts = input.split(" ", 2);
                Task task;
                if (parts.length < 2) {
                    System.out.println("Please provide a valid task description.");
                    return;
                }

                switch (cmd) {
                    case TODO -> task = new ToDo(parts[1]);
                    case DEADLINE -> {
                        try {
                            task = new Deadline(parts[1]);
                        } catch (DeadlineArgsException e) {
                            System.out.println(e.getMessage());
                            return;
                        }
                    }
                    case EVENT -> {
                        try {
                            task = new Event(parts[1]);
                        } catch (EventArgsException e) {
                            System.out.println(e.getMessage());
                            return;
                        }
                    }
                    default -> {
                        System.out.println("Unknown command. Please use 'todo', 'deadline', or 'event'.");
                        return;
                    }
                }

                this.ui.addTask(task);
                System.out.println("Got it. I've added this task:");
                System.out.println(task);
                System.out.println("Now you have " + this.ui.getTaskCount() + " task(s) in the list.");
            }
        }
    }
}
