package alterbrain.com.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import alterbrain.com.R;
import alterbrain.com.app.Constantes;

public class ProtocoloVglActivity extends AppCompatActivity {

    Button btnProtRealiz;
    TextView tvCasa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_protocolo_vgl);

        btnProtRealiz = findViewById(R.id.buttonListoProto);
        tvCasa = findViewById(R.id.textViewNumCasaPrto);

        tvCasa.setText("CASA "+Constantes.ID_ALERTA_SEGR);
        btnProtRealiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ProtocoloVglActivity.this, MessageVglActivity.class);
                startActivity(i);
            }
        });
    }
}