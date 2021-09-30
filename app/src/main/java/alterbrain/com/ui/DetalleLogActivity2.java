package alterbrain.com.ui;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import alterbrain.com.MainActivity7;
import alterbrain.com.R;
import alterbrain.com.ScanActivity;
import alterbrain.com.ServiciosActivity3;
import alterbrain.com.Transparencia6Activity;
import alterbrain.com.Transparencia7Activity;
import alterbrain.com.app.Constantes;

public class DetalleLogActivity2 extends AppCompatActivity {

    private static final int PET = 3000, ALUM = 5000;
    private PieChart pcCircular1, pcCircular2;
    static int graficaPet = 0, graficaAl = 0;
    static float porcPet = 0, porcAl = 0;
    TextView tvTituloDet;
    Button btnreiniciarPet, btnreiniciarAl, btnSal;
    String URL = "https://missvecinos.com.mx/android/updateconts.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_log2);

        pcCircular1 = findViewById(R.id.pcCircular1);
        pcCircular2 = findViewById(R.id.pcCircular2);
        tvTituloDet = findViewById(R.id.textViewTituloDetLogs);
        btnreiniciarPet = findViewById(R.id.buttonReset1);
        //btnreiniciarPet.setEnabled(false);
        btnSal = findViewById(R.id.buttonSalirDetLog);

        cargaDatos();

        btnSal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(DetalleLogActivity2.this, UbicaLogActivity.class);
                startActivity(i);
                finish();
            }
        });

        btnreiniciarPet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntentIntegrator integrator = new IntentIntegrator(DetalleLogActivity2.this);
                integrator.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES);
                integrator.setPrompt("Lector - AGUA");
                integrator.setCameraId(0);
                integrator.setBeepEnabled(true);
                integrator.setBarcodeImageEnabled(true);
                integrator.initiateScan();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);

        if (result != null){
            if (result.getContents() == null){
                Toast.makeText(this, "Lectura cancelada", Toast.LENGTH_LONG).show();
            }else {
                Toast.makeText(this, result.getContents(), Toast.LENGTH_LONG).show();
                //txtResultado.setText(result.getContents());
                //TODO comparar el codigo leido con el de Constantes, si coinciden, actualizar los datos, e ir al menu inicio
                if (Constantes.CODCONTEN_FRACC.equals(result.getContents())){
                    //Toast.makeText(this, "iguales", Toast.LENGTH_LONG).show();
                    StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            /*si el texto de respuesta es correcto, crearemos
                             * un objeto de intencion y lanzar una actividad de éxito con esa intencion*/
                            if (response.equals("success")) {
                                Toast.makeText(DetalleLogActivity2.this, "¡Reinicio exitoso!", Toast.LENGTH_SHORT).show();
                                /*btnreiniciarPet.setClickable(false);
                                finish();*/
                                Intent i = new Intent(DetalleLogActivity2.this, MainActivity7.class);
                                startActivity(i);
                                finish();
                            } else if (response.equals("failure")) {
                                Toast.makeText(DetalleLogActivity2.this, "Ocurrió un error!", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            //crear un detector de errores para manejar los errores de manera adecuada
                            Toast.makeText(getApplicationContext(), error.toString().trim(), Toast.LENGTH_SHORT).show();
                        }
                    }){
                        @Nullable
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            Map<String, String> data = new HashMap<>();
                            data.put("idfracc", ""+Constantes.ID_FRACC);
                            return data;
                        }
                    };
                    //crear instancia de RQ (cola de solicitudes)
                    RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                    requestQueue.add(stringRequest);
                }else{
                    Toast.makeText(this, "Codigo no valido", Toast.LENGTH_LONG).show();
                }
            }
        }else{
            super.onActivityResult(requestCode, resultCode, data);
        }

    }

    private void cargaDatos() {
        tvTituloDet.setText("FRACCIONAMIENTO " + Constantes.NOM_FRACC.toUpperCase());

        graficaPet = Constantes.CONTPET_FRACC;
        graficaAl = Constantes.CONTALUMN_FRACC;

        porcPet = (float)((graficaPet * 100)/PET);
        porcAl = (float)((graficaAl*100)/ALUM);
        float resto1 = (float)100 - porcPet;
        float resto2 = (float)100 - porcAl;

        ArrayList<PieEntry> entries = new ArrayList<PieEntry>();
        entries.add(new PieEntry(porcPet, "Usado"));
        entries.add(new PieEntry(resto1, "Libre"));

        PieDataSet pieDataSet = new PieDataSet(entries, "");
        pieDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        pieDataSet.setValueTextColor(Color.BLACK);
        pieDataSet.setValueTextSize(16f);

        PieData pieData = new PieData(pieDataSet);

        pcCircular1.getDescription().setEnabled(false);
        pcCircular1.setCenterText("CONTENEDOR PET");
        pcCircular1.setData(pieData);
        pcCircular1.invalidate();
        pcCircular1.notifyDataSetChanged();

        /* -----------------------------------COMIENZA LA SEGUNDA GRAFICA---------------------------------*/
        ArrayList<PieEntry> entries2 = new ArrayList<PieEntry>();
        entries2.add(new PieEntry(porcAl, "Usado"));
        entries2.add(new PieEntry(resto2, "Libre"));

        PieDataSet pieDataSet2 = new PieDataSet(entries2, "");
        pieDataSet2.setColors(ColorTemplate.COLORFUL_COLORS);
        pieDataSet2.setValueTextColor(Color.BLACK);
        pieDataSet2.setValueTextSize(16f);

        PieData pieData2 = new PieData(pieDataSet2);

        pcCircular2.getDescription().setEnabled(false);
        pcCircular2.setCenterText("CONTENEDOR AL");
        pcCircular2.setData(pieData2);
        pcCircular2.invalidate();
        pcCircular2.notifyDataSetChanged();
    }
}