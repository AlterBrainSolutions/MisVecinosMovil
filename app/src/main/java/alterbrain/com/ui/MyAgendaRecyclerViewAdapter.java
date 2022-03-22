package alterbrain.com.ui;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import alterbrain.com.DetalleEventoActivity;
import alterbrain.com.R;
import alterbrain.com.VeDocumentoActivity;


import java.util.List;


public class MyAgendaRecyclerViewAdapter extends RecyclerView.Adapter<MyAgendaRecyclerViewAdapter.ViewHolder> {

    private Context ctx;
    private final List<Agenda> mValues;

    public MyAgendaRecyclerViewAdapter(Context context, List<Agenda> items) {
        ctx = context;
        mValues = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_agenda, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        String idEvento = String.valueOf(holder.mItem.getIdEvento());
        String titulo = holder.mItem.getTipo();
        String descripcion = holder.mItem.getAsunto();
        String solicitante = holder.mItem.getResponsable();
        String fecha = holder.mItem.getFecha();
        String imagen = holder.mItem.getImagen();
        holder.textViewTipo.setText(titulo);
        holder.textViewAsunto.setText(descripcion);
        holder.textViewResponsable.setText(solicitante);
        holder.textViewFecha.setText(fecha);
        holder.buttonMensaje.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent detail = new Intent(ctx, DetalleEventoActivity.class);
                detail.putExtra("Titulo", titulo);
                detail.putExtra("Desc", descripcion);
                //detail.putExtra("Solic", solicitante);
                detail.putExtra("Fecha", fecha);
                detail.putExtra("Imagen", imagen);
                ctx.startActivity(detail);
            }
        });

        //holder.buttonMensaje.setOnClickListener(new View.OnClickListener() {
        //            @Override
        //            public void onClick(View v) {
        //                /*Intent detail = new Intent(ctx, VeDocumentoActivity.class);
        //                ctx.startActivity(detail);*/
        //            }
        //        });

        /*holder.textViewAsunto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                *//*Intent detail = new Intent(ctx, VeDocumentoActivity.class);
                ctx.startActivity(detail);*//*
            }
        });*/

    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView textViewTipo;
        public final TextView textViewAsunto;
        public final TextView textViewResponsable;
        public final TextView textViewFecha;
        public final Button buttonMensaje;
        public Agenda mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            textViewTipo = (TextView) view.findViewById(R.id.textViewTipoAgn);
            textViewAsunto = (TextView) view.findViewById(R.id.textViewAsuntoAgn);
            textViewResponsable = (TextView) view.findViewById(R.id.textViewResponsableAgn);
            textViewFecha = (TextView) view.findViewById(R.id.textViewFechaAgn);
            buttonMensaje = view.findViewById(R.id.buttonNotificacionAgn);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + textViewTipo.getText() + "'";
        }
    }
}