package server;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class FileServer implements Runnable {

    @Override
    public void run() {

        try {
            ServerSocket server = new ServerSocket(5000);
            Socket connection = server.accept();

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
