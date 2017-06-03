package gt.umg.ventasonline.entities;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by BYRON TOLEDO on 6/1/2017.
 */

public class Producto {

    private Integer id;
    private String nombreProducto;
    private String marca;
    private Categoria categoria;
    private Usuario usuario;
    private BigDecimal precio;
    private Date fechaIngreso;
    private String descripcion;
    private EstadoProducto estadoproducto;
    private boolean activo;

    public Producto() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public EstadoProducto getEstadoproducto() {
        return estadoproducto;
    }

    public void setEstadoproducto(EstadoProducto estadoproducto) {
        this.estadoproducto = estadoproducto;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }
}
