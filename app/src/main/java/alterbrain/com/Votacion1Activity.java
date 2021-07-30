package alterbrain.com;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Votacion1Activity extends AppCompatActivity {

    Button btnComenzar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_votacion1);
        btnComenzar = findViewById(R.id.buttonComezarenc);
        btnComenzar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Votacion1Activity.this, Votacion2Activity.class);
                startActivity(i);
            }
        });
    }
}