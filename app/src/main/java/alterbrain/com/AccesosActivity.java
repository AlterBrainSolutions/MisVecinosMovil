package alterbrain.com;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import alterbrain.com.ui.AcoAcceso3Activity;
import alterbrain.com.ui.AcpAcceso2Activity;
import alterbrain.com.ui.AcpAcceso4Activity;
import alterbrain.com.ui.AcpAcesoActivity;

public class AccesosActivity extends AppCompatActivity {

    ImageView ivAgregarAcc;
    //TextView tvNombre3, tvNombre1, tvNombre2, tvNombre4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accesos);

        ivAgregarAcc = findViewById(R.id.imageViewAgregarAcc);
        /*tvNombre3 = findViewById(R.id.textViewNombreAcc3);
        tvNombre1 = findViewById(R.id.textViewNombreAcc1);
        tvNombre2 = findViewById(R.id.textViewNombreAcc2);
        tvNombre4 = findViewById(R.id.textViewNombreAcc4);*/

        ivAgregarAcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(AccesosActivity.this, CrearAccesoActivity.class);
                startActivity(i);
            }
        });
        /*tvNombre3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(AccesosActivity.this, AcpAcesoActivity.class);
                startActivity(i);
            }
        });
        tvNombre1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(AccesosActivity.this, AcpAcceso2Activity.class);
                startActivity(i);
            }
        });
        tvNombre2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(AccesosActivity.this, AcoAcceso3Activity.class);
                startActivity(i);
            }
        });
        tvNombre4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(AccesosActivity.this, AcpAcceso4Activity.class);
                startActivity(i);
            }
        });*/
    }
}