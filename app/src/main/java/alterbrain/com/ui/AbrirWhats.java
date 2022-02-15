package alterbrain.com.ui;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

public class AbrirWhats {

    public AbrirWhats() {
    }

    public void open(Context ctx, int num, String serv){
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_VIEW);
        String uri = "whatsapp://send?phone="+"52"+num+"&text=Hola, buen dia "+serv+" necesito ayuda con su servicio";
        sendIntent.setData(Uri.parse(uri));
        ctx.startActivity(sendIntent);
    }
}
