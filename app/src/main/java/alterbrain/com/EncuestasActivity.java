package alterbrain.com;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class EncuestasActivity extends AppCompatActivity {

    TextView tvTextoEncuesta;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_encuestas);
        tvTextoEncuesta = findViewById(R.id.textViewDescEnc);

        tvTextoEncuesta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(EncuestasActivity.this, Votacion1Activity.class);
                startActivity(i);
            }
        });
    }
}