package alterbrain.com.ui;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

import alterbrain.com.R;
import alterbrain.com.model.Noticia2;

/**
 * A fragment representing a list of Items.
 */
public class NoticiaFragment extends Fragment {

    RecyclerView recyclerView;
    MyNoticiaRecyclerViewAdapter adapterNoticias;
    List<Noticia2> noticiaList;
    Noticia2 documento;
    FirebaseFirestore db;
    String titulo, descripcion, idn;
    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public NoticiaFragment() {
        db = FirebaseFirestore.getInstance();
        documento = new Noticia2();
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static NoticiaFragment newInstance(int columnCount) {
        NoticiaFragment fragment = new NoticiaFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_noticia_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }
            // Lista de elementos (Restaurantes)
            noticiaList = new ArrayList<>();

            db.collection("noticias")
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            noticiaList = new ArrayList<>();

                            for (QueryDocumentSnapshot document : task.getResult()) {

                                Noticia2 cadena = document.toObject(Noticia2.class);
                                noticiaList.add(cadena);

                                adapterNoticias = new MyNoticiaRecyclerViewAdapter(getActivity(), noticiaList);
                                recyclerView.setAdapter(adapterNoticias);

                                /*titulo = document.get("titulo").toString();
                                descripcion = document.get("descripcion").toString();
                                idn = document.get("idn").toString();
                                documento.setTitulo(titulo);
                                documento.setDescripcion(descripcion);
                                documento.setFecha(null);
                                documento.setIdn(idn);



                                noticiaList.add(new Noticia2(""+titulo,  ""+descripcion, null, ""+idn));
                                //noticiaList.add(new Noticia2("",  document.get("descripcion").toString(), null, document.get("idn").toString()));
                                Log.d("Documentos", document.getId() + " => " + document.getData());*/
                                //Log.d("Documentos", "Titulo => " +document.get("titulo").toString());


                            }

                            /*noticias.setNoticiaList(noticiaList);

                            List<Noticia2> individuo = new ArrayList<>();
                            individuo = noticias.getNoticiaList();
                            Noticia2 cadena = new Noticia2();
                            String cadena2 = "";
                            for (int i = 0; i < individuo.size(); i++)
                            {
                                cadena = individuo.get(i);
                                cadena2+= cadena.getTitulo();
                            }
                            Log.d("Documentos", "PRUEBA: titulo= "+cadena2);*/



                        }
                    });

            /*noticiaList.add(new Noticia("Fraccionamiento San Miguel:", "Regalo de despensas", "https://casasenmetepec.mx/wp-content/uploads/Fraccionamiento-san-andres-Fachada-Atardecer1.jpg", "19-03-2021", "0"));
            noticiaList.add(new Noticia("Fraccionamiento Santa Monica", "Situación de vivienda en México", "https://img10.naventcdn.com/avisos/18/00/55/88/29/07/720x532/122518655.jpg", "17-03-2021", "1"));
            noticiaList.add(new Noticia("Fraccionamiento San Andres:", "Junta Vecinal", "https://imagenes.milenio.com/ektFvmdDbT8nFsJuZCXNUFJ9wuM=/958x596/https://www.milenio.com/uploads/media/2019/04/21/demanda-compradores-vivienda-casas-renta.jpg", "10-02-2021", "2"));
            noticiaList.add(new Noticia("Fraccionamiento Santa Monica", "¡ AYÚDAME A REGRESAR A MI CASA !", "https://img10.naventcdn.com/avisos/18/00/55/88/29/07/720x532/122518655.jpg", "04-02-2021", "3"));
            noticiaList.add(new Noticia("Fraccionamiento Lomas de Metepec:", "Pago de agua", "https://i0.wp.com/carpin.mx/wp-content/uploads/2020/04/La-cima-acceso.jpg", "25-01-2021", "4"));*/
            //documento = noticiaList.get(1);
            //Log.d("Documentos", "PRUEBA: titulo= "+documento.getTitulo());

            /*List<Noticia2> individuo2 = new ArrayList<>();
            individuo2 = noticias.getNoticiaList();
            Noticia2 cadena3 = new Noticia2();
            String cadena22 = "";
            for (int i = 0; i < individuo2.size(); i++)
            {
                cadena3 = individuo2.get(i);
                cadena22+= cadena3.getTitulo();
            }
            Log.d("Documentos", "PRUEBA: titulo= "+titulo);*/


        }
        return view;
    }
}