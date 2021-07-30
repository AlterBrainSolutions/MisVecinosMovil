package alterbrain.com;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class CodigoAccActivity extends AppCompatActivity {

    Button btnEnviarCod, btnRegresaLogin;
    EditText editTextCodigo;
    String codigo;

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
                }else{
                    finish();
                    Intent i = new Intent(CodigoAccActivity.this, RegistrarseActivity.class);
                    startActivity(i);
                }
            }
        });
        btnRegresaLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(CodigoAccActivity.this, LoginActivity.class);
                startActivity(i);
            }
        });
    }
}