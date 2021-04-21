package rodrigo.javier.movies.retrofit;

import retrofit2.Call;
import retrofit2.http.GET;
import rodrigo.javier.movies.beans.MoviesApiResult;

public interface TheMovieApiInterface {

    @GET("movie/popular?api_key=f60067a1481cd9254310b37a0022dbd7&language=es-ES&page=1")
    Call<MoviesApiResult> getMovies();


}
