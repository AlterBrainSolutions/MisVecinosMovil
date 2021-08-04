package alterbrain.com;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Documentos2Activity extends AppCompatActivity {

    Button btn1, btn2, btn3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_documentos2);
        btn3 = findViewById(R.id.buttonVeDoc3);
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent detail = new Intent(Documentos2Activity.this, VeDocActivity3.class);
                startActivity(detail);
            }
        });

        /*btn1 = findViewById(R.id.buttonVeDoc);
        btn2 = findViewById(R.id.buttonVeDoc2);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent detail = new Intent(Documentos2Activity.this, VeDoc2Activity.class);
                startActivity(detail);
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent detail = new Intent(Documentos2Activity.this, VeDocActivity.class);
                startActivity(detail);
            }
        });*/
    }
}