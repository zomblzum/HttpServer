import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

class Server implements Runnable {
    private Settings settings;

    Server(Settings settings) {
        this.settings = settings;
    }

    @Override
    public void run() {
        try {
            tryRun();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void tryRun() throws IOException {
        ServerSocket serverSocket = new ServerSocket(settings.getPort());
        System.out.println("Server started on port: " + serverSocket.getLocalPort());
        while(true) {
            Socket request = serverSocket.accept();
            Thread response = new Thread(new RequestHandler(request));
            response.start();
        }
    }
}
