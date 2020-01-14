import java.io.IOException;

class RequestParser {
    private static final String INDEX_DEFAULT_NAME = "index.html";

    String getUrlFromRequest(String request) throws IOException {
        String urlString = request.substring(request.indexOf(' ')+2);
        urlString = urlString.substring(0, urlString.indexOf(' '));

        if (urlString.endsWith("/") || urlString.isEmpty()) {
            urlString += INDEX_DEFAULT_NAME;
        }

        return urlString;
    }
}
