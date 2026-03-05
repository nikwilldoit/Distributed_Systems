import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.util.LinkedList;
import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static java.lang.System.exit;

public class RandomNumberGenerator {


    private static final LinkedList<Double> randomNumbersQueue = new LinkedList<>();
    private static final Lock randomNumbersLock = new ReentrantLock();
    private static final Condition randomNumbersCondition = randomNumbersLock.newCondition();


    public static void main(String[] args) {
        validateInput(args);
        int bufferSize = Integer.parseInt(args[0]);
        int port = Integer.parseInt(args[1]);
        initializeBuffer(bufferSize);
        initializeServer(port);

    }

    public static void initializeRandomNumberGeneratorServer(int bufferSize,int port) {
        if  (bufferSize <= 100) {
            throw new IllegalArgumentException("Buffer size must be greater than or equal to 100.Invalid Configuration defined");
        }
        if (port <= 0 || port > 65535) {
            throw new IllegalArgumentException("Port must be greater than 0 and less than 65535.Invalid Configuration defined");
        }
        initializeBuffer(bufferSize);
        initializeServer(port);
    }



     private static void validateInput(String[] args) {
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
         for (int i = 0; i < bufferSize; i++) {
             randomNumbersQueue.addLast(getRandomNumber());
         }
     }

     public static Double getRandomNumber() {
         return new Random().nextDouble();
     }

    static class ResponseThread extends Thread {

        private final Socket socket;

        ResponseThread(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run()  {
            Double randomNumber;
            //Quickily retrieve number
            randomNumbersLock.lock();
            try {
                while (randomNumbersQueue.isEmpty()) {
                    try {
                        randomNumbersCondition.await();
                    } catch (InterruptedException e) {
                        //ignore, maintain only for debugging purposes
                    }

                }
                randomNumber = randomNumbersQueue.removeFirst();
            } finally {
                randomNumbersLock.unlock();
            }
            //quickly send number
            try {
                OutputStream output = socket.getOutputStream();
                String number = String.valueOf(randomNumber);
                byte[] numberByteSequence = number.getBytes();
                byte[] numberHashedByteSequence = Security.encryptSHA256(numberByteSequence);

                byte[] merged = ByteBuffer
                        .allocate(numberByteSequence.length + numberHashedByteSequence.length)
                        .put(numberByteSequence)
                        .put(numberHashedByteSequence)
                        .array();


                output.write(merged);
            } catch (IOException e) {
                System.out.println("Error could not send append data to output stream.");
                throw new RuntimeException(e);
            }
            try {
                socket.close();
            } catch (IOException e) {
                System.out.println("Error could not close socket.");
            }
            //since generators tend to be slow ,retrieve number and add it when possible
            randomNumber = getRandomNumber();
            randomNumbersLock.lock();
            try {
                randomNumbersQueue.addLast(randomNumber);
                randomNumbersCondition.signal(); // MUST be inside lock
            } finally {
                randomNumbersLock.unlock();
            }


        }


    }





     private static void initializeServer(int port){
         try {
             ServerSocket serverSocket = new ServerSocket(port);
             System.out.println("Server started. Waiting for a client...");

             while(true) {
                Socket socket = serverSocket.accept(); // blocks until client connects
                System.out.println("Client connected: " + socket.getInetAddress() + ":" + socket.getPort());
                new ResponseThread(socket).start();
             }

         } catch (IOException e) {
             System.out.println("Error service could not be initialized to socket");
             throw new RuntimeException(e);
         }
     }



}
