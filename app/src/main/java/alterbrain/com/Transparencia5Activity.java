package alterbrain.com;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
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
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import alterbrain.com.app.Constantes;

public class Transparencia5Activity extends AppCompatActivity {

    LinearLayout layoutList;
    Button btn1, btn2;
    TextView tvTotalIngresos, tvIngresoNeto, tvCasasCantidad;
    int mes = 5;
    private int usuario = Constantes.ID_USR;
    private String URL_corriente = "https://missvecinos.com.mx/android/transparenciaConsulta.php?usuario=" + usuario + "&mes=" + mes;
    private RequestQueue mQueue;
    BarChart barChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transparencia5);

        btn1 = findViewById(R.id.button5Back);
        btn2 = findViewById(R.id.button5Forw);
        /*buttont = findViewById(R.id.buttonDetalleTrn5);*/
        barChart = findViewById(R.id.pcBar5Transex);
        layoutList = findViewById(R.id.layout_list5);
        tvTotalIngresos = findViewById(R.id.tvTotalIngresos5);
        tvIngresoNeto = findViewById(R.id.tvIngresoNeto5);
        tvCasasCantidad = findViewById(R.id.tvCasasCantidad5);

        mQueue = Volley.newRequestQueue(Transparencia5Activity.this);
        jsonParse2();

        /*buttont.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Transparencia5Activity.this, TransparenciaD5Activity.class);
                startActivity(i);
            }
        });*/
        eventos();
    }
    private void eventos(){
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Transparencia5Activity.this, Transparencia4Activity.class);
                startActivity(i);
                finish();
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Transparencia5Activity.this, Transparencia6Activity.class);
                startActivity(i);
                finish();
            }
        });
    }

    private void jsonParse2() {

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, URL_corriente, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {

                            ArrayList<BarEntry> barEgresos = new ArrayList<>();
                            JSONArray resultados1 = response.getJSONArray("ingresos");

                            int tamRes1 = resultados1.length(), contGrafica = 1;
                            Float cantidad = 0f, auxCantidad = 0f;

                            for (int i = 0; i < tamRes1; i++) {

                                JSONObject jsonObject = new JSONObject(resultados1.get(i).toString());

                                cantidad += Float.parseFloat(jsonObject.getString("cantidad"));

                                if(i == 0){
                                    auxCantidad = cantidad;
                                }

                                /*View abonosView = getLayoutInflater().inflate(R.layout.row_egresos, null, false);*/

                            }

                            barEgresos.add(new BarEntry(contGrafica, cantidad));
                            contGrafica++;
                            /*"idEgresoFracc":"4","concepto":"xxx","descripcion":".","importe":"200.00","total":"200.00","imagen":""*/

                            tvTotalIngresos.setText(String.valueOf(cantidad) + " MN");
                            tvCasasCantidad.setText(auxCantidad + " X " + tamRes1);

                            /*Toast.makeText(Transparencia7Activity.this,
                                    "Cantidad total: " + cantidad, Toast.LENGTH_SHORT).show();*/

                            JSONArray resultados2 = response.getJSONArray("egresos");

                            int tamRes2 = resultados2.length(),aux = 1;
                            String concepto, imagen, total;
                            Float auxTotal = 0f;

                            for (int i = 0; i < tamRes2; i++) {

                                JSONObject jsonObject = new JSONObject(resultados2.get(i).toString());

                                concepto = jsonObject.getString("concepto");

                                total = jsonObject.getString("total");

                                auxTotal += Float.parseFloat(total);

                                imagen = jsonObject.getString("imagen");

                                barEgresos.add(new BarEntry(contGrafica, Float.parseFloat(total)));

                                View abonosView = getLayoutInflater().inflate(R.layout.row_egresos, null, false);

                                EditText editText1 = (EditText)abonosView.findViewById(R.id.etEgresoMonto);

                                EditText editText2 = (EditText)abonosView.findViewById(R.id.etEgresoNombre);

                                Button button = (Button) abonosView.findViewById(R.id.ivEgresoPDF);

                                if(imagen.isEmpty() || imagen.equals("0")){
                                    button.setVisibility(View.INVISIBLE);
                                }else{
                                    button.setVisibility(View.VISIBLE);
                                }

                                button.setText("https://la-joya.missvecinos.com.mx/admin/" + imagen);

                                button.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Intent i = new Intent(Transparencia5Activity.this, MuestraFotoTransparencia.class);
                                        Constantes.URL_IMG_TRP = (String) button.getText();
                                        startActivity(i);
                                        /*Toast.makeText(Transparencia11Activity.this,
                                                button.getText(), Toast.LENGTH_SHORT).show();*/
                                    }
                                });

                                editText1.setText(total + " MN");
                                editText1.setFocusable(false);

                                editText2.setText(concepto);
                                editText2.setFocusable(false);

                                layoutList.addView(abonosView);

                                aux++;
                                contGrafica++;
                            }

                            tvIngresoNeto.setText((cantidad - auxTotal) + " MN");
                            barEgresos.add(new BarEntry(contGrafica, cantidad - auxTotal));

                            BarDataSet barDataSet = new BarDataSet(barEgresos, "Tranparencia Mayo");
                            barDataSet.setColors(ColorTemplate.MATERIAL_COLORS);
                            barDataSet.setValueTextColor(Color.BLACK);
                            barDataSet.setValueTextSize(16f);

                            BarData barData = new BarData(barDataSet);

                            barChart.setFitBars(true);
                            barChart.setData(barData);
                            barChart.getDescription().setText("");
                            barChart.animateY(2000);

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