package alterbrain.com;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Calendar;

public class ReservaActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    private Spinner spnReserva;
    String reserva;
    ImageView ivFecha;
    TextView tvFecha;
    String fecha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reserva);
        ivFecha = findViewById(R.id.imageViewFechaReserva);
        tvFecha = findViewById(R.id.textViewFechaReserva);

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
                if (position !=0){
                    reserva = parent.getItemAtPosition(position).toString();
                }else{
                    reserva = "";
                }
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}