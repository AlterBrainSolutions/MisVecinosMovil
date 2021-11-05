package alterbrain.com.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import alterbrain.com.AdeudosActivity;
import alterbrain.com.R;
import alterbrain.com.app.Constantes;

public class DetalleAdeActivity extends AppCompatActivity {

    ConstraintLayout constraintLayoutCorriente;
    ConstraintLayout constraintLayoutAdeudo;
    TextView tvAdeudo, tvCorriente;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_ade);

        constraintLayoutCorriente = findViewById(R.id.constraintLayoutCorriente);
        constraintLayoutAdeudo = findViewById(R.id.constraintLayoutAdeudo);
        tvAdeudo = findViewById(R.id.tvConAdeudo);
        tvCorriente = findViewById(R.id.tvAlCorriente);

        int conAdeudo = Constantes.UN_MES + Constantes.DOS_MESES;

        tvCorriente.setText("Casas al corriente: " + Constantes.AL_CORRIENTE);
        tvAdeudo.setText("Casas con adeudo: " + conAdeudo);


        constraintLayoutCorriente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(DetalleAdeActivity.this, CorrienteActivity.class);
                startActivity(i);
            }
        });
        constraintLayoutAdeudo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i2 = new Intent(DetalleAdeActivity.this, DeudaActivity.class);
                startActivity(i2);
            }
        });
    }
}