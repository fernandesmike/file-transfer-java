package server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLOutput;

public class FileServer implements Runnable {

    String storagePath = "C:\\Users\\ferna\\OneDrive\\Desktop\\Application Development\\Socket\\file-transfer\\src\\server\\storage";
    String imgPath = "C:\\Users\\ferna\\OneDrive\\Desktop\\Application Development\\Socket\\file-transfer\\src\\clients\\files\\upload.png";

    @Override
    public void run() {

        System.out.println("Server: Waiting for connection...");

        try (ServerSocket server = new ServerSocket(5000);
             Socket connection = server.accept())
        {
            uploadFromClient(connection);

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private void uploadFromClient(Socket connection ) {

        File imgFile = new File(imgPath);

        System.out.println("Server: Client connection established.");

        File uploadedFile = new File(storagePath,"upload.png");

        try (BufferedInputStream bufferedClientStream = new BufferedInputStream(connection.getInputStream());
             BufferedOutputStream bufferedFileStream = new BufferedOutputStream(new FileOutputStream(uploadedFile)))
        {
            byte[] receivedBytes = new byte[(int) imgFile.length()];
            int _bytesRead;

            while ((_bytesRead = bufferedClientStream.read(receivedBytes)) != -1) {
                bufferedFileStream.write(receivedBytes);
            }

            Thread.sleep(2000);
        } catch (IOException | InterruptedException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("Server: Image uploaded at: " + uploadedFile.getAbsolutePath());
        System.out.println("Server offline");
    }
}
