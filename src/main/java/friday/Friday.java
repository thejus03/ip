public class Friday {
    private final Ui ui;

    public Friday() {
        this.ui = new Ui();
    }

    public void run() {
        ui.start();
    }

    public static void main(String[] args) {
        new Friday().run();
    }
}
