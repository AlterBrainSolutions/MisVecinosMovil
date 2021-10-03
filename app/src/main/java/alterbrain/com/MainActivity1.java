package alterbrain.com;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

import alterbrain.com.app.Constantes;
import alterbrain.com.ui.home.HomeFragment;

public class MainActivity1 extends AppCompatActivity {

    public static String userId;
    public static String casaNum, descri;
    BottomNavigationView bnvMenuSeg;
    BottomAppBar babMenuSeg;
    FloatingActionButton fabMenuSeg;
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
        /*FloatingActionButton fab = findViewById(R.id.fab1);
        //final FirebaseAuth[] firebaseAuth = new FirebaseAuth[1];
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //*Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                   //*     .setAction("Action", null).show();
                //firebaseAuth[0] = FirebaseAuth.getInstance();
                FirebaseAuth.getInstance().signOut();
                finishAffinity();
            }
        });*/
        DrawerLayout drawer = findViewById(R.id.drawer_layout1);
        NavigationView navigationView = findViewById(R.id.nav_view1);

        bnvMenuSeg = findViewById(R.id.bnvMenu2);
        babMenuSeg = findViewById(R.id.bmbMenu);
        fabMenuSeg = findViewById(R.id.fab1);

        configNav();

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

        //TODO para evitar que se muestre en los fragments del menu de hamburguesa, solo se mostrara el menu en donde se tiene
        //TODO establecido
        navController.addOnDestinationChangedListener((controller, destination, arguments) -> {
            if (destination.getId() == R.id.nav_gallery || destination.getId() == R.id.nav_slideshow
                    || destination.getId() == R.id.salirFragment2) {
                bnvMenuSeg.setVisibility(View.INVISIBLE);
                babMenuSeg.setVisibility(View.INVISIBLE);
                fabMenuSeg.setVisibility(View.INVISIBLE);
            } else {
                bnvMenuSeg.setVisibility(View.VISIBLE);
                babMenuSeg.setVisibility(View.VISIBLE);
                fabMenuSeg.setVisibility(View.VISIBLE);
            }
        });

        //R.id.nav_host_fragment1;

        /*Bundle extras = getIntent().getExtras();
        userId = extras.getString(Constantes.EXTRA_USER_ID);*/

        /*profilePic = findViewById(R.id.imageViewNav);
        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();*/

        //TODO para verificar si ha visto o no ha visto la ayuda(seguridad) manda a llamar al metodo y este retorna true o false
        //TODO dependiendo el caso
        fabMenuSeg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Toast.makeText( HomeFragment.this.getActivity(),
                        "Booleasno "+Boolean.toString(getSavedPreferences()), Toast.LENGTH_SHORT).show();*/
                //TODO si ya lo ha visto, manda directo al activity
                //TODO es necesario crear otras ayudas ya que las que uso son las de detalle reciclaje
                if (getSavedPreferences() == true) {
                    Intent detail = new Intent(MainActivity1.this, ReciclajeActivity.class);
                    startActivity(detail);
                    //TODO si no lo ha visto manda la ayuda
                } else {
                    Intent detail = new Intent(MainActivity1.this, ActivityDetalleAyuda.class);
                    startActivity(detail);
                }
            }
        });
    }

    //TODO sirve para revisar si el usuario ya ha visto la ayuda
    //TODO Este metodo retorna un false o true, dependiendo el valor que contenga la variable isOpenedForNOMBREDEUSUARIO
    //TODO debe de buscarse la variable que guardamos en el metodo SavePreferences, o no va a funcionar
    public boolean getSavedPreferences(){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(MainActivity1.this);
        return preferences.getBoolean("isOpenedfor"+ Constantes.ID_USR, false);
    }

    //TODO el metodo para guardar en SharedPreferences (
    // public void savePreferences(){
    //        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(ActivityDetalleAyuda.this);
    //        SharedPreferences.Editor editor = preferences.edit();
    //        editor.putBoolean("isOpenedfor"+ Constantes.ID_USR, true);
    //        editor.commit();
    //    }
    //    checar tambien la clase ActivityDetalleAyuda para ver como se guarda
    //    se debe guardar con otro nombre de variable por ejemplo seguridadIsOpenedFor + Constantes.ID_USR
    //    y este metodo se debe poner en donde se tenga pensado poner el boton de finalizar, en caso de haber, o en donde
    //    termina la ayuda)

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

    private void configNav(){
        NavigationUI.setupWithNavController(bnvMenuSeg, Navigation.findNavController(this, R.id.nav_host_fragment1));
    }
}