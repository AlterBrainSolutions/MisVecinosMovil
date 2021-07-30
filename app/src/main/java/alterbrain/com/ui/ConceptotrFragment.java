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

public class ConceptotrFragment extends Fragment {

    RecyclerView recyclerView;
    MyConceptotrRecyclerViewAdapter conceptotrRecyclerViewAdapter;
    List<Conceptotr> conceptotrList;
    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;

    public ConceptotrFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static ConceptotrFragment newInstance(int columnCount) {
        ConceptotrFragment fragment = new ConceptotrFragment();
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
        View view = inflater.inflate(R.layout.fragment_conceptotr_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }
            conceptotrList = new ArrayList<>();
            conceptotrList.add(new Conceptotr("12,800.00 MN", "VIGILANCIA"));
            conceptotrList.add(new Conceptotr("4,641.00 MN", "MANTENIMIENTO"));
            conceptotrList.add(new Conceptotr("4,982.00 MN", "COMPRA DE EQUIPOS"));
            conceptotrList.add(new Conceptotr("1,397.00 MN", "DECORACIÓN"));
            conceptotrList.add(new Conceptotr("300.00 MN", "REPARACIÓN FUGA DE AGUA"));
            conceptotrList.add(new Conceptotr("195.0 MN", "PAPELERÍA"));

            conceptotrRecyclerViewAdapter = new MyConceptotrRecyclerViewAdapter(getActivity(), conceptotrList);
            recyclerView.setAdapter(conceptotrRecyclerViewAdapter);
        }
        return view;
    }
}