package alterbrain.com;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.Calendar;
import java.util.Date;

import alterbrain.com.model.Noticia2;
import alterbrain.com.model.User;

public class CrearNoticiaActivity extends AppCompatActivity {

    EditText editTextTitulo,editTextDescripcion;
    ImageView ivAgregaImagen;
    Button btnGuardar;

    FirebaseFirestore db;
    String titulo, descripcion, idn, ape1, fecha;
    String time = "";
    Calendar c;

    public Uri imageUri;
    private FirebaseStorage storage;
    private StorageReference storageReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_noticia);

        editTextTitulo = findViewById(R.id.editTextTextTitulocn);
        editTextDescripcion = findViewById(R.id.editTextTextDescribecn);
        ivAgregaImagen = findViewById(R.id.imageViewAgregarIcn);
        btnGuardar = findViewById(R.id.buttonGuardarcn);

        db = FirebaseFirestore.getInstance();

        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();

        eventos();
    }

    private void eventos() {
        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                c = Calendar.getInstance();
                int hora = c.get(Calendar.HOUR_OF_DAY);
                int minuto = c.get(Calendar.MINUTE);
                int anio = c.get(Calendar.YEAR);
                int mes  = c.get(Calendar.MONTH);
                int dia = c.get(Calendar.DAY_OF_MONTH);

                time = anio+ "-" + (mes+1) + "-" + dia + " " + hora + ":" + minuto;

                titulo = editTextTitulo.getText().toString();
                descripcion = editTextDescripcion.getText().toString();
                fecha = time;
                idn = titulo + "_1";
                if(titulo.isEmpty()){
                    editTextTitulo.setError("El titulo es obligatorio");
                }else if(descripcion.isEmpty()){
                    editTextDescripcion.setError("La descripcion es obligatoria");
                }else {
                    uploadPicture();
                    createNoticia();

                }
            }
        });
        ivAgregaImagen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                choosePicture();
            }
        });
    }



    private void choosePicture() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, 1);

    }
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK && data != null && data.getData() != null) {
            imageUri = data.getData();
            ivAgregaImagen.setImageURI(imageUri);
            //uploadPicture();
        }
    }

    private void uploadPicture() {

        final ProgressDialog pd = new ProgressDialog(CrearNoticiaActivity.this);
        pd.setTitle("Actualizando Imagen...");
        pd.show();

        //final String photoKey = MainActivity1.userId;
        StorageReference mountainImagesRef = storageReference.child("noticias/" + idn);

        mountainImagesRef.putFile(imageUri)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        pd.dismiss();
                        Toast.makeText(CrearNoticiaActivity.this, "Imagen Actualizada.", Toast.LENGTH_LONG).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                pd.dismiss();
                Toast.makeText(CrearNoticiaActivity.this, "Fallo Al Actualizar.", Toast.LENGTH_LONG).show();
            }
        })
                .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onProgress(@NonNull UploadTask.TaskSnapshot tasksnapshot) {
                        double progressPercent = (100.0 * tasksnapshot.getBytesTransferred() / tasksnapshot.getTotalByteCount());
                        pd.setMessage("Porcentaje: " + (int) progressPercent + "%");
                    }
                });
    }
    private void createNoticia() {
        //Almacenar la informacion del usuario en Firestore
        Noticia2 noticia = new Noticia2(titulo, descripcion, fecha, idn);

        db.collection("noticias")
                .document(noticia.getIdn())
                .set(noticia)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        limpiar();
                        //Navegar hacia la siguiente pantalla
                        /*finish();
                        Intent i = new Intent(CrearNoticiaActivity.this, NoticiasActivity.class);
                        startActivity(i);*/
                    }
                });
    }
    public void limpiar(){
        editTextTitulo.setText("");
        editTextDescripcion.setText("");
        Drawable myDrawable = getResources().getDrawable(R.drawable.ic_menu_gallery);
        ivAgregaImagen.setImageDrawable(myDrawable);
    }
}