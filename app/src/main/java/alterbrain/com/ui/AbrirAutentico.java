package alterbrain.com.ui;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class AbrirAutentico {

    public AbrirAutentico() {
    }

    public void open(Context ctx, int idA){
        Intent detail = new Intent(ctx, ConsultaAutnActivity.class);
        detail.putExtra("valorAutentico", idA);
        ctx.startActivity(detail);
        //Toast.makeText(ctx, "id acceso= "+idA, Toast.LENGTH_SHORT).show();
    }
}
