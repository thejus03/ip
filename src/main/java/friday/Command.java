package friday;

public enum Command {
    BYE, TODO, DEADLINE, EVENT, LIST, DELETE, MARK, UNMARK, FIND;

    public static Command getCommand(String input) {
        if (input.toLowerCase().equals("bye")) return Command.BYE;
        if (input.toLowerCase().equals("list")) return Command.LIST;
        if (input.toLowerCase().startsWith("todo")) return Command.TODO;
        if (input.toLowerCase().startsWith("deadline")) return Command.DEADLINE;
        if (input.toLowerCase().startsWith("event")) return Command.EVENT;
        if (input.toLowerCase().startsWith("delete")) return Command.DELETE;
        if (input.toLowerCase().startsWith("mark")) return Command.MARK;
        if (input.toLowerCase().startsWith("unmark")) return Command.UNMARK;
        if (input.toLowerCase().startsWith("find")) return Command.FIND;
        throw new IllegalArgumentException("Unknown command: " + input);
    }
}
