package friday;

import java.util.ArrayList;
import java.util.List;

import friday.task.Task;

public class TaskList {
    private final List<Task> tasks;

    public TaskList() {
        this.tasks = Storage.readFile();
    }

    public TaskList(List<Task> t) {
        this.tasks = t;
    }

    /**
     * Returns the number of tasks in the list
     * @return Integer count
     */
    public Integer count() {
        return this.tasks.size();
    }

    /**
     * Returns the task at the given index
     * @param index
     * @return
     */
    public Task get(int index) {
        return this.tasks.get(index);
    }

    /**
     * Adds a task to the list
     * @param task
     */
    public void add(Task task) {
        this.tasks.add(task);
    }

    /**
     * Deletes the task at the given index and returns it
     * @param index
     * @return
     */
    public Task delete(int index) {
        return this.tasks.remove(index);
    }

    /**
     * Saves the current list of tasks to the file
     */
    public void save() {
        Storage.saveToFile(this.tasks);
    }

    public TaskList find(String keyword) {
        ArrayList<Task> matchedTasks = new ArrayList();
        for (Task task : this.tasks) {
            if (task.getDesc().contains(keyword)) {
                matchedTasks.add(task);
            }
        }
        return new TaskList(matchedTasks);
    }

}
