package alterbrain.com.ui;

import android.content.Context;
import android.content.Intent;

public class AbrirNoticia {

    public AbrirNoticia() {
    }

    public void open(Context ctx, int idN){
        Intent detail = new Intent(ctx, ConsultaNtcActivity.class);
        detail.putExtra("valorNoticia", idN);
        ctx.startActivity(detail);
    }
}
