package alterbrain.com;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Transparencia6Activity extends AppCompatActivity {

    Button btn1,btn2,buttont;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transparencia6);

        btn1 = findViewById(R.id.button11Trn);
        btn2 = findViewById(R.id.button12Trn);

        buttont = findViewById(R.id.buttonDetalleTrn6);

        buttont.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Transparencia6Activity.this, TransparenciaD6Activity.class);
                startActivity(i);
            }
        });
        eventos();
    }
    private void eventos(){
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Transparencia6Activity.this, Transparencia5Activity.class);
                startActivity(i);
                finish();
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Transparencia6Activity.this, Transparencia7Activity.class);
                startActivity(i);
                finish();
            }
        });
    }
}