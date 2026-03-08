package Entity.reducer;

import Entity.Node;
import java.io.Serial;

public class ReducerNode extends Node {

    @Serial
    private static final long serialVersionUID = 1L;

    public ReducerNode(String ip, int port) {
        super(ip, port);
    }
}