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
import alterbrain.com.MainActivity5;
import alterbrain.com.R;
import alterbrain.com.app.Constantes;

public class ConsultaImgFrUsuActivity extends AppCompatActivity implements Response.Listener<JSONObject>, Response.ErrorListener{

    RequestQueue rq;
    JsonRequest jr;
    String nomimg ="", nomfrc = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta_img_fr_usu);

        rq = Volley.newRequestQueue(this);

        consultaImgFrUsu();
    }

    private void consultaImgFrUsu() {
        String url ="https://missvecinos.com.mx/android/consultaimgfrcusr.php?idFraccUsu="+ Constantes.IDFRACC_USR;

        jr = new JsonObjectRequest(Request.Method.GET,url,null, this, this);
        rq.add(jr);
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Toast.makeText(this, "No se encontró img de fracc " +error.toString(), Toast.LENGTH_LONG).show();
    }

    @Override
    public void onResponse(JSONObject response) {
        Toast.makeText(this, "Se ha encontrado img de fracc " +Constantes.IDFRACC_USR, Toast.LENGTH_SHORT).show();

        JSONArray jsonArray = response.optJSONArray("datos");
        JSONObject jsonObject = null;

        try {
            jsonObject = jsonArray.getJSONObject(0);

            nomimg = jsonObject.optString("imagen");
            nomfrc = jsonObject.optString("nombreFracc");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Intent i = new Intent(this, MainActivity1.class);
        Constantes.IMG_FRACC = nomimg;
        Constantes.NOM_FRACC = nomfrc;

        startActivity(i);
        finish();
    }
}