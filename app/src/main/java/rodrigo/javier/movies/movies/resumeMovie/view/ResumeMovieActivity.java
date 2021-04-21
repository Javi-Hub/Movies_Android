package rodrigo.javier.movies.movies.resumeMovie.view;

import android.graphics.Color;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import rodrigo.javier.movies.R;
import rodrigo.javier.movies.beans.Movie;

public class ResumeMovieActivity extends AppCompatActivity

        {   //Declaro atributos a mostrar en la pantalla
            private ImageView imgResumeMovie;
            private TextView txtResumeTitle;
            private TextView txtResumeDate;
            private TextView txtResumeRate;
            private TextView txtResumeLanguage;
            private TextView txtResumeVote;
            private TextView txtResumePlot;
            private FloatingActionButton fab;

            private String title = "";
            private boolean click = false;
            private ArrayList<Movie> movies;
            private Movie movie;

            public static String TAG = ResumeMovieActivity.class.getSimpleName();

            @Override
            protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_resume_movie);

                initComponents();
                // Guardar título que proviene al pulsar en la etiqueta seleccionada
                Bundle details = getIntent().getExtras();
                movies = (ArrayList<Movie>) getIntent().getSerializableExtra("movies");
                movie = (Movie) getIntent().getSerializableExtra("movie_set");
                Log.d(TAG, "[getMovie] Title " + movie.getTitle());
                if (details != null){
                    title = details.getString("title");
                }

                selectedMovie();

                fab.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        click = !click;
                        int color = (click) ? Color.RED : Color.WHITE;
                        fab.setColorFilter(color);
                    }
                });

             }

             //Inicializar los componentes del xml
             public void initComponents(){
                imgResumeMovie = findViewById(R.id.activity_resume_movie_image);
                txtResumeTitle = findViewById(R.id.activity_resume_movie_title);
                txtResumeDate =  findViewById(R.id.activity_resume_movie_date);
                txtResumeLanguage =  findViewById(R.id.activity_resume_movie_language);
                txtResumeVote= findViewById(R.id.activity_resume_movie_vote);
                txtResumeRate = findViewById(R.id.activity_resume_movie_rate);
                txtResumePlot = findViewById(R.id.activity_resume_movie_plot);
                fab = findViewById(R.id.activity_resume_floating_button);
                fab.setColorFilter(Color.WHITE);
                txtResumePlot.setMovementMethod(new ScrollingMovementMethod());
             }

            public void selectedMovie() {
                final String URL_IMAGE = "https://image.tmdb.org/t/p/original";
                Picasso.get().load(URL_IMAGE + movie.getImage()).into(imgResumeMovie);
                txtResumeTitle.setText(movie.getTitle());
                txtResumeDate.setText("Fecha: " + movie.getDate());
                txtResumeLanguage.setText("Idioma: " + movie.getLanguage());
                txtResumeVote.setText("Votos: " + movie.getVote());
                txtResumeRate.setText("Valoración: " + movie.getRate());
                txtResumePlot.setText(movie.getOverview());
                System.out.println(movie.getTitle());
            }
        }