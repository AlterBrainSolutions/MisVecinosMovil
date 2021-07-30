package alterbrain.com;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

public class DescripNoticia2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_descrip_noticia2);

        Bundle extras = getIntent().getExtras();
        String idNoticia = extras.getString("valorNoticia");

        Toast.makeText(this, idNoticia, Toast.LENGTH_LONG).show();
    }
}