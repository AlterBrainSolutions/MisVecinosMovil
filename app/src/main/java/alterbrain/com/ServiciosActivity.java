package alterbrain.com;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.Calendar;

public class ServiciosActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    private Spinner spnServicios;
    String serv;
    ImageView ivFecha;
    TextView tvFecha;
    String fecha;
    ImageView ivAgregaImagen;

    public Uri imageUri;
    private FirebaseStorage storage;
    private StorageReference storageReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_servicios);
        ivFecha = findViewById(R.id.imageViewFechaServicio);
        tvFecha = findViewById(R.id.textViewFechaServicio);
        ivAgregaImagen = findViewById(R.id.imageViewSubeImgSrv);

        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();

        eventos();
        SpinnerComponentes();
    }

    private void eventos() {
        ivFecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abrirCalendario(v);
            }
        });
        ivAgregaImagen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                choosePicture();
            }
        });
    }
    private void choosePicture() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, 1);

    }
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK && data != null && data.getData() != null) {
            imageUri = data.getData();
            ivAgregaImagen.setImageURI(imageUri);
            uploadPicture();
        }
    }

    private void uploadPicture() {

        final ProgressDialog pd = new ProgressDialog(ServiciosActivity.this);
        pd.setTitle("Actualizando Imagen...");
        pd.show();

        //final String photoKey = MainActivity1.userId;
        StorageReference mountainImagesRef = storageReference.child("servicios/" + serv);

        mountainImagesRef.putFile(imageUri)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        pd.dismiss();
                        Toast.makeText(ServiciosActivity.this, "Imagen Actualizada.", Toast.LENGTH_LONG).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                pd.dismiss();
                Toast.makeText(ServiciosActivity.this, "Fallo Al Actualizar.", Toast.LENGTH_LONG).show();
            }
        })
                .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onProgress(@NonNull UploadTask.TaskSnapshot tasksnapshot) {
                        double progressPercent = (100.0 * tasksnapshot.getBytesTransferred() / tasksnapshot.getTotalByteCount());
                        pd.setMessage("Porcentaje: " + (int) progressPercent + "%");
                    }
                });
    }


    private void SpinnerComponentes(){
        ArrayAdapter<CharSequence> serviciosAdapter;

        serviciosAdapter = ArrayAdapter.createFromResource(this, R.array.servicios, android.R.layout.simple_spinner_item);

        spnServicios = findViewById(R.id.spinnerServicios);
        spnServicios.setAdapter(serviciosAdapter);

        spnServicios.setOnItemSelectedListener(this);
    }
    public void abrirCalendario(View view){
        Calendar cal = Calendar.getInstance();

        int anio = cal.get(Calendar.YEAR);
        int mes = cal.get(Calendar.MONTH);
        int dia = cal.get(Calendar.DAY_OF_MONTH);

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            DatePickerDialog dpd = new DatePickerDialog(ServiciosActivity.this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    fecha = dayOfMonth + "/" + (month +1) + "/" + year;
                    tvFecha.setText(fecha);
                }
            }, anio,mes,dia);
            dpd.show();
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (parent.getId()){
            case R.id.spinnerServicios:
                //if (position !=0){
                    serv = parent.getItemAtPosition(position).toString();
                //}else{
                  //  serv = "";
                //}
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}