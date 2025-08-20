import java.util.Scanner;

public class Friday {
    public static void main(String[] args) {
        System.out.println("-".repeat(50));
        System.out.println("Hello! I'm Friday");
        System.out.println("What can I do for you?");
        System.out.println("-".repeat(50));
        Scanner scanner = new Scanner(System.in);

        while (true) {
            String input = scanner.nextLine();
            if (input.toLowerCase().equals("bye")) {
                break;
            }
            System.out.println("-".repeat(50));
            System.out.println(input);
            System.out.println("-".repeat(50));
        }
        System.out.println("-".repeat(50));
        scanner.close();

        System.out.println("Bye. Hope to see you again soon!");
    }
}
