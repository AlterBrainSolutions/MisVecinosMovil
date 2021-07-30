package alterbrain.com;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import alterbrain.com.model.User;

public class RegistrarseActivity extends AppCompatActivity {
    EditText etName, etEmail, etPass, etPass2, etApellido1,etApellido2, etUsuario;
    Button btnRegistro, btnRegresaLogin;
    FirebaseAuth firebaseAuth;
    FirebaseFirestore db;
    String name, email, password, password2, ape1, ape2, usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrarse);

        etName = findViewById(R.id.editTextName);
        etEmail = findViewById(R.id.editTextEmail);
        etPass = findViewById(R.id.editTextPassword);
        etPass2 = findViewById(R.id.editTextPassword2);
        etApellido1 = findViewById(R.id.editTextApellido1);
        etApellido2 = findViewById(R.id.editTextApellido2);
        etUsuario = findViewById(R.id.editTextUsuarioReg);

        btnRegistro = findViewById(R.id.buttonEnviar);
        btnRegresaLogin = findViewById(R.id.buttonRegresar);

        firebaseAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
        eventos();
    }

    private void eventos() {
        btnRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = etName.getText().toString();
                email = etEmail.getText().toString();
                password = etPass.getText().toString();
                password2 = etPass2.getText().toString();
                ape1 = etApellido1.getText().toString();
                ape2 = etApellido2.getText().toString();
                usuario = etUsuario.getText().toString();

                if(name.isEmpty()){
                    etName.setError("El nombre es obligatorio");
                }else if(email.isEmpty()){
                    etEmail.setError("El email es obligatorio");
                }else if (password.isEmpty()){
                    etPass.setError("La contraseña es obligatoria");
                }else if (password2.isEmpty()){
                    etPass2.setError("Repetir La contraseña es obligatorio");
                }else if (ape1.isEmpty()){
                    etApellido1.setError("El primer apellido es obligatorio");
                }else if (ape2.isEmpty()){
                    etApellido2.setError("El segundo apellido es obligatorio");
                }else if (usuario.isEmpty()){
                    etUsuario.setError("El usuario es obligatorio");
                }else {
                    //TODO: realizar registro en Firebase Auth
                    createUser();
                }
            }
        });
        btnRegresaLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(RegistrarseActivity.this, LoginActivity.class);
                startActivity(i);
            }
        });
    }

    private void createUser() {
        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            FirebaseUser user = firebaseAuth.getCurrentUser();
                            updateUI(user);
                        }else{
                            Toast.makeText(RegistrarseActivity.this, "Error en el registro", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void updateUI(FirebaseUser user) {
        if (user != null){
            //Almacenar la informacion del usuario en Firestore
            User nuevoUsuario = new User(name, email, password, ape1, ape2);

            db.collection("users")
                    .document(user.getUid())
                    .set(nuevoUsuario)
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            //Navegar hacia la siguiente pantalla
                            finish();
                            Intent i = new Intent(RegistrarseActivity.this, MainActivity1.class);
                            startActivity(i);
                        }
                    });

        }else {
            etPass.setError("Nombre, Email y/o contraseña incorrectos");
            etPass.requestFocus();
        }
    }
}