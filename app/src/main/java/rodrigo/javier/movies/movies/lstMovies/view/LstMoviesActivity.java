package rodrigo.javier.movies.movies.lstMovies.view;

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
import rodrigo.javier.movies.movies.filter.view.FilterMoviesActivity;
import rodrigo.javier.movies.movies.lstMovies.adapter.LstMovieAdapter;
import rodrigo.javier.movies.movies.lstMovies.contract.LstMoviesContract;
import rodrigo.javier.movies.movies.lstMovies.presenter.LstMoviesPresenter;

public class LstMoviesActivity
        extends AppCompatActivity
        implements LstMoviesContract.View
        {
            private RecyclerView recycler;
            private DividerItemDecoration divider;
            private RecyclerView.LayoutManager lManager;
            private LstMoviesPresenter lstMoviesPresenter;
            private Spinner spinFilter;

            @Override
            protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_lst_movies);
                initComponents();

                //Adaptador para capturar la selección desde el Spinner
                ArrayAdapter<CharSequence> spAdapter = ArrayAdapter.createFromResource(this,
                        R.array.lstSpinner, android.R.layout.simple_spinner_dropdown_item);
                spinFilter.setAdapter(spAdapter);

                //Capturar la selección pulsada en el spinner
                spinFilter.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        if (parent.getSelectedItem().toString().equals("Mejor Valoradas")){
                            Intent broadcastFilter = new Intent(getBaseContext(), FilterMoviesActivity.class);
                            broadcastFilter.putExtra("position", spAdapter.getPosition("Mejor Valoradas"));
                            startActivity(broadcastFilter);
                        } else if(parent.getSelectedItem().toString().equals("Mas Votadas")){
                            Intent broadcastFilter = new Intent(getBaseContext(), FilterMoviesActivity.class);
                            broadcastFilter.putExtra("position", spAdapter.getPosition("Mas Votadas"));
                            startActivity(broadcastFilter);
                        }
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });

                //Comunicar con la clase Presenter desde el View
                lstMoviesPresenter = new LstMoviesPresenter(this);
                lstMoviesPresenter.getMovies();

                // Obtener el Recycler
                recycler = findViewById(R.id.recyclerLstMovies);
                recycler.setHasFixedSize(true);


                //Usar el administrador para LinearLayout
                lManager = new LinearLayoutManager(this);
                recycler.setLayoutManager(lManager);

            }

            @Override
            public void success(ArrayList<Movie> movies) {
                // Crear un nuevo adaptador
                LstMovieAdapter adapter = new LstMovieAdapter(movies);
                //División entre las etiquetas de la lista
                divider = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
                divider.setDrawable(getResources().getDrawable(R.drawable.recyclerview_divider));
                recycler.addItemDecoration(divider);
                recycler.setAdapter(adapter);
            }

            @Override
            public void error(String message) {
                Toast.makeText(this, "Fallo conexión con el servidor," +
                        " al cargar el listado de peliculas", Toast.LENGTH_SHORT).show();
            }

            public void initComponents(){
                spinFilter = (Spinner) findViewById(R.id.spinFilter);
            }

        }