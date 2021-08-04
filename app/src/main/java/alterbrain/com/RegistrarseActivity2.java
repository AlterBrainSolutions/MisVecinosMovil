package alterbrain.com;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
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

public class RegistrarseActivity2 extends AppCompatActivity {

    private EditText etName, etEmail, etPassword, etReenterPassword;
    private TextView tvStatus;
    private Button btnRegister, btnRegresar;
    private String URL = "https://missvecinos.com.mx/android/register2.php";
    private String name, email, password, reenterPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrarse2);

        etName = findViewById(R.id.editTextUsuarioReg);
        etEmail = findViewById(R.id.editTextEmail);
        etPassword = findViewById(R.id.editTextPassword);
        etReenterPassword = findViewById(R.id.editTextPassword2);
        tvStatus = findViewById(R.id.textView6);
        btnRegister = findViewById(R.id.buttonEnviar);
        btnRegresar = findViewById(R.id.buttonRegresar);
        name = email = password = reenterPassword = "";

        eventos();
    }

    private void eventos() {
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = etName.getText().toString().trim();
                email = etEmail.getText().toString().trim();
                password = etPassword.getText().toString().trim();
                reenterPassword = etReenterPassword.getText().toString().trim();
                if (!password.equals(reenterPassword)){
                    Toast.makeText(RegistrarseActivity2.this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show();
                }
                else if (!name.equals("") && !email.equals("") && !password.equals("")){
                    StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            /*si el texto de respuesta es correcto, crearemos
                             * un objeto de intenciony lanzar una actividad de éxito con esa intencion*/
                            if (response.equals("success")) {
                                tvStatus.setText("Registrado exitosamente");
                                btnRegister.setClickable(false);
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
                            data.put("name", name);
                            data.put("email", email);
                            data.put("password", password);
                            return data;
                        }
                    };
                    //crear instancia de RQ (cola de solicitudes)
                    RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                    requestQueue.add(stringRequest);
                }
            }
        });
        btnRegresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegistrarseActivity2.this, LoginActivity2.class);
                startActivity(intent);
                finish();
            }
        });
    }
}