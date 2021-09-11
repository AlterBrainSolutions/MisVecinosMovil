package alterbrain.com;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import alterbrain.com.ui.DetalleAdeActivity2;


public class ReciclajeActivity extends AppCompatActivity {
    int numPET;
    int numAL;
    Button btningresaReciclaje, btndetalleReciclaje, btnConfirmar;
    TextView tvalertPET, tvalertAL;

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

                tvalertAL = mView.findViewById(R.id.textViewAlertAL);
                tvalertPET = mView.findViewById(R.id.textViewAlertPET);
                btnConfirmar = mView.findViewById(R.id.buttonAlertConf);

                tvalertPET.setText(String.valueOf(numPET));
                tvalertAL.setText(String.valueOf(numAL));

                btnConfirmar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                /*Intent i = new Intent(AdeudosActivity.this, DetalleAdeActivity.class);
                startActivity(i);*/
                        Intent i = new Intent(ReciclajeActivity.this, ScanActivity.class);
                        startActivity(i);
                    }
                });

                mBuilder.setView(mView);
                AlertDialog dialog = mBuilder.create();
                dialog.show();
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