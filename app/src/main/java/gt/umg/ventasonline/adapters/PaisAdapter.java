package gt.umg.ventasonline.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import gt.umg.ventasonline.R;
import gt.umg.ventasonline.entities.Pais;

/**
 * Created by wilver on 28/05/17.
 */

public class PaisAdapter extends BaseAdapter {

    private Pais[] paisArray;
    private Context context;

    public PaisAdapter(Pais[] paisArray, Context context) {
        this.paisArray = paisArray;
        this.context = context;
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        View view = convertView;

        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.layout_item_spinner, null);
        }

        Pais pais = paisArray[position];

        TextView description = (TextView) view.findViewById(R.id.template_item_spinner_text);
        description.setText(pais.getNombrePais());

        return view;

    }

    @Override
    public int getCount() {
        return paisArray.length;
    }

    @Override
    public Object getItem(int position) {
        return paisArray[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

}
