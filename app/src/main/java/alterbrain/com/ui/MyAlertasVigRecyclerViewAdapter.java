package alterbrain.com.ui;

import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import alterbrain.com.R;

import java.util.List;


public class MyAlertasVigRecyclerViewAdapter extends RecyclerView.Adapter<MyAlertasVigRecyclerViewAdapter.ViewHolder> {

    private Context ctx;
    private final List<AlertasVig> mValues;
    AbrirAlerta abrirAlerta;

    public MyAlertasVigRecyclerViewAdapter(Context contexto, List<AlertasVig> items) {
        ctx = contexto;
        mValues = items;
        abrirAlerta = new AbrirAlerta();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_alertasvig, parent, false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.textViewCasa.setText("CASA "+holder.mItem.getIdAlertaUsuario());
        holder.textViewFecha.setText(holder.mItem.getFechaActivacion());

        holder.textViewCasa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abrirAlerta.open(ctx, holder.mItem.getIdSeguridad(), holder.mItem.getIdAlertaUsuario());
                /*Intent detail = new Intent(ctx, AutorizadosActivity.class);
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
        public AlertasVig mItem;


        public ViewHolder(View view) {
            super(view);
            mView = view;
            textViewCasa = view.findViewById(R.id.textViewCasaAlert);
            textViewFecha = view.findViewById(R.id.textViewFechaAlert);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + textViewCasa.getText() + "'";
        }
    }
}