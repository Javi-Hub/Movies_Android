package rodrigo.javier.movies.movies.lstMovies.presenter;

import java.util.ArrayList;

import rodrigo.javier.movies.beans.Movie;
import rodrigo.javier.movies.movies.lstMovies.contract.LstMoviesContract;
import rodrigo.javier.movies.movies.lstMovies.model.LstMoviesModel;

public class LstMoviesPresenter
    implements LstMoviesContract.Presenter
    {

    private LstMoviesContract.View view;
    public LstMoviesModel lstMoviesModel;

    public LstMoviesPresenter(LstMoviesContract.View view) {
        this.view = view;
        this.lstMoviesModel = new LstMoviesModel();
    }

        //Obtener el listado de peliculas desde la clase Model
        @Override
        public void getMovies() {
            lstMoviesModel.getMoviesWS(new LstMoviesContract.Model.OnLstMoviesListener() {
                //Caso satisfactorio
                @Override
                public void onResolve(ArrayList<Movie> movies) {
                    view.success(movies);
                }
                //Caso de no obtener el listado
                @Override
                public void onReject(String error) {
                    view.error("Problemas al mostrar la lista");
                }
            });

        }
    }
