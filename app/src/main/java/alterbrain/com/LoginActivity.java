package alterbrain.com;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import alterbrain.com.app.Constantes;

public class LoginActivity extends AppCompatActivity {

    private EditText etEmail, etPassword;
    private Button btnLogin;
    private ImageButton ibAbsweb;
    private FirebaseAuth firebaseAuth;
    private String email, password , userId = "";
    boolean trylogin = false;

    EditText txtusu, txtpass;
    Button btnmenu,btnaviso, btnregistrar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //getSupportActionBar().hide();
        setContentView(R.layout.activity_login);

        etEmail = findViewById(R.id.editTextEmail);
        etPassword = findViewById(R.id.editTextPassword);
        btnLogin = findViewById(R.id.buttonLogin);

        firebaseAuth = FirebaseAuth.getInstance();

        eventos();

        txtusu =  findViewById(R.id.editTextEmail);
        txtpass = findViewById(R.id.editTextPassword);
        btnmenu = findViewById(R.id.buttonLogin);
        btnaviso = findViewById(R.id.buttonAviso);
        btnregistrar = findViewById(R.id.buttonRegistrar);
        ibAbsweb = findViewById(R.id.imageButtonAbs);

        Intent intent  = new Intent(LoginActivity.this, LoginActivity2.class);
        startActivity(intent);
        finish();
        /*btnmenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (txtusu.getText().toString().equals("") && txtpass.getText().toString().equals("")){
                    //abrir la otra ventana creada
                    Intent intent  = new Intent(LoginActivity.this, MainActivity1.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(LoginActivity.this, "Bienvenido", Toast.LENGTH_LONG).show();

                    //BORR
                    Intent intent  = new Intent(LoginActivity.this, MainActivity1.class);
                    startActivity(intent);
                }
            }
        });*/
        btnaviso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent  = new Intent(LoginActivity.this, AvisoActivity.class);
                startActivity(intent);
            }
        });
        btnregistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent  = new Intent(LoginActivity.this, CodigoAccActivity.class);
                startActivity(intent);
            }
        });

        /*ibAbsweb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent  = new Intent(LoginActivity.this, AbswebActivity.class);
                startActivity(intent);
            }
        });*/
    }

    private void eventos() {
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email = etEmail.getText().toString();
                password = etPassword.getText().toString();

                if(email.isEmpty()){
                    etEmail.setError("El email es obligatorio");
                }else if (password.isEmpty()){
                    etPassword.setError("La contraseña es obligatoria");
                }else {
                    //TODO: realizar login en Firebase Auth
                    loginUser();
                }
            }
        });

    }

    private void loginUser() {
        if (email.compareTo("usuario.adm") ==0 && password.compareTo("Abcde123") ==0){
            email = "bryan@minitwitter.com";
            password = "123456";
            firebaseAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            trylogin = true;
                            if (task.isSuccessful()){
                                FirebaseUser user= firebaseAuth.getCurrentUser();
                                updateUI(user);
                            } else {
                                Log.w("TAG", "signInError: ", task.getException());
                                updateUI(null);
                            }
                        }
                    });
        }else{
            firebaseAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            trylogin = true;
                            if (task.isSuccessful()){
                                FirebaseUser user= firebaseAuth.getCurrentUser();
                                updateUI(user);
                            } else {
                                Log.w("TAG", "signInError: ", task.getException());
                                updateUI(null);
                            }
                        }
                    });
        }
    }
    private void updateUI(FirebaseUser user) {
        if (user != null){
            //Almacenar la informacion del usuario en Firestore
            //TODO
            userId = user.getUid();
            //Navegar hacia la siguiente pantalla
            Intent i = new Intent(LoginActivity.this, MainActivity1.class);
            i.putExtra(Constantes.EXTRA_USER_ID, userId);
            startActivity(i);
        }else {
            if (trylogin) {
                etPassword.setError("Email y/o contraseña incorrectos");
                etPassword.requestFocus();
                if (email.compareTo("usuario.carpintero1") ==0 && password.compareTo("Abcde123") ==0){
                    Intent i = new Intent(LoginActivity.this, MainActivity3.class);
                    i.putExtra("servicio", "carpintero");
                    i.putExtra(Constantes.EXTRA_USER_ID, "");
                    startActivity(i);
                }
                if (email.compareTo("usuario.vigilante1") ==0 && password.compareTo("Abcde123") ==0){
                    Intent i = new Intent(LoginActivity.this, MainActivity5.class);
                    i.putExtra("servicio", "guardia");
                    i.putExtra(Constantes.EXTRA_USER_ID, "");
                    startActivity(i);
                }
            }
        }
    }

    @Override
    protected void onStart() {
        super.onStart();

        //comprobamos si el usuario a iniciado sesion en este dispositivo
        FirebaseUser currentUser = firebaseAuth.getCurrentUser();
        updateUI(currentUser);
    }
}