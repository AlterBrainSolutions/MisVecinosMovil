package alterbrain.com;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import alterbrain.com.app.Constantes;
import alterbrain.com.ui.SeguridadMapActivity;

public class SeguridadCounterActivity extends AppCompatActivity {

    private static final int REQUEST_CALL = 1;
    TextView contador;
    ProgressBar progressBar;
    CountDownTimer countDownTimer;
    Button btnEmergencias, btnCancelar;
    BottomNavigationView bnvMenuSeg;
    BottomAppBar babMenuSeg;
    FloatingActionButton fabMenuSeg;
    int usuario;
    String numCasa, URL = "https://missvecinos.com.mx/android/insertaseguridad.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seguridad_counter);

        contador = findViewById(R.id.tvProgressBarTextInside);
        progressBar = findViewById(R.id.progressBarSeg);
        btnEmergencias = findViewById(R.id.buttonLlamarEmergencias);
        btnCancelar = findViewById(R.id.buttonSegCancelarAlerta);
        bnvMenuSeg = findViewById(R.id.bnvMenuSeg2);
        babMenuSeg = findViewById(R.id.bmbMenuSeg2);
        fabMenuSeg = findViewById(R.id.fabSeg2);

        usuario = Constantes.ID_USR;
        numCasa = Constantes.NUM_CSA;

        /*configNav();*/

        bnvMenuSeg.setBackground(null);

        long duration = TimeUnit.SECONDS.toMillis(5);

        countDownTimer = new CountDownTimer(duration, 1000) {
            @Override
            public void onTick(long l) {
                String sDuration = String.format(Locale.ENGLISH, "%02d",
                        l / 1000);

                contador.setText(sDuration);

                int entero = Integer.parseInt(sDuration);

                /*Toast.makeText( SeguridadCounterActivity.this,"Numero " + entero
                        , Toast.LENGTH_SHORT).show();*/

                updateProgressBar((entero * 100)/5);
            }

            /*TODO poner la direccion que se trae de la base de datos del fraccionamioento*/

            @Override
            public void onFinish() {

                /*StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        *//*si el texto de respuesta es correcto, crearemos
                         * un objeto de intenciony lanzar una actividad de éxito con esa intencion*//*
                        if (response.equals("success")) {
                            Toast.makeText(SeguridadCounterActivity.this, "¡Registrado exitosamente!", Toast.LENGTH_SHORT).show();
                            //finish();
                        } else if (response.equals("failure")) {
                            Toast.makeText(SeguridadCounterActivity.this, "¡Ocurrió un error!", Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //crear un detector de errores para manejar los errores de manera adecuada
                        Toast.makeText(getApplicationContext(), error.toString().trim(), Toast.LENGTH_SHORT).show();
                    }
                }) {
                    @Nullable
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> data = new HashMap<>();
                        data.put("usuario", String.valueOf(5));
                        data.put("cantidadPet", String.valueOf(1));
                        data.put("cantidadAlum", String.valueOf(1));
                        data.put("codigoLeido", "JA0JWxippm");
                        return data;
                    }
                };
                //crear instancia de RQ (cola de solicitudes)
                RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                requestQueue.add(stringRequest);*/

                /*AcpAccesoDialogFragment dialog = new AcpAccesoDialogFragment();
                dialog.show(getSupportFragmentManager(), "AcpAccesoDialogFragment");*/

                StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        /*si el texto de respuesta es correcto, crearemos
                         * un objeto de intenciony lanzar una actividad de éxito con esa intencion*/
                        if (response.equals("success")) {
                            Toast.makeText(SeguridadCounterActivity.this, "¡Registrado exitosamente!", Toast.LENGTH_SHORT).show();
                            finish();
                        } else if (response.equals("failure")) {
                            Toast.makeText(SeguridadCounterActivity.this, "¡Ocurrió un error!", Toast.LENGTH_SHORT).show();
                        }else if(response.equals("No hay datos")){
                            Toast.makeText(SeguridadCounterActivity.this, "¡No hay datos registrados!", Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //crear un detector de errores para manejar los errores de manera adecuada
                        Toast.makeText(getApplicationContext(), error.toString().trim(), Toast.LENGTH_SHORT).show();
                    }
                }) {
                    @Nullable
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> data = new HashMap<>();
                        data.put("usuario", String.valueOf(usuario));
                        data.put("casa", numCasa);
                        return data;
                    }
                };
                //crear instancia de RQ (cola de solicitudes)
                RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                requestQueue.add(stringRequest);

                /*Toast.makeText(SeguridadCounterActivity.this, "He terminado"
                        , Toast.LENGTH_SHORT).show();*/

                /*Toast.makeText(SeguridadCounterActivity.this, "Fraccionamiento " + usuario
                        , Toast.LENGTH_SHORT).show();*/

                //TODO
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

    /*private void insertaS() {


    }*/

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