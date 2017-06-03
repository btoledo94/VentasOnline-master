package gt.umg.ventasonline.entities;

/**
 * Created by wilver on 28/05/17.
 */

public class Ciudad {
    private Integer id;
    private Estado estado;

    private String ciudadNombre;

    public Ciudad() {
    }

    public Ciudad(Integer id, Estado estado, String ciudadNombre) {
        this.id = id;
        this.estado = estado;
        this.ciudadNombre = ciudadNombre;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public String getCiudadNombre() {
        return ciudadNombre;
    }

    public void setCiudadNombre(String ciudadNombre) {
        this.ciudadNombre = ciudadNombre;
    }
}
