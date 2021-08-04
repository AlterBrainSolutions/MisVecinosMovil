package alterbrain.com;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.net.Uri;
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

public class ServiciosActivity2 extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    private Spinner spnServicios;
    String serv;
    ImageView ivFecha;
    TextView tvFecha, tvStatus;
    String fecha, descripcion, presupuesto, casa, URL = "https://missvecinos.com.mx/android/serviciocrp.php";
    ImageView ivAgregaImagen;
    EditText etDescripcion, etPresupuesto;
    Button btnGuardar;

    public Uri imageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_servicios2);
        ivFecha = findViewById(R.id.imageViewFechaServicio);
        tvFecha = findViewById(R.id.textViewFechaServicio);
        ivAgregaImagen = findViewById(R.id.imageViewSubeImgSrv);
        etDescripcion = findViewById(R.id.editTextDescripServicio);
        etPresupuesto = findViewById(R.id.editTextPresupuestoServ);
        btnGuardar = findViewById(R.id.buttonGuardarServ);
        tvStatus = findViewById(R.id.textViewTituloSolServ);

        eventos();
        SpinnerComponentes();

    }

    private void eventos() {
        ivFecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abrirCalendario(v);
            }
        });
        ivAgregaImagen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                choosePicture();
            }
        });

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                casa = Constantes.NOM_USR;
                serv = "Carpintero";
                descripcion = etDescripcion.getText().toString().trim();
                presupuesto = etPresupuesto.getText().toString().trim();

                if (!casa.equals("") && !serv.equals("") && !fecha.equals("") && !descripcion.equals("") && !presupuesto.equals("")){
                    StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            /*si el texto de respuesta es correcto, crearemos
                             * un objeto de intenciony lanzar una actividad de éxito con esa intencion*/
                            if (response.equals("success")) {
                                tvStatus.setText("Registrado exitosamente");
                                btnGuardar.setClickable(false);
                            } else if (response.equals("failure")) {
                                tvStatus.setText("Ocurrió un error!");
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
                            data.put("casa", casa);
                            data.put("serv", serv);
                            data.put("fecha", fecha);
                            data.put("descripcion", descripcion);
                            data.put("presupuesto", presupuesto);
                            return data;
                        }
                    };
                    //crear instancia de RQ (cola de solicitudes)
                    RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                    requestQueue.add(stringRequest);
                }
            }
        });
    }
    private void choosePicture() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, 1);

    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK && data != null && data.getData() != null) {
            imageUri = data.getData();
            ivAgregaImagen.setImageURI(imageUri);
            //uploadPicture();
        }
    }

    private void SpinnerComponentes(){
        ArrayAdapter<CharSequence> serviciosAdapter;

        serviciosAdapter = ArrayAdapter.createFromResource(this, R.array.servicios, android.R.layout.simple_spinner_item);

        spnServicios = findViewById(R.id.spinnerServicios);
        spnServicios.setAdapter(serviciosAdapter);

        spnServicios.setOnItemSelectedListener(this);
    }

    public void abrirCalendario(View view){
        Calendar cal = Calendar.getInstance();

        int anio = cal.get(Calendar.YEAR);
        int mes = cal.get(Calendar.MONTH);
        int dia = cal.get(Calendar.DAY_OF_MONTH);

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            DatePickerDialog dpd = new DatePickerDialog(ServiciosActivity2.this, new DatePickerDialog.OnDateSetListener() {
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
            case R.id.spinnerServicios:
                //if (position !=0){
                serv = parent.getItemAtPosition(position).toString();
                //}else{
                //  serv = "";
                //}
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}