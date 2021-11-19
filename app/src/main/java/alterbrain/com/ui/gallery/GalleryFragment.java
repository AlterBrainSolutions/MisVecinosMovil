package alterbrain.com.ui.gallery;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.ListenerRegistration;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import alterbrain.com.MainActivity1;
import alterbrain.com.R;
import alterbrain.com.app.Constantes;
import alterbrain.com.model.User;

import static android.app.Activity.RESULT_OK;

public class GalleryFragment extends Fragment {

    EditText etName, etEmail, etPass, etPass2, etApellido1,etApellido2;
    Button btnRegistro, btnRegresaLogin;
    FirebaseFirestore db;
    String nameU ="", emailU, passU, ape1U, ape2U;
    User usuario;
    ListenerRegistration listenerUser = null;

    private ImageView profilePic;
    public Uri imageUri;
    private FirebaseStorage storage;
    private StorageReference storageReference;

    private GalleryViewModel galleryViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        galleryViewModel =
                new ViewModelProvider(this).get(GalleryViewModel.class);
        View root = inflater.inflate(R.layout.fragment_gallery, container, false);

        etName = root.findViewById(R.id.editTextNameCo);
        etEmail = root.findViewById(R.id.editTextEmailCo);
        etPass = root.findViewById(R.id.editTextPasswordCo);
        etApellido1 = root.findViewById(R.id.editTextApellido1Co);
        etApellido2 = root.findViewById(R.id.editTextApellido2Co);

        btnRegistro = root.findViewById(R.id.buttonGuardarCo);
        db = FirebaseFirestore.getInstance();

        profilePic = root.findViewById(R.id.profilePic);

        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();

        //eventos();

        //getPlayerNames();
        //Toast.makeText(getActivity(), MainActivity1.userId, Toast.LENGTH_SHORT).show();

        //Get
        getDatosUser();

        return root;
    }

    private void getDatosUser() {
        nameU = Constantes.NOM_USR;
        emailU = Constantes.EMAIL_USR;
        passU = Constantes.PAS_USR;
        etName.setText(nameU);
        etEmail.setText(emailU);
        etPass.setText(passU);

        //TODO Comentar el siguiente codigo y agregar un nuevo fragment --un nuevo gallery para cada usuario. pa q editen sus datos
        if (nameU == null){
            nameU = Constantes.NOM_USULOGIS;
            etName.setText(nameU);
            passU= Constantes.PAS_USULOGIS;
            etPass.setText(passU);
        }
    }

    private void eventos() {
        btnRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                actualizarUser();
            }
        });
        profilePic.setOnClickListener(new View.OnClickListener() {
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
        if (requestCode == 1 && resultCode== RESULT_OK && data!=null && data.getData()!=null){
            imageUri = data.getData();
            profilePic.setImageURI(imageUri);
            uploadPicture();
        }
    }
    private void uploadPicture() {

        final ProgressDialog pd = new ProgressDialog(getActivity());
        pd.setTitle("Actualizando Imagen...");
        pd.show();

        final String photoKey = MainActivity1.userId;
        StorageReference mountainImagesRef = storageReference.child("images/" + photoKey);

        mountainImagesRef.putFile(imageUri)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        pd.dismiss();
                        Toast.makeText(getActivity(), "Imagen Actualizada.", Toast.LENGTH_LONG).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                pd.dismiss();
                Toast.makeText(getActivity(), "Fallo Al Actualizar.", Toast.LENGTH_LONG).show();
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

    // GetContent creates an ActivityResultLauncher<String> to allow you to pass
// in the mime type you'd like to allow the user to select
    /*ActivityResultLauncher<String> mGetContent = registerForActivityResult(new ActivityResultContracts.GetContent(),
            new ActivityResultCallback<Uri>() {
                @Override
                public void onActivityResult(Uri uri) {
                    // Handle the returned Uri

                }
            });*/


    private void actualizarUser() {
        usuario.setName(etName.getText().toString());
        usuario.setApellido1(etApellido1.getText().toString());
        usuario.setApellido2(etApellido2.getText().toString());
        usuario.setEmail(etEmail.getText().toString());
        usuario.setPassword(etPass.getText().toString());

        // Actualizar en Firestore los datos de la jugada
        db.collection("users")
                .document(MainActivity1.userId)
                .set(usuario)
                .addOnSuccessListener(getActivity(), new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(getActivity(), "Datos actualizados", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(getActivity(), new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.w("ERROR", "Error al guardar la jugada");
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        //datosUserListener();
    }
    private void datosUserListener(){
        listenerUser = db.collection("users")
                .document(MainActivity1.userId)
                .addSnapshotListener(getActivity(), new EventListener<DocumentSnapshot>() {
                    @Override
                    public void onEvent(@Nullable DocumentSnapshot snapshot, @Nullable FirebaseFirestoreException error) {
                        if(error != null) {
                            Toast.makeText(getActivity(), "Error al obtener los datos de la jugadas", Toast.LENGTH_SHORT).show();
                            return;
                        }

                        String source = snapshot != null
                                && snapshot.getMetadata().hasPendingWrites() ? "Local" : "Server";

                        if(snapshot.exists() && source.equals("Server")) {
                            //Parsenado DocumentSnapshot > User
                            usuario = snapshot.toObject(User.class);
                            if (nameU.isEmpty()){
                                getPlayerNames();
                            }
                        }
                    }
                });
    }

    private void getPlayerNames() {
        // Obtener el nombre del player 1
        db.collection("users")
                .document(MainActivity1.userId)
                .get()
                .addOnSuccessListener(getActivity(), new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot snapshot) {
                        nameU = snapshot.get("name").toString();
                        emailU = snapshot.get("email").toString();
                        passU = snapshot.get("password").toString();
                        ape1U = snapshot.get("apellido1").toString();
                        ape2U = snapshot.get("apellido2").toString();
                        etName.setText(nameU);
                        etEmail.setText(emailU);
                        etPass.setText(passU);
                        etApellido1.setText(ape1U);
                        etApellido2.setText(ape2U);
                    }
                });

        //Obtener imagen de perfil
        StorageReference imageRef = storageReference.child("images/" + MainActivity1.userId);

        final long ONE_MEGABYTE = (1024 * 1024) * 3;
        imageRef.getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
            @Override
            public void onSuccess(byte[] bytes) {
                Bitmap bitmap = BitmapFactory.decodeByteArray(bytes,0,bytes.length);
                profilePic.setImageBitmap(bitmap);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                // Handle any errors
            }
        });
    }
    @Override
    public void onStop() {
        /*if(listenerUser != null) {
            listenerUser.remove();
        }*/
        super.onStop();
    }
}