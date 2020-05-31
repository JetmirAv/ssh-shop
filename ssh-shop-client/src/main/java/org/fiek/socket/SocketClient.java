package org.fiek.socket;

import io.socket.client.IO;
import io.socket.client.Socket;

public class SocketClient {

    final String hostname;
    final Integer port;
    public static Socket socket;
    public ChatSocket chatSocket = new ChatSocket();

    public SocketClient(String hostname, Integer port) {
        this.hostname = hostname;
        this.port = port;
        execute();
    }

    public void execute() {
        try {
            socket = IO.socket(this.hostname);

            chatSocket.onMessage(socket);

            socket.connect();

        } catch (Exception exception) {
            System.out.println("Exception" + exception.getMessage());
            exception.printStackTrace();

        }
    }

    public Socket getSocket() {
        return socket;
    }

}
