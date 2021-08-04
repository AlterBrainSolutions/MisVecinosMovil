package alterbrain.com;

import android.os.Bundle;
import android.view.Menu;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

import alterbrain.com.app.Constantes;

public class MainActivity1 extends AppCompatActivity {

    public static String userId;
    public static String casaNum;
    private AppBarConfiguration mAppBarConfiguration1;
    private AppBarConfiguration mAppBarConfiguration2;
    //public String userId = "";


    /*View navHostFragment;
    View navHostFragment2;
    private ImageView profilePic;
    private FirebaseStorage storage;
    private StorageReference storageReference;*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle extras = getIntent().getExtras();
        //userId = extras.getString(Constantes.EXTRA_USER_ID);
        casaNum = "Casa 0";
        //casaNum = "Casa 1";

        setContentView(R.layout.activity_main1);
        Toolbar toolbar = findViewById(R.id.toolbar1);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab1);
        //final FirebaseAuth[] firebaseAuth = new FirebaseAuth[1];
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*/
                //firebaseAuth[0] = FirebaseAuth.getInstance();
                FirebaseAuth.getInstance().signOut();
                finishAffinity();
            }
        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout1);
        NavigationView navigationView = findViewById(R.id.nav_view1);

        /*navHostFragment = findViewById(R.id.nav_host_fragment2);
        navHostFragment.setVisibility(View.GONE);
        navHostFragment2 = findViewById(R.id.nav_host_fragment3);
        navHostFragment2.setVisibility(View.GONE);*/

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration1 = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow, R.id.salirFragment2)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment1);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration1);
        NavigationUI.setupWithNavController(navigationView, navController);
        navigationView.inflateMenu(R.menu.activity_main_drawer);
        //R.id.nav_host_fragment1;

        /*Bundle extras = getIntent().getExtras();
        userId = extras.getString(Constantes.EXTRA_USER_ID);*/

        /*profilePic = findViewById(R.id.imageViewNav);
        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();*/
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment1);
        /*//Obtener imagen de perfil
        StorageReference imageRef = storageReference.child("images/" + MainActivity1.userId);

        final long ONE_MEGABYTE = 1024 * 1024;
        imageRef.getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
            @Override
            public void onSuccess(byte[] bytes) {
                Bitmap bitmap = BitmapFactory.decodeByteArray(bytes,0,bytes.length);
                profilePic.setImageBitmap(bitmap);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                // Handle any errors
            }
        });*/
        return NavigationUI.navigateUp(navController, mAppBarConfiguration1)
                || super.onSupportNavigateUp();
    }
}