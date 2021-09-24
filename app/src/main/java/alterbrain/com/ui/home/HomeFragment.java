package alterbrain.com.ui.home;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import alterbrain.com.AccesosActivity;
import alterbrain.com.ActivityDetalleAyuda;
import alterbrain.com.AdeudosActivity;
import alterbrain.com.AgendaActivity;
import alterbrain.com.AnuncioActivity;
import alterbrain.com.ConversacionActivity;
import alterbrain.com.Documentos2Activity;
import alterbrain.com.EncuestasActivity;
import alterbrain.com.MainActivity1;
import alterbrain.com.Noticias3Activity;
import alterbrain.com.PagosActivity;
import alterbrain.com.R;
import alterbrain.com.ReciclajeActivity;
import alterbrain.com.ReservaActivity;
import alterbrain.com.ServiciosActivity3;
import alterbrain.com.Transparencia7Activity;
import alterbrain.com.app.Constantes;

public class HomeFragment extends Fragment {
    TextView tvDescrip;
    ImageView ivNoticias, ivAgenda, ivDocumentos, ivManita, ivTransparency, ivPagos, ivAdeudos, ivEncuestas, ivConversacion, ivReciclaje;
    ImageView btnMas, btnCerrar, btnAnuncio, btnReserva, btnServicio, btnBuzon;
    ConstraintLayout constraintMenuPop;
    RelativeLayout rlHome;
    String descri;
    FirebaseFirestore db;
    boolean yaloVioXD = false;

    private ImageView profilePic;
    private FirebaseStorage storage;
    private StorageReference storageReference;

    private HomeViewModel homeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        tvDescrip = root.findViewById(R.id.textViewDescripcion);
        ivNoticias = root.findViewById(R.id.imageViewNoticias);
        ivAgenda = root.findViewById(R.id.imageViewAgenda);
        ivDocumentos = root.findViewById(R.id.imageViewDocumentosh);
        ivReciclaje = root.findViewById(R.id.imageViewReciclaje);
        ivManita = root.findViewById(R.id.imageView_mas);
        ivTransparency = root.findViewById(R.id.imageViewTransparenciah);
        ivPagos = root.findViewById(R.id.imageViewPagosh);
        ivAdeudos = root.findViewById(R.id.imageViewAdeudosh);
        ivEncuestas = root.findViewById(R.id.imageViewEncuestas);
        ivConversacion = root.findViewById(R.id.imageViewConversacion);
        btnCerrar = root.findViewById(R.id.imageViewCerrarPop);
        btnAnuncio = root.findViewById(R.id.imageViewAnuncios);
        btnReserva = root.findViewById(R.id.imageViewReserva);
        btnBuzon = root.findViewById(R.id.imageViewBuzon);
        btnServicio = root.findViewById(R.id.imageViewServicios);
        constraintMenuPop = root.findViewById(R.id.constraintMenuPop);
        rlHome = root.findViewById(R.id.relativeLayoutHomeServ);

        db = FirebaseFirestore.getInstance();

        ivNoticias.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent noticia = new Intent(getActivity(), Noticias3Activity.class);
                getActivity().startActivity(noticia);
            }
        });
        ivDocumentos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent detail = new Intent(getActivity(), Documentos2Activity.class);
                getActivity().startActivity(detail);
            }
        });
        ivReciclaje.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Toast.makeText( HomeFragment.this.getActivity(),
                        "Booleasno "+Boolean.toString(getSavedPreferences()), Toast.LENGTH_SHORT).show();*/
                if (getSavedPreferences() == true) {
                    Intent detail = new Intent(getActivity(), ReciclajeActivity.class);
                    getActivity().startActivity(detail);
                } else {
                    Intent detail = new Intent(getActivity(), ActivityDetalleAyuda.class);
                    getActivity().startActivity(detail);
                }
            }
        });
        ivAgenda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), AgendaActivity.class);
                startActivity(i);
            }
        });
        ivConversacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), ConversacionActivity.class);
                startActivity(i);
            }
        });
        btnCerrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                constraintMenuPop.setVisibility(View.GONE);
            }
        });
        ivManita.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                constraintMenuPop.setVisibility(View.VISIBLE);
                //rlHome.setBackground(v.setBackground();
            }
        });
        ivTransparency.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), Transparencia7Activity.class);
                startActivity(i);
            }
        });
        ivPagos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), PagosActivity.class);
                startActivity(i);
            }
        });
        ivAdeudos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), AdeudosActivity.class);
                startActivity(i);
            }
        });
        ivEncuestas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), EncuestasActivity.class);
                startActivity(i);
            }
        });
        btnAnuncio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), AnuncioActivity.class);
                startActivity(i);
            }
        });
        btnReserva.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), ReservaActivity.class);
                startActivity(i);
            }
        });
        btnBuzon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), AccesosActivity.class);
                startActivity(i);
            }
        });
        btnServicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), ServiciosActivity3.class);
                startActivity(i);
            }
        });
        profilePic = root.findViewById(R.id.imageView_face);
        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();

        //getPlayerNames();
        //MainActivity1.userId = "";

        //Nombre usu
        getUsuName();

        return root;
    }

    /*public void savePreferences(){
        SharedPreferences preferences = this.getActivity().getSharedPreferences("usrPreferences", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean("isOpenedfor"+ Constantes.ID_USR, true);
        editor.commit();
    }*/

    public boolean getSavedPreferences(){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(HomeFragment.this.getActivity());
        return preferences.getBoolean("isOpenedfor"+ Constantes.ID_USR, false);
    }

    private void getUsuName() {
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

        final long ONE_MEGABYTE = (1024 * 1024) * 3;
        imageRef.getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
            @Override
            public void onSuccess(byte[] bytes) {
                Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                profilePic.setImageBitmap(bitmap);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                // Handle any errors
            }
        });

    }
}