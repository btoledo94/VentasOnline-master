package gt.umg.ventasonline.entities;

import java.util.Date;

/**
 * Created by wilver on 28/05/17.
 */

public class Sesion {

    private Integer id;
    private String token;
    private Date fechaInicio;
    private Date fechaFin;
    private Usuario usuario;

    public Sesion() {

    }

    public Sesion(Integer id, String token, Date fechaInicio, Date fechaFin, Usuario usuario) {
        this.id = id;
        this.token = token;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.usuario = usuario;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

}
