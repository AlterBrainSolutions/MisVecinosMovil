package alterbrain.com;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class DescripNoticiaActivity extends AppCompatActivity {

    ImageView ivNoticia;
    public Uri imageUri;
    private FirebaseStorage storage;
    private StorageReference storageReference;

    String idNoticia;

    TextView tvTituloNot, tvDescricionNot, tvFechaNot;
    FirebaseFirestore db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_descrip_noticia);

        ivNoticia = findViewById(R.id.imageViewDescNoti);
        tvTituloNot = findViewById(R.id.textViewTituloDesN);
        tvDescricionNot = findViewById(R.id.textViewDescripcionDesN);
        tvFechaNot = findViewById(R.id.textViewFechaDesN);

        db = FirebaseFirestore.getInstance();

        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();

        Bundle extras = getIntent().getExtras();
        idNoticia = extras.getString("valorNoticia");
        //eventos();

        getPlayerNames();

    }

    private void getPlayerNames() {
        // Obtener el nombre del player 1
        db.collection("noticias")
                .document(idNoticia)
                .get()
                .addOnSuccessListener(DescripNoticiaActivity.this, new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot snapshot) {
                        tvTituloNot.setText(snapshot.get("titulo").toString());
                        tvFechaNot.setText(snapshot.get("fecha").toString());
                        tvDescricionNot.setText(snapshot.get("descripcion").toString());
                    }
                });

        //Obtener imagen de perfil
        StorageReference imageRef = storageReference.child("noticias/" + idNoticia);

        final long ONE_MEGABYTE = (1024 * 1024) * 3;
        imageRef.getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
            @Override
            public void onSuccess(byte[] bytes) {
                Bitmap bitmap = BitmapFactory.decodeByteArray(bytes,0,bytes.length);
                ivNoticia.setImageBitmap(bitmap);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                // Handle any errors
            }
        });
    }
}