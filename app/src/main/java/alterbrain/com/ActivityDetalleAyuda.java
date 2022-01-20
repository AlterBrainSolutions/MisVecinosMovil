package alterbrain.com;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import com.google.zxing.integration.android.IntentIntegrator;

import alterbrain.com.app.Constantes;

public class ActivityDetalleAyuda extends AppCompatActivity {

    int positionPager = 0, numeroItems;
    Button botonSlider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_ayuda);
        ViewPager2 pager;

        /*pager = findViewById(R.id.detalleAyudavP);*/

        /*sigSlider = v.findViewById(R.id.buttonSiguienteSlider);*/
        pager = findViewById(R.id.detalleAyudavP);
        botonSlider = findViewById(R.id.buttonSiguienteSlider);
        ViewPagerAdapter adapter = new ViewPagerAdapter(ActivityDetalleAyuda.this);
        pager.setAdapter(adapter);
        /*botonPrueba = ViewPagerAdapter.pruebaButton;*/

        botonSlider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                positionPager = pager.getCurrentItem();
                numeroItems = pager.getAdapter().getItemCount();
                /*Toast.makeText( ActivityDetalleAyuda.this,
                        "Los numeros son: "+ numeroItems , Toast.LENGTH_SHORT).show();*/
                if (positionPager < numeroItems) {
                    positionPager++;
                    pager.setCurrentItem(positionPager);
                }
                if (positionPager == 4) {
                    //TODO en este caso se pone en la pagina 4 que es la ultima, y es la que manda a llamar al activity siguiente
                    savePreferences();
                    Intent i = new Intent(ActivityDetalleAyuda.this, ReciclajeActivity.class);
                    startActivity(i);
                    finish();
                }
                if(positionPager == numeroItems - 1){
                    botonSlider.setText("Finalizar");
                }
            }
        });

        pager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            // This method is triggered when there is any scrolling activity for the current page
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
                if(position == 3){
                    botonSlider.setText("Finalizar");
                }else{
                    botonSlider.setText("Siguiente");
                }
            }

            // triggered when you select a new page
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
            }

            // triggered when there is
            // scroll state will be changed
            @Override
            public void onPageScrollStateChanged(int state) {
                super.onPageScrollStateChanged(state);
            }
        });
    }

    //TODO metodo para guardar la variable booleana para la ayuda
    public void savePreferences(){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(ActivityDetalleAyuda.this);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean("isOpenedfor"+ Constantes.ID_USR, true);
        editor.commit();
    }
}