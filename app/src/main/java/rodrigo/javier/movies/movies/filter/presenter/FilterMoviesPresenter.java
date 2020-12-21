package rodrigo.javier.movies.movies.filter.presenter;

import java.util.ArrayList;

import rodrigo.javier.movies.beans.Movie;
import rodrigo.javier.movies.movies.filter.contract.FilterMoviesContract;
import rodrigo.javier.movies.movies.filter.model.FilterMoviesModel;
import rodrigo.javier.movies.movies.filter.view.FilterMoviesActivity;

public class FilterMoviesPresenter implements FilterMoviesContract.Presenter {

    private FilterMoviesModel model;
    private FilterMoviesActivity view;

    public FilterMoviesPresenter(FilterMoviesActivity view){
        this.view = view;
        this.model = new FilterMoviesModel(this);
    }

    //Obtener listado de películas desde la clase Model filtradas por valoración
    @Override
    public void getMoviesRate() {
        model.getFilterMoviesRate();
    }

    //Devolver listado de películas filtradas por valoración a la clase View
    @Override
    public void setMoviesRate(ArrayList<Movie> listMoviesRate) {
        view.successRate(listMoviesRate);
    }

    //Obtener listado de películas desde la clase Model filtradas por votos
    @Override
    public void getMoviesVote() {
        model.getFilterMoviesVote();
    }

    //Devolver listado de películas filtradas por votos a la clase View
    @Override
    public void setMoviesVote(ArrayList<Movie> lstMoviesVote) {
        view.successVote(lstMoviesVote);
    }
}
