package Entity;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

public abstract class Node implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    protected String ip;
    protected int port;

    public Node(String ip, int port) {
        this.ip = ip;
        this.port = port;
    }

    public String getIP() {
        return ip;
    }

    public int getPort() {
        return port;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (!(o instanceof Node node)) return false;

        return port == node.port && Objects.equals(ip, node.ip);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ip, port);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() +
                "{ip='" + ip + '\'' +
                ", port=" + port +
                '}';
    }
}