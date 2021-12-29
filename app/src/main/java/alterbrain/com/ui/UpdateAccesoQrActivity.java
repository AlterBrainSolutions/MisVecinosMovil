package alterbrain.com.ui;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import alterbrain.com.MainActivity1;
import alterbrain.com.MainActivity5;
import alterbrain.com.R;
import alterbrain.com.app.Constantes;

public class UpdateAccesoQrActivity extends AppCompatActivity {

    String tiempo,nombre, fechaRegs, fechaVis, horaEntrada, horaSalida, tipo, comenta;
    int id, idUsu, hora,minutos, quehorasson;
    String URL_HE = "https://missvecinos.com.mx/android/updateHEAcc.php";
    String URL_FchQr = "https://missvecinos.com.mx/android/updatePerAcc.php";
    String URL_historial = "https://missvecinos.com.mx/android/histAccesos.php";
    String URL_Dl = "https://missvecinos.com.mx/android/deleteAcc.php";

    EditText etMsg;
    Button btnR;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_acceso_qr);

        Bundle extras = getIntent().getExtras();
        id = extras.getInt("idAcceso");
        idUsu = extras.getInt("idAccesoUsuario");
        tiempo = extras.getString("tiempo");
        nombre = extras.getString("nombre");
        fechaRegs = extras.getString("fechaRegs");
        fechaVis = extras.getString("fechaVist");
        horaEntrada = extras.getString("horaEntrada");
        horaSalida = extras.getString("horaSalida");
        tipo = extras.getString("tipoVisitante");
        comenta = extras.getString("comentarios");
        etMsg = findViewById(R.id.editTextScan);
        btnR = findViewById(R.id.buttonScan);

        btnR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(UpdateAccesoQrActivity.this, MainActivity5.class);
                startActivity(i);
                finish();
            }
        });

        if (Constantes.ID_USR != idUsu){
            Toast.makeText(this, "QR de otro Vecino: "+idUsu, Toast.LENGTH_SHORT).show();
            finish();
        }else {
            Toast.makeText(this, "hora Entrada: ["+horaEntrada+"]", Toast.LENGTH_SHORT).show();

            if (horaEntrada.isEmpty()){
                Calendar c = Calendar.getInstance();
                hora = c.get(Calendar.HOUR_OF_DAY);
                minutos = c.get(Calendar.MINUTE);
                //Toast.makeText(this, "hora: "+hora+":"+minutos, Toast.LENGTH_SHORT).show();
                if (fechaVis.compareTo("0000-00-00") ==0){
                    Calendar cal = Calendar.getInstance();

                    int anio = cal.get(Calendar.YEAR);
                    int mes = cal.get(Calendar.MONTH);
                    int dia = cal.get(Calendar.DAY_OF_MONTH);

                    fechaVis = anio+"-"+(mes+1)+"-"+dia;
                }

                UpdateHoraEn();
                etMsg.setText("Configuración: "+tiempo+"\nInvitado: "+nombre+"\nFecha de visita: "+fechaVis+"\nHora de entrada: "+hora+":"+minutos+
                        "\nTipo: "+tipo+"\nComentario: "+comenta);
            }else{
                if (horaSalida.isEmpty()){
                    Calendar c = Calendar.getInstance();
                    hora = c.get(Calendar.HOUR_OF_DAY);
                    minutos = c.get(Calendar.MINUTE);
                    //Toast.makeText(this, "hora: "+hora+":"+minutos, Toast.LENGTH_SHORT).show();

                    //updateHoraSal();
                    if (tiempo.compareTo("Permanente") ==0){
                        //Toast.makeText(UpdateAccesoQrActivity.this, "FechaV: ["+ fechaVis+"]", Toast.LENGTH_SHORT).show();
                        if (fechaVis.compareTo("0000-00-00") ==0){
                            Calendar cal = Calendar.getInstance();

                            int anio = cal.get(Calendar.YEAR);
                            int mes = cal.get(Calendar.MONTH);
                            int dia = cal.get(Calendar.DAY_OF_MONTH);

                            fechaVis = anio+"-"+(mes+1)+"-"+dia;
                        }
                        guardarHist();
                        updateFechaQr();
                        etMsg.setText("Configuración: "+tiempo+"\nInvitado: "+nombre+"\nFecha de visita: "+fechaVis+"\nHora de Entrada: "+horaEntrada+
                                "\nHora de Salida:"+hora+":"+minutos+"\nTipo: "+tipo+"\nComentario: "+comenta);
                    }else if (tiempo.compareTo("Ocasional") ==0){
                        //Toast.makeText(UpdateAccesoQrActivity.this, "tiempo: "+ tiempo, Toast.LENGTH_SHORT).show();
                        guardarHist();
                        //Toast.makeText(UpdateAccesoQrActivity.this, "id= ["+id+"]", Toast.LENGTH_SHORT).show();
                        borrarAcc();

                        etMsg.setText("Configuración: "+tiempo+"\nInvitado: "+nombre+"\nFecha de visita: "+fechaVis+"\nHora de Entrada: "+horaEntrada+
                                "\nHora de Salida:"+hora+":"+minutos+"\nTipo: "+tipo+"\nComentario: "+comenta);
                    }else{
                        Toast.makeText(UpdateAccesoQrActivity.this, "Error, tipo de acceso indefinido: "+ tiempo, Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(this, "Este Qr ya tiene hora de entrada y salida", Toast.LENGTH_SHORT).show();
                }
            }
        }

    }

    private void updateFechaQr() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_FchQr, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                /*si el texto de respuesta es correcto, crearemos
                 * un objeto de intencion y lanzar una actividad de éxito con esa intencion*/
                if (response.equals("success")) {
                    Toast.makeText(UpdateAccesoQrActivity.this, "¡Datos actualizados exitosamente!", Toast.LENGTH_SHORT).show();

                    //mostrar mensaje en editT
                    //finish
                } else if (response.equals("failure")) {
                    Toast.makeText(UpdateAccesoQrActivity.this, "Ocurrió un error al actualizar!", Toast.LENGTH_SHORT).show();
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
                data.put("idAcc", ""+id);
                return data;
            }
        };
        //crear instancia de RQ (cola de solicitudes)
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);
    }

    private void borrarAcc() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_Dl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                /*si el texto de respuesta es correcto, crearemos
                 * un objeto de intencion y lanzar una actividad de éxito con esa intencion*/
                if (response.equals("success")) {
                    Toast.makeText(UpdateAccesoQrActivity.this, "¡Borrado exitosamente!", Toast.LENGTH_SHORT).show();
                    /*Intent i = new Intent(UpdateAccesoQrActivity.this, MainActivity1.class);
                    startActivity(i);
                    finish();*/
                } else if (response.equals("failure")) {
                    Toast.makeText(UpdateAccesoQrActivity.this, "Ocurrió un error!", Toast.LENGTH_SHORT).show();
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
                data.put("idacc", ""+id);
                return data;
            }
        };
        //crear instancia de RQ (cola de solicitudes)
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);
    }

    private void guardarHist() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_historial, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                /*si el texto de respuesta es correcto, crearemos
                 * un objeto de intencion y lanzar una actividad de éxito con esa intencion*/
                if (response.equals("success")) {
                    Toast.makeText(UpdateAccesoQrActivity.this, "¡Registro historial exitoso!", Toast.LENGTH_SHORT).show();

                } else if (response.equals("failure")) {
                    Toast.makeText(UpdateAccesoQrActivity.this, "Ocurrió un error al guardar historial!", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //crear un detector de errores para manejar los errores de manera adecuada
                Toast.makeText(getApplicationContext(), error.toString().trim(), Toast.LENGTH_LONG).show();
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> data = new HashMap<>();
                data.put("idAccesoFracc", ""+Constantes.ID_VIGFRACC);
                data.put("numeroCasa", ""+Constantes.NUM_CSA);
                data.put("tiempo", ""+tiempo);
                data.put("nombre", ""+nombre);
                data.put("fechaRegs", ""+fechaRegs);
                data.put("fechaVist", ""+fechaVis);
                data.put("horaEntrada", ""+horaEntrada);
                data.put("horaSalida", ""+hora+":"+minutos);
                data.put("tipoVisitante", ""+tipo);
                data.put("comentarios", ""+comenta);
                return data;
            }
        };
        //crear instancia de RQ (cola de solicitudes)
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);
    }

    private void UpdateHoraEn() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_HE, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                /*si el texto de respuesta es correcto, crearemos
                 * un objeto de intencion y lanzar una actividad de éxito con esa intencion*/
                if (response.equals("success")) {
                    Toast.makeText(UpdateAccesoQrActivity.this, "¡Entrada actualizada exitosamente!", Toast.LENGTH_SHORT).show();
                    if (tiempo == "Permanente"){

                    }
                    /*Intent i = new Intent(AcpAcceso2Activity.this, MainActivity1.class);
                    startActivity(i);
                    finish();*/
                } else if (response.equals("failure")) {
                    Toast.makeText(UpdateAccesoQrActivity.this, "Ocurrió un error al actualizar la hora!", Toast.LENGTH_SHORT).show();
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
                data.put("idAcc", ""+id);
                data.put("horaEntrada", ""+hora+":"+minutos);
                return data;
            }
        };
        //crear instancia de RQ (cola de solicitudes)
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);
    }
}