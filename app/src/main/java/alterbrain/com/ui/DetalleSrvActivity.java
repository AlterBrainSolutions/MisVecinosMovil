package alterbrain.com.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import alterbrain.com.MapsActivity;
import alterbrain.com.R;
import alterbrain.com.app.Constantes;

public class DetalleSrvActivity extends AppCompatActivity {

    String casa, fecha, descripcion, presupuesto, imagen;
    TextView tvCasa, tvFecha, tvDescrip, tvPresupuesto;
    ImageView imageViewServi;
    Button btnAceptar, btnDeclinar, btnUbicacion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_srv);

        Bundle extras = getIntent().getExtras();
        casa = extras.getString("casa");
        fecha = extras.getString("fecha");
        descripcion = extras.getString("descripcion");
        presupuesto = extras.getString("presupuesto");
        imagen = extras.getString("imagen");
        //Toast.makeText(this, "Casa= "+casa, Toast.LENGTH_SHORT).show();

        Constantes.CASA_SRV = casa;
        Constantes.FECHA_SRV = fecha;
        Constantes.PRESUPUESTO_SRV = presupuesto;

        tvCasa = findViewById(R.id.textViewCasaDtSrv);
        tvFecha = findViewById(R.id.textViewFechaDtSrv);
        tvDescrip = findViewById(R.id.textViewDescripcionDtSrv);
        tvPresupuesto = findViewById(R.id.textViewPresupuestoDtSrv);
        imageViewServi = findViewById(R.id.imageViewDtSrv);
        btnUbicacion = findViewById(R.id.buttonUbicacionDtSrv);
        btnAceptar = findViewById(R.id.buttonAceptarDtSrv);
        btnDeclinar = findViewById(R.id.buttonDeclinarDtSrv);

        tvCasa.setText(casa);
        tvFecha.setText("Fecha de servicio: "+fecha);
        tvDescrip.setText("Descripción: "+descripcion);
        tvPresupuesto.setText("Presupuesto estimado: "+presupuesto);

        /*Glide.with(this)
                .load(imagen)
                .centerCrop()
                .into(imageViewServi);*/
        btnUbicacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(DetalleSrvActivity.this, MapsActivity.class);
                startActivity(i);
            }
        });
        btnAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AcpServicioDialogFragment dialog = new AcpServicioDialogFragment();
                dialog.show(getSupportFragmentManager(), "AcpServicioDialogFragment");
            }
        });
        btnDeclinar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DclServicioDialogFragment dialog = new DclServicioDialogFragment();
                dialog.show(getSupportFragmentManager(), "DclServicioDialogFragment");
            }
        });
    }
}