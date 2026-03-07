package org.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.nio.channels.ClosedByInterruptException;

public interface Connection {


    static Socket connect(String host, int port) {
        try {
            Socket socket = new Socket(host, port);
            return socket;
        } catch (UnknownHostException e) {
            throw new RuntimeException("Error connecting to " + host + ":" + port, e);
        } catch (IOException e) {
            throw new RuntimeException("Error binding a socket to: " + host + ":" + port, e);
        }
    }

    static void sendStringData(Socket socket, String data) {
        try {
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        } catch (ClosedByInterruptException e) {
            throw new RuntimeException("Error while sending data to " + socket.getRemoteSocketAddress() + ". Host terminated the connection.");
        } catch (IOException e) {
            throw new RuntimeException("Error on data transfer to " + socket.getRemoteSocketAddress() + ".", e);
        }
    }

    static String receiveStringData(Socket socket) {
        String data = "";
        try {
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));
            return in.readLine();

        } catch (ClosedByInterruptException e) {
            throw new RuntimeException("Error,server failed to receive data from " + socket.getRemoteSocketAddress()+ ". Host terminated the connection.");
        }catch (IOException e) {
            throw new RuntimeException("Error on data transfer from " + socket.getRemoteSocketAddress() + ".", e);
        }
    }




}
