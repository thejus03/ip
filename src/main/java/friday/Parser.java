package friday;

import friday.task.Deadline;
import friday.task.DeadlineArgsException;
import friday.task.Event;
import friday.task.EventArgsException;
import friday.task.Task;
import friday.task.ToDo;

public class Parser {
        private static final String TASK_NUMBER_PROMPT = "Please also specify the task number.\n";
        private static final String INVALID_TASK_NUMBER = "Ah... This task number does not exist!\n";
        private static final String INVALID_DESCRIPTION = "Please provide a valid task description.\n";

        private final Ui ui;

        public Parser(Ui ui) {
                assert ui != null : "UI instance cannot be null";
                this.ui = ui;
        }

        /**
         * Parses the user input and executes the corresponding command.
         * @param input The user input string.
         * @return The response string after executing the command.
         */

        public String parse(String input) {
                assert input != null : "Input string cannot be null";
                assert !input.trim().isEmpty() : "Input string cannot be empty";

                Command cmd = Command.getCommand(input);
                assert cmd != null : "Command cannot be null";

                return switch (cmd) {
                        case BYE -> handleBye();
                        case LIST -> handleList();
                        case FIND -> handleFind(input);
                        case UNMARK, MARK, DELETE -> handleTaskStateChange(cmd, input);
                        case TODO, DEADLINE, EVENT -> handleTaskCreation(cmd, input);
                };
        }

        private String handleBye() {
                this.ui.stop();
                return "";
        }

        /**
         * Creates a string representation of the task list.
         * */
        private String handleList() {
                StringBuilder out = new StringBuilder("Here are your tasks:\n");
                for (int i = 0; i < this.ui.getTaskCount(); i++) {
                        out.append(i + 1).append(". ").append(this.ui.getTask(i)).append("\n");
                }
                return out.toString();
        }

        /**
         * Finds tasks containing the specified keyword
         * */
        private String handleFind(String input) {
                String[] parts = input.split(" ");
                if (parts.length < 2) {
                        return "Please specify the keyword to find";
                }

                String keyword = parts[1].trim();
                TaskList matchedTasks = this.ui.find(keyword);
                StringBuilder out = new StringBuilder("Here are the matching tasks in your list:\n");
                for (int i = 0; i < matchedTasks.count(); i++) {
                        out.append(i + 1).append(". ").append(matchedTasks.get(i)).append("\n");
                }
                return out.toString();
        }

        private String handleTaskStateChange(Command cmd, String input) {
                String[] parts = input.split(" ");
                if (parts.length < 2) {
                        return TASK_NUMBER_PROMPT;
                }

                try {
                        int taskNumber = Integer.parseInt(parts[1]) - 1;
                        if (!isValidTaskIndex(taskNumber)) {
                                return INVALID_TASK_NUMBER;
                        }
                        return executeTaskStateChange(cmd, taskNumber);
                } catch (NumberFormatException e) {
                        return "Please enter a valid task number.\n";
                }
        }

        private boolean isValidTaskIndex(int index) {
                return index >= 0 && index < this.ui.getTaskCount();
        }

        private String executeTaskStateChange(Command cmd, int taskNumber) {
                Task task = this.ui.getTask(taskNumber);
                return switch (cmd) {
                        case UNMARK -> {
                                task.markAsNotDone();
                                yield "OK, I've marked this task as not done: " + task + "\n";
                        }
                        case MARK -> {
                                task.markAsDone();
                                yield "Nice! I've marked this task as done: " + task + "\n";
                        }
                        case DELETE -> {
                                Task removedTask = this.ui.deleteTask(taskNumber);
                                yield String.format("Noted. I've removed this task: %s\nNow you have %d task(s) in the list.\n",
                                        removedTask, this.ui.getTaskCount());
                        }
                        default -> "";
                };
        }

        private String handleTaskCreation(Command cmd, String input) {
                String[] parts = input.split(" ", 2);
                if (parts.length < 2) {
                        return INVALID_DESCRIPTION;
                }

                try {
                        Task task = createTask(cmd, parts[1]);
                        if (task == null) {
                                return "Unknown command. Please use 'todo', 'deadline', or 'event'.\n";
                        }

                        this.ui.addTask(task);
                        return String.format("Got it. I've added this task:\n%s\nNow you have %d task(s) in the list.\n",
                                task, this.ui.getTaskCount());
                } catch (DeadlineArgsException | EventArgsException e) {
                        return e.getMessage();
                }
        }

        private Task createTask(Command cmd, String description) {
                return switch (cmd) {
                        case TODO -> new ToDo(description);
                        case DEADLINE -> new Deadline(description);
                        case EVENT -> new Event(description);
                        default -> null;
                };
        }
}
