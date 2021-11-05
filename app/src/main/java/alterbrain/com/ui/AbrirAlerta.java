package alterbrain.com.ui;

import android.content.Context;
import android.content.Intent;

import alterbrain.com.MainActivity1;
import alterbrain.com.app.Constantes;

public class AbrirAlerta {
    public AbrirAlerta(){
    }

    public void open(Context ctx, int idSegr, String casa){
        Intent detail = new Intent(ctx, ProtocoloVglActivity.class);
        Constantes.ID_SEGR = idSegr;
        Constantes.NUMCASA_SEGR = casa;
        ctx.startActivity(detail);
    }
}
