import java.io.File;
import java.util.Date;

class ResponseBuilder {
    Response buildResponseFromFile(File file) {
        ResponseStatus status;
        String header;

        if(file.exists()) {
            status = ResponseStatus.OK;
            header = getHeader(status);
        } else {
            status = ResponseStatus.NOT_FOUND;
            header = getHeader(status);
            file = null;
        }

        return new Response(status, header, file);
    }

    private String getHeader(ResponseStatus status) {
        return "HTTP/1.1 " + status.getCode() + " " + status.getStatus() + "\n" +
                "Date: " + new Date().toGMTString() + "\n" +
                "Accept-Ranges: none\n" +
                "\n";
    }
}
