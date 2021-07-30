package alterbrain.com.ui;

import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import alterbrain.com.R;

import java.util.List;


public class MyVisitanteRecyclerViewAdapter extends RecyclerView.Adapter<MyVisitanteRecyclerViewAdapter.ViewHolder> {

    private Context ctx;
    private final List<Visitante> mValues;
    AbrirCasa abrirCasa;

    public MyVisitanteRecyclerViewAdapter(Context contexto, List<Visitante> items) {
        ctx = contexto;
        mValues = items;
        abrirCasa = new AbrirCasa();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_visitante, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.textViewCasa.setText(holder.mItem.getCasa());

        holder.textViewCasa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abrirCasa.open(ctx, holder.mItem.getId(), holder.mItem.getCasa());
                /*Intent detail = new Intent(ctx, AutorizadosActivity.class);
                ctx.startActivity(detail);*/
            }
        });
        /*Glide.with(ctx)
                .load(holder.mItem.getImagen())
                .centerCrop()
                .into(holder.imageViewCasa);*/
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView textViewCasa;
        //public final ImageView imageViewCasa;
        public Visitante mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            textViewCasa = view.findViewById(R.id.textViewCasaVst);
            //imageViewCasa = view.findViewById(R.id.imageViewCasaVst);
        }

        @Override
        public String toString() {
            return super.toString() + textViewCasa.getText().toString();
        }
    }
}