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

import alterbrain.com.app.Constantes;
import alterbrain.com.ui.ConsultaUsrActivity;
import alterbrain.com.ui.ConsultaUsrLogsActivity;

public class LoginActivity2 extends AppCompatActivity {

    private EditText etEmail, etPassword, etUsuario;
    private String email, password, usuario, usuarioLogis, contraseniaLogis;
    private String URL = "https://missvecinos.com.mx/android/login3.php";
    private String URLlogs = "https://missvecinos.com.mx/android/login4.php";
    private Button btnLogin;

    Button btnaviso, btnregistrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);
        usuario = email = password = usuarioLogis = contraseniaLogis= "";
        //etEmail = findViewById(R.id.editTextEmail);
        etUsuario = findViewById(R.id.editTextUsuario);
        etPassword = findViewById(R.id.editTextPassword);
        btnLogin = findViewById(R.id.buttonLogin);

        btnaviso = findViewById(R.id.buttonAviso);
        btnregistrar = findViewById(R.id.buttonRegistrar);

        eventos();
    }

    private void eventos() {
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //email = etEmail.getText().toString().trim();
                usuario = etUsuario.getText().toString().trim();
                usuarioLogis = usuario;
                password = etPassword.getText().toString().trim();
                contraseniaLogis = password;
                /*si ambos campos no estan vacios, enviaremos una solicitud de publicacion usando
                 *   volley*/

                if (usuario.compareTo("usuario.carpintero1") ==0 && password.compareTo("Abcde123") ==0){
                    Intent i = new Intent(LoginActivity2.this, MainActivity3.class);
                    i.putExtra("servicio", "carpintero");
                    i.putExtra(Constantes.EXTRA_USER_ID, "");
                    startActivity(i);
                    finish();
                }else if (usuario.compareTo("usuario.vigilante1") ==0 && password.compareTo("Abcde123") ==0){
                    Intent i = new Intent(LoginActivity2.this, MainActivity5.class);
                    i.putExtra("servicio", "guardia");
                    i.putExtra(Constantes.EXTRA_USER_ID, "");
                    startActivity(i);
                    finish();
                }else if (usuario.compareTo("usuario.logistica1") ==0 && password.compareTo("Abcde123") ==0){
                    Intent i = new Intent(LoginActivity2.this, MainActivity7.class);
                    i.putExtra("servicio", "logistica");
                    i.putExtra(Constantes.EXTRA_USER_ID, "");
                    startActivity(i);
                    finish();
                }else if(!usuario.equals("") && !password.equals("")){
                    StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            /*si el texto de respuesta es correcto, crearemos
                             //* un objeto de intenciony lanzar una actividad de éxito con esa intencion
                            //TODO cifrar contrasenia y agregar imagen de usuario, tipo de usuario*/
                            if (response.equals("success")) {
                                Intent intent = new Intent(LoginActivity2.this, ConsultaUsrActivity.class);
                                //intent.putExtra("emailUsr", email);
                                intent.putExtra("nombreUsr", usuario);
                                intent.putExtra("passUsr", password);
                                startActivity(intent);
                                finish();
                            } else if (response.equals("failure")) {
                                //solo se mostrara una vez el comentario de los datos incorrectos
                                // ya que de no encontrarse aqui consulta en la otra tabla.
                                //Toast.makeText(LoginActivity2.this, "Datos incorrectos Id/Password 1", Toast.LENGTH_SHORT).show();
                                // TODO METODO ** QUE HACE LO MISMO DESDE EL ELSE 000IF
                                validaUsuLogs();
                            }
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            //crear un detector de errores para manejar los errores de manera adecuada
                            Toast.makeText(LoginActivity2.this, error.toString().trim(), Toast.LENGTH_SHORT).show();
                        }
                    }){
                        //*para una solicitud de publicacion para agregat parámetros de formulario
                         //* o valores, el metodo de los parametros de la puerta debe sobreescribirsse
                         //* y se debe devolver un mapa, en el mapa necesitamos poner nuestros parametros

                        @Nullable
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            Map<String, String> data = new HashMap<>();
                            //data.put("email", email);
                            data.put("usuario", usuario);
                            data.put("password", password);
                            return data;
                        }
                    };
                    //crear instancia de la RQ (cola de solicitudes)
                    RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                    requestQueue.add(stringRequest);
                }
                else {
                        Toast.makeText(LoginActivity2.this, "Los campos no pueden estar vacios", Toast.LENGTH_SHORT).show();
                    }
                }
        });
        btnaviso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent  = new Intent(LoginActivity2.this, AvisoActivity.class);
                startActivity(intent);
            }
        });
        btnregistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent  = new Intent(LoginActivity2.this, CodigoAccActivity.class);
                startActivity(intent);
            }
        });
    }

    private void validaUsuLogs() {
        //METODO PARA VALIDAR QUE SI NO ES USUARIO DE FERACC, ENTONCES ES UNO DE LOGS
        if(!usuarioLogis.equals("") && !contraseniaLogis.equals("")){
            StringRequest stringRequest = new StringRequest(Request.Method.POST, URLlogs, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    /*si el texto de respuesta es correcto, crearemos
                     * un objeto de intenciony lanzar una actividad de éxito con esa intencion*/
                    //TODO cifrar contrasenia y agregar imagen de usuario, tipo de usuario
                    if (response.equals("success")) {
                        Intent intent = new Intent(LoginActivity2.this, ConsultaUsrLogsActivity.class);
                        //intent.putExtra("emailUsr", email);
                        intent.putExtra("nombreUsr", usuario);
                        intent.putExtra("passUsr", password);
                        startActivity(intent);
                        finish();
                        //Toast.makeText(LoginActivity2.this, "yes i do", Toast.LENGTH_SHORT).show();
                    } else if (response.equals("failure")) {
                        Toast.makeText(LoginActivity2.this, "Datos incorrectos Id/Password --2", Toast.LENGTH_SHORT).show();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    //crear un detector de errores para manejar los errores de manera adecuada
                    Toast.makeText(LoginActivity2.this, error.toString().trim(), Toast.LENGTH_SHORT).show();
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
                    data.put("usuarioLogis", usuarioLogis);
                    data.put("contraseniaLogis", contraseniaLogis);
                    return data;
                }
            };
            //crear instancia de la RQ (cola de solicitudes)
            RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
            requestQueue.add(stringRequest);
        }else {
            Toast.makeText(LoginActivity2.this, "Los campos no pueden estar vacios", Toast.LENGTH_SHORT).show();
        }
    }
}