package alterbrain.com;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Transparencia7Activity extends AppCompatActivity {

    Button btn1,btn2,buttont;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transparencia7);

        btn1 = findViewById(R.id.button13Trn);
        btn2 = findViewById(R.id.button14Trn);

        buttont = findViewById(R.id.buttonDetalleTrn7);

        buttont.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Transparencia7Activity.this, TransparenciaD7Activity.class);
                startActivity(i);
            }
        });
        eventos();
    }
    private void eventos(){
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Transparencia7Activity.this, Transparencia6Activity.class);
                startActivity(i);
                finish();
            }
        });
    }
}