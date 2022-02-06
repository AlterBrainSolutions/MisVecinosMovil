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
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Currency;

import alterbrain.com.app.Constantes;

public class Transparencia11Activity extends AppCompatActivity {

    LinearLayout layoutList;
    Button btn1, btn2;
    TextView tvTotalIngresos, tvIngresoNeto, tvCasasCantidad, tvIngresosAntes, tvSaldoMesAnterior, tvResiduosMes;
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
        tvSaldoMesAnterior = findViewById(R.id.tvSaldoMesAnterior11);
        tvResiduosMes = findViewById(R.id.tvIngresoReciclaje11);

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
        if(Constantes.MES_ACT <= 11){
            btn2.setVisibility(View.INVISIBLE);
        }else{
            btn2.setVisibility(View.VISIBLE);
        }
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

                            NumberFormat numberFormat = NumberFormat.getCurrencyInstance();
                            numberFormat.setMaximumFractionDigits(2);
                            numberFormat.setCurrency(Currency.getInstance("MXN"));
                            String currencySymbol = numberFormat.format(0.00).replace("0.00", "");

                            /*TODO ---------------------INGRESOS ANTERIORES----------------------------------------------*/
                            JSONArray resIngAnt = response.getJSONArray("ingresosAntes");
                            Float ingresosAntes = 0f;

                            JSONObject jsonObject2 = new JSONObject(resIngAnt.get(0).toString());

                            ingresosAntes = Float.parseFloat(jsonObject2.getString("total"));

                            /*TODO ------------------------EGRESOS ANTERIORES-------------------------------------------*/
                            JSONArray resEgrAnt = response.getJSONArray("egresosAntes");
                            Float egresosAntes = 0f;

                            JSONObject jsonObject3 = new JSONObject(resEgrAnt.get(0).toString());

                            egresosAntes = Float.parseFloat(jsonObject3.getString("total"));

                            anterior = ingresosAntes - egresosAntes;

                            /*TODO ---------------------------INGRESOS----------------------------------------*/

                            JSONArray resultados1 = response.getJSONArray("ingresos");
                            ArrayList<BarEntry> barCantidad = new ArrayList<>();

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

                            /*TODO ------------------------RECICLAJE DEL MES-------------------------------------------*/

                            JSONArray  respReciclaje = response.getJSONArray("reciclaje");
                            float reciclaje = 0f;
                            JSONObject jsonObjRec = new JSONObject(respReciclaje.get(0).toString());
                            reciclaje = Float.parseFloat(jsonObjRec.getString("cantidad"));

                            /*TODO ------------------------PARA MOSTRAR DATOS DE GRAFICAS Y TEXTVIEWS-------------------------------------------*/

                            barCantidad.add(new BarEntry(contGrafica, new float[]{reciclaje, cantidad}));
                            /*BarDataSet barDataSet1 = new BarDataSet(barEgresos, "Cantidad");*/
                            contGrafica++;
                            /*"idEgresoFracc":"4","concepto":"xxx","descripcion":".","importe":"200.00","total":"200.00","imagen":""*/

                            tvTotalIngresos.setText(numberFormat.format(cantidad).replace(currencySymbol,
                                    currencySymbol + " "));
                            tvCasasCantidad.setText(numberFormat.format(auxCantidad).replace(currencySymbol,
                                    currencySymbol + " ") + " X " + tamRes1);
                            cantidad += reciclaje;
                            tvResiduosMes.setText(numberFormat.format(reciclaje).replace(currencySymbol,
                                    currencySymbol + " "));
                            /*Toast.makeText(Transparencia7Activity.this,
                                    "Cantidad total: " + cantidad, Toast.LENGTH_SHORT).show();*/

                            /*TODO --------------------------E G R E S O S-----------------------------------------*/
                            JSONArray resultados2 = response.getJSONArray("egresos");
                            ArrayList<BarEntry> barEgresos = new ArrayList<>();

                            int tamRes2 = resultados2.length(), aux = 1;
                            String concepto, imagen, total;
                            float auxTotal = 0f;/*TODO cambié el tipo de Float a float*/
                            float egresosArr[] = new float[tamRes2];
                            String labelsEg[] = new String[tamRes2];

                            for (int i = 0; i < tamRes2; i++) {

                                JSONObject jsonObject = new JSONObject(resultados2.get(i).toString());

                                concepto = jsonObject.getString("concepto");

                                total = jsonObject.getString("total");

                                auxTotal += Float.parseFloat(total);

                                egresosArr[i] = Float.parseFloat(total);
                                labelsEg[i] = "Eg. " + aux;
                                /*barEgresos.add(new BarEntry(contGrafica, egresosArr[i] = Float.parseFloat(total)));*/

                                /*BarDataSet barDataSet2 = new BarDataSet(barEgresos, "Egresos");*/
                                /*xAxisLabels.add(concepto);*/

                                imagen = jsonObject.getString("imagen");

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

                                editText1.setText(numberFormat.format(Float.parseFloat(total)).replace(currencySymbol,
                                        currencySymbol + " "));
                                editText1.setFocusable(false);

                                editText2.setText(concepto);
                                editText2.setFocusable(false);

                                layoutList.addView(abonosView);

                                aux++;
                                /*contGrafica++;*/
                            }

