package gt.umg.ventasonline.entities;

import java.util.Date;

/**
 * Created by BYRON TOLEDO on 6/3/2017.
 */

public class Factura {

    private Integer id;
    private Date fechaIngreso;
    private Usuario usuario;
    private Producto producto;
    private Integer numeroTarjerta;
    private String nombreTarjeta;
    private  String fechaCaducida;
    private Integer codigoSeguridad;

    public Factura() {

    }

    public Factura(Usuario usuario, Producto producto, Integer numeroTarjerta, String nombreTarjeta, String fechaCaducida, Integer codigoSeguridad) {
        this.id = id;
        this.fechaIngreso = fechaIngreso;
        this.usuario = usuario;
        this.producto = producto;
        this.numeroTarjerta = numeroTarjerta;
        this.nombreTarjeta = nombreTarjeta;
        this.fechaCaducida = fechaCaducida;
        this.codigoSeguridad = codigoSeguridad;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public Integer getNumeroTarjerta() {
        return numeroTarjerta;
    }

    public void setNumeroTarjerta(Integer numeroTarjerta) {
        this.numeroTarjerta = numeroTarjerta;
    }

    public String getNombreTarjeta() {
        return nombreTarjeta;
    }

    public void setNombreTarjeta(String nombreTarjeta) {
        this.nombreTarjeta = nombreTarjeta;
    }

    public String getFechaCaducida() {
        return fechaCaducida;
    }

    public void setFechaCaducida(String fechaCaducida) {
        this.fechaCaducida = fechaCaducida;
    }

    public Integer getCodigoSeguridad() {
        return codigoSeguridad;
    }

    public void setCodigoSeguridad(Integer codigoSeguridad) {
        this.codigoSeguridad = codigoSeguridad;
    }
}
