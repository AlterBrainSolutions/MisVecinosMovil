package alterbrain.com;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
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

public class TransparenciaD7Activity extends AppCompatActivity {

    LinearLayout layout_list;
    TextView tvIngresoTotDet, tvIngresoNetoDet;
    int mes = 7;
    private int usuario = Constantes.ID_USR;
    private String URL_corriente = "https://missvecinos.com.mx/android/transparenciaConsulta.php?usuario=" + usuario + "&mes=" + mes;
    private RequestQueue mQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transparencia_d7);

        layout_list = findViewById(R.id.layout_list7Detail);
        tvIngresoTotDet = findViewById(R.id.tvIngresoTotal7Detail);
        tvIngresoNetoDet = findViewById(R.id.tvIngresoNeto7Detail);

        mQueue = Volley.newRequestQueue(TransparenciaD7Activity.this);
        jsonParse2();
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

                            tvIngresoTotDet.setText(String.valueOf(cantidad) + " MN");

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

                                editText1.setText(total);
                                editText1.setFocusable(false);

                                editText2.setText(concepto);
                                editText2.setFocusable(false);

                                layout_list.addView(abonosView);

                                View abonosView2 = getLayoutInflater().inflate(R.layout.row_detalle, null, false);

                                EditText editText1D = (EditText)abonosView2.findViewById(R.id.etEgresoMontoDetail);

                                EditText editText2D = (EditText)abonosView2.findViewById(R.id.etEgresoNombreDetail);

                                editText1D.setText(total);
                                editText1D.setFocusable(false);

                                editText2D.setText(concepto);
                                editText2D.setFocusable(false);

                                layout_list.addView(abonosView2);

                            }

                            tvIngresoNetoDet.setText((cantidad - auxTotal) + " MN");

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