package alterbrain.com.ui;

import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import alterbrain.com.R;

import java.util.List;


public class MyLogsFraccionamientoRecyclerViewAdapter extends RecyclerView.Adapter<MyLogsFraccionamientoRecyclerViewAdapter.ViewHolder> {

    private Context ctx;
    private final List<Fraccionamiento> mValues;
    AbrirFracc abrirFracc;

    public MyLogsFraccionamientoRecyclerViewAdapter(Context contexto, List<Fraccionamiento> items) {
        ctx = contexto;
        mValues = items;
        abrirFracc = new AbrirFracc();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_logsfraccionamiento, parent, false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.textViewNomFracc.setText("FRACCIONAMIENTO "+holder.mItem.getNombreFracc());

        holder.textViewNomFracc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abrirFracc.open(ctx, holder.mItem.getIdFraccionamiento(), holder.mItem.getNombreFracc());
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
        public final TextView textViewNomFracc;
        //public final ImageView imageViewCasa;
        public Fraccionamiento mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            textViewNomFracc = view.findViewById(R.id.textViewNombreLogsFracc);
            //imageViewCasa = view.findViewById(R.id.imageViewCasaVst);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + textViewNomFracc.getText() + "'";
        }
    }
}