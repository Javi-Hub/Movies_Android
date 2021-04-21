package rodrigo.javier.movies.movies.lstMovies.view;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

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

            public static String TAG = LstMoviesActivity.class.getSimpleName();

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
                Log.d(TAG, "[succes] Title " + movies.get(0).getTitle());
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
                                nav.setSelected(true);
                                setFragment(List_Movies_Fragment.newInstance(new ArrayList<>(movies)));
                                break;
                            case R.id.menu_nav_rate_movies:
                                nav.setSelected(true);
                                setFragment(List_Movies_Rate_Fragment.newInstance(new ArrayList<>(movies)));
                                break;
                            case R.id.menu_nav_vote_movies:
                                nav.setSelected(true);
                                setFragment(List_Movies_Votes_Fragment.newInstance(new ArrayList<>(movies)));
                                break;
                        }

                        return false;
                    }
                });
            }

        }
