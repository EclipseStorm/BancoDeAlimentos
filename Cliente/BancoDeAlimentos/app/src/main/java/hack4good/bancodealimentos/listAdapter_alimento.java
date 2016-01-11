package hack4good.bancodealimentos;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class listAdapter_alimento extends ArrayAdapter<Alimento> {

    public listAdapter_alimento(Context context, int textViewResourceId) {
        super(context, textViewResourceId);
    }

    public listAdapter_alimento(Context context, int resource, List<Alimento> items) {
        super(context, resource, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;

        if (v == null) {

            LayoutInflater vi;
            vi = LayoutInflater.from(getContext());
            v = vi.inflate(R.layout.row_alimentos, null);

        }

        Alimento p = getItem(position);

        if (p != null) {

            TextView tt = (TextView) v.findViewById(R.id.nombre);

            if (tt != null) {
                tt.setText(p.getName());
            }
        }

        return v;

    }
}