package hack4good.bancodealimentos;

/**
 * Created by root on 18/04/15.
 */

public class Entidad {

    private String name;
    private String direccion;
    private int id;

    public Entidad (int id, String name, String direccion) {
        this.id = id;
        this.name = name;
        this.direccion = direccion;
    }

    public String getDireccion() {
        return this.direccion;
    }

    public String getName() {
        return this.name;
    }

    public int getId() {
        return this.id;
    }

}
