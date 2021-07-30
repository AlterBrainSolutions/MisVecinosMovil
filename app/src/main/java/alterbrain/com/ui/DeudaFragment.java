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


public class DeudaFragment extends Fragment {

    RecyclerView recyclerView;
    MyDeudaRecyclerViewAdapter adapterDeuda;
    List<Deuda> deudaList;
    // TODO: Hacer una consulta a la base de datos en la que regrese las casas con noDeudaMeses mayor a cero, y otra consulta(corriente) que no

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;

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
            deudaList = new ArrayList<>();
            deudaList.add(new Deuda(5, "Sta_Monica", "Casa 5", 1, "01-06-2021"));
            deudaList.add(new Deuda(10, "Sta_Monica", "Casa 10", 2, "01-05-2021"));
            deudaList.add(new Deuda(17, "Sta_Monica", "Casa 17", 3, "01-04-2021"));
            deudaList.add(new Deuda(25, "Sta_Monica", "Casa 25", 3, "01-04-2021"));
            deudaList.add(new Deuda(27, "Sta_Monica", "Casa 27", 2, "01-05-2021"));
            deudaList.add(new Deuda(28, "Sta_Monica", "Casa 28", 1, "01-06-2021"));
            deudaList.add(new Deuda(29, "Sta_Monica", "Casa 29", 1, "01-06-2021"));
            deudaList.add(new Deuda(39, "Sta_Monica", "Casa 39", 1, "01-06-2021"));
            deudaList.add(new Deuda(41, "Sta_Monica", "Casa 41", 1, "01-06-2021"));

            //recyclerView.setAdapter(new MyDeudaRecyclerViewAdapter(DummyContent.ITEMS));
            adapterDeuda = new MyDeudaRecyclerViewAdapter(getActivity(), deudaList);
            recyclerView.setAdapter(adapterDeuda);
        }
        return view;
    }
}