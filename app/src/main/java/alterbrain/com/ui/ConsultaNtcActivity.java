package alterbrain.com.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

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
import alterbrain.com.model.Noticia3;

public class ConsultaNtcActivity extends AppCompatActivity implements Response.Listener<JSONObject>, Response.ErrorListener{

    int idNoticia;
    RequestQueue rq;
    JsonRequest jr;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta_ntc);

        Bundle extras = getIntent().getExtras();
        idNoticia = extras.getInt("valorNoticia");

        Toast.makeText(this, "ID= "+idNoticia, Toast.LENGTH_SHORT).show();

        rq = Volley.newRequestQueue(this);

        consultaNtc();
        
    }

    private void consultaNtc() {
        String url ="https://missvecinos.com.mx/android/consultantc.php?idntc="+idNoticia;

        jr = new JsonObjectRequest(Request.Method.GET,url,null, this, this);
        rq.add(jr);
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Toast.makeText(this, "No se encontró la noticia " +error.toString(), Toast.LENGTH_LONG).show();
    }

    @Override
    public void onResponse(JSONObject response) {
        Servicio servicio = new Servicio();
        Noticia3 noticia3 = new Noticia3();
        Toast.makeText(this, "Se ha encontrado la noticia " +idNoticia, Toast.LENGTH_SHORT).show();

        JSONArray jsonArray = response.optJSONArray("datos");
        JSONObject jsonObject = null;

        try {
            jsonObject = jsonArray.getJSONObject(0);
            noticia3.setTitulo(jsonObject.optString("tituloNoticia"));
            noticia3.setDescripcion(jsonObject.optString("descripcion"));
            noticia3.setFecha(jsonObject.optString("fecha"));
            noticia3.setImagen(jsonObject.optString("imagen"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        Intent i = new Intent(this, DetalleNtcActivity.class);
        i.putExtra("titulo", noticia3.getTitulo());
        i.putExtra("descripcion", noticia3.getDescripcion());
        i.putExtra("fecha", noticia3.getFecha());
        i.putExtra("imagen", noticia3.getImagen());
        startActivity(i);
        finish();
    }
}