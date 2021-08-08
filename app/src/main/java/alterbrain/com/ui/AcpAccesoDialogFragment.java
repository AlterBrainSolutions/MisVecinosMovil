package alterbrain.com.ui;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import net.glxn.qrgen.android.QRCode;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import alterbrain.com.R;
import alterbrain.com.app.Constantes;

public class AcpAccesoDialogFragment extends DialogFragment {


    String nombre, fecha, comentario,tipo, tx, mensaje;
    TextView tvDatos;

    ImageView ivCerrar;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL, R.style.FullScreenDialogStyle);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View view = inflater.inflate(R.layout.acp_acceso_full_dialog, container, false);

        ivCerrar = view.findViewById(R.id.imageViewCerrarAcp);
        nombre = Constantes.NOMBRE_ACCE;
        fecha = Constantes.FECHA_ACCE;
        comentario = Constantes.COMENTARIO_ACCE;
        tipo = Constantes.TIPO_ACCE;
        tx = nombre+"\n"+tipo+"\n"+fecha+"\n"+comentario;

        tvDatos = view.findViewById(R.id.textViewDatosAcc);
        tvDatos.setText(tx);

        eventos();

        String texto = Constantes.COD_ACCE;
        Bitmap bitmap = QRCode.from(texto).bitmap();
        // Suponiendo que tienes un ImageView con el id ivCodigoGenerado
        ImageView imagenCodigo = view.findViewById(R.id.imageViewQR);
        imagenCodigo.setImageBitmap(bitmap);

        return view;

    }

    private void eventos() {
        ivCerrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mensaje = tvDatos.getText().toString();

                if(!mensaje.isEmpty()) {
                    showDialogConfirm();
                } else {
                    getDialog().dismiss();
                }
            }
        });
    }
    private void showDialogConfirm() {
        // 1. Instantiate an AlertDialog.Builder with its constructor
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        // 2. Chain together various setter methods to set the dialog characteristics
        builder.setMessage("¿Desea realmente eliminar los Datos? El QR se borrará")
                .setTitle("Cancelar Acceso");

        builder.setPositiveButton("Eliminar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
                getDialog().dismiss();
                //^
            }
        });
        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
            }
        });

        // 3. Get the AlertDialog from create()
        AlertDialog dialog = builder.create();

        dialog.show();
    }
}
