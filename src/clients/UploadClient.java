package clients;

import java.io.*;
import java.net.Socket;

public class UploadClient implements Runnable{

    @Override
    public void run() {

        try (Socket socket = new Socket("localhost", 5000))
        {
            File imgFile = new File("");

            try (BufferedInputStream bufferedFileStream = new BufferedInputStream(new FileInputStream(imgFile));
                 BufferedOutputStream bufferedSocketStream = new BufferedOutputStream(socket.getOutputStream());)
            {
                byte[] buffer = new byte[(int) imgFile.length()];
                int bytesRead;

                while ((bytesRead = bufferedFileStream.read(buffer)) != -1) {
                    bufferedSocketStream.write(buffer, 0, bytesRead);
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
