package alterbrain.com.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import alterbrain.com.R;

public class ConsultaServActivity extends AppCompatActivity implements Response.Listener<JSONObject>, Response.ErrorListener{

    int idServicio;
    RequestQueue rq;
    JsonRequest jr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta_serv);

        Bundle extras = getIntent().getExtras();
        idServicio = extras.getInt("valorServicio");

        Toast.makeText(this, "ID= "+idServicio, Toast.LENGTH_SHORT).show();

        rq = Volley.newRequestQueue(this);
        consultaSrv();
    }

    private void consultaSrv() {
        String url ="https://missvecinos.com.mx/android/consultasrv2.php?idsrv="+idServicio;

        //String url ="https://192.168.0.32/login/sesion.php?user="+cajaUser.getText().toString()+
        //"&pwd="+cajaPawd.getText().toString();
        jr = new JsonObjectRequest(Request.Method.GET,url,null, this, this);
        rq.add(jr);
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Toast.makeText(this, "No se encontró el servicio " +error.toString(), Toast.LENGTH_LONG).show();
    }

    @Override
    public void onResponse(JSONObject response) {
        Servicio servicio = new Servicio();
        Toast.makeText(this, "Se ha encontrado al servicio " +idServicio, Toast.LENGTH_SHORT).show();

        JSONArray jsonArray = response.optJSONArray("datos");
        JSONObject jsonObject = null;

        try {
            jsonObject = jsonArray.getJSONObject(0);
            servicio.setCasa(jsonObject.optString("casa"));
            servicio.setFecha(jsonObject.optString("fecha"));
            servicio.setDescripcion(jsonObject.optString("descripcion"));
            servicio.setPresupuesto(jsonObject.optString("presupuesto"));
            servicio.setImagen(jsonObject.optString("imagen"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        Intent i = new Intent(this, DetalleSrvActivity.class);
        i.putExtra("casa", servicio.getCasa());
        i.putExtra("fecha", servicio.getFecha());
        i.putExtra("descripcion", servicio.getDescripcion());
        i.putExtra("presupuesto", servicio.getPresupuesto());
        i.putExtra("imagen", servicio.getImagen());
        startActivity(i);
        finish();
    }
}