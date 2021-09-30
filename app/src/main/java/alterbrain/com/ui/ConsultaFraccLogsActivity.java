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
import alterbrain.com.app.Constantes;
import alterbrain.com.model.Noticia3;

public class ConsultaFraccLogsActivity extends AppCompatActivity implements Response.Listener<JSONObject>, Response.ErrorListener{

    int idFraccLogs;
    String nomFracc;
    RequestQueue rq;
    JsonRequest jr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta_fracc_logs);

        Bundle extras = getIntent().getExtras();
        idFraccLogs = extras.getInt("valorFraccL");
        nomFracc = extras.getString("nomFracc");

        //Toast.makeText(this, "ID= "+idFraccLogs, Toast.LENGTH_SHORT).show();

        rq = Volley.newRequestQueue(this);

        consultaFracL();
    }

    private void consultaFracL() {
        String url ="https://missvecinos.com.mx/android/consultafracclogs.php?idFracc="+idFraccLogs;

        jr = new JsonObjectRequest(Request.Method.GET,url,null, this, this);
        rq.add(jr);
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Toast.makeText(this, "No se encontró la noticia " +error.toString(), Toast.LENGTH_LONG).show();
    }

    @Override
    public void onResponse(JSONObject response) {
        //Noticia3 noticia3 = new Noticia3();
        Fraccionamiento fraccionamiento = new Fraccionamiento();
        //TODO Comentar el Toast
        Toast.makeText(this, "Se ha encontrado al fraccionamiento " +idFraccLogs, Toast.LENGTH_SHORT).show();

        JSONArray jsonArray = response.optJSONArray("datos");
        JSONObject jsonObject = null;

        try {
            jsonObject = jsonArray.getJSONObject(0);
            fraccionamiento.setLinkFracc(jsonObject.optString("linkFracc"));
            fraccionamiento.setDireccion(jsonObject.optString("direccion"));
            fraccionamiento.setLatitud(jsonObject.optDouble("latitud"));
            fraccionamiento.setLongitud(jsonObject.optDouble("longitud"));
            fraccionamiento.setNoCasas(jsonObject.optInt("noCasas"));
            fraccionamiento.setContPet(jsonObject.optInt("contPet"));
            fraccionamiento.setContAlum(jsonObject.optInt("contAlum"));
            fraccionamiento.setCodigoContenedor(jsonObject.optString("codigoContenedor"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        Intent i = new Intent(this, UbicaLogActivity.class);
        Constantes.ID_FRACC = idFraccLogs;
        Constantes.NOM_FRACC = nomFracc;
        Constantes.DIREC_FRACC = fraccionamiento.getDireccion();
        Constantes.LAT_FRACC = fraccionamiento.getLatitud();
        Constantes.LONG_FRACC = fraccionamiento.getLongitud();
        Constantes.NUMCASAS_FRACC = fraccionamiento.getNoCasas();
        Constantes.CONTPET_FRACC = fraccionamiento.getContPet();
        Constantes.CONTALUMN_FRACC = fraccionamiento.getContAlum();
        Constantes.CODCONTEN_FRACC = fraccionamiento.getCodigoContenedor();
        startActivity(i);
        finish();
    }
}