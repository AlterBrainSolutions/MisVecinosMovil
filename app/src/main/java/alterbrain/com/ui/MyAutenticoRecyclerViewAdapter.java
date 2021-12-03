package alterbrain.com.ui;

import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import java.util.List;

import alterbrain.com.R;


public class MyAutenticoRecyclerViewAdapter extends RecyclerView.Adapter<MyAutenticoRecyclerViewAdapter.ViewHolder> {

    private Context ctx;
    private final List<Autentico> mValues;
    AbrirAutentico abrirAutentico;

    public MyAutenticoRecyclerViewAdapter(Context contexto, List<Autentico> items) {
        ctx = contexto;
        mValues = items;
        abrirAutentico = new AbrirAutentico();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_autentico, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.textViewNombre.setText(holder.mItem.getNombre());
        holder.textViewFechaV.setText("Fecha de visita: " + holder.mItem.getFechaVis());
        holder.textViewComen.setText("Comentario: " + holder.mItem.getComentario());

        holder.textViewNombre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abrirAutentico.open(ctx, holder.mItem.getIdAcceso());
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView textViewNombre;
        public final TextView textViewFechaV;
        public final TextView textViewComen;

        public Autentico mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            textViewNombre = view.findViewById(R.id.textViewNombreAtn);
            textViewFechaV = view.findViewById(R.id.textViewFechaVAtn);
            textViewComen = view.findViewById(R.id.textViewComentarioAtn);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + textViewNombre.getText() + "'";
        }
    }
}