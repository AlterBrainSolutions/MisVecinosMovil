package alterbrain.com.ui;

import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import java.util.List;

import alterbrain.com.R;
import alterbrain.com.model.Adeudos;

public class MyDeudaRecyclerViewAdapter extends RecyclerView.Adapter<MyDeudaRecyclerViewAdapter.ViewHolder> {

    private int mes=0;
    private Context ctx;
    private final List<Adeudos> mValues;

    public MyDeudaRecyclerViewAdapter(Context context, List<Adeudos> items) {
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
        mes = holder.mItem.getMesesDeDeuda();
        holder.textViewCasa.setText("Casa: " + holder.mItem.getNumeroCasa());
        holder.textViewNumMesAde.setText(""+mes);
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView textViewCasa;
        public final TextView textViewNumMesAde;
        public Adeudos mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            textViewCasa = (TextView) view.findViewById(R.id.textViewCasaDeu);
            textViewNumMesAde = (TextView) view.findViewById(R.id.textViewNumMesDeu);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + textViewCasa.getText() + "'";
        }
    }
}