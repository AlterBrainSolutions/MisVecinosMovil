package alterbrain.com.ui;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.constraintlayout.solver.state.State;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.Map;

import alterbrain.com.LoginActivity2;
import alterbrain.com.MainActivity5;
import alterbrain.com.R;
import alterbrain.com.app.Constantes;


public class VigilanteFragment extends Fragment implements Response.Listener<JSONObject>, Response.ErrorListener{

    //private String URLaler = "https://missvecinos.com.mx/android/consultanumaler.php";
    RequestQueue rq;
    JsonRequest jr;
    String numalerts = "";
    TextView tvNomV, tvNumAler, tvNomFr;
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

        rq = Volley.newRequestQueue(getContext());
        consultaAlertas();

        constraintLayout = view.findViewById(R.id.cnstrLayoutVgl);
        tvNomV = view.findViewById(R.id.textViewNomVig);
        tvNumAler = view.findViewById(R.id.textViewNumAlerVig);
        tvNomFr = view.findViewById(R.id.textViewNomFraccVig);
        ivFondoHome = view.findViewById(R.id.imageViewHomVig);

        tvNomV.setText(Constantes.NOM_VIG);
        tvNomFr.setText("FRACCIONAMIENTO "+Constantes.NOM_FRACC);

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

    private void consultaAlertas() {
        String url ="https://missvecinos.com.mx/android/consultanumaler.php?idVigilante="+ Constantes.ID_VIG;

        jr = new JsonObjectRequest(Request.Method.GET,url,null, this, this);
        rq.add(jr);
    }


    @Override
    public void onErrorResponse(VolleyError error) {
        Toast.makeText(getContext(), "No se encontró el numero de alertas " +error.toString(), Toast.LENGTH_LONG).show();
    }

    @Override
    public void onResponse(JSONObject response) {
        Toast.makeText(getContext(), "Se ha encontrado el número de alertas " +numalerts, Toast.LENGTH_SHORT).show();

        JSONArray jsonArray = response.optJSONArray("datos");
        JSONObject jsonObject = null;

        try {
            jsonObject = jsonArray.getJSONObject(0);

            numalerts = jsonObject.optString("numAler");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        tvNumAler.setText(numalerts+" ALERTAS");
    }
}