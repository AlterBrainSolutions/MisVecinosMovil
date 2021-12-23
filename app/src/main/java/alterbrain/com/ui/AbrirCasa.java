package alterbrain.com.ui;

import android.content.Context;
import android.content.Intent;

import alterbrain.com.MainActivity1;
import alterbrain.com.app.Constantes;

public class AbrirCasa {
    public AbrirCasa() {
    }

    public void open(Context ctx, int idC, String casa){
        Intent detail = new Intent(ctx, AutorizadosActivity.class);
        //detail.putExtra("idCasa", idC);
        Constantes.ID_USR = idC;
        Constantes.NUM_CSA = casa;
        ctx.startActivity(detail);
    }
}
