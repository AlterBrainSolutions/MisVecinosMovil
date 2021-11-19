package alterbrain.com;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

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

public class Transparencia12Activity extends AppCompatActivity {

    LinearLayout layoutList;
    Button btn1, btn2;
    TextView tvTotalIngresos, tvIngresoNeto;
    int mes = 12;
    private int usuario = Constantes.ID_USR;
    private String URL_corriente = "https://missvecinos.com.mx/android/transparenciaConsulta.php?usuario=" + usuario + "&mes=" + mes;
    private RequestQueue mQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transparencia12);

        btn1 = findViewById(R.id.button12Back);
        btn2 = findViewById(R.id.button12Forw);
        /*buttont = findViewById(R.id.buttonDetalleTrn12);*/
        layoutList = findViewById(R.id.layout_list12);
        tvTotalIngresos = findViewById(R.id.tvTotalIngresos12);
        tvIngresoNeto = findViewById(R.id.tvIngresoNeto12);

        mQueue = Volley.newRequestQueue(Transparencia12Activity.this);
        jsonParse2();

        /*buttont.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Transparencia7Activity.this, TransparenciaD7Activity.class);
                startActivity(i);
            }
        });*/
        eventos();
    }

    private void eventos() {
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Transparencia12Activity.this, Transparencia11Activity.class);
                startActivity(i);
                finish();
            }
        });
        /*btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Transparencia8Activity.this, Transparencia9Activity.class);
                startActivity(i);
                finish();
            }
        });*/
    }

    private void jsonParse2() {

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, URL_corriente, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {

                            JSONArray resultados1 = response.getJSONArray("ingresos");

                            int tamRes1 = resultados1.length();
                            Float cantidad = 0f;

                            for (int i = 0; i < tamRes1; i++) {

                                JSONObject jsonObject = new JSONObject(resultados1.get(i).toString());

                                cantidad += Float.parseFloat(jsonObject.getString("cantidad"));

                                /*View abonosView = getLayoutInflater().inflate(R.layout.row_egresos, null, false);*/

                            }

                            /*"idEgresoFracc":"4","concepto":"xxx","descripcion":".","importe":"200.00","total":"200.00","imagen":""*/

                            tvTotalIngresos.setText(String.valueOf(cantidad) + " MN");

                            /*Toast.makeText(Transparencia7Activity.this,
                                    "Cantidad total: " + cantidad, Toast.LENGTH_SHORT).show();*/

                            JSONArray resultados2 = response.getJSONArray("egresos");

                            int tamRes2 = resultados2.length();
                            String concepto, imagen, total;
                            Float auxTotal = 0f;

                            for (int i = 0; i < tamRes2; i++) {

                                JSONObject jsonObject = new JSONObject(resultados2.get(i).toString());

                                concepto = jsonObject.getString("concepto");

                                total = jsonObject.getString("total");

                                auxTotal += Float.parseFloat(total);

                                /*imagen = jsonObject.getString("imagen");*/

                                View abonosView = getLayoutInflater().inflate(R.layout.row_egresos, null, false);

                                EditText editText1 = (EditText)abonosView.findViewById(R.id.etEgresoMonto);

                                EditText editText2 = (EditText)abonosView.findViewById(R.id.etEgresoNombre);

                                ImageView imageView = (ImageView)abonosView.findViewById(R.id.ivEgresoPDF);

                                editText1.setText(total);
                                editText1.setFocusable(false);

                                editText2.setText(concepto);
                                editText2.setFocusable(false);

                                layoutList.addView(abonosView);

                            }

                            tvIngresoNeto.setText((cantidad - auxTotal) + " MN");

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
    }

}