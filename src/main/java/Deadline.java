public class Deadline extends Task {
    private String by;
    public Deadline(String desc) {
        super(parseDesc(desc));
        this.by = desc.split("/by", 2)[1].trim();

    }

    @Override
    public String getTypeofTask() {
        return "[D]";
    }

    @Override
    public String toString() {
        return getTypeofTask() + " " + getStatusIcon() + " " + getDesc() + " (by: " + this.by + ")";
    }

    public static String parseDesc(String desc) {
        String[] parts = desc.split("/by", 2);
        return parts[0].trim();
    }
}
