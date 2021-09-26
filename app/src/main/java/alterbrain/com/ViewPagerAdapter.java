package alterbrain.com;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

public class ViewPagerAdapter extends RecyclerView.Adapter<ViewPagerAdapter.ViewHolder> {

    // Array of images
    // Adding images from drawable folder
    private int[] images = {R.drawable.ic_ayuda_reciclaje1, R.drawable.ic_ayuda_reciclaje2,
            R.drawable.ic_ayuda_reciclaje3, R.drawable.ic_ayuda_reciclaje4};

    private String[] textoDescripcion = {"¿Sabías que en cada hogar se generan más de 9 " +
            "kilogramos de basura por semana?\n\n" + "¿Sabías que un hogar promedio pude desechar " +
            "más de 1500 envases de PET y latas de ALUMINIO al año?",
            "1. Separa los envases de PET y las latas de Aluminio.\n" +
                    "2. Comprímelos lo más que puedas.\n" +
                    "3. Llévalos al contenedor e introdúcelos en su lugar correspondiente.\n" +
                    "4. En la app de \"MIS VECINOS\", registra el número de envases que has " +
                    "introducido.",
            "La plataforma \"MIS VECINOS\" te plantea el objetivo de fomentar el reciclaje\n\n" +
                    "¿CÓMO?\nUtilizando los contenedores que se instalaran en tu fraccionamiento," +
                    " en los cuales podras depositar los envases de PET y Aluminio.",
            "Desde la app de \"MIS VECINOS\", lee el código QR que está en la parte frontal del " +
                    "contenedor.\n\nEn la app de \"MIS VECINOS\", te informaremos la cantidad de " +
                    "envases que se recicló por mes/año y los beneficios de esta acción."};

    private String[] textoTitle = {"Prueba1",
            "CÓMO SE USAN LOS CONTENDORES:",
            "Prueba Title 3",
            "Prueba Title 4"};

    private Context ctx;

    // Constructor of our ViewPager2Adapter class
    ViewPagerAdapter(Context ctx) {
        this.ctx = ctx;
    }
    // This method returns our layout

    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(ctx).inflate(R.layout.view_pager_item, parent, false);
        return new ViewHolder(view);
    }

    // This method binds the screen with the view
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // This will set the images in imageview
        holder.images.setImageResource(images[position]);
        holder.textTitle.setText(textoTitle[position]);
        holder.textDesc.setText(textoDescripcion[position]);
        if(position == 0 || position == 2 || position == 3){
            holder.textTitle.setVisibility(View.GONE);
        }else{
            holder.textTitle.setVisibility(View.VISIBLE);
            holder.textTitle.setTextSize(20);
            holder.textDesc.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_START);
        }
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

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            images = itemView.findViewById(R.id.ivPerro_prueba);
            textTitle = itemView.findViewById(R.id.tvAyudaR1);
            textDesc = itemView.findViewById(R.id.tvAyudaR2);
        }
    }

}
