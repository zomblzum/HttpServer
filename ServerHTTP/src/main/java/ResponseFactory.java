import java.io.File;
import java.util.Date;

class ResponseFactory {
    Response getResponseFromFile(File file) {
        ResponseBuilder responseBuilder;

        if(file.exists()) {
            responseBuilder = new SuccessResponseBuilder(file);
        } else {
            responseBuilder = new NotFoundResponseBuilder();
        }

        return responseBuilder.build();
    }

    private abstract class ResponseBuilder {
        protected abstract Response build();

        protected String getHeader(ResponseStatus status) {
            return "HTTP/1.1 " + status.getCode() + " " + status.getStatus() + "\n" +
                    "Date: " + new Date().toGMTString() + "\n" +
                    "Accept-Ranges: none\n\n";
        }
    }

    private class SuccessResponseBuilder extends ResponseBuilder {
        private File file;

        SuccessResponseBuilder(File file) {
            this.file = file;
        }

        @Override
        protected Response build() {
            ResponseStatus status = ResponseStatus.OK;
            String header = getHeader(status);
            return new Response(status, header, file);
        }
    }

    private class NotFoundResponseBuilder extends ResponseBuilder {
        @Override
        protected Response build() {
            ResponseStatus status = ResponseStatus.NOT_FOUND;
            String header = getHeader(status);
            return new Response(status, header, null);
        }
    }
}
