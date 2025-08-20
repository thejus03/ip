public class ToDo extends Task {

    public ToDo(String desc) {
        super(ToDo.parseDesc(desc));
    }

    @Override
    public String getTypeofTask() {
        return "[T]";
    }

     public static String parseDesc(String desc) {
        return desc;
    }

    @Override
    public String toString() {
        return getTypeofTask() + " " + getStatusIcon() + " " + getDesc();
    }
}
