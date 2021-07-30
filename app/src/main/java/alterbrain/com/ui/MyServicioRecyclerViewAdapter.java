package alterbrain.com.ui;

import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import alterbrain.com.R;

import java.util.List;


public class MyServicioRecyclerViewAdapter extends RecyclerView.Adapter<MyServicioRecyclerViewAdapter.ViewHolder> {

    private Context ctx;
    private final List<Servicios> mValues;
    AbrirServicio abrirServicio;

    public MyServicioRecyclerViewAdapter(Context contexto, List<Servicios> items) {
        ctx = contexto;
        mValues = items;
        abrirServicio = new AbrirServicio();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_servicio, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.textViewCasa.setText(holder.mItem.getCasa());
        holder.textViewFecha.setText(holder.mItem.getFecha());
        holder.textViewDescri.setText(holder.mItem.getDescripcion());

        holder.textViewDescri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abrirServicio.open(ctx, holder.mItem.getId());
                /*Intent detail = new Intent(ctx, DetalleSrvActivity.class);
                ctx.startActivity(detail);*/
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView textViewCasa;
        public final TextView textViewFecha;
        public final TextView textViewDescri;
        public Servicios mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            textViewCasa = view.findViewById(R.id.textViewCasaSrv);
            textViewFecha = view.findViewById(R.id.textViewFechaSrv);
            textViewDescri = view.findViewById(R.id.textViewDescriSrv);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + textViewCasa.getText() + "'";
        }
    }
}