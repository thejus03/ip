package friday.task;

public class ToDo extends Task {

    public ToDo(String desc) {
        super(ToDo.parseDesc(desc));
    }

    @Override
    public String getTypeofTask() {
        return "[T]";
    }

    /**
     * Parses the description of the ToDo task
     * @param desc
     * @return
     */
     public static String parseDesc(String desc) {
        return desc;
    }

    @Override
    public String toString() {
        return getTypeofTask() + " " + getStatusIcon() + " " + getDesc();
    }
}
