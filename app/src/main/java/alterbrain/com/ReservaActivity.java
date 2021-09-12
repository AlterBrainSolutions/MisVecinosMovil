package alterbrain.com;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
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

public class ReservaActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    private Spinner spnReserva;
    String reserva = "Cancha deportiva";
    ImageView ivFecha;
    TextView tvFecha;
    String fecha;
    Button btnAceptar;
    EditText etDescripcion;
    String titulo, descrip, casa, URL = "https://missvecinos.com.mx/android/crearreserva.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reserva);
        ivFecha = findViewById(R.id.imageViewFechaReserva);
        tvFecha = findViewById(R.id.textViewFechaReserva);
        etDescripcion = findViewById(R.id.editTextDescripResr);

        btnAceptar = findViewById(R.id.buttonEnviarResr);

        btnAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                casa = Constantes.NOM_USR;
                titulo = "Reserva: "+reserva;
                descrip = etDescripcion.getText().toString().trim();

                if (!casa.equals("") && !titulo.equals("") && !descrip.isEmpty()){
                    StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            /*si el texto de respuesta es correcto, crearemos
                             * un objeto de intenciony lanzar una actividad de éxito con esa intencion*/
                            if (response.equals("success")) {
                                Toast.makeText(ReservaActivity.this, "¡Registrado exitosamente!", Toast.LENGTH_SHORT).show();
                                btnAceptar.setClickable(false);
                                finish();
                            } else if (response.equals("failure")) {
                                Toast.makeText(ReservaActivity.this, "¡Ocurrió un error!", Toast.LENGTH_SHORT).show();

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
                            data.put("descripcion", descrip);
                            return data;
                        }
                    };
                    //crear instancia de RQ (cola de solicitudes)
                    RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                    requestQueue.add(stringRequest);
                }
            }
        });

        ivFecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abrirCalendario(v);
            }
        });

        SpinnerComponentes();
    }
    private void SpinnerComponentes(){
        ArrayAdapter<CharSequence> reservaAdapter;

        reservaAdapter = ArrayAdapter.createFromResource(this, R.array.areas, android.R.layout.simple_spinner_item);

        spnReserva = findViewById(R.id.spinnerReserva);
        spnReserva.setAdapter(reservaAdapter);

        spnReserva.setOnItemSelectedListener(this);
    }
    public void abrirCalendario(View view){
        Calendar cal = Calendar.getInstance();

        int anio = cal.get(Calendar.YEAR);
        int mes = cal.get(Calendar.MONTH);
        int dia = cal.get(Calendar.DAY_OF_MONTH);

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            DatePickerDialog dpd = new DatePickerDialog(ReservaActivity.this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    fecha = dayOfMonth + "/" + (month +1) + "/" + year;
                    tvFecha.setText(fecha);
                }
            }, anio,mes,dia);
            dpd.show();
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (parent.getId()){
            case R.id.spinnerReserva:
                //if (position !=0){
                    reserva = parent.getItemAtPosition(position).toString();
                //}else{
                    //reserva = "Cancha deportiva";
                //}
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}