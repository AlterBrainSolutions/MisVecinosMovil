package alterbrain.com.ui;

import android.content.Context;
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
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import alterbrain.com.R;



public class ServicioFragment extends Fragment {

    private static final String URL_players = "https://missvecinos.com.mx/android/servicios2.php";
    RecyclerView recyclerView;
    MyServicioRecyclerViewAdapter servicioRecyclerViewAdapter;
    List<Servicios> serviciosList;


    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;


    public ServicioFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static ServicioFragment newInstance(int columnCount) {
        ServicioFragment fragment = new ServicioFragment();
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
        View view = inflater.inflate(R.layout.fragment_servicio_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }
            // Lista de elementos (Restaurantes)
            serviciosList = new ArrayList<>();

            loadPlayers();

            /*servicioList.add(new Servicio("Casa 23", "Carpintero", "14-05-2021", "Ayer se despompuso el tercer cajon de nuestra comoda, y ya no cierra bien.", "https://missvecinos.com.mx/android/silla.jpg", "2800"));
            servicioList.add(new Servicio("", "Carpintero", "14-05-2021", "Ayer se despompuso el tercer cajon de nuestra comoda, y ya no cierra bien.", "https://missvecinos.com.mx/android/silla.jpg", "2800"));
            servicioRecyclerViewAdapter =new MyServicioRecyclerViewAdapter(getActivity(), servicioList);
            recyclerView.setAdapter(servicioRecyclerViewAdapter);*/
        }
        return view;
    }
    private void loadPlayers(){

        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_players,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray array = new JSONArray(response);

                            for (int i = 0; i < array.length(); i++) {
                                JSONObject servicio = array.getJSONObject(i);

                                serviciosList.add(new Servicios(
                                        servicio.getInt("id"),
                                        servicio.getString("casa"),
                                        servicio.getString("fecha"),
                                        servicio.getString("descripcion"),
                                        servicio.getString("imagen")
                                ));
                            }
                            recyclerView.setAdapter(new MyServicioRecyclerViewAdapter(getContext(), serviciosList));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getContext(), error.toString(), Toast.LENGTH_SHORT).show();
                    }
                });
        Volley.newRequestQueue(getContext()).add(stringRequest);
    }
}