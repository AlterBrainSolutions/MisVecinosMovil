package alterbrain.com.ui.home;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;

import alterbrain.com.AccesosActivity;
import alterbrain.com.ActivityDetalleAyuda;
import alterbrain.com.AdeudosActivity;
import alterbrain.com.AgendaActivity;
import alterbrain.com.AnuncioActivity;
import alterbrain.com.ConversacionActivity;
import alterbrain.com.Documentos2Activity;
import alterbrain.com.EncuestasActivity;
import alterbrain.com.MainActivity1;
import alterbrain.com.Noticias3Activity;
import alterbrain.com.PagosActivity;
import alterbrain.com.R;
import alterbrain.com.ReciclajeActivity;
import alterbrain.com.ReservaActivity;
import alterbrain.com.ServiciosActivity3;
import alterbrain.com.Transparencia10Activity;
import alterbrain.com.Transparencia11Activity;
import alterbrain.com.Transparencia12Activity;
import alterbrain.com.Transparencia2Activity;
import alterbrain.com.Transparencia3Activity;
import alterbrain.com.Transparencia4Activity;
import alterbrain.com.Transparencia5Activity;
import alterbrain.com.Transparencia6Activity;
import alterbrain.com.Transparencia7Activity;
import alterbrain.com.Transparencia8Activity;
import alterbrain.com.Transparencia9Activity;
import alterbrain.com.TransparenciaActivity;
import alterbrain.com.app.Constantes;
import alterbrain.com.model.Adeudos;
import alterbrain.com.ui.MyDeudaRecyclerViewAdapter;

public class HomeFragment extends Fragment {
    TextView tvDescrip, tvFraccName;
    ImageView ivNoticias, ivAgenda, ivDocumentos, ivManita, ivTransparency, ivPagos, ivAdeudos, ivEncuestas, ivConversacion, ivReciclaje;
    ImageView btnMas, btnCerrar, btnAnuncio, btnReserva, btnServicio, btnBuzon;
    ConstraintLayout constraintMenuPop;
    RelativeLayout rlHome;
    String descri;
    FirebaseFirestore db;
    private int usuario = Constantes.ID_USR, conAdeudo = 0, alCorriente = 0;
    private String URL_corriente = "https://missvecinos.com.mx/android/adeudosConsulta.php?usuario=" + usuario;
    private RequestQueue mQueue;

    private ImageView profilePic;
    private FirebaseStorage storage;
    private StorageReference storageReference;

    private HomeViewModel homeViewModel;

   /* @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }*/

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        tvDescrip = root.findViewById(R.id.textViewDescripcion);
        ivNoticias = root.findViewById(R.id.imageViewNoticias);
        ivAgenda = root.findViewById(R.id.imageViewAgenda);
        ivDocumentos = root.findViewById(R.id.imageViewDocumentosh);
        ivReciclaje = root.findViewById(R.id.imageViewReciclaje);
        ivManita = root.findViewById(R.id.imageView_mas);
        ivTransparency = root.findViewById(R.id.imageViewTransparenciah);
        ivPagos = root.findViewById(R.id.imageViewPagosh);
        ivAdeudos = root.findViewById(R.id.imageViewAdeudosh);
        ivEncuestas = root.findViewById(R.id.imageViewEncuestas);
        ivConversacion = root.findViewById(R.id.imageViewConversacion);
        btnCerrar = root.findViewById(R.id.imageViewCerrarPop);
        btnAnuncio = root.findViewById(R.id.imageViewAnuncios);
        btnReserva = root.findViewById(R.id.imageViewReserva);
        btnBuzon = root.findViewById(R.id.imageViewBuzon);
        btnServicio = root.findViewById(R.id.imageViewServicios);
        constraintMenuPop = root.findViewById(R.id.constraintMenuPop);
        rlHome = root.findViewById(R.id.relativeLayoutHomeServ);
        tvFraccName = root.findViewById(R.id.textViewDescFrac);

        db = FirebaseFirestore.getInstance();

        mQueue = Volley.newRequestQueue(getActivity());

        jsonParse2();

