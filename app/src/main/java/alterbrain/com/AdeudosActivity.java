package alterbrain.com;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import alterbrain.com.ui.DetalleAdeActivity;
import alterbrain.com.ui.DetalleAdeActivity2;

public class AdeudosActivity extends AppCompatActivity {

    Button btnDetalleAde;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adeudos);
        btnDetalleAde = findViewById(R.id.buttonDetalleAde);

        btnDetalleAde.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Intent i = new Intent(AdeudosActivity.this, DetalleAdeActivity.class);
                startActivity(i);*/
                Intent i = new Intent(AdeudosActivity.this, DetalleAdeActivity2.class);
                startActivity(i);
            }
        });
    }
}