import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Friday {
    public static void main(String[] args) {
        System.out.println("-".repeat(50));
        System.out.println("Hello! I'm Friday");
        System.out.println("What can I do for you?");
        List<Task> tasks = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("-".repeat(50));
            String input = scanner.nextLine();
            Command cmd = Command.getCommand(input);
            System.out.println("-".repeat(50));
            switch (cmd) {
                case BYE -> {
                    scanner.close();

                    System.out.println("Bye. Hope to see you again soon!");
                    return;
                }
                case LIST -> {
                    System.out.println("Here are your tasks:");
                    for (int i = 0; i < tasks.size(); i++) {
                        System.out.println((i + 1) + ". " + tasks.get(i));
                    }

                }
                case UNMARK -> {
                    String[] parts = input.split(" ");
                    if (parts.length < 2) {
                        System.out.println("Please specify the task number to unmark.");
                        continue;
                    }
                    try {
                        int taskNumber = Integer.parseInt(parts[1]) - 1;
                        if (taskNumber < 0 || taskNumber >= tasks.size()) {
                            System.out.println("Ah... This task number does not exist!");
                        } else {
                            tasks.get(taskNumber).markAsNotDone();
                            System.out.println("OK, I've marked this task as not done: " + tasks.get(taskNumber));
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Please enter a valid task number.");
                    }

                }
                case MARK -> {
                    String[] parts = input.split(" ");
                    if (parts.length < 2) {
                        System.out.println("Please specify the task number to mark as done.");
                        continue;
                    }
                    try {
                        int taskNumber = Integer.parseInt(parts[1]) - 1;
                        if (taskNumber < 0 || taskNumber >= tasks.size()) {
                            System.out.println("Ah... This task number does not exist!");
                        } else {
                            tasks.get(taskNumber).markAsDone();
                            System.out.println("Nice! I've marked this task as done: " + tasks.get(taskNumber));
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Please enter a valid task number.");
                    }
                }
                case DELETE -> {
                    String[] parts = input.split(" ");
                    if (parts.length < 2) {
                        System.out.println("Please specify the task number to delete.");
                        continue;
                    }
                    try {
                        int taskNumber = Integer.parseInt(parts[1]) - 1;
                        if (taskNumber < 0 || taskNumber >= tasks.size()) {
                            System.out.println("Ah... This task number does not exist!");
                        } else {
                            Task removedTask = tasks.remove(taskNumber);
                            System.out.println("Noted. I've removed this task: " + removedTask);
                            System.out.println("Now you have " + tasks.size() + " task(s) in the list.");
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
                        continue;
                    }

                    switch (cmd) {
                        case TODO -> task = new ToDo(parts[1]);
                        case DEADLINE -> {
                            try {
                                task = new Deadline(parts[1]);
                            } catch (DeadlineArgsException e) {
                                System.out.println(e.getMessage());
                                continue;
                            }
                        }
                        case EVENT -> {
                            try {
                                task = new Event(parts[1]);
                            } catch (EventArgsException e) {
                                System.out.println(e.getMessage());
                                continue;
                            }
                        }
                        default -> {
                            System.out.println("Unknown command. Please use 'todo', 'deadline', or 'event'.");
                            continue;
                        }
                    }
                    tasks.add(task);
                    System.out.println("Got it. I've added this task:");
                    System.out.println(task);
                    System.out.println("Now you have " + tasks.size() + " task(s) in the list.");
                }
            }

        }
    }
}
