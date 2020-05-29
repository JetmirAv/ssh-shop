package org.fiek.socket;

import com.google.gson.JsonObject;
import io.socket.client.IO;
import io.socket.client.Socket;

import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import java.io.*;
import java.net.*;

public class SocketClient {

    final String hostname;
    final Integer port;
    public static Socket socket;

    public SocketClient(String hostname, Integer port) {
        this.hostname = hostname;
        this.port = port;
        execute();
    }

    public void execute() {
        try {
            socket = IO.socket(this.hostname);
            socket.connect();

            ChatSocket.onMessage(socket);

        } catch (Exception exception) {
            System.out.println("Exception" + exception.getMessage());
            exception.printStackTrace();

        }
    }

    public Socket getSocket() {
        return socket;
    }

}
