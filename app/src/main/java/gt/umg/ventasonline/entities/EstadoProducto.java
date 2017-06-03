package gt.umg.ventasonline.entities;

/**
 * Created by BYRON TOLEDO on 6/1/2017.
 */

public class EstadoProducto {

    private Integer Id;

    private String descripcion;

    private boolean activo;

    public EstadoProducto() {

    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
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
