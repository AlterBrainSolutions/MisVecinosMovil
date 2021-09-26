package alterbrain.com.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import alterbrain.com.R;
import alterbrain.com.app.Constantes;


public class LogisticaFragment extends Fragment {

    TextView tvDatosUsu;
    String datosUsu;
    public LogisticaFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_logistica, container, false);
        tvDatosUsu = root.findViewById(R.id.textViewDatosUsuLogs);

        datosUsu = Constantes.NOM_USULOGIS + " " + Constantes.APELL_USULOGIS + "\n Vehículo " + Constantes.UNIDAD_USULOGIS;
        tvDatosUsu.setText(datosUsu);
        return root;
    }
}