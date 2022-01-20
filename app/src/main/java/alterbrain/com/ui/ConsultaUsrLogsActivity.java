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

import alterbrain.com.MainActivity1;
import alterbrain.com.MainActivity7;
import alterbrain.com.R;
import alterbrain.com.app.Constantes;

public class ConsultaUsrLogsActivity extends AppCompatActivity implements Response.Listener<JSONObject>, Response.ErrorListener{

    String emailUsr,passUsr, nombreUsr;
    RequestQueue rq;
    JsonRequest jr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta_usr_logs);

        nombreUsr = emailUsr = passUsr = "";
        Bundle extras = getIntent().getExtras();
        //emailUsr = extras.getString("emailUsr");
        nombreUsr = extras.getString("nombreUsr");
        passUsr = extras.getString("passUsr");

        //Toast.makeText(this, "Buscando: "+nombreUsr, Toast.LENGTH_SHORT).show();

        rq = Volley.newRequestQueue(this);

        consultaUsrLogs();
    }

    private void consultaUsrLogs() {
        String url ="https://missvecinos.com.mx/android/consultausr3.php?nm="+nombreUsr+"&ps="+passUsr;

        jr = new JsonObjectRequest(Request.Method.GET,url,null, this, this);
        rq.add(jr);
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Toast.makeText(this, "No se encontró al usuario " +error.toString(), Toast.LENGTH_LONG).show();
    }

    @Override
    public void onResponse(JSONObject response) {

        //Usuario usuario = new Usuario();
        UsuLogis usuLogis = new UsuLogis();
        Toast.makeText(this, "Se ha encontrado al usuario " +nombreUsr, Toast.LENGTH_SHORT).show();

        JSONArray jsonArray = response.optJSONArray("datos");
        JSONObject jsonObject = null;

        try {
            jsonObject = jsonArray.getJSONObject(0);

            usuLogis.setId(jsonObject.optInt("IDUsuario"));
            usuLogis.setNombre(jsonObject.optString("Nombre"));
            usuLogis.setApellido(jsonObject.optString("primerApellido"));
            usuLogis.setTipo(jsonObject.optString("tipoUsuario"));
            usuLogis.setUnidad(jsonObject.optInt("numeroUnidad"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Intent i = new Intent(this, MainActivity7.class);

        Constantes.NOM = nombreUsr;
        Constantes.PAS = passUsr;

        Constantes.ID_USULOGIS = usuLogis.getId();
        Constantes.NOM_USULOGIS = usuLogis.getNombre();
        Constantes.APELL_USULOGIS = usuLogis.getApellido();
        Constantes.USU_USULOGIS = nombreUsr;
        Constantes.PAS_USULOGIS = passUsr;
        Constantes.TIPO_USULOGIS = usuLogis.getTipo();
        Constantes.UNIDAD_USULOGIS = usuLogis.getUnidad();

        startActivity(i);
        finish();

    }
}