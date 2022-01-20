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

public class ConsultaNumCasaQrActivity extends AppCompatActivity implements Response.Listener<JSONObject>, Response.ErrorListener{

    String  tiempo, nombre, fechaRegs, fechaVist, horaEntrada, horaSalida, tipoVisitante, comentarios;
    int idUsr, idAcceso, numeroCasa;
    RequestQueue rq;
    JsonRequest jr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta_num_casa_qr);

        Bundle extras = getIntent().getExtras();
        idAcceso = extras.getInt("idAcceso");
        idUsr = extras.getInt("idAccesoUsuario");
        tiempo = extras.getString("tiempo");
        nombre = extras.getString("nombre");
        fechaRegs = extras.getString("fechaRegs");
        fechaVist = extras.getString("fechaVist");
        horaEntrada = extras.getString("horaEntrada");
        horaSalida = extras.getString("horaSalida");
        tipoVisitante = extras.getString("tipoVisitante");
        comentarios = extras.getString("comentarios");

        rq = Volley.newRequestQueue(this);

        consultaNumCasa();
    }

    private void consultaNumCasa() {
        String url ="https://missvecinos.com.mx/android/consultaNumCasaQr.php?idUsr="+idUsr;

        jr = new JsonObjectRequest(Request.Method.GET,url,null, this, this);
        rq.add(jr);
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Toast.makeText(this, "No se encontró el numero de Casa " +error.toString(), Toast.LENGTH_LONG).show();
    }

    @Override
    public void onResponse(JSONObject response) {
        //Acceso acceso = new Acceso();
        Toast.makeText(this, "Se ha encontrado el numero de Casa ", Toast.LENGTH_SHORT).show();

        JSONArray jsonArray = response.optJSONArray("datos");
        JSONObject jsonObject = null;

        try {
            jsonObject = jsonArray.getJSONObject(0);
            numeroCasa = jsonObject.optInt("numeroCasa");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        Intent i = new Intent(this, UpdateAccesoQrActivity2.class);
        i.putExtra("numeroCasa", numeroCasa);
        i.putExtra("idAcceso",idAcceso);
        i.putExtra("idAccesoUsuario",idUsr);
        i.putExtra("tiempo", tiempo);
        i.putExtra("nombre", nombre);
        i.putExtra("fechaRegs", fechaRegs);
        i.putExtra("fechaVist", fechaVist);
        i.putExtra("horaEntrada", horaEntrada);
        i.putExtra("horaSalida", horaSalida);
        i.putExtra("tipoVisitante", tipoVisitante);
        i.putExtra("comentarios", comentarios);
        startActivity(i);
        finish();
    }
}