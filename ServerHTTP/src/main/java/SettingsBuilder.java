class SettingsBuilder {
    private static final int DEFAULT_PORT = 8080;

    private String[] args;

    SettingsBuilder(String[] args) {
        this.args = args;
    }

    Settings build() {
        int port = getPort();

        return new Settings(port);
    }

    private int getPort() {
        try {
            return Integer.parseInt(args[0]);
        } catch (Exception ignored) {
            return DEFAULT_PORT;
        }
    }
}
