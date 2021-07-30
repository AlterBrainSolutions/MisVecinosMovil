package alterbrain.com;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Noticias2Activity extends AppCompatActivity {

    TextView tvdescn1, tvdescn2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_noticias2);
        tvdescn1 = findViewById(R.id.textViewDescripNoticia2);
        tvdescn2 = findViewById(R.id.textViewDescripNoticia3);

        tvdescn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Noticias2Activity.this, DescripNoticia2Activity.class);
                startActivity(i);
            }
        });
        tvdescn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Noticias2Activity.this, DescripNoticia3Activity.class);
                startActivity(i);
            }
        });
    }
}