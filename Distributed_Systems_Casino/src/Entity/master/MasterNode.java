package Entity.master;

import Entity.Node;
import java.io.Serial;

public class MasterNode extends Node {

    @Serial
    private static final long serialVersionUID = 1L;

    public MasterNode(String ip, int port) {
        super(ip, port);
    }
}