import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

import static java.lang.System.exit;

public class RandomNumberGenerator {


    private static double buffer[];


     public static void main(String[] args) {
        validateInput(args);
        int bufferSize = Integer.parseInt(args[0]);
        int port = Integer.parseInt(args[1]);
        initializeBuffer(bufferSize);


     }

     private static void validateInput(String args[]) {
         if (args.length != 2) {
             System.out.println("Usage:  RandomNumberGenerator <Buffer_Size> <Port>");
             System.out.println("Buffer_Size: The size of the buffer containing the random integers.The buffer size must be an integer greater than 100.");
             System.out.println("Port: The port number of the random integers must be an integer.");
             exit(0);
         }
         try {
             int bufferSize = Integer.parseInt(args[0]);
             if (bufferSize <= 0) {
                 System.out.println("Buffer Size must be a positive integer.");
                 exit(0);
             } else if (bufferSize < 100) {
                 System.out.println("Buffer Size must be greater than or equal to 100.");
                 exit(0);
             }
         } catch (NumberFormatException e) {
             System.out.println("Invalid Arguments.");
             System.out.println("Expected positive int, got: " + args[0]);
             exit(0);
         }
         int port = Integer.parseInt(args[1]);
         if (port < 0 || port > 65535) {
             System.out.println("Invalid Port.");
             exit(0);
         }
     }

     private static void initializeBuffer(int bufferSize) {
         buffer = new double[bufferSize];
         for (int i = 0; i < bufferSize; i++) {
             buffer[i] = Math.random();
         }
     }


     void initializeServer(int port){
         try {
             ServerSocket serverSocket = new ServerSocket(port);
             System.out.println("Server started. Waiting for a client...");

             while(true) {
                 Socket socket = serverSocket.accept(); // blocks until client connects
                 System.out.println("Client connected: " + socket.getInetAddress());
                 socket.getOutputStream().write(4242);
             }

         } catch (IOException e) {
             System.out.println("Error service could not be initialized to socket");
         }
     }




}
