package friday;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import friday.task.Deadline;
import friday.task.Event;
import friday.task.Task;
import friday.task.ToDo;

public class Storage {
    private static final String filePath = System.getProperty("user.dir") + "/src/main/data/Friday.txt";

    public static List<Task> readFile() {
        try {
            File f = new File(Storage.filePath);
            if (!f.exists()) {
                return new ArrayList<>();
            }
            Scanner sc = new Scanner(f);
            List<Task> tasks = new ArrayList<>();
            while (sc.hasNextLine()) {   // read line by line
                String line = sc.nextLine();
                String[] parts = line.split("\\|");
                String taskType = parts[0].trim();
                String desc = parts[2].trim();
                boolean isDone = parts[1].trim().equals("1");
                Task task = null;

                switch (taskType) {
                    case "[T]" -> task = new ToDo(desc);
                    case "[D]" -> task = new Deadline(desc);
                    case "[E]" -> task = new Event(desc);
                    default -> System.out.println("Unknown task type: " + taskType);
                }
                if (task != null && isDone) task.markAsDone();
                if (task != null) tasks.add(task);
            }
            sc.close();

            return tasks;
        } catch(IOException e) {
            System.out.println("Error creating file: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    public static void saveToFile(List<Task> tasks) {
        final int maxTries = 3;
        int tries = 0;
        while (tries < maxTries) {
            try {
                File f = new File(Storage.filePath);
                if (!f.getParentFile().exists()) {
                    f.getParentFile().mkdirs();
                }
                try (FileWriter writer = new FileWriter(Storage.filePath)) {
                    for (Task task : tasks) {
                        String taskType = task.getTypeofTask();
                        String isDone = task.IsDone() ? "1" : "0";
                        writer.write(taskType + " | " + isDone + " | " + task.getFullDesc() + System.lineSeparator());
                    }
                    break;
                }
            } catch (IOException e) {
                System.out.println("Error writing to file: " + e.getMessage());
            } finally {
                tries++;
            }
        }
    }
}
