package alterbrain.com;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import alterbrain.com.app.Constantes;

public class DetalleEventoActivity extends AppCompatActivity {

    String titulo, fecha, descripcion, imagen;
    TextView tvTitulo, tvFecha, tvDescrip;
    ImageView imageViewNoticia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_evento);
        Bundle extras = getIntent().getExtras();
        titulo = extras.getString("Titulo");
        descripcion = extras.getString("Desc");
        fecha = extras.getString("Fecha");
        imagen = extras.getString("Imagen");

        imageViewNoticia = findViewById(R.id.imageViewDescEvento);
        tvTitulo = findViewById(R.id.textViewTituloEvento);
        tvFecha = findViewById(R.id.textViewFechaEvento);
        tvDescrip = findViewById(R.id.textViewDescripcionEvento);

        tvTitulo.setText(titulo);
        tvFecha.setText(fecha);
        tvDescrip.setText(descripcion);

        String link= imagen;
        String mailparams = link.replaceAll("(^\\.\\.)", "");
        /*Toast.makeText(DetalleEventoActivity.this,"https://"+
                Constantes.LINK_FRACC + mailparams, Toast.LENGTH_SHORT).show();*/

        if (imagen.equals("")){

        }else{
            Glide.with(this)
                    .load("https://"+ Constantes.LINK_FRACC + mailparams)
                    //.load("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ_OUqRyLzfvUizaJme2qsDfz7gM5mLXBo37Q&usqp=CAU")
                    .into(imageViewNoticia);
        }
        /*
        * "Titulo", titulo);
                detail.putExtra("Desc", descripcion);
                detail.putExtra("Solic", solicitante);
                detail.putExtra("Fecha", fecha);
                detail.putExtra("Imagen", imagen);
        * */
    }
}