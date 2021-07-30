package alterbrain.com;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Transparencia5Activity extends AppCompatActivity {

    Button btn1,btn2,buttont;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transparencia5);

        btn1 = findViewById(R.id.button9Trn);
        btn2 = findViewById(R.id.button10Trn);

        buttont = findViewById(R.id.buttonDetalleTrn5);

        buttont.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Transparencia5Activity.this, TransparenciaD5Activity.class);
                startActivity(i);
            }
        });
        eventos();
    }
    private void eventos(){
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Transparencia5Activity.this, Transparencia4Activity.class);
                startActivity(i);
                finish();
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Transparencia5Activity.this, Transparencia6Activity.class);
                startActivity(i);
                finish();
            }
        });
    }
}