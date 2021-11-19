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
import alterbrain.com.ui.placeholder.PlaceholderContent;


public class AlertasVigFragment extends Fragment {

    private static final String URL_players = "https://missvecinos.com.mx/android/alertasvig.php?idVigSeg="+ Constantes.ID_VIG;

    RecyclerView recyclerView;
    MyAlertasVigRecyclerViewAdapter myAlertasVigRecyclerViewAdapter;
    List<AlertasVig> alertasVigList;

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;


    public AlertasVigFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static AlertasVigFragment newInstance(int columnCount) {
        AlertasVigFragment fragment = new AlertasVigFragment();
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
        View view = inflater.inflate(R.layout.fragment_alertasvig_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }
            alertasVigList = new ArrayList<>();
            //recyclerView.setAdapter(new MyAlertasVigRecyclerViewAdapter(PlaceholderContent.ITEMS));

            loadAlertas();
        }
        return view;
    }

    private void loadAlertas() {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_players,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray array = new JSONArray(response);

                            for (int i = 0; i < array.length(); i++) {
                                JSONObject alerta = array.getJSONObject(i);

                                alertasVigList.add(new AlertasVig(
                                        alerta.getInt("idSeguridad"),
                                        alerta.getString("casa"),
                                        alerta.getString("horaActivacion")
                                ));
                            }
                            recyclerView.setAdapter(new MyAlertasVigRecyclerViewAdapter(getContext(), alertasVigList));
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