package org.fiek.socket;

import io.socket.client.IO;
import io.socket.client.Socket;

public class SocketClient {

    final String hostname;
    public static Socket socket;
    public ChatSocket chatSocket = new ChatSocket();

    public SocketClient(String hostname) {
        this.hostname = hostname;
        execute();
    }

    public void execute() {
        try {
            socket = IO.socket(this.hostname);

            chatSocket.onMessage(socket);

            socket.connect();

        } catch (Exception exception) {
            exception.printStackTrace();

        }
    }

    public Socket getSocket() {
        return socket;
    }

}
