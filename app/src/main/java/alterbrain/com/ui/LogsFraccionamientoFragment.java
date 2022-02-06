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

import alterbrain.com.MainActivity1;
import alterbrain.com.R;
import alterbrain.com.app.Constantes;


public class LogsFraccionamientoFragment extends Fragment {

    private static String URL_fraccs = "https://missvecinos.com.mx/android/fraccs.php?nombreRecolector="+ Constantes.USU_USULOGIS.toString();
    RecyclerView recyclerView;
    MyLogsFraccionamientoRecyclerViewAdapter myLogsFraccionamientoRecyclerViewAdapter;
    List<Fraccionamiento> fraccionamientoList;

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;

    public LogsFraccionamientoFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static LogsFraccionamientoFragment newInstance(int columnCount) {
        LogsFraccionamientoFragment fragment = new LogsFraccionamientoFragment();
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

        URL_fraccs = "https://missvecinos.com.mx/android/fraccs.php?nombreRecolector="+ Constantes.USU_USULOGIS.toString();
        Toast.makeText(getContext(), ""+URL_fraccs, Toast.LENGTH_SHORT).show();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_logsfraccionamiento_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }
            fraccionamientoList = new ArrayList<>();

            /*fraccionamientoList.add(new Fraccionamiento(1,"link vacio", "FRACCIONAMIENTO INADEM", 1,15,35));
            fraccionamientoList.add(new Fraccionamiento(2,"link vacio", "FRACCIONAMIENTO SANTA MONICA", 1,15,35));
            myLogsFraccionamientoRecyclerViewAdapter = new MyLogsFraccionamientoRecyclerViewAdapter(getActivity(), fraccionamientoList);
            recyclerView.setAdapter(myLogsFraccionamientoRecyclerViewAdapter);*/

            loadFraccs();
        }
        return view;
    }

    private void loadFraccs() {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_fraccs,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray array = new JSONArray(response);

                            for (int i = 0; i < array.length(); i++) {
                                JSONObject fracc = array.getJSONObject(i);

                                fraccionamientoList.add(new Fraccionamiento(
                                        fracc.getInt("IDFraccionamiento"),
                                        fracc.getString("nombreFracc")
                                ));
                            }
                            recyclerView.setAdapter(new MyLogsFraccionamientoRecyclerViewAdapter(getContext(), fraccionamientoList));
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