package alterbrain.com;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import alterbrain.com.ui.DetalleAdeActivity2;


public class ReciclajeActivity extends AppCompatActivity {
    int numPET;
    int numAL;
    Button btningresaReciclaje, btndetalleReciclaje, btnConfirmar;
    TextView tvalertPET, tvalertAL;
    ImageView ivCerrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reciclaje);

        NumberPicker np = findViewById(R.id.numberPickerPET);
        NumberPicker np2 = findViewById(R.id.numberPickerAL);

        np.setMinValue(0);
        np.setMaxValue(10000);

        np2.setMinValue(0);
        np2.setMaxValue(10000);

        np.setOnValueChangedListener(onValueChangeListener);
        np2.setOnValueChangedListener(onValueChangeListener2);

        btningresaReciclaje = findViewById(R.id.buttonIngresarReciclaje);
        btndetalleReciclaje = findViewById(R.id.buttonDetalleReciclaje);

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
                ivCerrar = mView.findViewById(R.id.ivCerrarAlert);

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

                ivCerrar.setOnClickListener(new View.OnClickListener() {
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

        if (result != null){
            if (result.getContents() == null){
                Toast.makeText(this, "Lectura cancelada", Toast.LENGTH_LONG).show();
            }else {
                Toast.makeText(this, result.getContents(), Toast.LENGTH_LONG).show();
                /*txtResultado.setText(result.getContents());*/
                finish();
            }
        }else{
            super.onActivityResult(requestCode, resultCode, data);
        }

    }

    NumberPicker.OnValueChangeListener onValueChangeListener =
            new     NumberPicker.OnValueChangeListener(){
                @Override
                public void onValueChange(NumberPicker numberPicker, int i, int i1) {
                    //Toast.makeText( ReciclajeActivity.this,
                    // "selected number "+numberPicker.getValue(), Toast.LENGTH_SHORT).show();
                    numPET = numberPicker.getValue();
                }
            };

    NumberPicker.OnValueChangeListener onValueChangeListener2 =
            new     NumberPicker.OnValueChangeListener(){
                @Override
                public void onValueChange(NumberPicker numberPicker, int i, int i1) {
                    //Toast.makeText( ReciclajeActivity.this,
                    //"selected number "+numberPicker.getValue(), Toast.LENGTH_SHORT).show();
                    numAL = numberPicker.getValue();
                }
            };
}