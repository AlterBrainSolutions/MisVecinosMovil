package alterbrain.com.ui;

import android.content.Context;
import android.content.Intent;

public class AbrirMOficio {
    public AbrirMOficio() {
    }

    public void open(Context ctx, String direcOfi){
        Intent detail = new Intent(ctx, UbicaOfiActivity.class);
        detail.putExtra("coordenadas", direcOfi);
        ctx.startActivity(detail);
    }
}
