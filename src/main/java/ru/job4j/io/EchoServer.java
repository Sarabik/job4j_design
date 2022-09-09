package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {
    private static final Logger LOG = LoggerFactory.getLogger(EchoServer.class.getName());

    public static void main(String[] args) throws IOException {
        try (ServerSocket server = new ServerSocket(9000)) {
            while (!server.isClosed()) {
                Socket socket = server.accept();
                try (OutputStream out = socket.getOutputStream();
                     BufferedReader in = new BufferedReader(
                             new InputStreamReader(socket.getInputStream()))) {
                    out.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                    String msg = in.readLine().split(" ")[1].substring(6);
                    if ("Exit".equals(msg)) {
                        server.close();
                    } else if ("Hello".equals(msg)) {
                        out.write("Hello!".getBytes());
                    } else {
                        out.write(msg.getBytes());
                    }
                    out.flush();
                } catch (IOException e) {
                    LOG.error("Error while opening input or output stream", e);
                }
            }
        } catch (IOException e) {
            LOG.error("Error while opening server socket", e);
        }

    }
}
