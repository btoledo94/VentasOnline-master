package gt.umg.ventasonline.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import gt.umg.ventasonline.R;
import gt.umg.ventasonline.entities.Ciudad;

/**
 * Created by wilver on 28/05/17.
 */

public class CiudadAdapter extends BaseAdapter {

    private Ciudad[] ciudadArray;
    private Context context;

    public CiudadAdapter(Ciudad[] ciudadArray, Context context) {
        this.ciudadArray = ciudadArray;
        this.context = context;
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        View view = convertView;

        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.layout_item_spinner, null);
        }

        Ciudad ciudad = ciudadArray[position];

        TextView description = (TextView) view.findViewById(R.id.template_item_spinner_text);
        description.setText(ciudad.getCiudadNombre());

        return view;

    }

    @Override
    public int getCount() {
        return ciudadArray.length;
    }

    @Override
    public Object getItem(int position) {
        return ciudadArray[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

}
