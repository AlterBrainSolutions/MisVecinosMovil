package alterbrain.com.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import alterbrain.com.R;

public class AcpAcceso2Activity extends AppCompatActivity {

    String nombre, fecha, tipo, comenta;
    TextView tvDatos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acp_acceso2);

        Bundle extras = getIntent().getExtras();
        nombre = extras.getString("nombre");
        fecha = extras.getString("fecha");
        tipo = extras.getString("tipoVisitante");
        comenta = extras.getString("comentarios");

        tvDatos = findViewById(R.id.textViewDatosAcc);

        tvDatos.setText(nombre+"\n"+fecha+"\n"+tipo+"\n"+comenta);

    }
}