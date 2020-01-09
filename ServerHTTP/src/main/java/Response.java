import java.io.File;

class Response {
    private ResponseStatus status;
    private String header;
    private File content;

    Response(ResponseStatus status, String header, File content) {
        this.status = status;
        this.header = header;
        this.content = content;
    }

    ResponseStatus getStatus() {
        return status;
    }

    String getHeader() {
        return header;
    }

    File getContent() {
        return content;
    }
}
