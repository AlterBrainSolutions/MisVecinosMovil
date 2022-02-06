package alterbrain.com;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import alterbrain.com.app.Constantes;
import alterbrain.com.model.Adeudos;
import alterbrain.com.ui.DetalleAdeActivity;
import alterbrain.com.ui.DetalleAdeActivity2;
import alterbrain.com.ui.MyCorrienteRecyclerViewAdapter;

public class AdeudosActivity extends AppCompatActivity {

    Button btnDetalleAde;
    TextView tvAdeudoUnMes,tvAdeudoMasDeUnMes, tvCorriente, tvNumeroCasas, tvNombreFracc;
    int alCorriente = Constantes.AL_CORRIENTE, dosMeses = Constantes.DOS_MESES, unMes = Constantes.UN_MES, numViviendas = 0;
    /*private int usuario = Constantes.ID_USR;*//* conAdeudo = 0, alCorriente = 0*//*;
    private String URL_corriente = "https://missvecinos.com.mx/android/adeudosConsulta.php?usuario=" + usuario;
    private RequestQueue mQueue;*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adeudos);
        btnDetalleAde = findViewById(R.id.buttonDetalleAde);
        tvAdeudoMasDeUnMes = findViewById(R.id.tvMasDeUnoRet);
        tvAdeudoUnMes = findViewById(R.id.tvUnMesRet);
        tvCorriente = findViewById(R.id.tvCorrienteConteo);
        tvNumeroCasas = findViewById(R.id.tvNumeroCasasAdeudos);
        tvNombreFracc = findViewById(R.id.tvnombreFraccAdeudos);

        tvCorriente.setText(String.valueOf(alCorriente));
        tvAdeudoUnMes.setText(String.valueOf(unMes));
        tvAdeudoMasDeUnMes.setText(String.valueOf(dosMeses));
        tvNombreFracc.setText("NÚMERO DE VIVIENDAS EN EL FRACCIONAMIENTO: " + Constantes.NOMBRE_FRACC);
        numViviendas = Constantes.AL_CORRIENTE+Constantes.DOS_MESES+Constantes.UN_MES;
        tvNumeroCasas.setText(String.valueOf(numViviendas));

        /*mQueue = Volley.newRequestQueue(AdeudosActivity.this);

        jsonParse2();*/

        if (Constantes.DETAIL_USR_VISIBLE == 2 && Constantes.ROL_USR == 2){
            btnDetalleAde.setEnabled(false);
        }else if(Constantes.ROL_USR == 1 || Constantes.ROL_USR == 3){
            btnDetalleAde.setEnabled(true);
        }

        btnDetalleAde.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(AdeudosActivity.this, DetalleAdeActivity.class);
                startActivity(i);
                /*Intent i = new Intent(AdeudosActivity.this, DetalleAdeActivity2.class);
                startActivity(i);*/
            }
        });

    }

    /*private void jsonParse2() {

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, URL_corriente, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {

                            JSONArray resultados2 = response.getJSONArray("user_vis");

                            int tamRes2 = resultados2.length(), visibilidad, rol_usr;

                            for (int i = 0; i < tamRes2; i++) {

                                JSONObject jsonObject = new JSONObject(resultados2.get(i).toString());

                                visibilidad = jsonObject.getInt("visibilidad");
                                rol_usr = jsonObject.getInt("idUsuarioRol");

                                Toast.makeText(AdeudosActivity.this,
                                        "Un mes: " + visibilidad + "dos meses: " +
                                                rol_usr, Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        mQueue.add(request);
    }*/
}