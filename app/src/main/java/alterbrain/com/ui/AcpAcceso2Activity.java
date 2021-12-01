package alterbrain.com.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import alterbrain.com.BuildConfig;
import alterbrain.com.R;
import alterbrain.com.app.Constantes;

public class AcpAcceso2Activity extends AppCompatActivity {

    String nombre, fecha, tipo, comenta, codigo;
    TextView tvDatos;
    ImageView ivQR;
    Bitmap bitmap;
    Button btnCompartir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acp_acceso2);

        Bundle extras = getIntent().getExtras();
        nombre = extras.getString("nombre");
        fecha = extras.getString("fecha");
        tipo = extras.getString("tipoVisitante");
        comenta = extras.getString("comentarios");
        codigo = extras.getString("codigo");

        tvDatos = findViewById(R.id.textViewDatosAcc);
        ivQR = findViewById(R.id.imageViewQrAcpAcc);
        btnCompartir = findViewById(R.id.buttonSendQrAcc);

        tvDatos.setText(nombre+"\n"+fecha+"\n"+tipo+"\n"+comenta);

        try {
            BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
            bitmap = barcodeEncoder.encodeBitmap(codigo, BarcodeFormat.QR_CODE, 750,750);
            ivQR.setImageBitmap(bitmap);
        } catch (WriterException e) {
            e.printStackTrace();
        }
        eventos();
    }

    private void eventos() {
        btnCompartir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // save bitmap to cache directory
                try {
                    File cachePath = new File(AcpAcceso2Activity.this.getCacheDir(), "images");
                    cachePath.mkdirs(); // don't forget to make the directory
                    FileOutputStream stream = new FileOutputStream(cachePath + "/image.png"); // overwrites this image every time
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
                    stream.close();

                } catch (IOException e) {
                    e.printStackTrace();
                }
                File imagePath = new File(AcpAcceso2Activity.this.getCacheDir(), "images");
                File newFile = new File(imagePath, "image.png");
                Uri contentUri = FileProvider.getUriForFile(AcpAcceso2Activity.this, BuildConfig.APPLICATION_ID + ".fileprovider", newFile);

                if (contentUri != null) {
                    Intent shareIntent = new Intent();
                    shareIntent.setAction(Intent.ACTION_SEND);
                    shareIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION); // temp permission for receiving app to read this file
                    shareIntent.setDataAndType(contentUri, AcpAcceso2Activity.this.getContentResolver().getType(contentUri));
                    shareIntent.putExtra(Intent.EXTRA_STREAM, contentUri);
                    shareIntent.setType("image/png");
                    startActivity(Intent.createChooser(shareIntent, "Choose an app"));
                }
            }
        });
    }
}