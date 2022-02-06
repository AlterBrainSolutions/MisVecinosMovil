package alterbrain.com;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

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

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;

import alterbrain.com.app.Constantes;

public class Transparencia11Activity extends AppCompatActivity {

    LinearLayout layoutList;
    Button btn1, btn2;
    TextView tvTotalIngresos, tvIngresoNeto, tvCasasCantidad, tvIngresosAntes;
    int mes = 11;
    float anterior, acumuladoTotal, ingresoNeto;
    private int usuario = Constantes.ID_USR;
    private String URL_corriente = "https://missvecinos.com.mx/android/transparenciaConsulta.php?usuario=" + usuario + "&mes=" + mes;
    private RequestQueue mQueue;
    BarChart barChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transparencia11);
        btn1 = findViewById(R.id.button11Back);
        btn2 = findViewById(R.id.button11Forw);
        /*buttont = findViewById(R.id.buttonDetalleTrn11);*/
        barChart = findViewById(R.id.pcBar11Transex);
        layoutList = findViewById(R.id.layout_list11);
        tvTotalIngresos = findViewById(R.id.tvTotalIngresos11);
        tvIngresoNeto = findViewById(R.id.tvIngresoNeto11);
        tvCasasCantidad = findViewById(R.id.tvCasasCantidad11);
        tvIngresosAntes = findViewById(R.id.tvIngresosAntes11);

        mQueue = Volley.newRequestQueue(Transparencia11Activity.this);
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
                Intent i = new Intent(Transparencia11Activity.this, Transparencia10Activity.class);
                startActivity(i);
                finish();
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Transparencia11Activity.this, Transparencia12Activity.class);
                startActivity(i);
                finish();
            }
        });
    }


    /*private void botonImagen() {
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Transparencia11Activity.this, Transparencia10Activity.class);
                startActivity(i);
                finish();
            }
        });
    }
    */

    private void jsonParse2() {

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, URL_corriente, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {

                            JSONArray resIngAnt = response.getJSONArray("ingresosAntes");
                            Float ingresosAntes = 0f;

                            JSONObject jsonObject2 = new JSONObject(resIngAnt.get(0).toString());

                            ingresosAntes = Float.parseFloat(jsonObject2.getString("total"));

                            /*TODO -------------------------------------------------------------------*/
                            JSONArray resEgrAnt = response.getJSONArray("egresosAntes");
                            Float egresosAntes = 0f;

                            JSONObject jsonObject3 = new JSONObject(resEgrAnt.get(0).toString());

                            egresosAntes = Float.parseFloat(jsonObject3.getString("total"));

                            anterior = ingresosAntes - egresosAntes;

                            /*TODO -------------------------------------------------------------------*/

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

                                /*imagen = jsonObject.getString("imagen");*/

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

                                button.setText("https://la-joya.missvecinos.com.mx/admin/" + imagen.trim());

                                button.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Intent i = new Intent(Transparencia11Activity.this, MuestraFotoTransparencia.class);
                                        Constantes.URL_IMG_TRP = (String) button.getText();
                                        startActivity(i);
                                        /*Toast.makeText(Transparencia11Activity.this,
                                                button.getText(), Toast.LENGTH_SHORT).show();*/
                                    }
                                });

                                /*Toast.makeText(Transparencia11Activity.this,
                                        "Cantidad total: " + bitmap.toString(), Toast.LENGTH_SHORT).show();*/

                                editText1.setText(total + " MN");
                                editText1.setFocusable(false);

                                editText2.setText(concepto);
                                editText2.setFocusable(false);

                                layoutList.addView(abonosView);

                                aux++;
                                contGrafica++;
                            }

                            ingresoNeto = cantidad - auxTotal;
                            acumuladoTotal = ingresoNeto + anterior;

                            tvIngresoNeto.setText((ingresoNeto) + " MN");
                            tvIngresosAntes.setText(String.valueOf(acumuladoTotal)+ " MN");

                            barEgresos.add(new BarEntry(contGrafica, ingresoNeto));

                            BarDataSet barDataSet = new BarDataSet(barEgresos, "Transparencia Noviembre");
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