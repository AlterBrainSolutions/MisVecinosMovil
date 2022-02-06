package alterbrain.com.ui;

import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.bumptech.glide.Glide;

import java.util.List;

import alterbrain.com.Noticias3Activity;
import alterbrain.com.R;
import alterbrain.com.app.Constantes;
import alterbrain.com.model.Noticia3;


public class MyNoticiaRecyclerViewAdapter3 extends RecyclerView.Adapter<MyNoticiaRecyclerViewAdapter3.ViewHolder> {

    private Context ctx;
    private final List<Noticia3> mValues;
    AbrirNoticia abrirNoticia;

    public MyNoticiaRecyclerViewAdapter3(Context context, List<Noticia3> items) {
        ctx = context;
        mValues = items;
        abrirNoticia = new AbrirNoticia();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_noticia3, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.textViewTitulo.setText(holder.mItem.getTitulo());
        holder.textViewFecha.setText(holder.mItem.getFecha());

        holder.textViewTitulo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abrirNoticia.open(ctx, holder.mItem.getIdNoticia());
                //  3/3 Noticias3Activity.tvNoticia.setText(holder.mItem.getTitulo());
            }
        });

        Glide.with(ctx)
                .load("https://"+Constantes.LINK_FRACC+"/admin/"+holder.mItem.getImagen())
                .centerCrop()
                .into(holder.imageViewPhotoFrac);
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView textViewTitulo;
        public final TextView textViewFecha;
        public final ImageView imageViewPhotoFrac;
        public Noticia3 mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            textViewTitulo = (TextView) view.findViewById(R.id.textViewTituloN);
            textViewFecha = (TextView) view.findViewById(R.id.textViewFecha);
            imageViewPhotoFrac = view.findViewById(R.id.imageViewPhoto);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + textViewTitulo.getText() + "'";
        }
    }
}