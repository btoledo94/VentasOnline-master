package gt.umg.ventasonline.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import gt.umg.ventasonline.R;
import gt.umg.ventasonline.entities.TipoPago;

/**
 * Created by BYRON TOLEDO on 6/2/2017.
 */

public class PagoAdapter extends BaseAdapter {

    private TipoPago[] tipoPagos;
    private Context context;

    public PagoAdapter(TipoPago[] tipoPagos, Context context) {
        this.tipoPagos = tipoPagos;
        this.context = context;
    }

    @Override
    public int getCount() {
        return tipoPagos.length;
    }

    @Override
    public Object getItem(int position) {
        return tipoPagos[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertview, ViewGroup viewGroup) {
        View view = convertview;

        if(view == null){

            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.layout_item_spinner, null);

        }

        TipoPago tipoPago = tipoPagos[position];

        TextView descripcion = (TextView) view.findViewById(R.id.template_item_spinner_text);
        descripcion.setText(tipoPago.getDescription());

        return view;
    }
}
