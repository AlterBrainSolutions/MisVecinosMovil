package alterbrain.com;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Transparencia2Activity extends AppCompatActivity {

    Button buttont;
    Button btn1,btn2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transparencia2);
        btn1 = findViewById(R.id.button3Trn);
        btn2 = findViewById(R.id.button4Trn);

        eventos();

        buttont = findViewById(R.id.buttonDetalleTrn2);

        buttont.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Transparencia2Activity.this, TransparenciaD2Activity.class);
                startActivity(i);
            }
        });
    }
    private void eventos(){
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Transparencia2Activity.this, TransparenciaActivity.class);
                startActivity(i);
                finish();
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Transparencia2Activity.this, Transparencia3Activity.class);
                startActivity(i);
                finish();
            }
        });
    }
}