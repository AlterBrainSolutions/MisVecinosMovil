package alterbrain.com.ui;

import android.content.Context;
import android.content.Intent;

import alterbrain.com.MainActivity1;

public class AbrirFracc {
    public AbrirFracc() {
    }

    public void open(Context ctx, int idFracc, String nomFracc){
        Intent detail = new Intent(ctx, ConsultaFraccLogsActivity.class);
        detail.putExtra("valorFraccL", idFracc);
        detail.putExtra("nomFracc", nomFracc);
        //detail.putExtra("casaId", casa);
        ctx.startActivity(detail);
    }
}
