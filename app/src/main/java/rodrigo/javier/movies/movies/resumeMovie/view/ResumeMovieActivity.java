package rodrigo.javier.movies.movies.resumeMovie.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import rodrigo.javier.movies.R;
import rodrigo.javier.movies.beans.Movie;
import rodrigo.javier.movies.movies.resumeMovie.contract.ResumeMovieContract;
import rodrigo.javier.movies.movies.resumeMovie.presenter.ResumeMoviePresenter;

public class ResumeMovieActivity extends AppCompatActivity implements ResumeMovieContract.View

        {   //Declaro atributos a mostrar en la pantalla
            private ResumeMoviePresenter rmPresenter;
            private ImageView imgResumeMovie;
            private TextView txtResumeTitle;
            private TextView txtResumeDate;
            private TextView txtResumeRate;
            private TextView txtResumeLanguage;
            private TextView txtResumeVote;
            private TextView txtResumeOverview;

            private String title = "";
            private Movie movie;

            @Override
            protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_resume_movie);

                initComponents();
                // Guardar título que proviene al pulsar en la etiqueta seleccionada
                Bundle details = getIntent().getExtras();
                if (details != null){
                    title = details.getString("title");
                }
                //Recoger la pelicula seleccionada
                movie = new Movie();
                movie.setTitle(title);
                //Comunicar con la clase Presenter desde el View
                rmPresenter = new ResumeMoviePresenter(this);
                rmPresenter.getMovie(movie);

             }

             //Inicializar los componentes del xml
             public void initComponents(){
                imgResumeMovie = (ImageView) findViewById(R.id.imgResumeMovie);
                txtResumeTitle = (TextView) findViewById(R.id.txtResumeTitle);
                txtResumeDate = (TextView) findViewById(R.id.txtResumeDate);
                txtResumeLanguage = (TextView) findViewById(R.id.txtResumeLanguage);
                txtResumeVote= (TextView) findViewById(R.id.txtResumeVote);
                txtResumeRate = (TextView) findViewById(R.id.txtResumeRate);
                txtResumeOverview = (TextView) findViewById(R.id.txtResumeOverview);
             }

            //Mostrar los datos de la película seleccionada
            @Override
            public void selectedMovie(Movie movie) {
                Picasso.get().load(movie.getImage()).into(imgResumeMovie);
                txtResumeTitle.setText(movie.getTitle());
                txtResumeDate.setText("Fecha: " + movie.getDate());
                txtResumeLanguage.setText("Idioma: " + movie.getLanguage());
                txtResumeVote.setText("Votos: " + movie.getVote());
                txtResumeRate.setText("Valoración: " + movie.getRate());
                txtResumeOverview.setText(movie.getOverview());
            }
        }