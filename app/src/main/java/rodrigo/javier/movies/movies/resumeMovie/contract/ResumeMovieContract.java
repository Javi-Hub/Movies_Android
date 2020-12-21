package rodrigo.javier.movies.movies.resumeMovie.contract;

import rodrigo.javier.movies.beans.Movie;

public interface ResumeMovieContract {
    interface View{
        void selectedMovie(Movie movie);
    }

    interface Presenter{
        void getMovie(Movie movie);
        void setMovie(Movie movie);
    }

    interface Model{
       void getMovieFromList(Movie movie);
    }
}
