package alterbrain.com;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;
import java.util.concurrent.TimeUnit;

import alterbrain.com.ui.SeguridadMapActivity;

public class SeguridadCounterActivity extends AppCompatActivity {

    private static final int REQUEST_CALL = 1;
    TextView contador;
    ProgressBar progressBar;
    CountDownTimer countDownTimer;
    Button btnEmergencias, btnCancelar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seguridad_counter);

        contador = findViewById(R.id.tvProgressBarTextInside);
        progressBar = findViewById(R.id.progressBarSeg);
        btnEmergencias = findViewById(R.id.buttonLlamarEmergencias);
        btnCancelar = findViewById(R.id.buttonSegCancelarAlerta);

        long duration = TimeUnit.SECONDS.toMillis(10);

        countDownTimer = new CountDownTimer(duration, 1000) {
            @Override
            public void onTick(long l) {
                String sDuration = String.format(Locale.ENGLISH, "%02d",
                        l / 1000);

                contador.setText(sDuration);

                int entero = Integer.parseInt(sDuration);

                /*Toast.makeText( SeguridadCounterActivity.this,"Numero " + entero
                        , Toast.LENGTH_SHORT).show();*/

                updateProgressBar(entero * 10);
            }

            @Override
            public void onFinish() {

                Toast.makeText(SeguridadCounterActivity.this, "He terminado"
                        , Toast.LENGTH_SHORT).show();

            }
        }.start();

        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(SeguridadCounterActivity.this, "Alerta cancelada"
                        , Toast.LENGTH_SHORT).show();
                onBackPressed();
                /*
                contador.setText("10");
                updateProgressBar(100);*/
            }
        });

        btnEmergencias.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Toast.makeText(SeguridadCounterActivity.this, "Alerta cancelada"
                        , Toast.LENGTH_SHORT).show();
                onBackPressed();*/
                makeEmergencyCall();
                /*
                contador.setText("10");
                updateProgressBar(100);*/
            }
        });

        /*countDownTimer.cancel();*/
    }

    private void makeEmergencyCall() {

        String number = "911";

        if(ContextCompat.checkSelfPermission(SeguridadCounterActivity.this,
                Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED)
        {
            //TODO si aun no tenemos el permiso
            ActivityCompat.requestPermissions(SeguridadCounterActivity.this,
                    new String[]{Manifest.permission.CALL_PHONE}, REQUEST_CALL);
        }else{
            //TODO hacemos la llamada
            String dial = "tel:" + number;
            startActivity(new Intent(Intent.ACTION_CALL, Uri.parse(dial)));
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CALL){
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                makeEmergencyCall();
            }else{
                Toast.makeText(SeguridadCounterActivity.this, "Ocurrio un error"
                        , Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void updateProgressBar(int entero) {

        progressBar.setProgress(entero);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent detail = new Intent(SeguridadCounterActivity.this, SeguridadMapActivity.class);
        startActivity(detail);
        countDownTimer.cancel();
        finish();
    }
}