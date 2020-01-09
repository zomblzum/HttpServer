import lib.Header;
import log.Log;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


public class Server { 
    private static final int DEFAULT_PORT = 1337;    
    
    public static void main(String[] args) {
        Header client = new Header();
        ServerSocket serverSocket = null; 
        int port;
        
            if (args.length > 0) { 
                port = Integer.parseInt(args[0]);
            }  else {
                port = DEFAULT_PORT;
            }
            
            try { 
                serverSocket = new ServerSocket(port, 0); 
                System.out.println("Server started on port: " + serverSocket.getLocalPort() + "\n");
            } catch (IOException e) { 
                System.out.println(e.getStackTrace());
                System.exit(1);
            }
            
        
        while (true) {
            try { 
                Socket connectSocket = serverSocket.accept();
                              
                String clientHeader = client.readHeader(connectSocket.getInputStream());
                String request = client.getURLFromHeader(clientHeader);
                
                Thread thread1 = new Thread(new Response(request,connectSocket));
                Thread thread2 = new Thread(new Log(clientHeader));
                
                thread1.start();
                thread2.start();               
                
            } catch (IOException e) { 
                System.out.println(e.getStackTrace());
                System.exit(-1);
            } 
        } 
    }
}