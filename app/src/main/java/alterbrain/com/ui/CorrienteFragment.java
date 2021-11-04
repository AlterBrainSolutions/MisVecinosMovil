package alterbrain.com.ui;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import alterbrain.com.R;
import alterbrain.com.app.Constantes;
import alterbrain.com.model.Adeudos;
import alterbrain.com.model.Noticia3;


public class CorrienteFragment extends Fragment {

    RecyclerView recyclerView;
    MyCorrienteRecyclerViewAdapter adapterDeuda;
    List<Adeudos> deudaList;
    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;
    private int usuario = Constantes.ID_USR;
    private String URL_corriente = "https://missvecinos.com.mx/android/adeudosConsulta.php?usuario=" + usuario;
    private RequestQueue mQueue;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public CorrienteFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static CorrienteFragment newInstance(int columnCount) {
        CorrienteFragment fragment = new CorrienteFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_corriente_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }
            deudaList = new ArrayList<>();

            mQueue = Volley.newRequestQueue(getActivity());

            jsonParse2();
            //recyclerView.setAdapter(new MyCorrienteRecyclerViewAdapter(DummyContent.ITEMS));
            /*adapterDeuda = new MyCorrienteRecyclerViewAdapter(getActivity(), deudaList);
            recyclerView.setAdapter(adapterDeuda);*/
        }
        return view;
    }

    private void jsonParse2() {

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, URL_corriente, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray resultados = response.getJSONArray("al corriente");

                            int tamRes = resultados.length();

                            for (int i = 0; i < tamRes; i++) {

                                JSONObject jsonObject = new JSONObject(resultados.get(i).toString());

                                int idUsuario = jsonObject.getInt("idAbonoRecibo");
                                int estatus = jsonObject.getInt("idAbonoEstatus");
                                String casa = jsonObject.getString("numeroCasa");
                                int fraccionamiento = jsonObject.getInt("idFraccionamientoUsuarios");

                                deudaList.add(new Adeudos(idUsuario, estatus, casa, fraccionamiento));

                               /* Toast.makeText(CorrienteFragment.this,
                                        "Un mes: " + casa, Toast.LENGTH_SHORT).show();*/
                            }
                            recyclerView.setAdapter(new MyCorrienteRecyclerViewAdapter(getContext(), deudaList));



                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        mQueue.add(request);
    }

}