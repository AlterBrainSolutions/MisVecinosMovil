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
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import alterbrain.com.R;
import alterbrain.com.app.Constantes;

/**
 * A fragment representing a list of Items.
 */
public class AgendaFragment extends Fragment {

    private static String URL_agenda;
    RecyclerView recyclerView;
    MyAgendaRecyclerViewAdapter adapterAgenda;
    List<Agenda> agendaList;
    private RequestQueue mQueue;


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
        /*Toast.makeText(AgendaFragment.this.getActivity(), Constantes.IDFRACC_USR +"/" + Constantes.DAY_SELECTED
                +"/" + Constantes.MONTH_SELECTED +"/" +Constantes.YEAR_SELECTED +"/" +
                "AgendaFragment", Toast.LENGTH_LONG).show();*/
        URL_agenda = "https://missvecinos.com.mx/android/agenda.php?fracc="
                +Constantes.IDFRACC_USR +"&dia="+Constantes.DAY_SELECTED
                +"&mes="+Constantes.MONTH_SELECTED+"&anio="+Constantes.YEAR_SELECTED;
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

            mQueue = Volley.newRequestQueue(AgendaFragment.this.getActivity());

            loadAgenda();
            /*agendaList.add(new Agenda(1,"Aviso", "Vacunacion Covid-19", "Administrador", "25-07-2021"));
            agendaList.add(new Agenda(2,"Actividad", "Torneo de futbol", "Administrador", "17-07-2021"));
            agendaList.add(new Agenda(3,"Aviso", "Carrera 5 km", "Administrador", "15-07-2021"));
            agendaList.add(new Agenda(4,"Actividad", "Reunion", "Administrador", "25-06-2021"));
            agendaList.add(new Agenda(5,"Aviso", "Bienvenida", "Administrador", "jueves 09:40"));
            agendaList.add(new Agenda(6,"Actividad", "Reparacion de Luz", "Administrador", "14-04-2021 12:40"));
            agendaList.add(new Agenda(7,"Actividad", "Baby shower", "Administrador", "17-04-2021 09:38"));
            adapterAgenda = new MyAgendaRecyclerViewAdapter(getActivity(), agendaList);*/
            recyclerView.setAdapter(adapterAgenda);
        }
        return view;
    }

    private void loadAgenda() {
        /*StringRequest stringRequest = new StringRequest(Request.Method.GET,
                URL_noticias = "https://missvecinos.com.mx/android/agenda.php?fracc="
                        +Constantes.ID_FRACC+"&dia="+Constantes.DAY_SELECTED
                        +"&mes="+Constantes.MONTH_SELECTED+"&year="+Constantes.YEAR_SELECTED,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray array = new JSONArray(response);

                            *//*TODO
                            *  agenda.idevento, agenda.idEventoFracc,
                            *  agenda.tituloevento, agenda.imagen, agenda.fechap,
                            *  agenda.descripcion,
                            *  agenda.solicitante
                            *  *//*

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
        Volley.newRequestQueue(getContext()).add(stringRequest);*/

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, URL_agenda, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {

                            /*TODO ---------------------------AGENDA----------------------------------------*/

                            /*TODO
                            *  agenda.idevento, agenda.idEventoFracc,
                            *  agenda.tituloevento, agenda.imagen, agenda.fechap,
                            *  agenda.descripcion,
                            *  agenda.solicitante
                            */

                            JSONArray agendaResultados = response.getJSONArray("agenda");

                            int tamRes1 = agendaResultados.length(), idevento = 0, ideventofracc = 0;
                            String tituloEv="", imagen= "", descripcion="", solicitante="", fechap="";

                            for (int i = 0; i < tamRes1; i++) {

                                JSONObject jsonObject = new JSONObject(agendaResultados.get(i).toString());

                                idevento = jsonObject.getInt("idevento");
                                ideventofracc = jsonObject.getInt("idEventoFracc");
                                tituloEv = jsonObject.getString("tituloevento");
                                imagen = jsonObject.getString("imagen");
                                fechap = jsonObject.getString("fechap");
                                descripcion = jsonObject.getString("descripcion");
                                solicitante = jsonObject.getString("solicitante");

                                /*jsonObject.getInt("idevento"),
                                jsonObject.getString("tituloevento"),
                                jsonObject.getString("descripcion"),
                                jsonObject.getString("solicitante"),
                                jsonObject.getString("fechap")*/

                                /*
                                {"agenda":[
                                    {"idevento":"42",
                                    "idEventoFracc":"4",
                                    "tituloevento":"TORTILLAS RICAS",
                                    "imagen":"..\/imgE\/tortillas.jpg",
                                    "fechap":"2022-02-20",
                                    "descripcion":"Hola muy buenas tardes a todos. Solo quer\u00eda comentarles que si alguien gusta adquirir tortillas blancas las estar\u00e1n entregando de lunes a s\u00e1bado a las 11:00 am est\u00e1n muy ricas yo las consumos y salen muy buenas suaves y no se hacen tiesas.",\
                                    "solicitante":"Casa 29"
                                    }]}
                                */

                                agendaList.add(new Agenda(
                                        idevento,
                                        tituloEv,
                                        descripcion,
                                        solicitante,
                                        fechap
                                ));

                                /*JSONObject jsonObject = new JSONObject(resultados1.get(i).toString());

                                cantidad += Float.parseFloat(jsonObject.getString("cantidad"));

                                if(i == 0){
                                    auxCantidad = cantidad;
                                }*/
                                /*View abonosView = getLayoutInflater().inflate(R.layout.row_egresos, null, false);*/

                            }
                            recyclerView.setAdapter(new MyAgendaRecyclerViewAdapter(getContext(), agendaList));
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