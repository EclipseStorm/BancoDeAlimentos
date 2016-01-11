package hack4good.bancodealimentos;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;


public class Datos_Alimento extends ActionBarActivity {

    private int id;
    private String nombre;

    public ArrayList l = new ArrayList<Entidad>();

    String host = "192.168.199.249";
    public String url = "http://" + host + "/h4g/api.php?q=prioridad_entidad";

    /*JSON*/
    JSONArray jsonArray = null;
    JSONObject jsonObj = null;

    public ListView lista;
    public ListAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos_alimentos);

        TextView t1 = (TextView) findViewById(R.id.dato_nombre);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            id = extras.getInt("id");
            url = url + "&id_alimento=" + id;
            nombre = (String) extras.getString("nombre");
            t1.setText(nombre);
        }

        lista = (ListView) findViewById(R.id.listaentidades);

        customAdapter = new listAdapter_entidad(this, R.layout.row_entidades, l);

        new JSONParse().execute();

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_datos_alimentos, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private class JSONParse extends AsyncTask<String, String, JSONArray> {
        private ProgressDialog pDialog;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(Datos_Alimento.this);
            pDialog.setMessage("Cargando ...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }
        @Override
        protected JSONArray doInBackground(String... args) {
            JSONParser jParser = new JSONParser();
            // Getting JSON from URL
            JSONArray json = jParser.getJSONFromUrl(url);
            return json;
        }
        @Override
        protected void onPostExecute(JSONArray json) {
            pDialog.dismiss();
            try {
                l.clear();
                for (int i=0; i<json.length(); i++){
                    jsonObj = json.getJSONObject(i);
                    l.add(new Entidad(jsonObj.getInt("ID_entidad"), jsonObj.getString("nombre"), jsonObj.getString("direccion")));
                }
                lista.setAdapter(customAdapter);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
