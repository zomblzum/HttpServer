import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.Socket;

class RequestHandler implements Runnable {
    private Socket socket;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;

    RequestHandler(Socket socket) throws IOException {
        this.socket = socket;
        this.socket.setSoTimeout(2000);
        this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
    }

    @Override
    public void run() {
        try {
            if(bufferedReader.ready()) {
                String requestFile = new RequestParser().getUrlFromRequest(bufferedReader.readLine());
                File responseFile = new File(requestFile);
                Response response = new ResponseFactory().getResponseFromFile(responseFile);
                sendResponse(response);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void sendResponse(Response response) throws IOException {
        bufferedWriter.write(response.getHeader());
        bufferedWriter.flush();

        if(response.getFile() != null) {
            String fileExtension = response.getFile().getName().substring(response.getFile().getName().lastIndexOf('.'));

            if ((fileExtension.contains(".png")) || fileExtension.contains(".jpg") ||
                    fileExtension.contains(".jpeg") || fileExtension.contains(".gif")) {
                BufferedImage image = ImageIO.read(response.getFile());

                if (image != null) {
                    ImageIO.write(image, fileExtension.substring(1), socket.getOutputStream());
                }
            }
            else {
                BufferedReader cachedFileBufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(response.getFile())));

                String line;
                while ((line = cachedFileBufferedReader.readLine()) != null) {
                    bufferedWriter.write(line);
                    bufferedWriter.newLine();
                }
                bufferedWriter.flush();

                cachedFileBufferedReader.close();
            }

            bufferedWriter.close();
        }
    }
}
