package alterbrain.com.ui;

import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import alterbrain.com.R;

import java.util.List;


public class MyCasasVigRecyclerViewAdapter extends RecyclerView.Adapter<MyCasasVigRecyclerViewAdapter.ViewHolder> {

    private Context ctx;
    private final List<CasasVig> mValues;
    AbrirCasa abrirCasa;

    public MyCasasVigRecyclerViewAdapter(Context contexto, List<CasasVig> items) {
        ctx = contexto;
        mValues = items;
        abrirCasa = new AbrirCasa();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_casasvig, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.textViewCasa.setText("CASA "+holder.mItem.getNumeroCasa());

        holder.textViewCasa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abrirCasa.open(ctx, holder.mItem.getIdUsu(), holder.mItem.getNumeroCasa());
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
        public CasasVig mItem;

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