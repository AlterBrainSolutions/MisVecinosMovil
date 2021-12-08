package alterbrain.com.ui;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import alterbrain.com.BuildConfig;
import alterbrain.com.MainActivity1;
import alterbrain.com.MainActivity7;
import alterbrain.com.R;
import alterbrain.com.app.Constantes;

public class AcpAcceso2Activity extends AppCompatActivity {

    String tiempo,nombre, fecha, tipo, comenta, codigo;
    String URL = "https://missvecinos.com.mx/android/deleteAcc.php";
    int id;
    TextView tvDatos;
    ImageView ivQR, ivBorrar;
    Bitmap bitmap;
    Button btnCompartir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acp_acceso2);

        Bundle extras = getIntent().getExtras();
        id = extras.getInt("idA");
        tiempo = extras.getString("tiempo");
        nombre = extras.getString("nombre");
        fecha = extras.getString("fecha");
        tipo = extras.getString("tipoVisitante");
        comenta = extras.getString("comentarios");
        codigo = extras.getString("codigo");

        tvDatos = findViewById(R.id.textViewDatosAcc);
        ivQR = findViewById(R.id.imageViewQrAcpAcc);
        btnCompartir = findViewById(R.id.buttonSendQrAcc);
        ivBorrar = findViewById(R.id.imageViewBorrarAcpAcc);

        tvDatos.setText("Configuración: "+tiempo+"\nInvitado: "+nombre+"\nFecha de visita: "+fecha+"\nTipo: "+tipo+"\nComentario: "+comenta);

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

        ivBorrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!nombre.isEmpty()){
                    showDialogConfirm();
                }else{
                    //getDialog().dismiss();
                    finish();
                }

            }
        });
    }

    private void showDialogConfirm() {
        // 1. Instantiate an AlertDialog.Builder with its constructor
        AlertDialog.Builder builder = new AlertDialog.Builder(AcpAcceso2Activity.this);

        // 2. Chain together various setter methods to set the dialog characteristics
        builder.setMessage("¿Desea realmente eliminar el acceso? El QR ya no se compartirá")
                .setTitle("Eliminar Acceso");

        builder.setPositiveButton("Eliminar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                //getDialog().dismiss();
                /*Intent i = new Intent(AcpAcceso2Activity.this, MainActivity1.class);
                startActivity(i);
                //TODO hacer una constante para que al regresar a CrearAcc se finalice ese activity*/
                borrarAcc();
                dialog.dismiss();
                //finish();
            }
        });
        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
            }
        });

        // 3. Get the AlertDialog from create()
        AlertDialog dialog = builder.create();

        dialog.show();
    }

    private void borrarAcc() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                /*si el texto de respuesta es correcto, crearemos
                 * un objeto de intencion y lanzar una actividad de éxito con esa intencion*/
                if (response.equals("success")) {
                    Toast.makeText(AcpAcceso2Activity.this, "¡Borrado exitosamente!", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(AcpAcceso2Activity.this, MainActivity1.class);
                    startActivity(i);
                    finish();
                } else if (response.equals("failure")) {
                    Toast.makeText(AcpAcceso2Activity.this, "Ocurrió un error!", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //crear un detector de errores para manejar los errores de manera adecuada
                Toast.makeText(getApplicationContext(), error.toString().trim(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> data = new HashMap<>();
                data.put("idacc", ""+id);
                return data;
            }
        };
        //crear instancia de RQ (cola de solicitudes)
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);
    }
}