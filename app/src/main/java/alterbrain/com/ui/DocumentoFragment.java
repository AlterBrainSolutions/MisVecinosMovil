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

import java.util.ArrayList;
import java.util.List;

import alterbrain.com.R;

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
            documentoList.add(new Documento("CALENDARIO DE ACTIVIDADES", "20-02-2020", "Documents/Fracci01"));
            documentoList.add(new Documento("Instrucciones de pagos e Instrucciones de rembolsos", "20-06-2020", "Documents/Fracci01"));
            documentoList.add(new Documento("Bienvenida", "24-02-2020", "Documents/Fracci01"));
            documentoList.add(new Documento("Instrucciones de pagos", "06-02-2021", "Documents/Fracci01"));
            documentoList.add(new Documento("Pago de facturas", "22-04-2020", "Documents/Fracci01"));

            adapterDocumento = new MyDocumentoRecyclerViewAdapter(getActivity(), documentoList);
            recyclerView.setAdapter(adapterDocumento);
        }
        return view;
    }
}