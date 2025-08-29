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
