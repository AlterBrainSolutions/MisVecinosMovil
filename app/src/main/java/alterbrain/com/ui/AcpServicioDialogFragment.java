package alterbrain.com.ui;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import java.util.Calendar;

import alterbrain.com.R;
import alterbrain.com.ServiciosActivity;
import alterbrain.com.app.Constantes;

public class AcpServicioDialogFragment extends DialogFragment {

    ImageView ivCerrar, ivCalendario;
    EditText  etPresupuesto, etComentario;
    TextView etFechaSrv, etFechaNueva, etCasa;
    Button btnEnviar;
    String mensaje, fecha;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL, R.style.FullScreenDialogStyle);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View view = inflater.inflate(R.layout.acp_servicio_full_dialog, container, false);

        ivCerrar = view.findViewById(R.id.imageViewCerrarAcp);
        ivCalendario = view.findViewById(R.id.imageViewCalendarioAcp);
        etFechaSrv = view.findViewById(R.id.textViewFechaSrvAcp);
        etFechaNueva = view.findViewById(R.id.textViewFechaNuevaAcp);
        etPresupuesto = view.findViewById(R.id.editTextPresupuestoAcp);
        etComentario = view.findViewById(R.id.editTextComentarioAcp);
        btnEnviar = view.findViewById(R.id.buttonEnviarAcp);
        etCasa = view.findViewById(R.id.textViewCasaAcp);



        eventos();

        etCasa.setText(Constantes.CASA_SRV);
        etFechaSrv.setText("Fecha de servicio: "+ Constantes.FECHA_SRV);
        etPresupuesto.setText(Constantes.PRESUPUESTO_SRV);

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
        ivCalendario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abrirCalendario(v);
            }
        });
    }

    private void showDialogConfirm() {
        // 1. Instantiate an AlertDialog.Builder with its constructor
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        // 2. Chain together various setter methods to set the dialog characteristics
        builder.setMessage("¿Desea realmente eliminar el Comentario? El mensaje se borrará")
                .setTitle("Cancelar Servicio");

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
    public void abrirCalendario(View view){
        Calendar cal = Calendar.getInstance();

        int anio = cal.get(Calendar.YEAR);
        int mes = cal.get(Calendar.MONTH);
        int dia = cal.get(Calendar.DAY_OF_MONTH);

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            DatePickerDialog dpd = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    fecha = dayOfMonth + "/" + (month +1) + "/" + year;
                    etFechaNueva.setText("Fecha nueva: "+fecha);
                }
            }, anio,mes,dia);
            dpd.show();
        }
    }
}
