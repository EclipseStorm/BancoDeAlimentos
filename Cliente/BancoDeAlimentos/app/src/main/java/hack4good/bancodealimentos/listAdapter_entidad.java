package hack4good.bancodealimentos;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class listAdapter_entidad extends ArrayAdapter<Entidad> {

    public listAdapter_entidad(Context context, int textViewResourceId) {
        super(context, textViewResourceId);
    }

    public listAdapter_entidad(Context context, int resource, List<Entidad> items) {
        super(context, resource, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;

        if (v == null) {

            LayoutInflater vi;
            vi = LayoutInflater.from(getContext());
            v = vi.inflate(R.layout.row_entidades, null);

        }

        Entidad p = getItem(position);

        if (p != null) {

            TextView tt = (TextView) v.findViewById(R.id.nombre);
            TextView tt1 = (TextView) v.findViewById(R.id.direccion);

            if (tt != null) {
                tt.setText(p.getName());
            }
            if (tt1 != null) {
                tt1.setText(p.getDireccion());
            }
        }

        return v;

    }
}