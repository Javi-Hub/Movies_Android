package rodrigo.javier.movies.movies.lstMovies.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Spinner;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

import rodrigo.javier.movies.R;
import rodrigo.javier.movies.beans.Movie;
import rodrigo.javier.movies.movies.lstMovies.contract.LstMoviesContract;
import rodrigo.javier.movies.movies.lstMovies.presenter.LstMoviesPresenter;

public class LstMoviesActivity
        extends AppCompatActivity
        implements LstMoviesContract.View
        {
            private LstMoviesPresenter lstMoviesPresenter;

            private FrameLayout frame_container;
            private LinearLayout errorLayout;
            private ProgressBar progressBar;
            private Button retryButton;
            private BottomNavigationView nav;

            private ArrayList<Movie> movies;

            @Override
            protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_lst_movies);
                initComponents();
                initBottomNavigation();
                //Comunicar con la clase Presenter desde el View
                lstMoviesPresenter = new LstMoviesPresenter(this);
                lstMoviesPresenter.getMovies(this);

                retryButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        progressBar.setVisibility(View.VISIBLE);
                        errorLayout.setVisibility(View.GONE);
                        //header.setVisibility(View.GONE);
                        frame_container.setVisibility(View.GONE);
                        lstMoviesPresenter.getMovies(LstMoviesActivity.this);
                    }
                });

            }

            @Override
            public void success(ArrayList<Movie> movies) {
                progressBar.setVisibility(View.GONE);
                errorLayout.setVisibility(View.GONE);
                frame_container.setVisibility(View.VISIBLE);
                nav.setVisibility(View.VISIBLE);
                this.movies = movies;
                setFragment(List_Movies_Fragment.newInstance(movies));
            }

            @Override
            public void error(String message) {
                progressBar.setVisibility(View.GONE);
                errorLayout.setVisibility(View.VISIBLE);
                frame_container.setVisibility(View.GONE);
                nav.setVisibility(View.GONE);
            }

            public void initComponents(){
                frame_container = findViewById(R.id.activity_lst_movies_fragment_container);
                errorLayout = findViewById(R.id.activity_lst_movies_error_loading);
                progressBar = findViewById(R.id.activity_lst_movies_loading_progressBar);
                retryButton = findViewById(R.id.button_retry);
                nav = findViewById(R.id.activity_lst_movies_bottom_navigation);
            }

            public void setFragment(Fragment fragment){
                getSupportFragmentManager().beginTransaction().replace(R.id.activity_lst_movies_fragment_container, fragment).commit();
            }

            public void initBottomNavigation(){
                nav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()){
                            case R.id.menu_nav_all_movies:
                                setFragment(List_Movies_Fragment.newInstance(new ArrayList<>(movies)));
                                break;
                            case R.id.menu_nav_rate_movies:
                                setFragment(List_Movies_Rate_Fragment.newInstance(new ArrayList<>(movies)));
                                break;
                            case R.id.menu_nav_vote_movies:
                                setFragment(List_Movies_Votes_Fragment.newInstance(new ArrayList<>(movies)));
                                break;
                        }

                        return false;
                    }
                });
            }

        }

//private LinearLayout header;
//private Spinner spinFilter;

//*************** En el onCreate **********************
/*//Adaptador para capturar la selección desde el Spinner
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
                });*/

//************ En el success***************
//header.setVisibility(View.VISIBLE);

//***************En el error **********
//header.setVisibility(View.GONE);
/*Toast.makeText(this, "Fallo conexión con el servidor," +
                        " al cargar el listado de peliculas", Toast.LENGTH_SHORT).show();*/

//********** En el initComponents ***************
//spinFilter = (Spinner) findViewById(R.id.spinFilter);
//header = findViewById(R.id.activity_lst_movies_header);