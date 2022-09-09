package alterbrain.com.ui;

import android.content.Context;
import android.content.Intent;

import alterbrain.com.MainActivity1;
import alterbrain.com.app.Constantes;

public class AbrirAlerta {
    public AbrirAlerta(){
    }

    public void open(Context ctx, int idSegr, int idAlertaUsuario){
        Intent detail = new Intent(ctx, ProtocoloVglActivity.class);
        Constantes.ID_SEGR = idSegr;
        Constantes.ID_ALERTA_SEGR = idAlertaUsuario;
        ctx.startActivity(detail);
    }
}
