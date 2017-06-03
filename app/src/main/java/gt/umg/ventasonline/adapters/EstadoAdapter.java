package gt.umg.ventasonline.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import gt.umg.ventasonline.R;
import gt.umg.ventasonline.entities.Estado;

/**
 * Created by wilver on 28/05/17.
 */

public class EstadoAdapter extends BaseAdapter {

    private Estado[] estadoArray;
    private Context context;

    public EstadoAdapter(Estado[] estadoArray, Context context) {
        this.estadoArray = estadoArray;
        this.context = context;
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        View view = convertView;

        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.layout_item_spinner, null);
        }

        Estado estado = estadoArray[position];

        TextView description = (TextView) view.findViewById(R.id.template_item_spinner_text);
        description.setText(estado.getEstadoNombre());

        return view;

    }

    @Override
    public int getCount() {
        return estadoArray.length;
    }

    @Override
    public Object getItem(int position) {
        return estadoArray[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

}
