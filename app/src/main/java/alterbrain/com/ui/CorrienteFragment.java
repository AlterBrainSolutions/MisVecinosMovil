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


public class CorrienteFragment extends Fragment {

    RecyclerView recyclerView;
    MyCorrienteRecyclerViewAdapter adapterDeuda;
    List<Deuda> deudaList;
    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public CorrienteFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static CorrienteFragment newInstance(int columnCount) {
        CorrienteFragment fragment = new CorrienteFragment();
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
        View view = inflater.inflate(R.layout.fragment_corriente_list, container, false);

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
            deudaList.add(new Deuda(1, "Sta_Monica", "Casa 1", 0, ""));
            deudaList.add(new Deuda(2, "Sta_Monica", "Casa 2", 0, ""));
            deudaList.add(new Deuda(3, "Sta_Monica", "Casa 3", 0, ""));
            deudaList.add(new Deuda(4, "Sta_Monica", "Casa 4", 0, ""));
            deudaList.add(new Deuda(6, "Sta_Monica", "Casa 6", 0, ""));
            deudaList.add(new Deuda(7, "Sta_Monica", "Casa 7", 0, ""));
            deudaList.add(new Deuda(8, "Sta_Monica", "Casa 8", 0, ""));
            deudaList.add(new Deuda(9, "Sta_Monica", "Casa 9", 0, ""));
            deudaList.add(new Deuda(11, "Sta_Monica", "Casa 11", 0, ""));
            deudaList.add(new Deuda(12, "Sta_Monica", "Casa 12", 0, ""));
            deudaList.add(new Deuda(13, "Sta_Monica", "Casa 13", 0, ""));
            deudaList.add(new Deuda(14, "Sta_Monica", "Casa 14", 0, ""));
            deudaList.add(new Deuda(15, "Sta_Monica", "Casa 15", 0, ""));
            deudaList.add(new Deuda(16, "Sta_Monica", "Casa 16", 0, ""));
            deudaList.add(new Deuda(18, "Sta_Monica", "Casa 18", 0, ""));
            deudaList.add(new Deuda(19, "Sta_Monica", "Casa 19", 0, ""));
            deudaList.add(new Deuda(20, "Sta_Monica", "Casa 20", 0, ""));
            deudaList.add(new Deuda(21, "Sta_Monica", "Casa 21", 0, ""));
            deudaList.add(new Deuda(22, "Sta_Monica", "Casa 22", 0, ""));
            deudaList.add(new Deuda(23, "Sta_Monica", "Casa 23", 0, ""));
            deudaList.add(new Deuda(24, "Sta_Monica", "Casa 24", 0, ""));
            deudaList.add(new Deuda(26, "Sta_Monica", "Casa 26", 0, ""));
            deudaList.add(new Deuda(30, "Sta_Monica", "Casa 30", 0, ""));
            deudaList.add(new Deuda(31, "Sta_Monica", "Casa 31", 0, ""));
            deudaList.add(new Deuda(32, "Sta_Monica", "Casa 32", 0, ""));
            deudaList.add(new Deuda(33, "Sta_Monica", "Casa 33", 0, ""));
            deudaList.add(new Deuda(34, "Sta_Monica", "Casa 34", 0, ""));
            deudaList.add(new Deuda(35, "Sta_Monica", "Casa 35", 0, ""));
            deudaList.add(new Deuda(36, "Sta_Monica", "Casa 36", 0, ""));
            deudaList.add(new Deuda(37, "Sta_Monica", "Casa 37", 0, ""));
            deudaList.add(new Deuda(38, "Sta_Monica", "Casa 38", 0, ""));
            deudaList.add(new Deuda(40, "Sta_Monica", "Casa 40", 0, ""));
            deudaList.add(new Deuda(42, "Sta_Monica", "Casa 42", 0, ""));
            deudaList.add(new Deuda(43, "Sta_Monica", "Casa 43", 0, ""));
            deudaList.add(new Deuda(44, "Sta_Monica", "Casa 44", 0, ""));
            deudaList.add(new Deuda(45, "Sta_Monica", "Casa 45", 0, ""));
            deudaList.add(new Deuda(46, "Sta_Monica", "Casa 46", 0, ""));
            deudaList.add(new Deuda(47, "Sta_Monica", "Casa 47", 0, ""));

            //recyclerView.setAdapter(new MyCorrienteRecyclerViewAdapter(DummyContent.ITEMS));
            adapterDeuda = new MyCorrienteRecyclerViewAdapter(getActivity(), deudaList);
            recyclerView.setAdapter(adapterDeuda);
        }
        return view;
    }
}