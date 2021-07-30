package alterbrain.com.ui;

import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import alterbrain.com.R;


import java.util.List;


public class MyAutorizadoRecyclerViewAdapter extends RecyclerView.Adapter<MyAutorizadoRecyclerViewAdapter.ViewHolder> {

    private Context ctx;
    private final List<Autorizado> mValues;

    public MyAutorizadoRecyclerViewAdapter(Context contexto, List<Autorizado> items) {
        ctx = contexto;
        mValues = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_autorizado, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.textViewNombre.setText(holder.mItem.getNombreinvi());

    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView textViewNombre;
        //public final ImageView imageViewFoto;
        public Autorizado mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            textViewNombre = view.findViewById(R.id.textViewNombreAtr);
            //imageViewFoto = view.findViewById(R.id.imageViewFotoAtr);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + textViewNombre.getText() + "'";
        }
    }
}