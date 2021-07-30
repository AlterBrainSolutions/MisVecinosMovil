package alterbrain.com;

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

import java.util.Calendar;

import alterbrain.com.app.Constantes;
import alterbrain.com.ui.AcpAccesoDialogFragment;
import alterbrain.com.ui.BrrAccesoDialogFragment;

public class CrearAccesoActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    private Spinner spnTipo;
    String tipo;
    ImageView ivFecha;
    TextView tvFecha;
    String fecha;
    Button btnAceptar, btnBorrar;
    EditText etComentario, etNombre;
    String nombre, comentario;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_acceso);
        ivFecha = findViewById(R.id.imageViewFechaAcc);
        tvFecha = findViewById(R.id.textViewFechaAcc);
        etNombre = findViewById(R.id.editTextNombreAcc);
        etComentario = findViewById(R.id.editTextComentarioAcc);


        /*nombre = Constantes.NOMBRE_ACCE;
        fecha = Constantes.FECHA_ACCE;
        comentario = Constantes.COMENTARIO_ACCE;
        tipo = Constantes.TIPO_ACCE;*/

        btnAceptar = findViewById(R.id.buttonAceptarAcc);
        btnBorrar = findViewById(R.id.buttonBorrarAcc);

        btnAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nombre = etNombre.getText().toString();
                comentario = etComentario.getText().toString();

                if (!nombre.isEmpty()){
                    Constantes.NOMBRE_ACCE = nombre;
                    Constantes.FECHA_ACCE = fecha;
                    Constantes.COMENTARIO_ACCE = comentario;
                    Constantes.TIPO_ACCE = tipo;
                    AcpAccesoDialogFragment dialog = new AcpAccesoDialogFragment();
                    dialog.show(getSupportFragmentManager(), "AcpAccesoDialogFragment");
                }else{
                    etNombre.setError("Complete los campos");
                    etNombre.requestFocus();
                }

            }
        });
        btnBorrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nombre = etNombre.getText().toString();
                comentario = etComentario.getText().toString();

                if (!nombre.isEmpty()){
                    Constantes.NOMBRE_ACCE = nombre;
                    Constantes.FECHA_ACCE = fecha;
                    Constantes.COMENTARIO_ACCE = comentario;
                    Constantes.TIPO_ACCE = tipo;
                    BrrAccesoDialogFragment dialog = new BrrAccesoDialogFragment();
                    dialog.show(getSupportFragmentManager(), "BrrAccesoDialogFragment");
                }else{
                    etNombre.setError("Complete los campos");
                    etNombre.requestFocus();
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
    public void abrirCalendario(View view){
        Calendar cal = Calendar.getInstance();

        int anio = cal.get(Calendar.YEAR);
        int mes = cal.get(Calendar.MONTH);
        int dia = cal.get(Calendar.DAY_OF_MONTH);

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            DatePickerDialog dpd = new DatePickerDialog(CrearAccesoActivity.this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    fecha = dayOfMonth + "/" + (month +1) + "/" + year;
                    tvFecha.setText(fecha);
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
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}