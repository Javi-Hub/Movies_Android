package rodrigo.javier.movies.movies.lstMovies.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import rodrigo.javier.movies.R;
import rodrigo.javier.movies.beans.Movie;
import rodrigo.javier.movies.movies.lstMovies.adapter.LstMovieAdapter;


public class List_Movies_Fragment extends Fragment {

    private RecyclerView recycler;
    private DividerItemDecoration divider;
    private RecyclerView.LayoutManager lManager;

    private View view;
    private ArrayList<Movie> movies;

    private static final String MOVIES = "param1";

    public static final String TAG = List_Movies_Fragment.class.getSimpleName();

    public List_Movies_Fragment() {

    }

    public static List_Movies_Fragment newInstance(ArrayList<Movie> movies) {
        List_Movies_Fragment fragment = new List_Movies_Fragment();
        Bundle args = new Bundle();
        args.putSerializable(MOVIES, movies);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null)
            movies = (ArrayList<Movie>) getArguments().getSerializable(MOVIES);
            Log.d(TAG, "[getArguments] Title " + movies.get(0).getTitle());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_list_movies, container, false);
        initComponents();
        loadingList(movies);
        return view;
    }

    public void initComponents(){
        recycler = view.findViewById(R.id.recycler_list_movies);

    }

    public void loadingList(ArrayList<Movie> lstMovies){

        // Obtener el Recycler
        recycler.setHasFixedSize(true);
        //Usar el administrador para LinearLayout
        lManager = new LinearLayoutManager(view.getContext());
        recycler.setLayoutManager(lManager);

        // Crear un nuevo adaptador
        LstMovieAdapter adapter = new LstMovieAdapter(lstMovies);
        //División entre las etiquetas de la lista
        divider = new DividerItemDecoration(view.getContext(), DividerItemDecoration.VERTICAL);
        divider.setDrawable(getResources().getDrawable(R.drawable.recyclerview_divider));
        recycler.addItemDecoration(divider);
        recycler.setAdapter(adapter);
    }
}