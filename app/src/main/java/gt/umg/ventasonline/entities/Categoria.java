package gt.umg.ventasonline.entities;

/**
 * Created by BYRON TOLEDO on 6/1/2017.
 */

public class Categoria {
    private Integer id;
    private String descripcion;
    private boolean activo;

    public Categoria() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }
}
