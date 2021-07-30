package alterbrain.com;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class TransparenciaActivity extends AppCompatActivity {

    Button btn1,btn2;
    Button buttont;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transparencia);

        btn1 = findViewById(R.id.button1Trn);
        btn2 = findViewById(R.id.button2Trn);

        buttont = findViewById(R.id.buttonDetalleTrn);

        buttont.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(TransparenciaActivity.this, TransparenciaDActivity.class);
                startActivity(i);
            }
        });
        eventos();
    }
    private void eventos(){
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(TransparenciaActivity.this, Transparencia2Activity.class);
                startActivity(i);
                finish();
            }
        });
    }
}