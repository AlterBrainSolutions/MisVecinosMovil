package alterbrain.com;

import android.content.Context;
import android.content.Intent;

public class AbrirNoticia {

    public AbrirNoticia() {
    }

    public void open(Context ctx, String idN){
        Intent detail = new Intent(ctx, DescripNoticiaActivity.class);
        detail.putExtra("valorNoticia", idN);
        ctx.startActivity(detail);
    }
}
