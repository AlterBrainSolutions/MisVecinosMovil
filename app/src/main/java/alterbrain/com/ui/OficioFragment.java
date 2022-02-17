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
import alterbrain.com.app.Constantes;
import alterbrain.com.model.Noticia3;


/**
 * A fragment representing a list of Items.
 */
public class OficioFragment extends Fragment {

    //String que guarda la url donde se almacena el PHP con la consulta que traera la lista de oficios
    private static String URL_oficios = "https://missvecinos.com.mx/android/oficios.php";
    //lista de objetos oficio, que guardan los oficios registrados en la web
    List<Oficio> oficioList;
    //recyclerView que sera utilizado para mostrar la fragment_list
    RecyclerView recyclerView;

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public OficioFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static OficioFragment newInstance(int columnCount) {
        OficioFragment fragment = new OficioFragment();
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
        //para actualizar la lista en caso de que se recargue el fragment
        URL_oficios = "https://missvecinos.com.mx/android/oficios.php";
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_oficio_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }
            oficioList = new ArrayList<>();

            loadOficios();
            //recyclerView.setAdapter(new MyOficioRecyclerViewAdapter(PlaceholderContent.ITEMS));
        }
        return view;
    }

    private void loadOficios() {
        //medodo usado con la libreria volley para obtner la lista de oficios
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_oficios,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray array = new JSONArray(response);

                            for (int i = 0; i < array.length(); i++) {
                                JSONObject oficio = array.getJSONObject(i);

                                oficioList.add(new Oficio(
                                        oficio.getInt("idOficio"),
                                        oficio.getString("oficio"),
                                        oficio.getString("direccion"),
                                        oficio.getString("telefono"),
                                        oficio.getString("email"),
                                        oficio.getString("imgP"),
                                        oficio.getString("imgE")
                                ));
                            }
                            recyclerView.setAdapter(new MyOficioRecyclerViewAdapter(getContext(), oficioList));
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