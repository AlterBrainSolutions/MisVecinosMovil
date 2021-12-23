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
import alterbrain.com.app.Constantes;

public class AutorizadosActivity extends AppCompatActivity {

    //int idCasa;
    Button btnScanner;
    TextView tvTitulo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_autorizados);

        /*Bundle extras = getIntent().getExtras();
        idCasa = extras.getInt("idCasa");*/
        //Toast.makeText(this, "id: "+idCasa, Toast.LENGTH_SHORT).show();

        btnScanner = findViewById(R.id.buttonEscanerAtr);
        tvTitulo = findViewById(R.id.textViewTituloAtr);
        tvTitulo.setText("AUTORIZADOS CASA "+ Constantes.NUM_CSA);

        btnScanner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(AutorizadosActivity.this, ScanActivity.class);
                startActivity(i);
            }
        });

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