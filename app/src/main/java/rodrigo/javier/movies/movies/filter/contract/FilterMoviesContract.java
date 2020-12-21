 package rodrigo.javier.movies.movies.filter.contract;

 import java.util.ArrayList;

 import rodrigo.javier.movies.beans.Movie;

 public interface FilterMoviesContract {
     interface View{
         void successRate(ArrayList<Movie> movies);
         void successVote(ArrayList<Movie> movies);
         void error(String message);
     }

     interface Presenter{
         void getMoviesRate();
         void setMoviesRate(ArrayList<Movie> lstMoviesRate);
         void getMoviesVote();
         void setMoviesVote(ArrayList<Movie> lstMoviesVote);
     }

     interface Model{
         void getFilterMoviesRate();
         void getFilterMoviesVote();
     }
}
