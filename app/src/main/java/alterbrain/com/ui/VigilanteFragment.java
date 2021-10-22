package alterbrain.com.ui;

import android.content.Intent;
import android.os.Bundle;

import androidx.constraintlayout.solver.state.State;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import alterbrain.com.R;


public class VigilanteFragment extends Fragment {


    ConstraintLayout constraintLayout;

    public VigilanteFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_vigilante, container, false);

        constraintLayout = view.findViewById(R.id.cnstrLayoutVgl);

        constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), ProtocoloVglActivity.class);
                getActivity().startActivity(i);

            }
        });
        return view;
    }
}