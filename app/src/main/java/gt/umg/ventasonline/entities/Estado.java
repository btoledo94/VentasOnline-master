package gt.umg.ventasonline.entities;

/**
 * Created by wilver on 28/05/17.
 */

public class Estado {
    private Integer id;

    private String estadoNombre;
    private Pais pais;

    public Estado() {
    }

    public Estado(Integer id, String estadoNombre, Pais pais) {
        this.id = id;
        this.estadoNombre = estadoNombre;
        this.pais = pais;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEstadoNombre() {
        return estadoNombre;
    }

    public void setEstadoNombre(String estadoNombre) {
        this.estadoNombre = estadoNombre;
    }

    public Pais getPais() {
        return pais;
    }

    public void setPais(Pais pais) {
        this.pais = pais;
    }
}
