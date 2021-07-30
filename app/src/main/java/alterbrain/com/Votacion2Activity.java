package alterbrain.com;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Votacion2Activity extends AppCompatActivity {

    Button btnContinuar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_votacion2);

        btnContinuar = findViewById(R.id.buttonContinuarenc);
        btnContinuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Votacion2Activity.this, Votacion3Activity.class);
                startActivity(i);
            }
        });
    }
}