package Users.manager;

public class Manager {
    private final String id;
    private final String name;

    public Manager(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}