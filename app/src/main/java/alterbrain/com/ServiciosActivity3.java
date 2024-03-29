package alterbrain.com;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import alterbrain.com.app.Constantes;
import alterbrain.com.ui.MyOficioRecyclerViewAdapter;
import alterbrain.com.ui.Oficio;

public class ServiciosActivity3 extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    Bitmap bitmap;
    private static final int COD_SELECCIONA = 10;
    private static final int COD_FOTO = 20;

    //spiner que tendra la lista de oficios vigentes
    private Spinner spnServicios;
    //String que guarda la url donde se almacena el PHP con la consulta que traera la lista de oficios
    private static String URL_oficios = "https://missvecinos.com.mx/android/oficios.php";
    //lista de objetos oficio, que guardan los oficios registrados en la web
    List<Oficio> oficioList;
    String serv;
    ImageView ivFecha, ivAgregaImagen;
    TextView tvFecha, tvStatus;
    String fecha, descripcion, presupuesto, casa, URL = "https://missvecinos.com.mx/android/serviciocrp.php";

    EditText etDescripcion, etPresupuesto;
    Button btnGuardar;

    public Uri imageUri;

    StringRequest stringRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_servicios3);

        //asignamos los objetos a sus vistas determinadas
        ivFecha = findViewById(R.id.imageViewFechaServicio);
        tvFecha = findViewById(R.id.textViewFechaServicio);
        ivAgregaImagen = findViewById(R.id.imageViewSubeImgSrv);
        etDescripcion = findViewById(R.id.editTextDescripServicio);
        etPresupuesto = findViewById(R.id.editTextPresupuestoServ);
        btnGuardar = findViewById(R.id.buttonGuardarServ);
        tvStatus = findViewById(R.id.textViewTituloSolServ);
        spnServicios = findViewById(R.id.spinnerServicios);

        //declaramos como Listener a esta Activity
        spnServicios.setOnItemSelectedListener(this);
        oficioList = new ArrayList<>();
        eventos();
        //metodo que se encarga de consultar y cargar la lista de oficios a la oficioList
        loadOficios();
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
                mostrarDialogOpciones();
                //choosePicture();
            }
        });

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                casa = Constantes.NOM_USR;
                serv = "Carpintero";
                descripcion = etDescripcion.getText().toString().trim();
                presupuesto = etPresupuesto.getText().toString().trim();

                if (!casa.equals("") && !serv.equals("") && !fecha.equals("") && !descripcion.equals("") && !presupuesto.equals("")){
                    cargarWebService();
                    StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            /*si el texto de respuesta es correcto, crearemos
                             * un objeto de intenciony lanzar una actividad de éxito con esa intencion*/
                            if (response.equals("success")) {
                                tvStatus.setText("¡Registrado exitosamente!");
                                Toast.makeText(ServiciosActivity3.this, "¡Registrado exitosamente!", Toast.LENGTH_SHORT).show();
                                btnGuardar.setClickable(false);
                                finish();
                            } else if (response.equals("failure")) {
                                tvStatus.setText("Ocurrió un error!");
                            }
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            //crear un detector de errores para manejar los errores de manera adecuada
                            Toast.makeText(getApplicationContext(), error.toString().trim(), Toast.LENGTH_SHORT).show();
                        }
                    }){
                        @Nullable
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            Map<String, String> data = new HashMap<>();
                            data.put("casa", casa);
                            data.put("serv", serv);
                            data.put("fecha", fecha);
                            data.put("descripcion", descripcion);
                            data.put("presupuesto", presupuesto);
                            return data;
                        }
                    };
                    //crear instancia de RQ (cola de solicitudes)
                    RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                    requestQueue.add(stringRequest);
                }
            }
        });
    }
    private void loadOficios() {
        //metodo usado con la libreria volley para obtener la lista de oficios
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_oficios,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray array = new JSONArray(response);

                            for (int i = 0; i < array.length(); i++) {
                                JSONObject oficio = array.getJSONObject(i);

                                oficioList.add(new Oficio(
                                        oficio.getInt("idOficio"),
                                        oficio.getString("oficio")
                                ));
                            }
                            //una vez cargada la oficios List con los nombres de los oficios se asignan al spinner
                            cargarSpinner();
                            //recyclerView.setAdapter(new MyOficioRecyclerViewAdapter(getContext(), oficioList));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(ServiciosActivity3.this, error.toString(), Toast.LENGTH_SHORT).show();
                    }
                });
        Volley.newRequestQueue(ServiciosActivity3.this).add(stringRequest);
    }

    private void cargarSpinner() {
        //asignamos los valores (nombres) de la lista de oficios que acabamos de consultar
        List<String> lables = new ArrayList<String>();

        //txtAgregar.setText("");

        for (int i = 0; i < oficioList.size(); i++) {
            lables.add(oficioList.get(i).getNomOficio());
        }

        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, lables);

        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spnServicios.setAdapter(spinnerAdapter);
    }

    private void mostrarDialogOpciones() {
        //Toast.makeText(this, "Mostrar opciones", Toast.LENGTH_SHORT).show();
        final CharSequence[] opciones={"Tomar Foto","Elegir de Galeria","Cancelar"};
        final AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("Elige una Opción");
        builder.setItems(opciones, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (opciones[i].equals("Tomar Foto")){
                    Toast.makeText(ServiciosActivity3.this, "Cargar camara", Toast.LENGTH_SHORT).show();
                    abrirCamara();
                }else{
                    if (opciones[i].equals("Elegir de Galeria")){
                        Intent intent=new Intent(Intent.ACTION_GET_CONTENT,
                                MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                        intent.setType("image/*");
                        startActivityForResult(intent.createChooser(intent,"Seleccione"),COD_SELECCIONA);
                    }else{
                        dialogInterface.dismiss();
                    }
                }
            }
        });
        //mostrar el alert dialog
        builder.show();
    }

    private void abrirCamara() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if(intent.resolveActivity(getPackageManager()) != null){
            startActivityForResult(intent, COD_FOTO);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode){
            case COD_SELECCIONA:
                Uri miPath=data.getData();
                ivAgregaImagen.setImageURI(miPath);

                try {
                    bitmap=MediaStore.Images.Media.getBitmap(this.getContentResolver(),miPath);
                    ivAgregaImagen.setImageBitmap(bitmap);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                break;
            case COD_FOTO:
                if (resultCode == RESULT_OK) {
                    Bundle extras = data.getExtras();

                    bitmap = (Bitmap) extras.get("data");
                    ivAgregaImagen.setImageBitmap(bitmap);
                }
                /*MediaScannerConnection.scanFile(this, new String[]{path}, null,
                        new MediaScannerConnection.OnScanCompletedListener() {
                            @Override
                            public void onScanCompleted(String path, Uri uri) {
                                Log.i("Path",""+path);
                            }
                        });

                bitmap= BitmapFactory.decodeFile(path);
                ivAgregaImagen.setImageBitmap(bitmap);*/

                break;
        }
        /*bitmap=redimensionarImagen(bitmap,600,800);*/
    }
    private void cargarWebService() {
        String url = "https://missvecinos.com.mx/ejemploBDRemota/wsJSONRegistroMovil.php";

        stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                /*si el texto de respuesta es correcto, crearemos
                 * un objeto de intenciony lanzar una actividad de éxito con esa intencion*/
                /*if (response.equals("success")) {
                    tvStatus.setText("Registrado exitosamente");
                    btnGuardar.setClickable(false);
                } else if (response.equals("failure")) {
                    tvStatus.setText("Ocurrió un error!");
                }*/
                if (response.trim().equalsIgnoreCase("registra")){
                    tvStatus.setText("Registrado exitosamente");
                    btnGuardar.setClickable(false);

                }else{
                    Toast.makeText(ServiciosActivity3.this,"No se ha registrado ",Toast.LENGTH_SHORT).show();
                    Log.i("RESPUESTA: ",""+response);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //crear un detector de errores para manejar los errores de manera adecuada
                Toast.makeText(getApplicationContext(), error.toString().trim(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> data = new HashMap<>();

                String imagen=convertirImgString(bitmap);

                data.put("casa", casa);
                data.put("serv", serv);
                data.put("fecha", fecha);
                data.put("descripcion", descripcion);
                data.put("presupuesto", presupuesto);
                data.put("imagen",imagen);

                return data;
            }
        };
        //crear instancia de RQ (cola de solicitudes)
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);

    }

    private String convertirImgString(Bitmap bitmap) {
        ByteArrayOutputStream array=new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,array);
        byte[] imagenByte=array.toByteArray();
        String imagenString= Base64.encodeToString(imagenByte,Base64.DEFAULT);

        return imagenString;
    }

    private void choosePicture() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_PICK);
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, 1);

    }

    public void abrirCalendario(View view){
        Calendar cal = Calendar.getInstance();

        int anio = cal.get(Calendar.YEAR);
        int mes = cal.get(Calendar.MONTH);
        int dia = cal.get(Calendar.DAY_OF_MONTH);

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            DatePickerDialog dpd = new DatePickerDialog(ServiciosActivity3.this, new DatePickerDialog.OnDateSetListener() {
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
        //metodo que reacciona cuando seleccionamos un item del spinner
        switch (parent.getId()){
            case R.id.spinnerServicios:
                serv = parent.getItemAtPosition(position).toString();
                Toast.makeText(
                        getApplicationContext(),
                        parent.getItemAtPosition(position).toString() + " Seleccionado" ,
                        Toast.LENGTH_LONG).show();
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}