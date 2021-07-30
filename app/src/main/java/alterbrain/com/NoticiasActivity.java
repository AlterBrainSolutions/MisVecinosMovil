package alterbrain.com;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class NoticiasActivity extends AppCompatActivity {
    Button btnAgregan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_noticias);

        btnAgregan = findViewById(R.id.buttonNuevaNot);

        btnAgregan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent detail = new Intent(NoticiasActivity.this, CrearNoticiaActivity.class);
                startActivity(detail);
            }
        });
    }
}