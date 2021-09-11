package alterbrain.com;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import alterbrain.com.app.Constantes;

public class AnuncioActivity extends AppCompatActivity {

    ImageView ivFecha;
    TextView tvFecha;
    String fecha;
    Button btnAceptar;
    EditText etDescripcion, etTitulo;
    String titulo, comentario, casa, URL = "https://missvecinos.com.mx/android/crearanuncio.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anuncio);
        ivFecha = findViewById(R.id.imageViewFechaAnn);
        tvFecha = findViewById(R.id.textViewFechaAnn);
        etTitulo = findViewById(R.id.editTextTituloAnn);
        etDescripcion = findViewById(R.id.editTextDescripcionAnn);

        btnAceptar = findViewById(R.id.buttonAceptarAnn);

        btnAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                casa = Constantes.NOM_USR;
                titulo = etTitulo.getText().toString().trim();
                comentario = etDescripcion.getText().toString().trim();

                if (!casa.equals("") && !titulo.equals("") && !comentario.isEmpty()){
                    StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            /*si el texto de respuesta es correcto, crearemos
                             * un objeto de intenciony lanzar una actividad de éxito con esa intencion*/
                            if (response.equals("success")) {
                                Toast.makeText(AnuncioActivity.this, "¡Registrado exitosamente!", Toast.LENGTH_SHORT).show();
                                btnAceptar.setClickable(false);
                                finish();
                            } else if (response.equals("failure")) {
                                Toast.makeText(AnuncioActivity.this, "¡Ocurrió un error!", Toast.LENGTH_SHORT).show();

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
                            data.put("solicitante", casa);
                            data.put("titulo", titulo);
                            data.put("fecha", fecha);
                            data.put("descripcion", comentario);
                            return data;
                        }
                    };
                    //crear instancia de RQ (cola de solicitudes)
                    RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                    requestQueue.add(stringRequest);
                }else{
                    etTitulo.setError("Complete los campos");
                    etTitulo.requestFocus();
                }

            }
        });

        ivFecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abrirCalendario(v);
            }
        });
    }
    public void abrirCalendario(View view){
        Calendar cal = Calendar.getInstance();

        int anio = cal.get(Calendar.YEAR);
        int mes = cal.get(Calendar.MONTH);
        int dia = cal.get(Calendar.DAY_OF_MONTH);

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            DatePickerDialog dpd = new DatePickerDialog(AnuncioActivity.this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    fecha = dayOfMonth + "/" + (month +1) + "/" + year;
                    tvFecha.setText(fecha);
                }
            }, anio,mes,dia);
            dpd.show();
        }
    }
}