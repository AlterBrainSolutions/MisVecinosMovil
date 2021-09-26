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
import alterbrain.com.model.Noticia3;

/**
 * A fragment representing a list of Items.
 */
public class NoticiaFragment3 extends Fragment {

    //TODO Modificar la consulta para que no consulte la descripcion de la noticia, y esta se pueda saber por separado con ConsultaNtcActivity
    private static final String URL_noticias = "https://missvecinos.com.mx/android/noticias.php";
    List<Noticia3> noticiasList;
    RecyclerView recyclerView;

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public NoticiaFragment3() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static NoticiaFragment3 newInstance(int columnCount) {
        NoticiaFragment3 fragment = new NoticiaFragment3();
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
        View view = inflater.inflate(R.layout.fragment_noticia3_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }
            noticiasList = new ArrayList<>();

            loadPlayers();

            //recyclerView.setAdapter(new MyNoticiaRecyclerViewAdapter3(DummyContent.ITEMS));
        }
        return view;
    }

    private void loadPlayers(){

        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_noticias,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray array = new JSONArray(response);

                            for (int i = 0; i < array.length(); i++) {
                                JSONObject noticia = array.getJSONObject(i);

                                noticiasList.add(new Noticia3(
                                        noticia.getInt("idNoticia"),
                                        noticia.getString("tituloNoticia"),
                                        noticia.getString("descripcion"),
                                        noticia.getString("fecha"),
                                        noticia.getString("imagen")
                                ));
                            }
                            recyclerView.setAdapter(new MyNoticiaRecyclerViewAdapter3(getContext(), noticiasList));
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