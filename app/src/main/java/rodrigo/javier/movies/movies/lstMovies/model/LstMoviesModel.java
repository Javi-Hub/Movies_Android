package rodrigo.javier.movies.movies.lstMovies.model;


import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.Nullable;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rodrigo.javier.movies.BuildConfig;
import rodrigo.javier.movies.beans.Movie;
import rodrigo.javier.movies.beans.MoviesApiResult;
import rodrigo.javier.movies.movies.lstMovies.contract.LstMoviesContract;
import rodrigo.javier.movies.retrofit.ApiClient;
import rodrigo.javier.movies.utils.Post;

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




    /*private ArrayList<Movie> lstArrayMovies;
    private OnLstMoviesListener onLstMoviesListener;

    //Obtener las peliculas desde la WebService
    @Override
    public void getMoviesWS(OnLstMoviesListener onLstMoviesListener) {
        // Callback
        this.onLstMoviesListener = onLstMoviesListener;
        BackgroundTask bckTask = new BackgroundTask();
        bckTask.execute();
    }

    //Montar capsula que me permite viajar al API
    class BackgroundTask extends AsyncTask<String, Integer, Boolean>{

        @Override
        protected Boolean doInBackground(String... strings) {
            try {
                *//*Obtener el archivo JSON con las peliculas de la API y
                convertirla en un array de objetos de la clase Movie*//*
                Post post = new Post();
                JSONObject objectMovies = post.getServerDataGetObject(BuildConfig.SEVER_URL);
                JSONArray lstMovies = objectMovies.getJSONArray("results");
                lstArrayMovies = Movie.getArrayListFromJSON(lstMovies);
                return true;

            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }

        }

        @Override
        protected void onPostExecute(Boolean resp) {
            //Devolver la lista con los datos obtenidos de la API
            if (resp){
                if (lstArrayMovies != null && lstArrayMovies.size() > 0){
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    onLstMoviesListener.onResolve(lstArrayMovies);
                }
            } else {
                onLstMoviesListener.onReject("Error al recoger los datos");
            }
        }
    }*/

}
