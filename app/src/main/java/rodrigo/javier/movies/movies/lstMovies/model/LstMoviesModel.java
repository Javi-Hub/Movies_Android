package rodrigo.javier.movies.movies.lstMovies.model;


import android.content.Context;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rodrigo.javier.movies.beans.Movie;
import rodrigo.javier.movies.beans.MoviesApiResult;
import rodrigo.javier.movies.movies.lstMovies.contract.LstMoviesContract;
import rodrigo.javier.movies.retrofit.ApiClient;

public class LstMoviesModel implements LstMoviesContract.Model {


    @Override
    public void getMoviesWS(Context context, final OnLstMoviesListener onLstMoviesListener) {
        ApiClient apiClient = new ApiClient(context);
        final Call<MoviesApiResult> request = apiClient.getMovies();

        request.enqueue(new Callback<MoviesApiResult>() {
            @Override
            public void onResponse(Call<MoviesApiResult> call, Response<MoviesApiResult> response) {
                if (response != null && response.body() != null){
                    onLstMoviesListener.onResolve(new ArrayList<Movie>(response.body().getResults()));
                }
            }

            @Override
            public void onFailure(Call<MoviesApiResult> call, Throwable t) {
                t.printStackTrace();
                onLstMoviesListener.onReject(t.getLocalizedMessage());
            }
        });
    }

}
