package rodrigo.javier.movies.movies.resumeMovie.presenter;

import rodrigo.javier.movies.beans.Movie;
import rodrigo.javier.movies.movies.resumeMovie.contract.ResumeMovieContract;
import rodrigo.javier.movies.movies.resumeMovie.model.ResumeMovieModel;
import rodrigo.javier.movies.movies.resumeMovie.view.ResumeMovieActivity;

public class ResumeMoviePresenter implements ResumeMovieContract.Presenter {

    public ResumeMovieModel rmModel;
    public ResumeMovieActivity view;

    public ResumeMoviePresenter(ResumeMovieActivity view){
        this.view = view;
        this.rmModel = new ResumeMovieModel(this);
    }

    //Recoger la pelicula para mostrarla en el view
    @Override
    public void setMovie(Movie movie) {
        if (view != null){
            view.selectedMovie(movie);
        }
    }

    //Comunicar con la clase Model para obtener la pelicula seleccionada
    @Override
    public void getMovie(Movie movie) {
        if (view != null)
            rmModel.getMovieFromList(movie);
    }

}