                            /*Arrays.sort(egresosArr);*//*TODO para cambiar ordenar el array de floats */
                            barEgresos.add(new BarEntry(contGrafica, egresosArr));
                            contGrafica++;

                            ArrayList<BarEntry> barIngresos = new ArrayList<>();
                            ingresoNeto = cantidad - auxTotal;
                            acumuladoTotal = ingresoNeto + anterior;

                            if(anterior >= 0){
                                tvSaldoMesAnterior.setText(numberFormat.format(anterior).replace(currencySymbol,
                                        currencySymbol + " "));
                            }else{
                                tvSaldoMesAnterior.setBackgroundColor(getResources().getColor(R.color.IngresosEgresosRojo));
                                tvSaldoMesAnterior.setText(numberFormat.format(anterior).replace(currencySymbol,
                                        currencySymbol + " "));
                            }
                            /*TODO -------------------------CALCULO INGRESO ANTERIOR------------------------------------------*/
                            tvIngresoNeto.setText(numberFormat.format(ingresoNeto).replace(currencySymbol,
                                    currencySymbol + " "));
                            tvIngresosAntes.setText(numberFormat.format(acumuladoTotal).replace(currencySymbol,
                                    currencySymbol + " "));

                            barIngresos.add(new BarEntry(contGrafica, acumuladoTotal));
                            /*BarDataSet barDataSet3 = new BarDataSet(barEgresos, "Ing. Neto");*/
                            /*xAxisLabels.add("Ingreso neto");*/

                            int [] colorsRed = {
                                    /* Color.parseColor("#FFEBEE"),
                                     Color.parseColor("#FFCDD2"),
                                     Color.parseColor("#EF9A9A"),
                                     Color.parseColor("#E57373"),*/
                                    Color.parseColor("#EF5350"),
                                    Color.parseColor("#F44336"),
                                    Color.parseColor("#E53935"),
                                    Color.parseColor("#D32F2F"),
                                    Color.parseColor("#C62828"),
                                    Color.parseColor("#B71C1C")
                                    /*Color.parseColor("#FF8A80"),
                                    Color.parseColor("#FF5252"),
                                    Color.parseColor("#FF1744"),
                                    Color.parseColor("#D50000")*/
                            };

                            int [] colorsGreen = {
                                    /* Color.parseColor("#FFEBEE"),
                                     Color.parseColor("#FFCDD2"),
                                     Color.parseColor("#EF9A9A"),
                                     Color.parseColor("#E57373"),*/
                                    Color.parseColor("#64DD17"),
                                    Color.parseColor("#B2FF59")
                                    /*Color.parseColor("#FF8A80"),
                                    Color.parseColor("#FF5252"),
                                    Color.parseColor("#FF1744"),
                                    Color.parseColor("#D50000")*/
                            };

                            /*TODO -----------------------------GRAFICAS--------------------------------------*/
                            /*TODO BARDATASET 1*/
                            BarDataSet barDataSet = new BarDataSet(barCantidad, "");
                            barDataSet.setColors(colorsGreen);
                            barDataSet.setValueTextSize(13f);
                            barDataSet.setValueTextColor(Color.BLACK);
                            barDataSet.setStackLabels(new String[]{"Rec.","Mant."});

                            /*TODO BARDATASET 2*/
                            BarDataSet barDataSet2 = new BarDataSet(barEgresos, "");
                            barDataSet2.setColors(colorsRed);
                            barDataSet2.setValueTextSize(13f);
                            barDataSet2.setValueTextColor(Color.BLACK);
                            barDataSet2.setStackLabels(labelsEg);

                            /*TODO BARDATASET 3*/
                            BarDataSet barDataSet3 = new BarDataSet(barIngresos, "Ingresos");
                            barDataSet3.setColors(Color.parseColor("#00C853"));
                            barDataSet3.setValueTextSize(13f);
                            barDataSet3.setValueTextColor(Color.BLACK);

                            BarData barData = new BarData(barDataSet, barDataSet2, barDataSet3);

                            barChart.setFitBars(true);
                            barChart.getXAxis().setDrawGridLines(false);
                            barChart.getXAxis().setEnabled(false);
                            barChart.getAxisLeft().setDrawGridLines(false);
                            barChart.getAxisRight().setDrawGridLines(false);
                            barChart.getAxisRight().setEnabled(false);
                            barChart.setDrawValueAboveBar(false);
                            /*barChart.getXAxis().setValueFormatter(new IndexAxisValueFormatter(xAxisLabels));*/
                            barChart.getXAxis().setTextSize(2f);
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