package alterbrain.com.ui.home;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

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
import alterbrain.com.AdeudosActivity;
import alterbrain.com.AgendaActivity;
import alterbrain.com.AnuncioActivity;
import alterbrain.com.Documentos2Activity;
import alterbrain.com.EncuestasActivity;
import alterbrain.com.MainActivity1;
import alterbrain.com.NoticiasActivity;
import alterbrain.com.PagosActivity;
import alterbrain.com.R;
import alterbrain.com.ReservaActivity;
import alterbrain.com.ServiciosActivity;
import alterbrain.com.Transparencia2Activity;
import alterbrain.com.Transparencia6Activity;

public class HomeFragment extends Fragment {
    TextView tvDescrip;
    ImageView ivNoticias, ivAgenda, ivDocumentos, ivManita, ivTransparency, ivPagos, ivAdeudos, ivEncuestas;
    ImageView btnMas,btnCerrar, btnAnuncio, btnReserva, btnServicio, btnBuzon;
    ConstraintLayout constraintMenuPop;
    RelativeLayout rlHome;
    String descri;
    FirebaseFirestore db;

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
        ivManita = root.findViewById(R.id.imageView_mas);
        ivTransparency = root.findViewById(R.id.imageViewTransparenciah);
        ivPagos = root.findViewById(R.id.imageViewPagosh);
        ivAdeudos = root.findViewById(R.id.imageViewAdeudosh);
        ivEncuestas = root.findViewById(R.id.imageViewEncuestas);
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
                Intent noticia = new Intent(getActivity(), NoticiasActivity.class);
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
        ivAgenda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), AgendaActivity.class);
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
                Intent i = new Intent(getActivity(), Transparencia6Activity.class);
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
                Intent i = new Intent(getActivity(), ServiciosActivity.class);
                startActivity(i);
            }
        });
        profilePic = root.findViewById(R.id.imageView_face);
        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();

        getPlayerNames();
        //MainActivity1.userId = "";

        return root;
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
}