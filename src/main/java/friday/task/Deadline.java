package task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    private String by;
    private String desc;
    public Deadline(String desc) {
        super(parseDesc(desc));
        this.desc = desc;
        String[] parts= desc.split("/by", 2);
        if (parts.length < 2) {
            throw new DeadlineArgsException("The deadline cannot be empty... it must have the 'by' part.");
        }
        this.by = parseTime(parts[1].trim());

    }

    @Override
    public String getFullDesc() {
        return this.desc;
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

    public static String parseTime(String by) {
        try {
            LocalDate date = LocalDate.parse(by);
            return date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        } catch (DateTimeParseException e) {
            return by;
        }
    }
}
