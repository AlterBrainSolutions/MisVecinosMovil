package alterbrain.com.ui;

import android.content.Context;
import android.content.Intent;

import alterbrain.com.MainActivity1;

public class AbrirCasa {
    public AbrirCasa() {
    }

    public void open(Context ctx, int idC, String casa){
        Intent detail = new Intent(ctx, AutorizadosActivity.class);
        detail.putExtra("valorCasa", idC);
        //detail.putExtra("casaId", casa);
        MainActivity1.casaNum = casa;
        ctx.startActivity(detail);
    }
}
