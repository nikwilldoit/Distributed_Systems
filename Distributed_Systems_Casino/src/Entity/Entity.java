package Entity;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Master, Worker and Reducer extend this class.
 * Provides TCP server functionality.
 */
public abstract class Entity {

    protected final String IP;
    protected final int PORT;
    protected ServerSocket serverSocket;

    /**
     * Constructor: initializes the server socket.
     */
    public Entity(String IP, int PORT) {

        this.IP = IP;
        this.PORT = PORT;

        try {
            serverSocket = new ServerSocket(PORT);
            System.out.println(getClass().getSimpleName()
                    + " started on port " + PORT);
        }
        catch(IOException e) {
            System.err.println("Failed to start server on port " + PORT);
            e.printStackTrace();
            System.exit(-1);
        }
    }

    /**
     * Main server loop.
     * Accepts connections and creates a new handler thread.
     */
    public void acceptConnections() {

        while(!serverSocket.isClosed()) {

            try {

                Socket socket = serverSocket.accept();

                System.out.println(
                        getClass().getSimpleName()
                                + " accepted connection from "
                                + socket.getRemoteSocketAddress()
                );

                Runnable handler = createHandler(socket);

                Thread thread = new Thread(handler);

                thread.start();

            }
            catch(IOException e) {
                System.err.println("Connection error");
                e.printStackTrace();
            }
        }
    }

    /**
     * Each subclass must define its handler.
     */
    protected abstract Runnable createHandler(Socket socket);

    public String getIP() {
        return IP;
    }

    public int getPORT() {
        return PORT;
    }
}