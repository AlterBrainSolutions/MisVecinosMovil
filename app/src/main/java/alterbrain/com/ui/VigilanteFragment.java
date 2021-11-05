package alterbrain.com.ui;

import android.content.Intent;
import android.os.Bundle;

import androidx.constraintlayout.solver.state.State;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.w3c.dom.Text;

import alterbrain.com.R;
import alterbrain.com.app.Constantes;


public class VigilanteFragment extends Fragment {


    TextView tvNomV;
    ConstraintLayout constraintLayout;
    ImageView ivFondoHome;

    public VigilanteFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_vigilante, container, false);

        constraintLayout = view.findViewById(R.id.cnstrLayoutVgl);
        tvNomV = view.findViewById(R.id.textViewNomVig);
        ivFondoHome = view.findViewById(R.id.imageViewHomVig);

        tvNomV.setText(Constantes.NOM_VIG);

        constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), ListCasasVigSegActivity.class);
                getActivity().startActivity(i);

            }
        });

        Glide.with(this)
                .load(Constantes.IMG_FRACC)
                .centerCrop()
                .into(ivFondoHome);
        return view;
    }
}