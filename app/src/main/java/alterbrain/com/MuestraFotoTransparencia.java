package alterbrain.com;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import alterbrain.com.app.Constantes;

public class MuestraFotoTransparencia extends AppCompatActivity {

    String ImagenURL = Constantes.URL_IMG_TRP;
    ImageView muestraImagen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_muestra_foto_transparencia);

        muestraImagen = findViewById(R.id.muestraImagenTransparencia);

        Glide.with(this).load(ImagenURL).into(muestraImagen);
    }
}