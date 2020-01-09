import java.io.IOException;
import java.util.Scanner;

public class ServerRunner implements Runnable {
    private final Thread serverThread;

    public static void main(String[] args) {
        try {
            ServerRunner serverRunner = new ServerRunner(args);
            Thread serverConsole = new Thread(serverRunner);
            serverConsole.run();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private ServerRunner(String[] args) throws IOException {
        Settings settings = new SettingsParser().parse(args);
        this.serverThread = new Thread(new Server(settings));
        this.serverThread.start();
    }

    @Override
    public void run() {
        Scanner scanner = new Scanner(System.in);
        String command;
        System.out.println("Waiting for command...");
        System.out.println("Print help to see all commands");
        while(true){
            command = scanner.nextLine();
            if (command.toLowerCase().equals("exit")) {
                serverThread.stop();
                scanner.close();
                break;
            }
            if (command.toLowerCase().equals("help")) {
                System.out.println("######################################");
                System.out.println("exit");
                System.out.println("######################################");
            }
        }
        System.exit(1);
    }
}
