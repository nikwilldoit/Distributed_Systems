package Entity.worker;

import Entity.Node;
import java.io.Serial;

public class WorkerNode extends Node {

    @Serial
    private static final long serialVersionUID = 1L;

    public WorkerNode(String ip, int port) {
        super(ip, port);
    }
}