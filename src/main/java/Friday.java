import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Friday {
    public static void main(String[] args) {
        System.out.println("-".repeat(50));
        System.out.println("Hello! I'm Friday");
        System.out.println("What can I do for you?");
        List<String> tasks = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("-".repeat(50));
            String input = scanner.nextLine();
            System.out.println("-".repeat(50));
            if (input.toLowerCase().equals("bye")) {
                break;
            } else if (input.toLowerCase().equals("list")){
                System.out.println("Here are your tasks: ");
                for (int i = 0; i < tasks.size(); i++) {
                    System.out.println((i + 1) + ". " + tasks.get(i));
                }
            } else {
                System.out.println(input);
                tasks.add(input);
            }
        }
        scanner.close();

        System.out.println("Bye. Hope to see you again soon!");
    }
}
