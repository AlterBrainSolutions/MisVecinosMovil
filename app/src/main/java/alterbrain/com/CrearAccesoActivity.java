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

import java.security.SecureRandom;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import alterbrain.com.app.Constantes;
import alterbrain.com.ui.AcpAccesoDialogFragment;
import alterbrain.com.ui.BrrAccesoDialogFragment;

public class CrearAccesoActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    private static SecureRandom random = new SecureRandom();
    private static final String CHARACTER_SET="0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    private Spinner spnTipo, spnTiempo;
    String tipo = "Habitual", tiempo="Ocasional";
    ImageView ivFecha;
    TextView tvFecha;
    Button btnAceptar, btnBorrar;
    EditText etComentario, etNombreInv;
    String nombreInv, comentario, casa, fechaVis, fechaReg, codAcce, URL = "https://missvecinos.com.mx/android/crearacceso2.php";
    int idFracc, idUsu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_acceso);
        ivFecha = findViewById(R.id.imageViewFechaAcc);
        tvFecha = findViewById(R.id.textViewFechaAcc);
        etNombreInv = findViewById(R.id.editTextNombreAcc);
        etComentario = findViewById(R.id.editTextComentarioAcc);

        btnAceptar = findViewById(R.id.buttonAceptarAcc);
        btnBorrar = findViewById(R.id.buttonBorrarAcc);

        Calendar cal = Calendar.getInstance();

        int anio = cal.get(Calendar.YEAR);
        int mes = cal.get(Calendar.MONTH);
        int dia = cal.get(Calendar.DAY_OF_MONTH);
        fechaReg = anio + "-" + (mes+1) + "-"+ dia;

        btnAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //casa = Constantes.NOM_USR;
                idFracc= Constantes.IDFRACC_USR;
                idUsu = Constantes.ID_USR;
                nombreInv = etNombreInv.getText().toString().trim();
                comentario = etComentario.getText().toString().trim();

                Toast.makeText(CrearAccesoActivity.this, ""+idFracc +"-"+ idUsu, Toast.LENGTH_SHORT).show();

                if (!fechaVis.equals("") && !tipo.equals("") && !nombreInv.isEmpty()){
                    Constantes.NOMBRE_ACCE = nombreInv;
                    Constantes.FECHA_ACCE = fechaVis;
                    Constantes.COMENTARIO_ACCE = comentario;
                    Constantes.TIPO_ACCE = tipo;
                    codAcce = getRandomString(16);
                    Constantes.COD_ACCE = codAcce;

                    //AcpAccesoDialogFragment dialog = new AcpAccesoDialogFragment();
                    //dialog.show(getSupportFragmentManager(), "AcpAccesoDialogFragment");

                    StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            /*si el texto de respuesta es correcto, crearemos
                             * un objeto de intenciony lanzar una actividad de éxito con esa intencion*/
                            if (response.equals("success")) {
                                Toast.makeText(CrearAccesoActivity.this, "¡Registrado exitosamente!", Toast.LENGTH_SHORT).show();
                                btnAceptar.setClickable(false);
                                AcpAccesoDialogFragment dialog = new AcpAccesoDialogFragment();
                                dialog.show(getSupportFragmentManager(), "AcpAccesoDialogFragment");
                                //finish();
                            } else if (response.equals("failure")) {
                                Toast.makeText(CrearAccesoActivity.this, "¡Ocurrió un error!", Toast.LENGTH_SHORT).show();

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
                            //data.put("usuario", casa);
                            data.put("idFracc", ""+idFracc);
                            data.put("idUsu", ""+idUsu);
                            data.put("tiempo", ""+tiempo);
                            data.put("nombreinv", nombreInv);
                            data.put("fechaReg", fechaReg);
                            data.put("fechaVis", fechaVis);
                            data.put("tipo", tipo);
                            data.put("comentarios", comentario);
                            data.put("codigo", codAcce);
                            return data;
                        }
                    };
                    //crear instancia de RQ (cola de solicitudes)
                    RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                    requestQueue.add(stringRequest);
                }else{
                    etNombreInv.setError("Complete los campos");
                    etNombreInv.requestFocus();
                }

            }
        });
        btnBorrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nombreInv = etNombreInv.getText().toString();
                comentario = etComentario.getText().toString();

                if (!nombreInv.isEmpty()){
                    Constantes.NOMBRE_ACCE = nombreInv;
                    Constantes.FECHA_ACCE = fechaVis;
                    Constantes.COMENTARIO_ACCE = comentario;
                    Constantes.TIPO_ACCE = tipo;
                    BrrAccesoDialogFragment dialog = new BrrAccesoDialogFragment();
                    dialog.show(getSupportFragmentManager(), "BrrAccesoDialogFragment");
                }else{
                    etNombreInv.setError("Complete los campos");
                    etNombreInv.requestFocus();
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
    // Create a random alphanumeric string
    private static String getRandomString(int len) {
        StringBuffer buff = new StringBuffer(len);
        for(int i=0;i<len;i++) {
            int offset = random.nextInt(CHARACTER_SET.length());
            buff.append(CHARACTER_SET.substring(offset,offset+1));
        }
        return buff.toString();
    }

    public void abrirCalendario(View view){
        Calendar cal = Calendar.getInstance();

        int anio = cal.get(Calendar.YEAR);
        int mes = cal.get(Calendar.MONTH);
        int dia = cal.get(Calendar.DAY_OF_MONTH);

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            DatePickerDialog dpd = new DatePickerDialog(CrearAccesoActivity.this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    //fecha = dayOfMonth + "/" + (month +1) + "/" + year;
                    fechaVis = year + "-" + (month +1) + "-" + dayOfMonth;
                    tvFecha.setText(fechaVis);
                }
            }, anio,mes,dia);
            dpd.show();
        }
    }
    private void SpinnerComponentes(){
        ArrayAdapter<CharSequence> reservaAdapter;

        reservaAdapter = ArrayAdapter.createFromResource(this, R.array.tipoacc, android.R.layout.simple_spinner_item);

        spnTipo = findViewById(R.id.spinnerTipoAcc);
        spnTipo.setAdapter(reservaAdapter);

        spnTipo.setOnItemSelectedListener(this);

        ArrayAdapter<CharSequence> tiempoAdapter;
        tiempoAdapter = ArrayAdapter.createFromResource(this, R.array.tiempoacc, android.R.layout.simple_spinner_item);

        spnTiempo = findViewById(R.id.spinnerTiempoAcc);
        spnTiempo.setAdapter(tiempoAdapter);

        spnTiempo.setOnItemSelectedListener(this);


    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (parent.getId()){
            case R.id.spinnerTipoAcc:
                //if (position !=0){
                tipo = parent.getItemAtPosition(position).toString();
                /*}else{
                    tipo = "";
                }*/
                break;
            case R.id.spinnerTiempoAcc:
                tiempo = parent.getItemAtPosition(position).toString();
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}