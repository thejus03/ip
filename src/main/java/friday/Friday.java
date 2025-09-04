package friday;

public class Friday {
    private final Ui ui;

    public Friday() {
        this.ui = new Ui();
    }

    /**
     * Generates a response for the user's chat message.
     */
    public String getResponse(String input) {
        return ui.getResponse(input);
    }
}
