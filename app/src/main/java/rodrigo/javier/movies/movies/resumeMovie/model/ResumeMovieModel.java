package rodrigo.javier.movies.movies.resumeMovie.model;

import java.util.ArrayList;

import rodrigo.javier.movies.beans.Movie;
import rodrigo.javier.movies.movies.resumeMovie.contract.ResumeMovieContract;
import rodrigo.javier.movies.movies.resumeMovie.presenter.ResumeMoviePresenter;


public class ResumeMovieModel implements ResumeMovieContract.Model {

    private ResumeMoviePresenter rmPresenter;
    private ArrayList<Movie> list;
    private Movie film;

    public ResumeMovieModel(ResumeMoviePresenter rmPresenter){
        this.rmPresenter = rmPresenter;
    }

    //Obtener la pelicula desde el método de la clase Movie
    @Override
    public void getMovieFromList(Movie movie) {
        list = Movie.getList();
        film = new Movie();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getTitle().equals(movie.getTitle())){
                film = list.get(i);
                if (film != null){
                    rmPresenter.setMovie(film);
                }
            }
        }
    }
}
