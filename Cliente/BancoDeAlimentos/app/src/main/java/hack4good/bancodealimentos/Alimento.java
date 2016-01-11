package hack4good.bancodealimentos;

/**
 * Created by root on 18/04/15.
 */


public class Alimento {

    private String name;
    private int id;

    public Alimento (int id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public int getId() {
        return this.id;
    }

}


