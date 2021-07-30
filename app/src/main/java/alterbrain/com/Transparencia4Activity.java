package alterbrain.com;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Transparencia4Activity extends AppCompatActivity {

    Button btn1,btn2,buttont;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transparencia4);
        btn1 = findViewById(R.id.button7Trn);
        btn2 = findViewById(R.id.button8Trn);

        buttont = findViewById(R.id.buttonDetalleTrn4);

        buttont.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Transparencia4Activity.this, TransparenciaD4Activity.class);
                startActivity(i);
            }
        });
        eventos();
    }
    private void eventos(){
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Transparencia4Activity.this, Transparencia3Activity.class);
                startActivity(i);
                finish();
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Transparencia4Activity.this, Transparencia5Activity.class);
                startActivity(i);
                finish();
            }
        });
    }
}