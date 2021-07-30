package alterbrain.com.ui;

import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

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
        holder.textViewTipo.setText(holder.mItem.getTipo());
        holder.textViewAsunto.setText(holder.mItem.getAsunto());
        holder.textViewResponsable.setText(holder.mItem.getResponsable());
        holder.textViewFecha.setText(holder.mItem.getFecha());
        //holder.buttonMensaje.setOnClickListener(new View.OnClickListener() {
        //            @Override
        //            public void onClick(View v) {
        //                /*Intent detail = new Intent(ctx, VeDocumentoActivity.class);
        //                ctx.startActivity(detail);*/
        //            }
        //        });

        holder.textViewAsunto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Intent detail = new Intent(ctx, VeDocumentoActivity.class);
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