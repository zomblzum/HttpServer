import lib.Header;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.PrintStream; 
import java.net.Socket; 

public class Response implements Runnable { 
    private final String request;
    private final Socket socket;
    private OutputStream out = null;
    private static final String FILES = "src/";
    private final Header head = new Header();
    
    @Override 
    public void run() { 
        try {
            int code = write(request);
            System.out.println("Resource: " + request + "\n");
            System.out.println("Result code: " + code + " " + head.getAnswer(code) + "\n"); 
        } catch (IOException e) {
            System.err.println("ANSWER ERROR");
        } finally {
            try {
                socket.close();
            } catch (Exception e) {
                System.err.println("SOCKET ERROR");
            }
        } 
    } 
    
    public Response(String request, Socket socket) throws IOException { 
        this.request = request;
        this.socket = socket;
        out = socket.getOutputStream();
    }

    private int write(String url) throws IOException {
        File page = new File(url);
        InputStream strm = new FileInputStream(page);
        int code = (strm != null) ? 200 : 404;
        String header = head.getHeader(code);
        PrintStream answer = new PrintStream(out, true, "UTF-8");
        answer.print(header); 
            if (code == 200) { 
                int count = 0; 
                byte[] buffer = new byte[1024];
                    while((count = strm.read(buffer)) != -1) {
                        out.write(buffer, 0, count);
                    }
            
            strm.close(); 
            } 
        return code;
    }
   
}