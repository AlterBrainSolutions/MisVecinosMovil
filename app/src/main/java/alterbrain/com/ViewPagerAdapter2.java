package alterbrain.com;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

public class ViewPagerAdapter2 extends RecyclerView.Adapter<ViewPagerAdapter2.ViewHolder>{

    // Array of images
    // Adding images from drawable folder
    private int[] images = {R.drawable.ic_seguridad_ayuda1, R.drawable.ic_seguridad_ayuda2,
            R.drawable.ic_seguridad_ayuda3};

    private String[] textoDescripcion = {"Presiona este botón cuando te encuentres en peligro, " +
            "tardará 1O segundos en enviar un mensaje con tu ubicación a tus contactos de emergencia.",
            "Agrega hasta a 3 de tus contactos para notificarles en caso de activar el servicio SOS y " +
                    "tener una pronta respuesta,",
            "\"Mis Vecinos\" enviará de la ubicación a los contactos de confianza y al servicio de " +
                    "vigilancia del fraccionamiento cuando se active el botón de pánico."};

    private String[] textoTitle = {"Boton SOS",
            "Contactos de emergencia",
            "Localizacion Automática"};

    private Context ctx;

    // Constructor of our ViewPager2Adapter class
    ViewPagerAdapter2(Context ctx) {
        this.ctx = ctx;
    }
    // This method returns our layout

    @NonNull
    @NotNull
    @Override
    public ViewPagerAdapter2.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(ctx).inflate(R.layout.view_pager_item, parent, false);
        return new ViewPagerAdapter2.ViewHolder(view);
    }

    // This method binds the screen with the view
    @Override
    public void onBindViewHolder(@NonNull ViewPagerAdapter2.ViewHolder holder, int position) {
        // This will set the images in imageview
        holder.images.setImageResource(images[position]);
        holder.textTitle.setText(textoTitle[position]);
        holder.textDesc.setText(textoDescripcion[position]);
        holder.vpaConstraintAdapter.setBackgroundColor(Color.parseColor("#FFF7E4DE"));
        /*if(position == 0 || position == 2 || position == 3){
            holder.textTitle.setVisibility(View.GONE);
        }else{
            holder.textTitle.setVisibility(View.VISIBLE);
            holder.textTitle.setTextSize(20);
            holder.textDesc.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_START);
        }*/
    }

    // This Method returns the size of the Array
    @Override
    public int getItemCount() {
        return images.length;
    }

    // The ViewHolder class holds the view
    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView images;
        TextView textTitle, textDesc;
        ConstraintLayout vpaConstraintAdapter;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            images = itemView.findViewById(R.id.ivPerro_prueba);
            textTitle = itemView.findViewById(R.id.tvAyudaR1);
            textDesc = itemView.findViewById(R.id.tvAyudaR2);
            vpaConstraintAdapter = itemView.findViewById(R.id.vpaAyudas);
        }
    }

}
