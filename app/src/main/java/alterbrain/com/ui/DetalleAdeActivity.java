package alterbrain.com.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import alterbrain.com.AdeudosActivity;
import alterbrain.com.R;

public class DetalleAdeActivity extends AppCompatActivity {

    ConstraintLayout constraintLayoutCorriente;
    ConstraintLayout constraintLayoutAdeudo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_ade);

        constraintLayoutCorriente = findViewById(R.id.constraintLayoutCorriente);
        constraintLayoutAdeudo = findViewById(R.id.constraintLayoutAdeudo);

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