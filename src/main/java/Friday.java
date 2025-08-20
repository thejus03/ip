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
            System.out.println("-".repeat(50));
            if (input.toLowerCase().equals("bye")) {
                break;
            } else if (input.toLowerCase().equals("list")) {
                System.out.println("Here are your tasks:");
                for (int i = 0; i < tasks.size(); i++) {
                    System.out.println((i + 1) + ". " + tasks.get(i));
                }
            }  else if (input.contains("unmark")) {
                String[] parts = input.split(" ");
                if (parts.length < 2) {
                    System.out.println("Please specify the task number to unmark.");
                    continue;
                }
                try {
                    int taskNumber = Integer.parseInt(parts[1]) - 1;
                    if (taskNumber < 0 || taskNumber >= tasks.size()) {
                        System.out.println("Error: Invalid task number!");
                    } else {
                        tasks.get(taskNumber).markAsNotDone();
                        System.out.println("OK, I've marked this task as not done: " + tasks.get(taskNumber).getDesc());
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Please enter a valid task number.");
                }
            } else if (input.contains("mark")) {
                String[] parts = input.split(" ");
                if (parts.length < 2) {
                    System.out.println("Please specify the task number to mark as done.");
                    continue;
                }
                try {
                    int taskNumber = Integer.parseInt(parts[1]) - 1;
                    if (taskNumber < 0 || taskNumber >= tasks.size()) {
                        System.out.println("Error: Invalid task number!");
                    } else {
                        tasks.get(taskNumber).markAsDone();
                        System.out.println("Nice! I've marked this task as done: " + tasks.get(taskNumber).getDesc());
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Please enter a valid task number.");
                }

            } else {
                System.out.println(input);
                String[] parts = input.split(" ", 2);
                Task task;
                if (parts.length < 2) {
                    System.out.println("Please provide a valid task description.");
                    continue;
                } else if (parts[0].equals("todo")) {
                    task = new ToDo(parts[1]);
                } else if (parts[0].equals("deadline")) {
                    task = new Deadline(parts[1]);
                } else if (parts[0].equals("event")) {
                    task = new Event(parts[1]);
                } else {
                    System.out.println("Unknown command. Please use 'todo', 'deadline', or 'event'.");
                    continue;
                }
                tasks.add(task);
                System.out.println("Got it. I've added this task:");
                System.out.println(task);
                System.out.println("Now you have " + tasks.size() + " task(s) in the list.");
            }
        }
        scanner.close();

        System.out.println("Bye. Hope to see you again soon!");
    }
}
