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
import alterbrain.com.ui.placeholder.PlaceholderContent;


public class LogsFraccionamientoFragment extends Fragment {

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
            fraccionamientoList.add(new Fraccionamiento(1,"link vacio", "FRACCIONAMIENTO INADEM", 1,15,35));
            fraccionamientoList.add(new Fraccionamiento(2,"link vacio", "FRACCIONAMIENTO SANTA MONICA", 1,15,35));
            myLogsFraccionamientoRecyclerViewAdapter = new MyLogsFraccionamientoRecyclerViewAdapter(getActivity(), fraccionamientoList);
            recyclerView.setAdapter(myLogsFraccionamientoRecyclerViewAdapter);
        }
        return view;
    }
}