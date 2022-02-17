package alterbrain.com.ui;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

public class AbrirWhats {

    public AbrirWhats() {
    }

    //metodo que recibe contexto de donde se ubica el fragmen, el numero de telefono(String porque es bigint) Y nomServicio
    public void open(Context ctx, String num, String serv){
        //metodo para enviar whatsapp
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_VIEW);
        //uri que contiene numero de telefono (lada mx) y mensaje por defecto
        String uri = "whatsapp://send?phone=52"+num+"&text=Hola, buen dia '"+serv+"', necesito ayuda con su servicio";
        sendIntent.setData(Uri.parse(uri));
        ctx.startActivity(sendIntent);
    }
}
