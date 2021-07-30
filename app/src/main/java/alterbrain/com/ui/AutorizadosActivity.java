package alterbrain.com.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import alterbrain.com.AccesosActivity;
import alterbrain.com.MainActivity1;
import alterbrain.com.R;
import alterbrain.com.ScanActivity;

public class AutorizadosActivity extends AppCompatActivity {

    int idCasa;
    String casaNum;
    View fragment;

    Button btnScan, btnScanner;
    EditText txtResultado;
    TextView tvTitulo, tvNombre, tvNombre2, tvNombre3, tvNombre4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_autorizados);

        /*fragment = findViewById(R.id.fragment5);
        fragment.findViewById(R.id.fragment5);*/


        Bundle extras = getIntent().getExtras();
        idCasa = extras.getInt("valorCasa");
        //casaNum = extras.getString("casaId");
        Toast.makeText(this, "Casa: "+idCasa, Toast.LENGTH_SHORT).show();
        Toast.makeText(this, "Casa numero: "+ MainActivity1.casaNum, Toast.LENGTH_SHORT).show();

        btnScanner = findViewById(R.id.buttonEscanerAtr);
        tvTitulo = findViewById(R.id.textViewTituloAtr);
        tvTitulo.setText("AUTORIZADOS CASA "+idCasa);
        tvNombre = findViewById(R.id.textViewNombreAcc3);
        tvNombre2 = findViewById(R.id.textViewNombreAcc1);
        tvNombre3 = findViewById(R.id.textViewNombreAcc2);
        tvNombre4 = findViewById(R.id.textViewNombreAcc4);

        btnScanner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(AutorizadosActivity.this, ScanActivity.class);
                startActivity(i);
            }
        });

        tvNombre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(AutorizadosActivity.this, AcpAcesoActivity.class);
                startActivity(i);
            }
        });
        tvNombre2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(AutorizadosActivity.this, AcpAcceso2Activity.class);
                startActivity(i);
            }
        });
        tvNombre3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(AutorizadosActivity.this, AcoAcceso3Activity.class);
                startActivity(i);
            }
        });
        tvNombre4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(AutorizadosActivity.this, AcpAcceso4Activity.class);
                startActivity(i);
            }
        });

        btnScan = findViewById(R.id.buttonScan);
        txtResultado = findViewById(R.id.editTextScan);

        /*btnScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntentIntegrator integrator = new IntentIntegrator(AutorizadosActivity.this);
                integrator.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES);
                integrator.setPrompt("Lector - AGUA");
                integrator.setCameraId(0);
                integrator.setBeepEnabled(true);
                integrator.setBarcodeImageEnabled(true);
                integrator.initiateScan();
            }
        });*/
    }

    /*@Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);

        if (result != null){
            if (result.getContents() == null){
                Toast.makeText(this, "Lectura cancelada", Toast.LENGTH_LONG).show();
            }else {
                Toast.makeText(this, result.getContents(), Toast.LENGTH_LONG).show();
                txtResultado.setText(result.getContents());
            }
        }else{
            super.onActivityResult(requestCode, resultCode, data);
        }

    }*/
}