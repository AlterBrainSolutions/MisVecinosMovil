package alterbrain.com.ui;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import alterbrain.com.R;
import alterbrain.com.app.Constantes;

public class BrrAccesoDialogFragment extends DialogFragment{

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

        View view = inflater.inflate(R.layout.brr_acceso_full_dialog, container, false);

        ivCerrar = view.findViewById(R.id.imageViewCerrarBrr);
        nombre = Constantes.NOMBRE_ACCE;
        fecha = Constantes.FECHA_ACCE;
        comentario = Constantes.COMENTARIO_ACCE;
        tipo = Constantes.TIPO_ACCE;
        tx = nombre+"\n"+tipo+"\n"+fecha+"\n"+comentario;

        tvDatos = view.findViewById(R.id.textViewDatosBAcc);
        tvDatos.setText(tx);

        eventos();

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
        builder.setMessage("¿Desea realmente eliminar los Datos? ")
                .setTitle("Cancelar Borrado");

        builder.setPositiveButton("Salir", new DialogInterface.OnClickListener() {
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
