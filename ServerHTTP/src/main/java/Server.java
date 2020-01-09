import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

class Server implements Runnable {
    private ServerSocket serverSocket;

    Server(Settings settings) throws IOException {
        serverSocket = new ServerSocket(settings.getPort());
    }

    @Override
    public void run() {
        System.out.println("Server started on port: " + serverSocket.getLocalPort());
        while(true) {
            try {
                Socket request = serverSocket.accept();
                Thread response = new Thread(new RequestHandler(request));
                response.start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}
