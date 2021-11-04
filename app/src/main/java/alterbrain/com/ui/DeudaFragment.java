package alterbrain.com.ui;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;

import alterbrain.com.R;
import alterbrain.com.app.Constantes;
import alterbrain.com.model.Adeudos;


public class DeudaFragment extends Fragment {

    RecyclerView recyclerView;
    MyDeudaRecyclerViewAdapter adapterDeuda;
    List<Adeudos> deudaList;
    private int mColumnCount = 1;
    private int usuario = Constantes.ID_USR;
    private String URL_corriente = "https://missvecinos.com.mx/android/adeudosConsulta.php?usuario=" + usuario;
    private RequestQueue mQueue;
    // TODO: Hacer una consulta a la base de datos en la que regrese las casas con noDeudaMeses mayor a cero, y otra consulta(corriente) que no

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public DeudaFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static DeudaFragment newInstance(int columnCount) {
        DeudaFragment fragment = new DeudaFragment();
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
        View view = inflater.inflate(R.layout.fragment_deuda_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }
            /*deudaList = new ArrayList<>();
            deudaList.add(new Deuda(5, "Sta_Monica", "Casa 5", 1, "01-06-2021"));
            deudaList.add(new Deuda(10, "Sta_Monica", "Casa 10", 2, "01-05-2021"));
            deudaList.add(new Deuda(17, "Sta_Monica", "Casa 17", 3, "01-04-2021"));
            deudaList.add(new Deuda(25, "Sta_Monica", "Casa 25", 3, "01-04-2021"));
            deudaList.add(new Deuda(27, "Sta_Monica", "Casa 27", 2, "01-05-2021"));
            deudaList.add(new Deuda(28, "Sta_Monica", "Casa 28", 1, "01-06-2021"));
            deudaList.add(new Deuda(29, "Sta_Monica", "Casa 29", 1, "01-06-2021"));
            deudaList.add(new Deuda(39, "Sta_Monica", "Casa 39", 1, "01-06-2021"));
            deudaList.add(new Deuda(41, "Sta_Monica", "Casa 41", 1, "01-06-2021"));
*/
            //recyclerView.setAdapter(new MyDeudaRecyclerViewAdapter(DummyContent.ITEMS));
            /*adapterDeuda = new MyDeudaRecyclerViewAdapter(getActivity(), deudaList);
            recyclerView.setAdapter(adapterDeuda);*/
            deudaList = new ArrayList<>();

            mQueue = Volley.newRequestQueue(getActivity());

            jsonParse2();
        }
        return view;
    }

    private void jsonParse2() {

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, URL_corriente, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray resultados = response.getJSONArray("resultados");

                            ArrayList<Integer> listdata = new ArrayList<Integer>();

                            int tamRes = resultados.length();

                            for (int i = 0; i < tamRes; i++) {

                                JSONObject jsonObject = new JSONObject(resultados.get(i).toString());

                                listdata.add(Integer.valueOf(jsonObject.getString("numeroCasa")));

                                /*if (i > 0) {
                                    JSONObject jsonObject2 = new JSONObject(resultados.get(i - 1).toString());

                                    if (Integer.valueOf(jsonObject.getString("numeroCasa"))
                                            != Integer.valueOf(jsonObject2.getString("numeroCasa"))) {

                                        masDedos += contadorAux;
                                        contadorAux = 0;
                                        numero++;
                                    } else {
                                        contadorAux++;
                                    }
                                }*/
                            }

                            JSONArray resultados2 = response.getJSONArray("con adeudo");

                            int tamRes2 = resultados2.length(), occurrences = 0;

                            for (int i = 0; i < tamRes2; i++) {

                                JSONObject jsonObject = new JSONObject(resultados2.get(i).toString());

                                String casa = jsonObject.getString("numeroCasa");

                                /*Toast.makeText(getActivity(),
                                "Un mes: " + listdata.size() +"json "+ tamRes, Toast.LENGTH_SHORT).show();
                                */

                                occurrences = Collections.frequency(listdata, Integer.valueOf(casa));

                                /*System.out.println(occurrences);*/
                                Log.d(String.valueOf(i), String.valueOf(occurrences));


                               deudaList.add(new Adeudos(casa, occurrences));
                            }

                            recyclerView.setAdapter(new MyDeudaRecyclerViewAdapter(getContext(), deudaList));


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