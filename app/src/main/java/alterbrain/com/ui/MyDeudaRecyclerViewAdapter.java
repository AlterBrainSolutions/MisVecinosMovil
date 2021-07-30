package alterbrain.com.ui;

import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import java.util.List;

import alterbrain.com.R;

public class MyDeudaRecyclerViewAdapter extends RecyclerView.Adapter<MyDeudaRecyclerViewAdapter.ViewHolder> {

    private int mes=0;
    private Context ctx;
    private final List<Deuda> mValues;

    public MyDeudaRecyclerViewAdapter(Context context, List<Deuda> items) {
        ctx = context;
        mValues = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_deuda, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {


        holder.mItem = mValues.get(position);
        mes = holder.mItem.getMesesdeuda();
        holder.textViewCasa.setText(holder.mItem.getCasa());
        holder.textViewNumMesAde.setText(""+mes);
        holder.textViewFecha.setText(holder.mItem.getFecha());
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView textViewCasa;
        public final TextView textViewNumMesAde;
        public final TextView textViewFecha;
        public Deuda mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            textViewCasa = (TextView) view.findViewById(R.id.textViewCasaDeu);
            textViewNumMesAde = (TextView) view.findViewById(R.id.textViewNumMesDeu);
            textViewFecha = view.findViewById(R.id.textViewFechaDeu);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + textViewCasa.getText() + "'";
        }
    }
}