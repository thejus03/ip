package friday;

public class Friday {
    private final Ui ui;

    public Friday() {
        this.ui = new Ui();
    }

    /**
     * Starts the Friday application
     */
    public void run() {
        ui.start();
    }

    /**
     * Main entry point to start the application
     * @param args
     */
    public static void main(String[] args) {
        new Friday().run();
    }

    /**
     * Generates a response for the user's chat message.
     */
    public String getResponse(String input) {
        return "Friday heard: " + input;
    }
}
