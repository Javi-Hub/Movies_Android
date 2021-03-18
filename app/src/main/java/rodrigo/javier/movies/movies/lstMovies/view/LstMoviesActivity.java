package rodrigo.javier.movies.movies.lstMovies.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Spinner;

import java.util.ArrayList;

import rodrigo.javier.movies.R;
import rodrigo.javier.movies.beans.Movie;
import rodrigo.javier.movies.movies.filter.view.FilterMoviesActivity;
import rodrigo.javier.movies.movies.lstMovies.contract.LstMoviesContract;
import rodrigo.javier.movies.movies.lstMovies.presenter.LstMoviesPresenter;

public class LstMoviesActivity
        extends AppCompatActivity
        implements LstMoviesContract.View
        {
            private LstMoviesPresenter lstMoviesPresenter;

            private LinearLayout header;
            private Spinner spinFilter;
            private FrameLayout frame_container;
            private LinearLayout errorLayout;
            private ProgressBar progressBar;
            private Button retryButton;

            private ArrayList<Movie> movies;

            @Override
            protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_lst_movies);
                initComponents();
                //Comunicar con la clase Presenter desde el View
                lstMoviesPresenter = new LstMoviesPresenter(this);
                lstMoviesPresenter.getMovies();

                //Adaptador para capturar la selección desde el Spinner
                ArrayAdapter<CharSequence> spAdapter = ArrayAdapter.createFromResource(this,
                        R.array.lstSpinner, android.R.layout.simple_spinner_dropdown_item);
                spinFilter.setAdapter(spAdapter);

                //Capturar la selección pulsada en el spinner
                spinFilter.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        if (parent.getSelectedItem().toString().equals("Mejor Valoradas")){
                            setFragment(List_Movies_Rate_Fragment.newInstance(new ArrayList<>(movies)));

                        } else if(parent.getSelectedItem().toString().equals("Mas Votadas")){
                            setFragment(List_Movies_Votes_Fragment.newInstance(new ArrayList<>(movies)));

                        } else if(parent.getSelectedItem().toString().equals("Todas las peliculas")) {
                            setFragment(List_Movies_Fragment.newInstance(new ArrayList<>(movies)));
                        }
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });

                retryButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        progressBar.setVisibility(View.VISIBLE);
                        errorLayout.setVisibility(View.GONE);
                        header.setVisibility(View.GONE);
                        frame_container.setVisibility(View.GONE);
                    }
                });

            }

            @Override
            public void success(ArrayList<Movie> movies) {
                progressBar.setVisibility(View.GONE);
                errorLayout.setVisibility(View.GONE);
                header.setVisibility(View.VISIBLE);
                frame_container.setVisibility(View.VISIBLE);
                this.movies = movies;
                setFragment(List_Movies_Fragment.newInstance(movies));
            }

            @Override
            public void error(String message) {
                progressBar.setVisibility(View.GONE);
                errorLayout.setVisibility(View.VISIBLE);
                header.setVisibility(View.GONE);
                frame_container.setVisibility(View.GONE);

                /*Toast.makeText(this, "Fallo conexión con el servidor," +
                        " al cargar el listado de peliculas", Toast.LENGTH_SHORT).show();*/
            }

            public void initComponents(){
                spinFilter = (Spinner) findViewById(R.id.spinFilter);
                header = findViewById(R.id.header_list_movies);
                frame_container = findViewById(R.id.fragment_container);
                errorLayout = findViewById(R.id.layout_error);
                progressBar = findViewById(R.id.error_progressBar);
                retryButton = findViewById(R.id.error_button_retry);
            }

            public void setFragment(Fragment fragment){
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).commit();
            }

        }