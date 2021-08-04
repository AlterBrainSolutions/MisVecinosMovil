package alterbrain.com.ui;

import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import alterbrain.com.AbrirNoticia;
import alterbrain.com.R;
import alterbrain.com.model.Noticia2;

import java.util.List;

public class MyNoticiaRecyclerViewAdapter extends RecyclerView.Adapter<MyNoticiaRecyclerViewAdapter.ViewHolder> {

    private Context ctx;
    private final List<Noticia2> mValues;
    String idNoticia = "", fracc = "";
    Intent detail;
    AbrirNoticia abrirNoticia;

    public MyNoticiaRecyclerViewAdapter(Context context, List<Noticia2> items) {
        ctx = context;
        mValues = items;
        abrirNoticia = new AbrirNoticia();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_noticia, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        // Rescatamos los datos del elemento que ocupa la posición "position"

        holder.mItem = mValues.get(position);

        holder.textViewNombreFrac.setText(holder.mItem.getTitulo());
        holder.textViewFecha.setText(holder.mItem.getFecha());
        //holder.textViewFecha.setBackgroundColor();

        /*idNoticia = holder.mItem.getIdNoticia();
        fracc = holder.mItem.getFraccionamiento();*/
        holder.textViewNombreFrac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                abrirNoticia.open(ctx, holder.mItem.getIdn());

                /*detail = new Intent(ctx, DescripNoticia2Activity.class);
                detail.putExtra("valorNoticia", idNoticia);
                ctx.startActivity(detail);*/

//                if (idNoticia.equals("1")){
//                    Intent detail = new Intent(ctx, DescripNoticiaActivity.class);
//                    ctx.startActivity(detail);
//                }else if (idNoticia.equals("3")){
//                    Intent detail = new Intent(ctx, DescripNoticia2Activity.class);
//                    ctx.startActivity(detail);
//                }else{
//                    Intent detail = new Intent(ctx, DescripNoticia3Activity.class);
//                    ctx.startActivity(detail);
//                }
                /*if (fracc.equals("Fraccionamiento Santa Monica")){
                    Intent detail = new Intent(ctx, DescripNoticia2Activity.class);
                    ctx.startActivity(detail);
                }*/

            }
        });

        /*Glide.with(ctx)
                .load(holder.mItem.getUrlPhoto())
                .centerCrop()
                .into(holder.imageViewPhotoFrac);*/
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView textViewNombreFrac;
        public final TextView textViewFecha;
        public final ImageView imageViewPhotoFrac;
        public Noticia2 mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            textViewNombreFrac = (TextView) view.findViewById(R.id.textViewTituloNtc);
            textViewFecha = (TextView) view.findViewById(R.id.textViewFecha);
            imageViewPhotoFrac = view.findViewById(R.id.imageViewPhoto);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + textViewNombreFrac.getText() + "'";
        }
    }
}