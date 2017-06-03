package gt.umg.ventasonline.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import gt.umg.ventasonline.R;

import gt.umg.ventasonline.entities.Producto;

/**
 * Created by BYRON TOLEDO on 6/1/2017.
 */

public class ProductoAdapter extends ArrayAdapter<Producto> {


    public ProductoAdapter(Producto[] productoArray, Context context) {
        super(context, 0, productoArray);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);


       if (convertView == null) {

           convertView = inflater.inflate(R.layout.layout_producto_detalle, parent, false);
        }

        Producto producto = getItem(position);

        TextView nombreProducto = (TextView) convertView.findViewById(R.id.layout_producto_detalle_nombre);
        TextView precioProducto = (TextView) convertView.findViewById(R.id.layout_producto_detalle_precio);
        TextView fechaProducto = (TextView) convertView.findViewById(R.id.layout_producto_detalle_fecha_ingreso);
        TextView marcaProducto = (TextView) convertView.findViewById(R.id.layout_producto_detalle_Marca);
        TextView descripcionProducto = (TextView) convertView.findViewById(R.id.layout_producto_detalle_descripcion);

        nombreProducto.setText(producto.getNombreProducto());
        precioProducto.setText("$" + producto.getPrecio());
        fechaProducto.setText(" Fecha Apertura:" + producto.getFechaIngreso());
        marcaProducto.setText(producto.getMarca());
        descripcionProducto.setText(producto.getDescripcion());

        return convertView;
    }

    @Override
    public Producto getItem(int position) {
        return super.getItem(position);
    }


}
