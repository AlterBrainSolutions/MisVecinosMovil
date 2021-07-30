package alterbrain.com.ui;

import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import java.util.List;
import java.util.zip.CheckedOutputStream;

import alterbrain.com.R;


public class MyConceptotrRecyclerViewAdapter extends RecyclerView.Adapter<MyConceptotrRecyclerViewAdapter.ViewHolder> {

    private Context ctx;
    private final List<Conceptotr> mValues;

    public MyConceptotrRecyclerViewAdapter(Context context, List<Conceptotr> items) {
        ctx = context;
        mValues = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_conceptotr, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.textViewConcepto.setText(holder.mItem.getConcepto());
        holder.textViewPagocnc.setText(holder.mItem.getPagocnc());
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView textViewConcepto;
        public final TextView textViewPagocnc;
        public Conceptotr mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            textViewConcepto = (TextView) view.findViewById(R.id.textViewConcepto);
            textViewPagocnc = (TextView) view.findViewById(R.id.textViewPagoCnc);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + textViewConcepto.getText() + "'";
        }
    }
}