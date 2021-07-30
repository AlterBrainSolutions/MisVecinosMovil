package alterbrain.com;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Transparencia3Activity extends AppCompatActivity {

    Button btn1,btn2, buttont;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transparencia3);
        btn1 = findViewById(R.id.button5Trn);
        btn2 = findViewById(R.id.button6Trn);

        buttont = findViewById(R.id.buttonDetalleTrn3);

        buttont.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Transparencia3Activity.this, TransparenciaD3Activity.class);
                startActivity(i);
            }
        });
        eventos();
    }
    private void eventos(){
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Transparencia3Activity.this, Transparencia2Activity.class);
                startActivity(i);
                finish();
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Transparencia3Activity.this, Transparencia4Activity.class);
                startActivity(i);
                finish();
            }
        });
    }
}