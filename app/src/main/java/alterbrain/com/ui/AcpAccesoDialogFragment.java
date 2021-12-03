package alterbrain.com.ui;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import net.glxn.qrgen.android.QRCode;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.FileProvider;
import androidx.fragment.app.DialogFragment;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import alterbrain.com.BuildConfig;
import alterbrain.com.MainActivity1;
import alterbrain.com.MainActivity5;
import alterbrain.com.R;
import alterbrain.com.app.Constantes;

public class AcpAccesoDialogFragment extends DialogFragment {

    Button btnCompartir;
    String nombre, fecha, comentario,tipo, tx, mensaje;
    TextView tvDatos;
    Bitmap bitmap;
    ImageView ivCerrar;
    ImageView imagenCodigo;


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
        btnCompartir = view.findViewById(R.id.btnCompartir);
        nombre = Constantes.NOMBRE_ACCE;
        fecha = Constantes.FECHA_ACCE;
        comentario = Constantes.COMENTARIO_ACCE;
        tipo = Constantes.TIPO_ACCE;
        tx = nombre+"\n"+tipo+"\n"+fecha+"\n"+comentario;
        tvDatos = view.findViewById(R.id.textViewDatosAcc);
        tvDatos.setText(tx);
        imagenCodigo = view.findViewById(R.id.imageViewQR);

        eventos();

        /*String texto = Constantes.COD_ACCE;
        bitmap = QRCode.from(texto).bitmap();
        // Suponiendo que tienes un ImageView con el id ivCodigoGenerado
        imagenCodigo.setImageBitmap(bitmap);*/


        try {
            BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
            bitmap = barcodeEncoder.encodeBitmap(Constantes.COD_ACCE, BarcodeFormat.QR_CODE, 750,750);
            imagenCodigo.setImageBitmap(bitmap);
        } catch (WriterException e) {
            e.printStackTrace();
        }



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

        btnCompartir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // save bitmap to cache directory
                try {
                    File cachePath = new File(getActivity().getCacheDir(), "images");
                    cachePath.mkdirs(); // don't forget to make the directory
                    FileOutputStream stream = new FileOutputStream(cachePath + "/image.png"); // overwrites this image every time
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
                    stream.close();

                } catch (IOException e) {
                    e.printStackTrace();
                }
                File imagePath = new File(getActivity().getCacheDir(), "images");
                File newFile = new File(imagePath, "image.png");
                Uri contentUri = FileProvider.getUriForFile(getActivity(), BuildConfig.APPLICATION_ID + ".fileprovider", newFile);

                if (contentUri != null) {
                    Intent shareIntent = new Intent();
                    shareIntent.setAction(Intent.ACTION_SEND);
                    shareIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION); // temp permission for receiving app to read this file
                    shareIntent.setDataAndType(contentUri, getActivity().getContentResolver().getType(contentUri));
                    shareIntent.putExtra(Intent.EXTRA_STREAM, contentUri);
                    shareIntent.setType("image/png");
                    startActivity(Intent.createChooser(shareIntent, "Choose an app"));
                }
            }
        });
    }
    private void showDialogConfirm() {
        // 1. Instantiate an AlertDialog.Builder with its constructor
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        // 2. Chain together various setter methods to set the dialog characteristics
        builder.setMessage("¿Desea realmente salir? El QR no se compartirá")
                .setTitle("No compartir Acceso");

        builder.setPositiveButton("Salir", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
                getDialog().dismiss();
                Intent i = new Intent(getContext(), MainActivity1.class);
                startActivity(i);
                //TODO hacer una constante para que al regresar a CrearAcc se finalice ese activity
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
