package rodrigo.javier.movies.retrofit;

import android.content.Context;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import rodrigo.javier.movies.beans.MoviesApiResult;

public class ApiClient {

    private Retrofit retrofit;
    private Context context;

    private static final String BASE_URL = "https://api.themoviedb.org/3/";

    public ApiClient (Context context){
        this.context = context;

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public Call<MoviesApiResult> getMovies(){
        TheMovieApiInterface service = retrofit.create(TheMovieApiInterface.class);
        return service.getMovies();
    }

}
