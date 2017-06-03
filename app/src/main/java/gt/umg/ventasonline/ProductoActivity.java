package gt.umg.ventasonline;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import gt.umg.ventasonline.Common.Utils;
import gt.umg.ventasonline.adapters.ProductoAdapter;
import gt.umg.ventasonline.entities.Categoria;
import gt.umg.ventasonline.entities.Producto;
import gt.umg.ventasonline.ws.ResourceResponse;
import gt.umg.ventasonline.ws.VentasOnlineWs;

public class ProductoActivity extends Activity {

    private VentasOnlineWs ventasOnlineWs;
    private ListView listView;
    private ProductoAdapter adapter;
    private Bundle parameters;
    private Categoria categoria;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_menu);

        ventasOnlineWs = new VentasOnlineWs();
        listView = (ListView) findViewById(R.id.producto_detalle_list_view);



        Utils.showCustomProgressDialog("Obteniendo Menu", ProductoActivity.this);

        ventasOnlineWs.getProducto(1).execute(new ResourceResponse<Producto[]>() {
            @Override
            public void success(int statusCode, Producto[] responseData) {
                Utils.hideCustomProgressDialog();
                adapter = new ProductoAdapter(responseData, ProductoActivity.this);
                listView.setAdapter(adapter);
            }

            @Override
            public void error(int errorCode, String error) {
                Utils.hideCustomProgressDialog();
                Log.e("getProducto",error);

            }
        });


    }
}
