package rodrigo.javier.movies.movies.lstMovies.contract;

import java.util.ArrayList;

import rodrigo.javier.movies.beans.Movie;

public interface LstMoviesContract {
    interface View{
        void success(ArrayList<Movie> movies);
        void error(String message);
    }

    interface Presenter{
        void getMovies();
    }

    interface Model{
        /*Me tienes que mandar al Callback -> Camino de retorno*/
        void getMoviesWS(OnLstMoviesListener onLstMoviesListener);
        /*Programación Reactiva*/
        interface OnLstMoviesListener{
            void onResolve(ArrayList<Movie> movies);
            void onReject(String error);
        }
    }
}
