import java.io.File;

class Response {
    private ResponseStatus status;
    private String header;
    private File file;

    Response(ResponseStatus status, String header, File file) {
        this.status = status;
        this.header = header;
        this.file = file;
    }

    ResponseStatus getStatus() {
        return status;
    }

    String getHeader() {
        return header;
    }

    File getFile() {
        return file;
    }
}
