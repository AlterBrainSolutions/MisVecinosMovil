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
import alterbrain.com.R;
import alterbrain.com.app.Constantes;
import alterbrain.com.model.Noticia3;

public class ConsultaUsrActivity extends AppCompatActivity implements Response.Listener<JSONObject>, Response.ErrorListener{

    String emailUsr,passUsr, nombreUsr;
    RequestQueue rq;
    JsonRequest jr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta_usr);

        nombreUsr = emailUsr = passUsr = "";
        Bundle extras = getIntent().getExtras();
        //emailUsr = extras.getString("emailUsr");
        nombreUsr = extras.getString("nombreUsr");
        passUsr = extras.getString("passUsr");

        rq = Volley.newRequestQueue(this);

        consultaUsr();
    }

    private void consultaUsr() {
        String url ="https://missvecinos.com.mx/android/consultausrPrueba.php?nm="+nombreUsr+"&ps="+passUsr;

        jr = new JsonObjectRequest(Request.Method.GET,url,null, this, this);
        rq.add(jr);
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Toast.makeText(this, "No se encontró al usuario " +error.toString(), Toast.LENGTH_LONG).show();
    }

    @Override
    public void onResponse(JSONObject response) {
        //Noticia3 noticia3 = new Noticia3();
        Usuario usuario = new Usuario();
        Toast.makeText(this, "Se ha encontrado al usuario " +nombreUsr, Toast.LENGTH_SHORT).show();

        JSONArray jsonArray = response.optJSONArray("datos");
        JSONObject jsonObject = null;

        try {
            jsonObject = jsonArray.getJSONObject(0);
            //noticia3.setTitulo(jsonObject.optString("tituloNoticia"));
            usuario.setId(jsonObject.optInt("idUsuario"));
            usuario.setIdFraccUsu(jsonObject.optInt("idFraccionamientoUsuarios"));
            usuario.setEmail(jsonObject.optString("mailUsuario"));
            usuario.setCodigo(jsonObject.optString("codigoAcceso"));
            usuario.setNumCasa(jsonObject.optString("numeroCasa"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        Intent i = new Intent(this, ConsultaImgFrUsuActivity.class);

        Constantes.NOM = nombreUsr;
        Constantes.PAS = passUsr;

        Constantes.ID_USR = usuario.getId();
        Constantes.IDFRACC_USR = usuario.getIdFraccUsu();
        Constantes.NOM_USR = nombreUsr;
        Constantes.EMAIL_USR = usuario.getEmail();
        Constantes.PAS_USR = passUsr;
        Constantes.COD_USR = usuario.getCodigo();
        Constantes.NUM_CSA = usuario.getNumCasa();

        startActivity(i);
        finish();
    }
}