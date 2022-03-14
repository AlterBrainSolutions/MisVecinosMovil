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

public class MyDocumentoRecyclerViewAdapter extends RecyclerView.Adapter<MyDocumentoRecyclerViewAdapter.ViewHolder> {

    private Context ctx;
    private final List<Documento> mValues;

    public MyDocumentoRecyclerViewAdapter(Context context, List<Documento> items) {
        ctx = context;
        mValues = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_documento, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.textViewTitulo.setText(holder.mItem.getTitulodoc());
        holder.textViewFecha.setText(holder.mItem.getFechadoc());
        String rutaDoc = mValues.get(position).getRuta();
        holder.buttonGoDocm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent detail = new Intent(ctx, VeDocumentoActivity.class);
                detail.putExtra("rutaDocumento", rutaDoc);
                ctx.startActivity(detail);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView textViewTitulo;
        public final TextView textViewFecha;
        public final Button buttonGoDocm;
        public Documento mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            textViewTitulo = (TextView) view.findViewById(R.id.textViewTitulodoc);
            textViewFecha= (TextView) view.findViewById(R.id.textViewFechaDoc);
            buttonGoDocm = view.findViewById(R.id.buttonVeDoc);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + textViewTitulo.getText() + "'";
        }
    }
}