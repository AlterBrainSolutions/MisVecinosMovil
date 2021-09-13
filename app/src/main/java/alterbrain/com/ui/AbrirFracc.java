package alterbrain.com.ui;

import android.content.Context;
import android.content.Intent;

import alterbrain.com.MainActivity1;

public class AbrirFracc {
    public AbrirFracc() {
    }

    public void open(Context ctx, int idC, String casa){
        Intent detail = new Intent(ctx, UbicaLogActivity.class);
        detail.putExtra("valorFracc", idC);
        //detail.putExtra("casaId", casa);
        ctx.startActivity(detail);
    }
}
