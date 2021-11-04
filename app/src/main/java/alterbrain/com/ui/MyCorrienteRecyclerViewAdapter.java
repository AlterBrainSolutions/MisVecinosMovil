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


public class MyCorrienteRecyclerViewAdapter extends RecyclerView.Adapter<MyCorrienteRecyclerViewAdapter.ViewHolder> {

    private Context ctx;
    private final List<Adeudos> mValues;

    public MyCorrienteRecyclerViewAdapter(Context context, List<Adeudos> items) {
        ctx = context;
        mValues = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_corriente, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.textViewCasaNum.setText("Casa "+ holder.mItem.getNumeroCasa());
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView textViewCasaNum;
        public Adeudos mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            textViewCasaNum = (TextView) view.findViewById(R.id.textViewCasaAde);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + textViewCasaNum.getText() + "'";
        }
    }
}