package Entity.master;

import java.io.*;
import java.net.Socket;

public class MasterHandler implements Runnable {

    private Master master;
    private Socket socket;

    public MasterHandler(Master master, Socket socket) {
        this.master = master;
        this.socket = socket;
    }

    @Override
    public void run() {

        try {

            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            PrintWriter out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);

            String request = in.readLine();

            System.out.println("Received request: " + request);

            String response = master.handleRequest(request);

            out.println(response);

            socket.close();

        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}