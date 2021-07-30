package alterbrain.com.ui;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import alterbrain.com.R;
import alterbrain.com.app.Constantes;

public class DclServicioDialogFragment extends DialogFragment {

    ImageView ivCerrar;
    EditText etComentario;
    TextView etCasa;
    RadioButton radioButton1, radioButton2, radioButton3;
    Button btnEnviar;
    String mensaje;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL, R.style.FullScreenDialogStyle);


    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View view = inflater.inflate(R.layout.dcl_servicio_full_dialog, container, false);

        ivCerrar = view.findViewById(R.id.imageViewCerrarDcl);
        etComentario = view.findViewById(R.id.editTextComentarioDcl);
        etCasa = view.findViewById(R.id.textViewCasaDcl);
        radioButton1 = view.findViewById(R.id.radioButtonUnoDcl);
        radioButton2 = view.findViewById(R.id.radioButtonDosDcl);
        radioButton3 = view.findViewById(R.id.radioButtonTresDcl);
        btnEnviar = view.findViewById(R.id.buttonEnviarDcl);

        eventos();

        etCasa.setText(Constantes.CASA_SRV);

        return view;

    }

    private void eventos() {
        ivCerrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mensaje = etComentario.getText().toString();

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
        builder.setMessage("¿Desea realmente eliminar el Comentario? El mensaje se borrará")
                .setTitle("Cancelar Declinar Servicio");

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
