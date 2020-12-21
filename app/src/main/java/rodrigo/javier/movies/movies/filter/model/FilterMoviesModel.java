package rodrigo.javier.movies.movies.filter.model;

import rodrigo.javier.movies.beans.Movie;
import rodrigo.javier.movies.movies.filter.contract.FilterMoviesContract;
import rodrigo.javier.movies.movies.filter.presenter.FilterMoviesPresenter;

public class FilterMoviesModel implements FilterMoviesContract.Model {

    private FilterMoviesPresenter presenter;

    public FilterMoviesModel(FilterMoviesPresenter presenter){
        this.presenter = presenter;
    }

    //Obtener listado de películas desde la clase Movie filtradas por valoración
    @Override
    public void getFilterMoviesRate() {
       if ( Movie.getListFilterRate() != null){
            presenter.setMoviesRate(Movie.getListFilterRate());
       }
    }

    //Obtener listado de películas desde la clase Movie filtradas por votos
    @Override
    public void getFilterMoviesVote() {
        if (Movie.getListFilterVote() != null){
            presenter.setMoviesVote(Movie.getListFilterVote());
        }
    }
}
