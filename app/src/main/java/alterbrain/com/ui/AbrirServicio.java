package alterbrain.com.ui;

import android.content.Context;
import android.content.Intent;

import alterbrain.com.DescripNoticiaActivity;

public class AbrirServicio {
    public AbrirServicio() {

    }

    public void open(Context ctx, int idN){
        Intent detail = new Intent(ctx, ConsultaServActivity.class);
        detail.putExtra("valorServicio", idN);
        ctx.startActivity(detail);
    }
}
