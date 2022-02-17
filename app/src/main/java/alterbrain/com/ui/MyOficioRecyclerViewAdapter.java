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

import alterbrain.com.R;
import alterbrain.com.app.Constantes;
import alterbrain.com.model.Noticia3;

public class MyOficioRecyclerViewAdapter extends RecyclerView.Adapter<MyOficioRecyclerViewAdapter.ViewHolder> {

    //contexto del activity para mostrar el fragment
    private Context ctx;
    //lista de oficios obtenida de la consulta de oficios
    private final List<Oficio> mValues;
    //Objeto AbrirWhats que sirve para mandar whats a cada oficio disponible
    AbrirWhats abrirWhats;

    public MyOficioRecyclerViewAdapter(Context context, List<Oficio> items) {
        //inicializamos las variables a usar posteriormente
        ctx = context;
        mValues = items;
        abrirWhats = new AbrirWhats();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_oficio, parent, false);
        return new MyOficioRecyclerViewAdapter.ViewHolder(view);

        //return new ViewHolder(FragmentOficioBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));

    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        //asignamos cada valor de la lista de Oficios a el objeto mItem de tipo oficio
        holder.mItem = mValues.get(position);
        //obtenemos los valores correspondientes de cada oficio, asignandolo a la vista correspondiente
        holder.textViewOficio.setText(holder.mItem.getNomOficio());
        holder.textViewEmail.setText(holder.mItem.getEmail());

        holder.imageViewWhatsapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //la clase abrir noticia tiene un metodo que nos permite mandar un whats a cada usuario de oficio
                //se envian datos especificos de cada oficio a requerir
                abrirWhats.open(ctx, holder.mItem.getTelefono(), holder.mItem.getNomOficio());
            }
        });

        //Libreria Glide para mostrar imagenes cargadas desde internet a el ImageView Correspondiente
        Glide.with(ctx)
                .load("https://"+ Constantes.LINK_FRACC+"/admin/"+holder.mItem.getImagenPer())
                .centerCrop()
                .into(holder.imageViewPerfil);

        Glide.with(ctx)
                .load("https://"+Constantes.LINK_FRACC+"/admin/"+holder.mItem.getImagenEstb())
                .into(holder.imageViewEstablecimiento);
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        //creamos los objetos a ser instanciados y asigandos porteriormente para cada oficio
        public final View mView;
        public final TextView textViewOficio;
        public final TextView textViewEmail;
        public final ImageView imageViewPerfil;
        public final ImageView imageViewEstablecimiento;
        public final ImageView imageViewDireccion;
        public final ImageView imageViewWhatsapp;
        public Oficio mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            //asignamos cada vista del fragment_oficio a su objeto correspondiente
            textViewOficio = (TextView) view.findViewById(R.id.textViewNomOficio);
            textViewEmail = (TextView) view.findViewById(R.id.textViewEmail_ofic);
            imageViewPerfil = view.findViewById(R.id.imageViewPerfil_ofc);
            imageViewEstablecimiento = view.findViewById(R.id.imageViewEstb_ofc);
            imageViewDireccion = view.findViewById(R.id.imageViewDir_ofcs);
            imageViewWhatsapp = view.findViewById(R.id.imageViewWhats_ofic);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + textViewOficio.getText() + "'";
        }
    }
}