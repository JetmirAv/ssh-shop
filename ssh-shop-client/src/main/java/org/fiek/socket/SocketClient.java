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
    Socket socket;

    public SocketClient(String hostname, Integer port) {
        this.hostname = hostname;
        this.port = port;
        execute();
    }

    public void execute() {
        try {
            Socket socket = IO.socket(this.hostname);
            socket.connect();
        } catch (Exception exception) {
            System.out.println("Exception" + exception.getMessage());
            exception.printStackTrace();

        }
    }
}
