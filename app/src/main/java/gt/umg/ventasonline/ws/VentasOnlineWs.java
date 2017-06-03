package gt.umg.ventasonline.ws;

import java.util.HashMap;
import java.util.Map;

import gt.umg.ventasonline.Common.Common;
import gt.umg.ventasonline.entities.Ciudad;
import gt.umg.ventasonline.entities.Estado;
import gt.umg.ventasonline.entities.InventarioProducto;
import gt.umg.ventasonline.entities.Pais;
import gt.umg.ventasonline.entities.Producto;
import gt.umg.ventasonline.entities.Sesion;
import gt.umg.ventasonline.entities.Usuario;

/**
 * Created by wilver on 28/05/17.
 */

public class VentasOnlineWs {

    public Resource<Sesion> login(String email, String password){

        String url = Common.getUrlWs() + "Sesion?correo={correo}&password={password}";

        Map<String, String> parameters = new HashMap<>();
        parameters.put("correo", email);
        parameters.put("password", password);

        return new Resource<Sesion>().post(url, parameters, Sesion.class, null);
    }

    public Resource<Pais[]> getPaises(){

        String url = Common.getUrlWs() + "Pais";
        Map<String, String> parameters = new HashMap<>();

        return new Resource<Pais[]>().get(url, parameters, Pais[].class);
    }

    public Resource<Estado[]> getEstados(Integer paisId){

        String url = Common.getUrlWs() + "Pais/{paisId}/Estado";
        Map<String, String> parameters = new HashMap<>();
        parameters.put("paisId", paisId.toString());

        return new Resource<Estado[]>().get(url, parameters, Estado[].class);
    }

    public Resource<Ciudad[]> getCiudades(Integer estadoId){
        String url = Common.getUrlWs() + "Estado/{estadoId}/Ciudad";
        Map<String, String> parameters = new HashMap<>();
        parameters.put("estadoId", estadoId.toString());

        return new Resource<Ciudad[]>().get(url, parameters, Ciudad[].class);
    }

    public Resource<Usuario> registrarUsuario(Usuario usuario){
        String url = Common.getUrlWs() + "Usuario";
        Map<String, String> parameters = new HashMap<>();

        return new Resource<Usuario>().post(url, parameters, Usuario.class, usuario);
    }

    public Resource<Producto[]> getProducto(Integer categoriaId){
        String url = Common.getUrlWs() + "Categoria/{categoriaId}/Producto";
        Map<String, String> parameters = new HashMap<>();
        parameters.put("categoriaId",categoriaId.toString());

        return new Resource<Producto[]>().get(url, parameters, Producto[].class);
    }

}
