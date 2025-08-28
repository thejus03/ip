package task;

public class Event extends Task {
    private String from;
    private String to;
    private String desc;

    public Event(String desc) {
        super(Event.parseDesc(desc));
        this.desc = desc;
        String[] parts = desc.split("/from", 2);
        if (parts.length < 2) {
            throw new EventArgsException("task.Event description must include '/from' and '/to' parts.");
        }
        String description = parts[0].trim();
        String[] partsFromTo = parts[1].split("/to", 2);
        if (partsFromTo.length < 2) {
            throw new EventArgsException("task.Event description must include '/to' part.");
        }

        this.from = partsFromTo[0].trim();
        this.to=  partsFromTo[1].trim();
    }

    @Override
    public String getFullDesc() {
        return this.desc;
    }

    @Override
    public String getTypeofTask() {
        return "[E]";
    }

    @Override
    public String toString() {
        return getTypeofTask() + " " + getStatusIcon() + " " + getDesc() +
               " (from: " + this.from + " to: " + this.to + ")";
    }

    public static String parseDesc(String desc) {
        String[] parts = desc.split("/from", 2);
        return parts[0].trim();
    }
}
