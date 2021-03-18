package rodrigo.javier.movies.movies.filter.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

import rodrigo.javier.movies.R;
import rodrigo.javier.movies.beans.Movie;
import rodrigo.javier.movies.movies.filter.adapter.FilterMoviesAdapter;
import rodrigo.javier.movies.movies.filter.contract.FilterMoviesContract;
import rodrigo.javier.movies.movies.filter.presenter.FilterMoviesPresenter;
import rodrigo.javier.movies.movies.lstMovies.adapter.LstMovieAdapter;
import rodrigo.javier.movies.movies.lstMovies.view.LstMoviesActivity;

public class FilterMoviesActivity extends AppCompatActivity implements FilterMoviesContract.View {

    private RecyclerView recyclerFilterMovies;
    private RecyclerView.LayoutManager lManager;
    private FilterMoviesPresenter filterMoviesPresenter;
    private DividerItemDecoration divider;
    private Spinner spinFilter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter_movies);
        initComponents();
        filterMoviesPresenter = new FilterMoviesPresenter(this);

        //Obtener la posición de la selección del spinner que se ha pulsado
        int pos = 0;
        Bundle filter = getIntent().getExtras();
        if (filter != null){
            pos = filter.getInt("position");
        }

        //Adaptador para capturar la selección desde el Spinner
        ArrayAdapter<CharSequence> spAdapter = ArrayAdapter.createFromResource(this,
                R.array.lstSpinner, android.R.layout.simple_spinner_dropdown_item);
        spinFilter.setAdapter(spAdapter);

        /*Al cambiar la pantalla desde la página principal mostrar en el spinner
        la selección pulsada*/
        spinFilter.setSelection(pos);

        /*Cambio de pantalla según la selección pulsada en el spinner */
        spinFilter.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (parent.getSelectedItem().toString().equals("Mejor Valoradas")){
                    filterMoviesPresenter.getMoviesRate();
                } else if (parent.getSelectedItem().toString().equals("Mas Votadas")){
                    filterMoviesPresenter.getMoviesVote();
                } else if (parent.getSelectedItem().toString().equals("Todas las películas")){
                    Intent broadcast = new Intent(getBaseContext(), LstMoviesActivity.class);
                    startActivity(broadcast);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }

    //Mostrar el listado de películas filtradas por Valoración
    @Override
    public void successRate(ArrayList<Movie> movies) {
        // Crear un nuevo adaptador
        showListFilter(movies);
    }
    //Mostrar listado de películas filtradas por votos
    @Override
    public void successVote(ArrayList<Movie> movies) {
        showListFilter(movies);
    }

    @Override
    public void error(String message) {
        Toast.makeText(this, "Error al mostrar las películas", Toast.LENGTH_SHORT).show();
    }

    public void initComponents(){
        spinFilter = (Spinner) findViewById(R.id.spinFilter);
    }

    public void showListFilter(ArrayList<Movie> movies){
        FilterMoviesAdapter adapter = new FilterMoviesAdapter(movies);

        lManager = new LinearLayoutManager(this);

        recyclerFilterMovies = (RecyclerView) findViewById(R.id.recyclerFilterMovies);
        recyclerFilterMovies.setHasFixedSize(true);
        recyclerFilterMovies.setLayoutManager(lManager);

        divider = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        recyclerFilterMovies.addItemDecoration(divider);

        recyclerFilterMovies.setAdapter(adapter);
    }
}