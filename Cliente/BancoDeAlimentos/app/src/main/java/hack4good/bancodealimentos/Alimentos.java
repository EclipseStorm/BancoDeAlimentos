package hack4good.bancodealimentos;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class Alimentos extends ActionBarActivity {

    public ArrayList<Alimento> l  = new ArrayList<Alimento>();;

    String host = "192.168.199.249";
    public String url = "http://" + host + "/h4g/api.php?q=alimentos";

    /*JSON*/
    JSONArray jsonArray = null;
    JSONObject jsonObj = null;

    public ListView lista;
    public ListAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entidades);

        lista = (ListView) findViewById(R.id.listView);

        customAdapter = new listAdapter_alimento(this, R.layout.row_alimentos, l);

        new JSONParse().execute();

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Alimento e = l.get(position);

                Intent myIntent = new Intent(view.getContext(), Datos_Alimento.class);
                myIntent.putExtra("id", e.getId());
                myIntent.putExtra("nombre", e.getName());
                startActivity(myIntent);

            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_alimentos, menu);
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
            pDialog = new ProgressDialog(Alimentos.this);
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
                    l.add(new Alimento(jsonObj.getInt("ID_alimento"), jsonObj.getString("nombre")));
                }
                lista.setAdapter(customAdapter);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}