package alterbrain.com.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import alterbrain.com.R;
import alterbrain.com.app.Constantes;

public class DetalleNtcActivity extends AppCompatActivity {

    String titulo, fecha, descripcion, imagen;
    TextView tvTitulo, tvFecha, tvDescrip;
    ImageView imageViewNoticia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_ntc);

        Bundle extras = getIntent().getExtras();
        titulo = extras.getString("titulo");
        descripcion = extras.getString("descripcion");
        fecha = extras.getString("fecha");
        imagen = extras.getString("imagen");

        tvTitulo = findViewById(R.id.textViewTituloDetN);
        tvFecha = findViewById(R.id.textViewFechaDetN);
        tvDescrip = findViewById(R.id.textViewDescripcionDetN);
        imageViewNoticia = findViewById(R.id.imageViewDescNoti);

        tvTitulo.setText(titulo);
        tvFecha.setText(fecha);
        tvDescrip.setText(descripcion);

        if (imagen.equals("")){

        }else{
            Glide.with(this)
                    .load("https://"+ Constantes.LINK_FRACC+"/admin/"+imagen)
                    .into(imageViewNoticia);
        }


    }
}