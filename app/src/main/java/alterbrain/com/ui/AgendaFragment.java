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
public class AgendaFragment extends Fragment {

    private static final String URL_noticias = "https://missvecinos.com.mx/android/agenda.php";
    RecyclerView recyclerView;
    MyAgendaRecyclerViewAdapter adapterAgenda;
    List<Agenda> agendaList;

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public AgendaFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static AgendaFragment newInstance(int columnCount) {
        AgendaFragment fragment = new AgendaFragment();
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
        View view = inflater.inflate(R.layout.fragment_agenda_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }
            agendaList = new ArrayList<>();

            loadAgenda();
            /*agendaList.add(new Agenda("Aviso", "Vacunacion Covid-19", "Administrador", "25-07-2021"));
            agendaList.add(new Agenda("Actividad", "Torneo de futbol", "Administrador", "17-07-2021"));
            agendaList.add(new Agenda("Aviso", "Carrera 5 km", "Administrador", "15-07-2021"));
            agendaList.add(new Agenda("Actividad", "Reunion", "Administrador", "25-06-2021"));
            agendaList.add(new Agenda("Aviso", "Bienvenida", "Administrador", "jueves 09:40"));
            agendaList.add(new Agenda("Actividad", "Reparacion de Luz", "Administrador", "14-04-2021 12:40"));
            agendaList.add(new Agenda("Actividad", "Baby shower", "Administrador", "17-04-2021 09:38"));
            adapterAgenda = new MyAgendaRecyclerViewAdapter(getActivity(), agendaList);
            recyclerView.setAdapter(adapterAgenda);*/
        }
        return view;
    }

    private void loadAgenda() {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_noticias,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray array = new JSONArray(response);

                            for (int i = 0; i < array.length(); i++) {
                                JSONObject agenda = array.getJSONObject(i);

                                agendaList.add(new Agenda(
                                        agenda.getInt("idevento"),
                                        agenda.getString("tituloevento"),
                                        agenda.getString("descripcion"),
                                        agenda.getString("solicitante"),
                                        agenda.getString("fechap")

                                ));
                            }
                            recyclerView.setAdapter(new MyAgendaRecyclerViewAdapter(getContext(), agendaList));
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