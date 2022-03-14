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
import alterbrain.com.model.Adeudos;
import alterbrain.com.model.Noticia3;

/**
 * A fragment representing a list of Items.
 */
public class DocumentoFragment extends Fragment {

    RecyclerView recyclerView;
    MyDocumentoRecyclerViewAdapter adapterDocumento;
    List<Documento> documentoList;

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;
    private int usuario = Constantes.ID_USR;
    private String URL_documentos = "https://missvecinos.com.mx/android/consultaDocumentos.php?usuario=" + usuario;
    private RequestQueue mQueue;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public DocumentoFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static DocumentoFragment newInstance(int columnCount) {
        DocumentoFragment fragment = new DocumentoFragment();
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
        View view = inflater.inflate(R.layout.fragment_documento_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }
            documentoList = new ArrayList<>();

            mQueue = Volley.newRequestQueue(getActivity());

            jsonParse2();
            /*documentoList = new ArrayList<>();
            documentoList.add(new Documento("CALENDARIO DE ACTIVIDADES", "20-02-2020", "Documents/Fracci01"));
            documentoList.add(new Documento("Instrucciones de pagos e Instrucciones de rembolsos", "20-06-2020", "Documents/Fracci01"));
            documentoList.add(new Documento("Bienvenida", "24-02-2020", "Documents/Fracci01"));
            documentoList.add(new Documento("Instrucciones de pagos", "06-02-2021", "Documents/Fracci01"));
            documentoList.add(new Documento("Pago de facturas", "22-04-2020", "Documents/Fracci01"));

            adapterDocumento = new MyDocumentoRecyclerViewAdapter(getActivity(), documentoList);
            recyclerView.setAdapter(adapterDocumento);*/
        }
        return view;
    }

    private void jsonParse2() {

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, URL_documentos, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray documentosResult = response.getJSONArray("documentos");

                            for (int i = 0; i < documentosResult.length(); i++) {
                                JSONObject documentos = documentosResult        .getJSONObject(i);

                                documentoList.add(new Documento(
                                        documentos.getInt("idDocumento"),
                                        documentos.getInt("idDocFracc"),
                                        documentos.getString("nombre"),
                                        documentos.getString("fechaP"),
                                        documentos.getString("archivo")
                                ));
                            }
                            recyclerView.setAdapter(new MyDocumentoRecyclerViewAdapter(getContext(), documentoList));

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