enum ResponseStatus {
    OK {
        public int getCode() {
            return 200;
        }
        public String getStatus() {
            return "OK";
        }
    },
    NOT_FOUND {
        public int getCode() {
            return 404;
        }
        public String getStatus() {
            return "Not Found";
        }
    };

    public abstract int getCode();
    public abstract String getStatus();
}
