package alterbrain.com.ui;

import android.content.Intent;
import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import alterbrain.com.AgendaActivity;
import alterbrain.com.R;


public class ServiciosFragment extends Fragment {

    TextView tvDescrip;
    ImageView ivNoticias, ivAgenda, ivDocumentos, ivManita, btnCerrar, ivTransparency, ivPagos, ivAdeudos, ivEncuestas;
    ImageView btnMas, btnAnuncio, btnReserva, btnServicio, btnBuzon;
    Button btnSer;
    ConstraintLayout constraintMenuPop;
    RelativeLayout rlHome;
    String descri;
    FirebaseFirestore db;

    private ImageView profilePic;
    private FirebaseStorage storage;
    private StorageReference storageReference;

    public ServiciosFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root =  inflater.inflate(R.layout.fragment_servicios, container, false);
        tvDescrip = root.findViewById(R.id.textViewDescripcion);
        ivManita = root.findViewById(R.id.imageView_mas);
        btnCerrar = root.findViewById(R.id.imageViewCerrarPop);
        constraintMenuPop = root.findViewById(R.id.constraintMenuPop);
        //btnSer = root.findViewById(R.id.buttonDetalleSrv);

        tvDescrip.setText("Pedro Mirafuentes");

        ivManita.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                constraintMenuPop.setVisibility(View.VISIBLE);
                //rlHome.setBackground(v.setBackground();
            }
        });
        /*btnCerrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                constraintMenuPop.setVisibility(View.GONE);
            }
        });
        /*btnSer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), DetalleSrvActivity.class);
                startActivity(i);
            }
        });*/

        return root;
    }
}