package Entity;

import Node.WorkerNode;
//import Wrappers.WorkerConfigWrapper;
//import com.google.gson.Gson;
import other.HashRing;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Accepts incoming socket connections in a loop.
 * For each connection, a new thread is created using a Handler instance.
 *
 * @throws IOException if the server socket fails to accept a connection
 */
public class Master extends Entity {
    //Workers
    private String worker_config_filepath;
    private List<WorkerNode> workersList;
    public HashRing workers;                        //Ο Master χρησιμοποιεί έναν HashRing για να κάνει κατανομή ""casino games"" στους Workers
    private final int VIRTUAL_NODES_OF_WORKER = 2;  /*Για κάθε Worker, ο HashRing βάζει δύο αντίγραφα του worker στο δαχτυλίδι,
                                                      για να έχουμε καλύτερη κατανομή φορτίου.*/


    //Constructor
    /**
     * Initializes the MasterNode with the specified IP and port.
     * Loads the worker configuration and sets up the hash ring.
     *
     * @param IP the IP address of the master
     * @param PORT the port number to listen on
     */
    public Master(String IP, int PORT) {
        super(IP, PORT);
        initiateWorkers();
        workers = new HashRing(workersList, VIRTUAL_NODES_OF_WORKER);
    }


    /**
     * Loads worker node configuration from the JSON file and stores it in a list.
     * Used during initialization to populate the hash ring.
     */
    private void initiateWorkers() {

        System.out.println(System.getProperty("user.dir"));//debugging.
        Path path = Paths.get( "Resources", "WorkerConfig.json");
        System.out.println(path);//debugging.

        try (FileReader reader = new FileReader(path.toFile())) {
//            Gson gson = new Gson();                                               //μετατροπή JSON σε Java αντικείμενα
//            WorkerConfigWrapper wrapper = gson.fromJson(reader, WorkerConfigWrapper.class); //τα μετατρέπει σε ένα Java αντικείμενο τύπου WorkerConfigWrapper
//            this.workersList = wrapper.getWorkers();    //Παίρνει από το wrapper τη λίστα των Workers
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public List<WorkerNode> getWorkersList() {return workersList;}

    public static void main(String[] args) {
        Master master = new Master(args[0], Integer.parseInt(args[1]));
        try {
            master.acceptConnections();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
