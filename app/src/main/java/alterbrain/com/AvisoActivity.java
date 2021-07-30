package alterbrain.com;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AvisoActivity extends AppCompatActivity {

    Button btnAceptarAviso;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aviso);

        btnAceptarAviso = findViewById(R.id.buttonAceptar);

        btnAceptarAviso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(AvisoActivity.this, LoginActivity.class);
                startActivity(i);
            }
        });
    }
}