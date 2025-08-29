package friday;

import java.util.List;

import friday.task.Task;

public class TaskList {
    private final List<Task> tasks;
    public TaskList() {
        this.tasks = Storage.readFile();
    }

    public Integer count() {
        return this.tasks.size();
    }

    public Task get(int index) {
        return this.tasks.get(index);
    }

    public void add(Task task) {
        this.tasks.add(task);
    }

    public Task delete(int index) {
        return this.tasks.remove(index);
    }

    public void save() {
        Storage.saveToFile(this.tasks);
    }

}
