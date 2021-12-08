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

public class ConsultaAutnActivity extends AppCompatActivity implements Response.Listener<JSONObject>, Response.ErrorListener{

    int idAutentico;
    RequestQueue rq;
    JsonRequest jr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta_autn);

        Bundle extras = getIntent().getExtras();
        idAutentico = extras.getInt("valorAutentico");

        //Toast.makeText(this, "ID= "+idAutentico, Toast.LENGTH_SHORT).show();

        rq = Volley.newRequestQueue(this);

        consultaAutn();
    }

    private void consultaAutn() {
        String url ="https://missvecinos.com.mx/android/consultaautn.php?idAutn="+idAutentico;

        jr = new JsonObjectRequest(Request.Method.GET,url,null, this, this);
        rq.add(jr);
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Toast.makeText(this, "No se encontró al autorizado " +error.toString(), Toast.LENGTH_LONG).show();
    }

    @Override
    public void onResponse(JSONObject response) {
        Noticia3 noticia3 = new Noticia3();
        Autentico autentico = new Autentico();
        //Toast.makeText(this, "Se ha encontrado al invitado " +idAutentico, Toast.LENGTH_SHORT).show();
        Toast.makeText(this, "Se ha encontrado al invitado ", Toast.LENGTH_SHORT).show();

        JSONArray jsonArray = response.optJSONArray("datos");
        JSONObject jsonObject = null;

        try {
            jsonObject = jsonArray.getJSONObject(0);
            autentico.setTiempo(jsonObject.optString("tiempo"));
            autentico.setNombre(jsonObject.optString("nombre"));
            autentico.setFechaVis(jsonObject.optString("fechaVist"));
            autentico.setTipoVisitante(jsonObject.optString("tipoVisitante"));
            autentico.setComentario(jsonObject.optString("comentarios"));
            autentico.setCodigo(jsonObject.optString("codigo"));

        } catch (JSONException e) {
            e.printStackTrace();
        }

        Intent i = new Intent(this, AcpAcceso2Activity.class);
        i.putExtra("idA",idAutentico);
        i.putExtra("tiempo", autentico.getTiempo());
        i.putExtra("nombre", autentico.getNombre());
        i.putExtra("fecha", autentico.getFechaVis());
        i.putExtra("tipoVisitante", autentico.getTipoVisitante());
        i.putExtra("comentarios", autentico.getComentario());
        i.putExtra("codigo", autentico.getCodigo());
        startActivity(i);
        finish();
    }
}