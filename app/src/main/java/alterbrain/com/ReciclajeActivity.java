package alterbrain.com;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import alterbrain.com.app.Constantes;
import alterbrain.com.ui.AcpAccesoDialogFragment;
import alterbrain.com.ui.DetalleAdeActivity2;


public class ReciclajeActivity extends AppCompatActivity {
    int numPET = 0, numAL = 0, usuario;
    Button btningresaReciclaje, btndetalleReciclaje, btnConfirmar, sigSlider, btnCerrar;
    TextView tvalertPET, tvalertAL;
    String URL = "https://missvecinos.com.mx/android/insertareciclaje.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reciclaje);

        NumberPicker np = findViewById(R.id.numberPickerPET);
        NumberPicker np2 = findViewById(R.id.numberPickerAL);

        np.setMinValue(0);
        np.setMaxValue(100);
        np.setValue(0);

        np2.setMinValue(0);
        np2.setMaxValue(100);
        np2.setValue(0);

        np.setOnValueChangedListener(onValueChangeListener);
        np2.setOnValueChangedListener(onValueChangeListener2);

        btningresaReciclaje = findViewById(R.id.buttonIngresarReciclaje);
        btndetalleReciclaje = findViewById(R.id.buttonDetalleReciclaje);

        if (numPET == 0 && numAL == 0) {
            btningresaReciclaje.setEnabled(false);
        }

        btningresaReciclaje.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Intent i = new Intent(AdeudosActivity.this, DetalleAdeActivity.class);
                startActivity(i);*/
                /*Toast.makeText( ReciclajeActivity.this,
                "Los numeros son: "+ numPET + " y "+ numAL, Toast.LENGTH_SHORT).show();*/

                AlertDialog.Builder mBuilder = new AlertDialog.Builder(ReciclajeActivity.this);
                View mView = getLayoutInflater().inflate(R.layout.dialog_conf_rec, null);

                mBuilder.setView(mView);
                AlertDialog dialog = mBuilder.create();
                dialog.show();

                tvalertAL = mView.findViewById(R.id.textViewAlertAL);
                tvalertPET = mView.findViewById(R.id.textViewAlertPET);
                btnConfirmar = mView.findViewById(R.id.buttonAlertConf);
                btnCerrar = mView.findViewById(R.id.buttonAlertCerrar);

                tvalertPET.setText(String.valueOf(numPET));
                tvalertAL.setText(String.valueOf(numAL));

                btnConfirmar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        IntentIntegrator integrator = new IntentIntegrator(ReciclajeActivity.this);
                        integrator.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES);
                        integrator.setPrompt("Lector - AGUA");
                        integrator.setCameraId(0);
                        integrator.setBeepEnabled(true);
                        integrator.setBarcodeImageEnabled(true);
                        integrator.initiateScan();
                    }
                    /*public void onClick(View v) {
                     *//*Intent i = new Intent(AdeudosActivity.this, DetalleAdeActivity.class);
                startActivity(i);*//*
                        Intent i = new Intent(ReciclajeActivity.this, ScanActivity.class);
                        startActivity(i);
                    }*/
                });

                btnCerrar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                    /*public void onClick(View v) {
                     *//*Intent i = new Intent(AdeudosActivity.this, DetalleAdeActivity.class);
                startActivity(i);*//*
                        Intent i = new Intent(ReciclajeActivity.this, ScanActivity.class);
                        startActivity(i);
                    }*/
                });
            }
        });

        btndetalleReciclaje.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Intent i = new Intent(AdeudosActivity.this, DetalleAdeActivity.class);
                startActivity(i);*/
                Intent i = new Intent(ReciclajeActivity.this, DetalleReciclajeActivity.class);
                startActivity(i);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);

        if (result != null) {
            if (result.getContents() == null) {
                Toast.makeText(this, "Lectura cancelada", Toast.LENGTH_LONG).show();
            } else if (result.getContents().equals("abcdefg12345")) {
                Toast.makeText(this, result.getContents(), Toast.LENGTH_LONG).show();
                Toast.makeText(this, numAL + " " + numPET, Toast.LENGTH_LONG).show();
                /*txtResultado.setText(result.getContents());*/
                usuario = Constantes.ID_USR;

                /*AcpAccesoDialogFragment dialog = new AcpAccesoDialogFragment();
                dialog.show(getSupportFragmentManager(), "AcpAccesoDialogFragment");*/

                StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        /*si el texto de respuesta es correcto, crearemos
                         * un objeto de intenciony lanzar una actividad de éxito con esa intencion*/
                        if (response.equals("success")) {
                            Toast.makeText(ReciclajeActivity.this, "¡Registrado exitosamente!", Toast.LENGTH_SHORT).show();
                            //finish();
                        } else if (response.equals("failure")) {
                            Toast.makeText(ReciclajeActivity.this, "¡Ocurrió un error!", Toast.LENGTH_SHORT).show();

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
                        data.put("cantidadPet", String.valueOf(numPET));
                        data.put("cantidadAlum", String.valueOf(numAL));
                        return data;
                    }
                };
                //crear instancia de RQ (cola de solicitudes)
                RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                requestQueue.add(stringRequest);

                finish();
            } else {
                Toast.makeText(ReciclajeActivity.this, "¡El codigo no es correcto!", Toast.LENGTH_SHORT).show();
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }

    }

    NumberPicker.OnValueChangeListener onValueChangeListener =
            new NumberPicker.OnValueChangeListener() {
                @Override
                public void onValueChange(NumberPicker numberPicker, int i, int i1) {
                    //Toast.makeText( ReciclajeActivity.this,
                    // "selected number "+numberPicker.getValue(), Toast.LENGTH_SHORT).show();
                    numPET = numberPicker.getValue();

                    if (numberPicker.getValue() == 0 && numAL == 0) {
                        btningresaReciclaje.setEnabled(false);
                    } else {
                        btningresaReciclaje.setEnabled(true);
                    }
                }
            };

    NumberPicker.OnValueChangeListener onValueChangeListener2 =
            new NumberPicker.OnValueChangeListener() {
                @Override
                public void onValueChange(NumberPicker numberPicker, int i, int i1) {
                    //Toast.makeText( ReciclajeActivity.this,
                    //"selected number "+numberPicker.getValue(), Toast.LENGTH_SHORT).show();
                    numAL = numberPicker.getValue();

                    if (numberPicker.getValue() == 0 && numPET == 0) {
                        btningresaReciclaje.setEnabled(false);
                    } else {
                        btningresaReciclaje.setEnabled(true);
                    }
                }
            };
}