        ivNoticias.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent noticia = new Intent(getActivity(), Noticias3Activity.class);
                getActivity().startActivity(noticia);
            }
        });
        ivDocumentos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent detail = new Intent(getActivity(), Documentos2Activity.class);
                getActivity().startActivity(detail);
            }
        });
        ivReciclaje.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Toast.makeText( HomeFragment.this.getActivity(),
                        "Booleasno "+Boolean.toString(getSavedPreferences()), Toast.LENGTH_SHORT).show();*/
                if (getSavedPreferences() == true) {
                    Intent detail = new Intent(getActivity(), ReciclajeActivity.class);
                    getActivity().startActivity(detail);
                } else {
                    Intent detail = new Intent(getActivity(), ActivityDetalleAyuda.class);
                    getActivity().startActivity(detail);
                }
            }
        });
        ivAgenda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), AgendaActivity.class);
                startActivity(i);
            }
        });
        ivConversacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), ConversacionActivity.class);
                startActivity(i);
            }
        });
        btnCerrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                constraintMenuPop.setVisibility(View.GONE);
            }
        });
        ivManita.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                constraintMenuPop.setVisibility(View.VISIBLE);
                //rlHome.setBackground(v.setBackground();
            }
        });
        ivTransparency.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Intent i = new Intent(getActivity(), Transparencia7Activity.class);
                startActivity(i);*/

                switch(Constantes.MES_ACT){
                    case 1:
                        Intent i = new Intent(getActivity(), TransparenciaActivity.class);
                        startActivity(i);
                        break;
                    case 2:
                        Intent i2 = new Intent(getActivity(), Transparencia2Activity.class);
                        startActivity(i2);
                        break;
                    case 3:
                        Intent i3 = new Intent(getActivity(), Transparencia3Activity.class);
                        startActivity(i3);
                        break;
                    case 4:
                        Intent i4 = new Intent(getActivity(), Transparencia4Activity.class);
                        startActivity(i4);
                        break;
                    case 5:
                        Intent i5 = new Intent(getActivity(), Transparencia5Activity.class);
                        startActivity(i5);
                        break;
                    case 6:
                        Intent i6 = new Intent(getActivity(), Transparencia6Activity.class);
                        startActivity(i6);
                        break;
                    case 7:
                        Intent i7 = new Intent(getActivity(), Transparencia7Activity.class);
                        startActivity(i7);
                        break;
                    case 8:
                        Intent i8 = new Intent(getActivity(), Transparencia8Activity.class);
                        startActivity(i8);
                        break;
                    case 9:
                        Intent i9= new Intent(getActivity(), Transparencia9Activity.class);
                        startActivity(i9);
                        break;
                    case 10:
                        Intent i10 = new Intent(getActivity(), Transparencia10Activity.class);
                        startActivity(i10);
                        break;
                    case 11:
                        Intent i11 = new Intent(getActivity(), Transparencia11Activity.class);
                        startActivity(i11);
                        break;
                    case 12:
                        Intent i12 = new Intent(getActivity(), Transparencia12Activity.class);
                        startActivity(i12);
                        break;
                }
            }
        });
        ivPagos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), PagosActivity.class);
                startActivity(i);
            }
        });
        ivAdeudos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), AdeudosActivity.class);
                startActivity(i);
            }
        });
        ivEncuestas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), EncuestasActivity.class);
                startActivity(i);
            }
        });
        btnAnuncio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), AnuncioActivity.class);
                startActivity(i);
            }
        });
        btnReserva.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), ReservaActivity.class);
                startActivity(i);
            }
        });
        btnBuzon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), AccesosActivity.class);
                startActivity(i);
            }
        });
        btnServicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), ServiciosActivity3.class);
                startActivity(i);
            }
        });
        profilePic = root.findViewById(R.id.imageView_face);
        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();

        //getPlayerNames();
        //MainActivity1.userId = "";

        //Nombre usu
        getUsuName();

        return root;
    }

    /*public void savePreferences(){
        SharedPreferences preferences = this.getActivity().getSharedPreferences("usrPreferences", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean("isOpenedfor"+ Constantes.ID_USR, true);
        editor.commit();
    }*/

    public boolean getSavedPreferences(){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(HomeFragment.this.getActivity());
        return preferences.getBoolean("isOpenedfor"+ Constantes.ID_USR, false);
    }

    private void getUsuName() {
        descri = Constantes.NUM_CSA;
        tvDescrip.setText("CASA " + descri);
    }

    private void getPlayerNames() {
        // Obtener el nombre del player 1
        db.collection("users")
                .document(MainActivity1.userId.toString())
                .get()
                .addOnSuccessListener(getActivity(), new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot snapshot) {
                        descri = snapshot.get("name").toString();
                        tvDescrip.setText(descri);
                    }
                });

        //Obtener imagen de perfil
        StorageReference imageRef = storageReference.child("images/" + MainActivity1.userId);

        final long ONE_MEGABYTE = (1024 * 1024) * 3;
        imageRef.getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
            @Override
            public void onSuccess(byte[] bytes) {
                Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                profilePic.setImageBitmap(bitmap);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                // Handle any errors
            }
        });

    }

    private void jsonParse2() {

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, URL_corriente, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {

                            /*****************************************************************************/

                            JSONArray resultadosFracc = response.getJSONArray("fracc_name");

                            JSONObject jsonObjectFracc = new JSONObject(resultadosFracc.get(0).toString());

                            /*Toast.makeText(getActivity(),
                                    "Fraccionamiento: " + resultadosFracc.get(0).toString(),
                                    Toast.LENGTH_SHORT).show();*/

                            Constantes.NOMBRE_FRACC = jsonObjectFracc.getString("nombreFracc");
                            tvFraccName.setText(Constantes.NOMBRE_FRACC);
                            /***************************************************************************/

                            JSONArray resultadosMes = response.getJSONArray("month_num");

                            JSONObject jsonObjectMes = new JSONObject(resultadosMes.get(0).toString());

                            Constantes.MES_ACT = jsonObjectMes.getInt("Mes");

                            /*****************************************************************************/

                            JSONArray resultados2 = response.getJSONArray("user_vis");

                            int tamRes2 = resultados2.length(), visibilidad = 0, rol_usr = 0;

                            for (int i = 0; i < tamRes2; i++) {

                                JSONObject jsonObject = new JSONObject(resultados2.get(i).toString());

                                visibilidad = jsonObject.getInt("visibilidad");
                                rol_usr = jsonObject.getInt("idUsuarioRol");

                                /*Toast.makeText(getActivity(),
                                        "Un mes: " + visibilidad + "dos meses: " +
                                                rol_usr, Toast.LENGTH_SHORT).show();*/
                            }

                            Constantes.DETAIL_USR_VISIBLE = visibilidad;
                            Constantes.ROL_USR = rol_usr;

                            /*****************************************************************************/

                            JSONArray resultados3 = response.getJSONArray("resultados");

                            ArrayList<Integer> listdata = new ArrayList<Integer>();

                            int tamRes3 = resultados3.length();

                            for (int i = 0; i < tamRes3; i++) {

                                JSONObject jsonObject = new JSONObject(resultados3.get(i).toString());

                                listdata.add(Integer.valueOf(jsonObject.getString("numeroCasa")));
                            }

                            /*****************************************************************************/

                            ArrayList<Integer> dataCounter = new ArrayList<Integer>();
                            JSONArray resultados4 = response.getJSONArray("con adeudo");
                            int tamRes4 = resultados4.length(), occurrences = 0;

                            for (int i = 0; i < tamRes4; i++) {

                                JSONObject jsonObject = new JSONObject(resultados4.get(i).toString());

                                String casa = jsonObject.getString("numeroCasa");

                                occurrences = Collections.frequency(listdata, Integer.valueOf(casa));

                                dataCounter.add(occurrences);

                                if(casa.equals(Constantes.NUM_CSA)){
                                    /*Toast.makeText(getActivity(),
                                            "Ocurrencia: " + occurrences +"casa: "+ casa, Toast.LENGTH_SHORT).show();*/
                                    Constantes.DEUDA_MESES_USR = occurrences;
                                }
                                /*System.out.println(occurrences);*/
                                /*Log.d(String.valueOf(i), String.valueOf(occurrences));*/
                            }

                            int unMes = 0,masDosMeses = 0;

                            for (int i = 0; i < dataCounter.size(); i++){

                                if (dataCounter.get(i) == 1){
                                    unMes++;
                                }else if(dataCounter.get(i) >= 2){
                                    masDosMeses++;
                                }
                                /*Log.d(String.valueOf(i), String.valueOf(dataCounter.get(i))+ " " +
                                        String.valueOf(unMes)+ " " +
                                        String.valueOf(masDosMeses));*/
                            }
                            Constantes.UN_MES = unMes;
                            Constantes.DOS_MESES = masDosMeses;

                            /*****************************************************************************/

                            ArrayList<Integer> dataAlCorriente = new ArrayList<Integer>();
                            JSONArray resultados = response.getJSONArray("al corriente");
                            int tamRes = resultados.length();

                            for (int i = 0; i < tamRes; i++) {

                                JSONObject jsonObject = new JSONObject(resultados.get(i).toString());

                                dataAlCorriente.add(Integer.valueOf(jsonObject.getString("numeroCasa")));
                            }

                            dataAlCorriente.removeAll(listdata);
                            Constantes.AL_CORRIENTE = dataAlCorriente.size();

                            /*****************************************************************************/

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        mQueue.add(request);
    }

    @Override
    public void onStart() {
        super.onStart();
        jsonParse2();
    }
}