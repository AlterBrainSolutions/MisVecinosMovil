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

public class ConsultaAccesoQrActivity extends AppCompatActivity implements Response.Listener<JSONObject>, Response.ErrorListener{

    String codigo;
    RequestQueue rq;
    JsonRequest jr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta_acceso);

        Bundle extras = getIntent().getExtras();
        codigo = extras.getString("codigoAcc");

        rq = Volley.newRequestQueue(this);

        consultaAcc();
    }

    private void consultaAcc() {
        String url ="https://missvecinos.com.mx/android/consultaAccQr.php?codigo="+codigo;

        jr = new JsonObjectRequest(Request.Method.GET,url,null, this, this);
        rq.add(jr);
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Toast.makeText(this, "No se encontró el Qr \t (Qr no valido) " +error.toString(), Toast.LENGTH_LONG).show();
        //aqui iria un finish();
    }

    @Override
    public void onResponse(JSONObject response) {
        Acceso acceso = new Acceso();
        Toast.makeText(this, "Se ha encontrado el Qr ", Toast.LENGTH_SHORT).show();

        JSONArray jsonArray = response.optJSONArray("datos");
        JSONObject jsonObject = null;

        try {
            jsonObject = jsonArray.getJSONObject(0);
            acceso.setIdAcceso(jsonObject.optInt("idAcceso"));
            acceso.setIdAccesoUsuario(jsonObject.optInt("idAccesoUsuario"));
            acceso.setTiempo(jsonObject.optString("tiempo"));
            acceso.setNombre(jsonObject.optString("nombre"));
            acceso.setFechaVisit(jsonObject.optString("fechaVist"));
            acceso.setFechaRegs(jsonObject.optString("fechaRegs"));
            acceso.setHoraEntrada(jsonObject.optString("horaEntrada"));
            acceso.setHoraSalida(jsonObject.optString("horaSalida"));
            acceso.setTipoVisitante(jsonObject.optString("tipoVisitante"));
            acceso.setComentarios(jsonObject.optString("comentarios"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        Intent i = new Intent(this, UpdateAccesoQrActivity.class);
        i.putExtra("idAcceso",acceso.getIdAcceso());
        i.putExtra("idAccesoUsuario",acceso.getIdAccesoUsuario());
        i.putExtra("tiempo", acceso.getTiempo());
        i.putExtra("nombre", acceso.getNombre());
        i.putExtra("fechaRegs", acceso.getFechaRegs());
        i.putExtra("fechaVist", acceso.getFechaVisit());
        i.putExtra("horaEntrada", acceso.getHoraEntrada());
        i.putExtra("horaSalida", acceso.getHoraSalida());
        i.putExtra("tipoVisitante", acceso.getTipoVisitante());
        i.putExtra("comentarios", acceso.getComentarios());
        startActivity(i);
        finish();
    }
}