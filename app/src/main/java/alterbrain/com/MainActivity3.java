package alterbrain.com;

import android.os.Bundle;
import android.view.View;
import android.view.Menu;
import android.widget.ImageView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import alterbrain.com.app.Constantes;

public class MainActivity3 extends AppCompatActivity {

    public static String userId, servicios;
    //private AppBarConfiguration mAppBarConfiguration;
    private AppBarConfiguration mAppBarConfiguration2;
    //public String userId = "";

    /*View navHostFragment2;
    View navHostFragment;

    private ImageView profilePic;
    private FirebaseStorage storage;
    private StorageReference storageReference;*/

    DrawerLayout drawer;
    NavigationView navigationView;
    NavController navController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle extras = getIntent().getExtras();
        userId = extras.getString(Constantes.EXTRA_USER_ID);
        servicios = extras.getString("servicio");
        /*Toast.makeText(this, "-"+userId+"-", Toast.LENGTH_SHORT).show();
        Toast.makeText(this, servicios, Toast.LENGTH_SHORT).show();*/


        setContentView(R.layout.activity_main3);
        Toolbar toolbar = findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab2);
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

        /*navHostFragment = findViewById(R.id.nav_host_fragment1);
        navHostFragment.setVisibility(View.GONE);
        navHostFragment2 = findViewById(R.id.nav_host_fragment3);
        navHostFragment2.setVisibility(View.GONE);*/

        drawer = findViewById(R.id.drawer_layout2);
        navigationView = findViewById(R.id.nav_view2);


        /*Toast.makeText(this, "1", Toast.LENGTH_SHORT).show();*/
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration2 = new AppBarConfiguration.Builder(
                R.id.serviciosFragment, R.id.galleryFragment, R.id.slideshowFragment, R.id.salirFragment)
                .setDrawerLayout(drawer)
                .build();
        navController = Navigation.findNavController(this, R.id.nav_host_fragment2);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration2);
        NavigationUI.setupWithNavController(navigationView, navController);
        navigationView.inflateMenu(R.menu.activity_second_drawer);



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
        navController = Navigation.findNavController(this, R.id.nav_host_fragment2);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration2) || super.onSupportNavigateUp();


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

    }
}