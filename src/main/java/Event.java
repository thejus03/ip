public class Event extends Task  {
    private String from;
    private String to;
    public Event(String desc) {
        super(Event.parseDesc(desc));
        String[] parts = desc.split("/from", 2);
        String description = parts[0].trim();
        String[] partsFromTo = parts[1].split("/to", 2);

        this.from = partsFromTo[0].trim();
        this.to=  partsFromTo[1].trim();
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
