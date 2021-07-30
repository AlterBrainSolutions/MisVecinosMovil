package alterbrain.com;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import alterbrain.com.ui.VeRecibo1Activity;
import alterbrain.com.ui.VeRecibo2Activity;
import alterbrain.com.ui.VeRecibo3Activity;
import alterbrain.com.ui.VeRecibo4Activity;
import alterbrain.com.ui.VeRecibo5Activity;
import alterbrain.com.ui.VeRecibo6Activity;
import alterbrain.com.ui.VeRecibo7Activity;

public class PagosActivity extends AppCompatActivity {

    TextView tvcEnero, tvcFebrero, tvC3, tvC4, tvC5, tvC6, tvC7;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pagos);

        tvcEnero = findViewById(R.id.textViewCEnerop);
        tvcFebrero = findViewById(R.id.textViewCFebrerop);
        tvC3 = findViewById(R.id.textViewCMarzoP);
        tvC4 = findViewById(R.id.textViewCAbrilp);
        tvC5 = findViewById(R.id.textViewCMayop);
        tvC6 = findViewById(R.id.textViewCJuniop);
        tvC7 = findViewById(R.id.textViewCJuliop);

        tvcEnero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent detail = new Intent(PagosActivity.this, VeRecibo1Activity.class);
                startActivity(detail);
            }
        });
        tvcFebrero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent detail = new Intent(PagosActivity.this, VeRecibo2Activity.class);
                startActivity(detail);
            }
        });
        tvC3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent detail = new Intent(PagosActivity.this, VeRecibo3Activity.class);
                startActivity(detail);
            }
        });
        tvC4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent detail = new Intent(PagosActivity.this, VeRecibo4Activity.class);
                startActivity(detail);
            }
        });
        tvC5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent detail = new Intent(PagosActivity.this, VeRecibo5Activity.class);
                startActivity(detail);
            }
        });
        tvC6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent detail = new Intent(PagosActivity.this, VeRecibo6Activity.class);
                startActivity(detail);
            }
        });
        tvC7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent detail = new Intent(PagosActivity.this, VeRecibo7Activity.class);
                startActivity(detail);
            }
        });
    }
}