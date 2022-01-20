package alterbrain.com;

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

import java.util.HashMap;
import java.util.Map;

import alterbrain.com.ui.ConsultaUsrLogsActivity;

public class CodigoAccActivity extends AppCompatActivity {

    Button btnEnviarCod, btnRegresaLogin;
    EditText editTextCodigo;
    String codigo;
    String URLCodusr = "https://missvecinos.com.mx/android/codUsr.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_codigo_acc);

        btnEnviarCod = findViewById(R.id.buttonEnviarCA);
        btnRegresaLogin = findViewById(R.id.buttonRegresarCA);
        editTextCodigo = findViewById(R.id.editTextTextCodigoCA);

        eventos();
    }
    private void eventos() {
        btnEnviarCod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                codigo = editTextCodigo.getText().toString();
                if(codigo.isEmpty()){
                    editTextCodigo.setError("El código es obligatorio");
                }else {
                    StringRequest stringRequest = new StringRequest(Request.Method.POST, URLCodusr, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            /*si el texto de respuesta es correcto, crearemos
                             * un objeto de intenciony lanzar una actividad de éxito con esa intencion*/
                            //TODO cifrar contrasenia y agregar imagen de usuario, tipo de usuario
                            if (response.equals("success")) {
                                Intent intent = new Intent(CodigoAccActivity.this, RegistrarseActivity2.class);
                                intent.putExtra("codigo", codigo);
                                startActivity(intent);
                                finish();
                                Toast.makeText(CodigoAccActivity.this, "Código encontrado", Toast.LENGTH_SHORT).show();
                            } else if (response.equals("failure")) {
                                Toast.makeText(CodigoAccActivity.this, "Código de acceso no encontrado", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            //crear un detector de errores para manejar los errores de manera adecuada
                            Toast.makeText(CodigoAccActivity.this, error.toString().trim(), Toast.LENGTH_SHORT).show();
                        }
                    }){
                        /*para una solicitud de publicacion para agregat parámetros de formulario
                         * o valores, el metodo de los parametros de la puerta debe sobreescribirsse
                         * y se debe devolver un mapa, en el mapa necesitamos poner nuestros parametros
                         * */
                        @Nullable
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            Map<String, String> data = new HashMap<>();
                            //data.put("email", email);
                            data.put("codigo", codigo);
                            return data;
                        }
                    };
                    //crear instancia de la RQ (cola de solicitudes)
                    RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                    requestQueue.add(stringRequest);
                }
                /*if(codigo.isEmpty()){
                    editTextCodigo.setError("El código es obligatorio");
                }else if (codigo.compareTo("o8tKVk4WA0EgtJ3") ==0){
                    finish();
                    Intent i = new Intent(CodigoAccActivity.this, ConsultaCodUsr.class);
                    startActivity(i);
                }else {
                    editTextCodigo.setError("El código no es valido");
                }*/
            }
        });
        btnRegresaLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(CodigoAccActivity.this, LoginActivity2.class);
                startActivity(i);
            }
        });
    }
}