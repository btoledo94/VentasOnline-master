package gt.umg.ventasonline;

import android.renderscript.Sampler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import gt.umg.ventasonline.Common.Common;
import gt.umg.ventasonline.adapters.PagoAdapter;
import gt.umg.ventasonline.entities.Factura;
import gt.umg.ventasonline.entities.Producto;
import gt.umg.ventasonline.entities.TipoPago;
import gt.umg.ventasonline.entities.Usuario;
import gt.umg.ventasonline.ws.VentasOnlineWs;

public class PagoActivity extends AppCompatActivity {

    private Bundle recuperarDatos;
    private TipoPago[] pagoArray = {TipoPago.AMERICAN_EXPRESS,TipoPago.MASTERCARD,TipoPago.VISA,TipoPago.VISA_ELECTRON};

    private Button pagoProducto;
    private EditText numeroTarjeta;
    private EditText nombreTarjeta;
    private EditText fechaCaducida;
    private EditText codigoSeguridad;
    private Spinner tipoPagoSpinner;
    private  Spinner Pais;
    private VentasOnlineWs ventasOnlineWs;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pago);

        setTitle("Datos de Pago");

        recuperarDatos = getIntent().getExtras();
        ventasOnlineWs = new VentasOnlineWs();

        numeroTarjeta = (EditText) findViewById(R.id.pago_tarjeta);
        nombreTarjeta = (EditText) findViewById(R.id.pago_tarjeta_nombre);
        fechaCaducida = (EditText) findViewById(R.id.pago_mes_caducidad+R.id.intermedio+R.id.pago_ano_caducidad);
        codigoSeguridad = (EditText) findViewById(R.id.tarjeta_codigo_seguridad);
        pagoProducto = (Button) findViewById(R.id.pago_confirmar_button);
        tipoPagoSpinner = (Spinner) findViewById(R.id.tipo_pago);

        PagoAdapter pagoAdapter = new PagoAdapter(pagoArray, this);
        tipoPagoSpinner.setAdapter(pagoAdapter);

        pagoProducto.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){
               formaPago();
            }
        });

    }

    private void formaPago(){

        if(numeroTarjeta.getText()==null||"".equals(numeroTarjeta.getText().toString())){
            numeroTarjeta.setError("Campo Requerido");
            return;
        }

        if(nombreTarjeta.getText()==null||"".equals(nombreTarjeta.getText().toString())){
            nombreTarjeta.setError("Campo Requerido");
            return;
        }

        if(fechaCaducida.getText()==null||"".equals(fechaCaducida.getText().toString())){
            fechaCaducida.setError("Campo Requerido");
            return;
        }

        if(codigoSeguridad.getText()==null||"".equals(codigoSeguridad.getText().toString())){
            codigoSeguridad.setError("Campo Requerido");
            return;
        }

        Usuario usuario = new Usuario();
        Producto producto = new Producto();

        Factura factura = new Factura();

        factura.setUsuario(usuario);
        factura.setCodigoSeguridad(Integer.valueOf(codigoSeguridad.getText().toString()));
        factura.setFechaCaducida(fechaCaducida.getText().toString());
        factura.setNombreTarjeta(nombreTarjeta.getText().toString());
        factura.setNumeroTarjerta(Integer.valueOf(numeroTarjeta.getText().toString()));
        factura.setProducto(producto);



    }
}
