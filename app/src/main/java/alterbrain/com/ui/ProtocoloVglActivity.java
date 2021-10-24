package alterbrain.com.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import alterbrain.com.R;

public class ProtocoloVglActivity extends AppCompatActivity {

    Button btnProtRealiz;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_protocolo_vgl);

        btnProtRealiz = findViewById(R.id.buttonListoProto);

        btnProtRealiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ProtocoloVglActivity.this, MessageVglActivity.class);
                startActivity(i);
            }
        });
    }
}