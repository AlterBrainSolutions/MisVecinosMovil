package alterbrain.com;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Votacion3Activity extends AppCompatActivity {

    Button btnContinuar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_votacion3);

        btnContinuar = findViewById(R.id.buttonContinuar2enc);
        btnContinuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Votacion3Activity.this, Votacion4Activity.class);
                startActivity(i);
            }
        });
    }
}