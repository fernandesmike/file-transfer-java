package clients;

import java.io.*;
import java.net.Socket;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

public class Client implements Runnable{

    @Override
    public void run() {

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        try (Socket socket = new Socket("localhost", 5000))
        {
            String imgPath = "C:\\Users\\ferna\\OneDrive\\Desktop\\Application Development\\Socket\\file-transfer\\src\\clients\\files\\upload.png";
            File imgFile = new File(imgPath);

            try (BufferedInputStream bufferedFileStream = new BufferedInputStream(new FileInputStream(imgFile));
                 BufferedOutputStream bufferedServerStream = new BufferedOutputStream(socket.getOutputStream()))
            {
                System.out.println("Client: Image found. Uploading...");
                byte[] buffer = new byte[(int) imgFile.length()];
                int _bytesRead;

                while ((_bytesRead = bufferedFileStream.read(buffer)) != -1) {
                    bufferedServerStream.write(buffer, 0, _bytesRead);
                }
            }

            Thread.sleep(2000);
        } catch (IOException | InterruptedException e) {
            System.out.println(e);
        }
    }
}
