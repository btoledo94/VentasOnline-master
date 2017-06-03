package gt.umg.ventasonline;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Date;

import gt.umg.ventasonline.Common.Utils;
import gt.umg.ventasonline.adapters.CiudadAdapter;
import gt.umg.ventasonline.adapters.EstadoAdapter;
import gt.umg.ventasonline.adapters.PaisAdapter;
import gt.umg.ventasonline.entities.Ciudad;
import gt.umg.ventasonline.entities.Estado;
import gt.umg.ventasonline.entities.Pais;
import gt.umg.ventasonline.entities.Usuario;
import gt.umg.ventasonline.ws.ResourceResponse;
import gt.umg.ventasonline.ws.VentasOnlineWs;

public class RegistroActivity extends AppCompatActivity {

    private VentasOnlineWs ventasOnlineWs;

    private Spinner paisSpinner;
    private Spinner estadoSpinner;
    private Spinner ciudadSpinner;
    private EditText nombreEditText;
    private EditText apellidoEditText;
    private EditText passwordEditText;
    private EditText correoEditText;
    private EditText direccionEditext;
    private Button registrarButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        ventasOnlineWs = new VentasOnlineWs();


        paisSpinner = (Spinner) findViewById(R.id.registro_pais_spinner);
        estadoSpinner = (Spinner) findViewById(R.id.registro_estado_spinner);
        ciudadSpinner = (Spinner) findViewById(R.id.registro_ciudad_spinner);

        nombreEditText = (EditText) findViewById(R.id.register_name);
        apellidoEditText = (EditText) findViewById(R.id.register_lastname);
        correoEditText = (EditText) findViewById(R.id.register_email);
        passwordEditText = (EditText) findViewById(R.id.register_password);
        direccionEditext = (EditText) findViewById(R.id.direccion);
        registrarButton = (Button) findViewById(R.id.register_button);


        Utils.showCustomProgressDialog("Obteniendo paises", RegistroActivity.this);
        ventasOnlineWs.getPaises().execute(new ResourceResponse<Pais[]>() {
            @Override
            public void success(int statusCode, Pais[] responseData) {
                Utils.hideCustomProgressDialog();

                PaisAdapter adapter = new PaisAdapter(responseData, RegistroActivity.this);

                paisSpinner.setAdapter(adapter);

            }

            @Override
            public void error(int errorCode, String error) {
                Utils.hideCustomProgressDialog();
                Utils.showCustomMessage(1, error, RegistroActivity.this);
            }
        });

        paisSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Pais pais = (Pais) paisSpinner.getSelectedItem();
                getEstados(pais.getId());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        estadoSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Estado estado = (Estado) estadoSpinner.getSelectedItem();
                getCiudades(estado.getId());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        registrarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registrar();
            }
        });

    }


    private void getEstados(Integer paisId){
        Utils.showCustomProgressDialog("Obteniendo estados", RegistroActivity.this);
        ventasOnlineWs.getEstados(paisId).execute(new ResourceResponse<Estado[]>() {
            @Override
            public void success(int statusCode, Estado[] responseData) {
                Utils.hideCustomProgressDialog();
                EstadoAdapter adapter = new EstadoAdapter(responseData, RegistroActivity.this);
                estadoSpinner.setAdapter(adapter);
            }

            @Override
            public void error(int errorCode, String error) {
                Utils.hideCustomProgressDialog();
                Utils.showCustomMessage(1, error, RegistroActivity.this);
            }
        });
    }

    private void getCiudades(Integer estadoId){
        Utils.showCustomProgressDialog("Obteniendo ciudades", RegistroActivity.this);
        ventasOnlineWs.getCiudades(estadoId).execute(new ResourceResponse<Ciudad[]>() {
            @Override
            public void success(int statusCode, Ciudad[] responseData) {
                Utils.hideCustomProgressDialog();
                CiudadAdapter adapter = new CiudadAdapter(responseData, RegistroActivity.this);
                ciudadSpinner.setAdapter(adapter);
            }

            @Override
            public void error(int errorCode, String error) {
                Utils.hideCustomProgressDialog();
                Utils.showCustomMessage(1, error, RegistroActivity.this);
            }
        });
    }

    private void registrar(){

        if(ciudadSpinner.getSelectedItem() == null){
            Toast.makeText(this, "Seleccione su ciudad", Toast.LENGTH_SHORT).show();
            return;
        }

        if(nombreEditText.getText() == null || "".equals(nombreEditText.getText().toString())){
            nombreEditText.setError("Nombre es requerido");
            return;
        }

        if(apellidoEditText.getText() == null || "".equals(apellidoEditText.getText().toString())){
            apellidoEditText.setError("Apellido es requerido");
            return;
        }

        if(correoEditText.getText() == null || "".equals(correoEditText.getText().toString())){
            correoEditText.setError("Correo es requerido");
            return;
        }

        if(passwordEditText.getText() == null || "".equals(passwordEditText.getText().toString())){
            passwordEditText.setError("Password es requerido");
            return;
        }

        if(direccionEditext.getText() == null || "".equals(direccionEditext.getText().toString())){
            direccionEditext.setError("Direccion es Requerido");
            return;
        }

        Ciudad ciudad = (Ciudad) ciudadSpinner.getSelectedItem();

        Usuario usuario = new Usuario();

        usuario.setCiudad(ciudad);
        usuario.setNombre(nombreEditText.getText().toString());
        usuario.setApellido(apellidoEditText.getText().toString());
        usuario.setCorreo(correoEditText.getText().toString());
        usuario.setPassword(passwordEditText.getText().toString());
        usuario.setFechaNacimiento(new Date());
        usuario.setDireccion(direccionEditext.getText().toString());

        Utils.showCustomProgressDialog("Registrando usuario", RegistroActivity.this);
        ventasOnlineWs.registrarUsuario(usuario).execute(new ResourceResponse<Usuario>() {
            @Override
            public void success(int statusCode, Usuario responseData) {
                Utils.hideCustomProgressDialog();
                if(statusCode == 200){
                    RegistroActivity.this.finish();
                } else {
                    Utils.showCustomMessage(1, "Ocurrio un error", RegistroActivity.this);
                }
            }

            @Override
            public void error(int errorCode, String error) {
                Utils.hideCustomProgressDialog();
                if(errorCode == 400){
                    Utils.showCustomMessage(1, "No se registrar el usuario", RegistroActivity.this);
                } else if(errorCode == 302){
                    Utils.showCustomMessage(1, "Ya existe un usuario con este correo", RegistroActivity.this);
                } else {
                    Utils.showCustomMessage(1, error, RegistroActivity.this);
                }
            }
        });

    }

}
