package gt.umg.ventasonline;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.Date;

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
    private Integer id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_producto);

        ventasOnlineWs = new VentasOnlineWs();
        listView = (ListView) findViewById(R.id.producto_detalle_list_view);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(ProductoActivity.this, PagoActivity.class);
                intent.putExtra("productoId", adapter.getItem(position).getId());
                intent.putExtra("productoPrecio", adapter.getItem(position).getPrecio().doubleValue());
                intent.putExtra("productoMarca", adapter.getItem(position).getMarca());
                intent.putExtra("productoDesscripcion", adapter.getItem(position).getDescripcion());
                startActivity(intent);

            }
        });

        parameters = getIntent().getExtras();


        Utils.showCustomProgressDialog("Obteniendo Menu", ProductoActivity.this);

        ventasOnlineWs.getProducto(parameters.getInt("categoriaId")).execute(new ResourceResponse<Producto[]>() {
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
