package alterbrain.com.ui;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;

import alterbrain.com.R;
import alterbrain.com.ServiciosActivity3;

public class MessageVglActivity extends AppCompatActivity {

    private static final int COD_SELECCIONA = 10;
    private static final int COD_FOTO = 20;

    Bitmap bitmap;
    ImageView ivAgregaImagen;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_vgl);

        ivAgregaImagen = findViewById(R.id.imageViewSubeImgAvsVig);

        eventos();
    }

    private void eventos() {
        ivAgregaImagen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mostrarDialogOpciones();
                //choosePicture();
            }
        });
    }

    private void mostrarDialogOpciones() {
        //Toast.makeText(this, "Mostrar opciones", Toast.LENGTH_SHORT).show();
        final CharSequence[] opciones={"Tomar Foto","Elegir de Galeria","Cancelar"};
        final AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("Elige una Opción");
        builder.setItems(opciones, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (opciones[i].equals("Tomar Foto")){
                    Toast.makeText(MessageVglActivity.this, "Cargar camara", Toast.LENGTH_SHORT).show();
                    abrirCamara();
                }else{
                    if (opciones[i].equals("Elegir de Galeria")){
                        Intent intent=new Intent(Intent.ACTION_GET_CONTENT,
                                MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                        intent.setType("image/*");
                        startActivityForResult(intent.createChooser(intent,"Seleccione"),COD_SELECCIONA);
                    }else{
                        dialogInterface.dismiss();
                    }
                }
            }
        });
        //mostrar el alert dialog
        builder.show();
    }

    private void abrirCamara() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if(intent.resolveActivity(getPackageManager()) != null){
            startActivityForResult(intent, COD_FOTO);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode){
            case COD_SELECCIONA:
                Uri miPath=data.getData();
                ivAgregaImagen.setImageURI(miPath);

                try {
                    bitmap=MediaStore.Images.Media.getBitmap(this.getContentResolver(),miPath);
                    ivAgregaImagen.setImageBitmap(bitmap);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                break;
            case COD_FOTO:
                if (resultCode == RESULT_OK) {
                    Bundle extras = data.getExtras();

                    bitmap = (Bitmap) extras.get("data");
                    ivAgregaImagen.setImageBitmap(bitmap);
                }


                break;
        }
        /*bitmap=redimensionarImagen(bitmap,600,800);*/
    }
}