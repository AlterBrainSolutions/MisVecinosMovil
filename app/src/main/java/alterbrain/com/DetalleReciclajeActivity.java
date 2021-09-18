package alterbrain.com;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.gson.JsonArray;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import alterbrain.com.app.Constantes;
import alterbrain.com.model.Noticia3;
import alterbrain.com.model.detalleReciclaje;
import alterbrain.com.ui.MyNoticiaRecyclerViewAdapter3;

public class DetalleReciclajeActivity extends AppCompatActivity {

    private static final int usuario = Constantes.ID_USR;
    private static final String URL_detalles = "https://missvecinos.com.mx/android/detallereciclaje.php?nm=" + usuario;
    private RequestQueue mQueue;
    private PieChart pcCircular1, pcCircular2;
    TextView tvcontPet, tvcontAl, tvcontPetmes, tvcontAlmes, tvcontAnio, tvcontFracc;
    private int contPetDia = 0, contAlumDia = 0, contPetMes = 0, contAlumMes = 0, contAnio = 0, contFracc = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_reciclaje);

        pcCircular1 = findViewById(R.id.pcCircular1);
        pcCircular2 = findViewById(R.id.pcCircular2);
        tvcontPet = findViewById(R.id.textViewcontPET);
        tvcontAl = findViewById(R.id.textViewcontAL);
        tvcontPetmes = findViewById(R.id.textViewcontPETmes);
        tvcontAlmes = findViewById(R.id.textViewcontALmes);
        tvcontAnio = findViewById(R.id.textViewcontAnio);
        tvcontFracc = findViewById(R.id.textViewcontFracc);

        mQueue = Volley.newRequestQueue(this);

        /*jsonParse();*/
        jsonParse2();

        ArrayList<PieEntry> entries = new ArrayList<PieEntry>();
        entries.add(new PieEntry(60.0f, "Usado"));

        PieDataSet pieDataSet = new PieDataSet(entries, "");
        pieDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        pieDataSet.setValueTextColor(Color.BLACK);
        pieDataSet.setValueTextSize(16f);

        PieData pieData = new PieData(pieDataSet);

        pcCircular1.setData(pieData);
        pcCircular1.getDescription().setEnabled(false);
        pcCircular1.setCenterText("CONTENEDOR PET");
        pcCircular1.animate();
        /* -----------------------------------COMIENZA LA SEGUNDA GRAFICA---------------------------------*/
        ArrayList<PieEntry> entries2 = new ArrayList<PieEntry>();
        entries2.add(new PieEntry(80.0f, "Usado"));

        PieDataSet pieDataSet2 = new PieDataSet(entries2, "");
        pieDataSet2.setColors(ColorTemplate.COLORFUL_COLORS);
        pieDataSet2.setValueTextColor(Color.BLACK);
        pieDataSet2.setValueTextSize(16f);

        PieData pieData2 = new PieData(pieDataSet2);

        pcCircular2.setData(pieData2);
        pcCircular2.getDescription().setEnabled(false);
        pcCircular2.setCenterText("CONTENEDOR AL");
        pcCircular2.animate();

        /*Toast.makeText(DetalleReciclajeActivity.this, String.valueOf(Constantes.ID_USR), Toast.LENGTH_SHORT).show();*/
    }

    private void jsonParse2() {
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, URL_detalles, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray porDia = response.getJSONArray("por dia");

                            int tamDia = porDia.length();

                            for (int i = 0; i < tamDia; i++) {

                                JSONObject jsonObject = new JSONObject(porDia.get(i).toString());
                                String numeroCasa = jsonObject.getString("numeroCasa");
                                contPetDia += jsonObject.getInt("cantidadPet");
                                contAlumDia += jsonObject.getInt("cantidadAlum");

                                tvcontPet.setText(String.valueOf(contPetDia));
                                tvcontAl.setText(String.valueOf(contAlumDia));

                                /*Toast.makeText(DetalleReciclajeActivity.this, String.valueOf(contPetDia)+ "y "+
                                        String.valueOf(contAlumDia) + " y " +
                                        numeroCasa, Toast.LENGTH_SHORT).show();*/
                            }


                            JSONArray porMes = response.getJSONArray("por mes");

                            int tamMes = porMes.length();

                            for (int i = 0; i < tamMes; i++) {

                                JSONObject jsonObject = new JSONObject(porMes.get(i).toString());
                                String numeroCasa = jsonObject.getString("numeroCasa");
                                contPetMes += jsonObject.getInt("cantidadPet");
                                contAlumMes += jsonObject.getInt("cantidadAlum");

                                tvcontPetmes.setText(String.valueOf(contPetMes));
                                tvcontAlmes.setText(String.valueOf(contAlumMes));

                                /*Toast.makeText(DetalleReciclajeActivity.this, String.valueOf(contPetMes)+ "y "+
                                        String.valueOf(contAlumMes) + " y " +
                                        numeroCasa, Toast.LENGTH_SHORT).show();*/

                            }

                            JSONArray porAnio = response.getJSONArray("por anio");

                            int tamAnio = porAnio.length();

                            for (int i = 0; i < tamAnio; i++) {

                                JSONObject jsonObject = new JSONObject(porAnio.get(i).toString());
                                String numeroCasa = jsonObject.getString("numeroCasa");
                                contAnio += jsonObject.getInt("cantidadPet");
                                contAnio += jsonObject.getInt("cantidadAlum");

                                tvcontAnio.setText(String.valueOf(contAnio));

                                /*Toast.makeText(DetalleReciclajeActivity.this, String.valueOf(contAnio)+ "y "+
                                        " y " + numeroCasa, Toast.LENGTH_SHORT).show();*/

                            }

                            JSONArray porFracc = response.getJSONArray("por fracc");

                            int tamFracc = porFracc.length();

                            for (int i = 0; i < tamFracc; i++) {
                                JSONObject jsonObject = new JSONObject(porFracc.get(i).toString());
                                String numeroCasa = jsonObject.getString("numeroCasa");
                                contFracc += jsonObject.getInt("cantidadPet");
                                contFracc += jsonObject.getInt("cantidadAlum");

                                tvcontFracc.setText(String.valueOf(contFracc));

                                /*Toast.makeText(DetalleReciclajeActivity.this, String.valueOf(contFracc)+ "y "+
                                        numeroCasa, Toast.LENGTH_SHORT).show();*/

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
    }
}