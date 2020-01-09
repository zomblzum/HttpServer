class SettingsParser {
    private static final int DEFAULT_PORT = 8080;

    private String[] args;

    Settings parse(String[] args) {
        this.args = args;

        int port = findPort();

        return new Settings(port);
    }

    private int findPort() {
        try {
            return Integer.parseInt(args[0]);
        } catch (Exception ignored) {
            return DEFAULT_PORT;
        }
    }
}
