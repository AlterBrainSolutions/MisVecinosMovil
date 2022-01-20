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

import alterbrain.com.MainActivity5;
import alterbrain.com.MainActivity7;
import alterbrain.com.R;
import alterbrain.com.app.Constantes;

public class ConsultaUsrVigActivity extends AppCompatActivity implements Response.Listener<JSONObject>, Response.ErrorListener{

    String passUsr, nombreUsr;
    RequestQueue rq;
    JsonRequest jr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta_usr_vig);

        nombreUsr = passUsr = "";
        Bundle extras = getIntent().getExtras();
        nombreUsr = extras.getString("nombreUsr");
        passUsr = extras.getString("passUsr");

        rq = Volley.newRequestQueue(this);

        consultaUsrVig();
    }

    private void consultaUsrVig() {
        String url ="https://missvecinos.com.mx/android/consultausrvig.php?user="+nombreUsr+"&ps="+passUsr;

        jr = new JsonObjectRequest(Request.Method.GET,url,null, this, this);
        rq.add(jr);
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Toast.makeText(this, "No se encontró al usuario " +error.toString(), Toast.LENGTH_LONG).show();
    }

    @Override
    public void onResponse(JSONObject response) {
        UsuVig usuVig = new UsuVig();
        Toast.makeText(this, "Se ha encontrado al usuario " +nombreUsr, Toast.LENGTH_SHORT).show();

        JSONArray jsonArray = response.optJSONArray("datos");
        JSONObject jsonObject = null;

        try {
            jsonObject = jsonArray.getJSONObject(0);

            usuVig.setIdVigilante(jsonObject.optInt("idVigilante"));
            usuVig.setIdFraccDVig(jsonObject.optInt("idVigilanteFracc"));
            usuVig.setNombre(jsonObject.optString("nombre"));
            usuVig.setImagen(jsonObject.optString("imagen"));
            usuVig.setTelefono(jsonObject.optDouble("telefono"));
            usuVig.setHorario(jsonObject.optString("horario"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Intent i = new Intent(this, ConsultaImgFActivity.class);

        Constantes.NOM = nombreUsr;
        Constantes.PAS = passUsr;

        Constantes.ID_VIG = usuVig.getIdVigilante();
        Constantes.ID_VIGFRACC = usuVig.getIdFraccDVig();
        Constantes.NOM_VIG = usuVig.getNombre();
        Constantes.FOTO_VIG = usuVig.getImagen();
        Constantes.TEL_VIG = usuVig.getTelefono();
        Constantes.HORAR_VIG = usuVig.getHorario();
        Constantes.USU_VIG = nombreUsr;
        Constantes.PAS_VIG = passUsr;

        startActivity(i);
        finish();
    }
}