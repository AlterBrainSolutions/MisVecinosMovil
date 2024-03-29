package alterbrain.com.ui;

import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import alterbrain.com.MainActivity1;
import alterbrain.com.R;
import alterbrain.com.app.Constantes;

public class SalirFragment extends Fragment {

    Button btnCierraSesion;
    TextView tvDescrip;
    String descri;
    FirebaseFirestore db;
    SharedPreferences sharedPreferences;

    private ImageView profilePic;
    private FirebaseStorage storage;
    private StorageReference storageReference;

    private SalirViewModel mViewModel;

    public static SalirFragment newInstance() {
        return new SalirFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.salir_fragment, container, false);
        btnCierraSesion = root.findViewById(R.id.buttonCerrarSesion);

        sharedPreferences = this.getActivity().getSharedPreferences("sesion_usuario", Context.MODE_PRIVATE);
        tvDescrip = root.findViewById(R.id.textViewDescripcionExit);
        profilePic = root.findViewById(R.id.imageView_faceExit);
        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();
        SharedPreferences.Editor editor = sharedPreferences.edit();

        btnCierraSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Constantes.NOM = "";
                Constantes.PAS = "";
                /*editor.remove("sesion_iniciada");
                editor.remove("usuario");
                editor.remove("contra");*/
                editor.clear();
                editor.putBoolean("sesion_iniciada", false);
                editor.commit();

                FirebaseAuth.getInstance().signOut();
                getActivity().finishAffinity();


            }
        });


        db = FirebaseFirestore.getInstance();

        //getPlayerNames();
        //getNameUsu();
        return root;
    }

    private void getNameUsu() {
        descri = Constantes.NOM_USR;
        tvDescrip.setText(descri);
    }

    private void getPlayerNames() {
        // Obtener el nombre del player 1
        db.collection("users")
                .document(MainActivity1.userId.toString())
                .get()
                .addOnSuccessListener(getActivity(), new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot snapshot) {
                        descri = snapshot.get("name").toString();
                        tvDescrip.setText(descri);
                    }
                });

        //Obtener imagen de perfil
        StorageReference imageRef = storageReference.child("images/" + MainActivity1.userId);

        final long ONE_MEGABYTE = 1024 * 1024;
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
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(SalirViewModel.class);
        // TODO: Use the ViewModel
    }

}