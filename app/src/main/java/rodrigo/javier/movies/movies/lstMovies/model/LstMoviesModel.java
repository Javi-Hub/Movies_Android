package rodrigo.javier.movies.movies.lstMovies.model;


import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import rodrigo.javier.movies.beans.Movie;
import rodrigo.javier.movies.movies.lstMovies.contract.LstMoviesContract;
import rodrigo.javier.movies.utils.Post;

public class LstMoviesModel
        implements LstMoviesContract.Model
        {
            private static final String URL = "https://api.themoviedb.org/3/movie/popular?" +
                    "api_key=f60067a1481cd9254310b37a0022dbd7&language=en-US&page=1";
            private static final String GENRE = "https://api.themoviedb.org/3/genre/movie/list?" +
                    "api_key=f60067a1481cd9254310b37a0022dbd7&language=en-US";
            private ArrayList<Movie> lstArrayMovies;

            OnLstMoviesListener onLstMoviesListener;

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
                    //La clase que se utiliza para "CONFIGURAR" es petición se llama -> POST
                    Post post = new Post();
                    HashMap<String, String> data = new HashMap();
                    //CLAVE-VALOR
                    data.put("api_key", "f60067a1481cd9254310b37a0022dbd7");
                    data.put("language", "es-ES");
                    data.put("page", "1");

                    try {
                        /*Obtener el archivo JSON con las peliculas de la API y
                        convertirla en un array de objetos de la clase Movie*/
                        JSONObject objectMovies = post.getServerDataGetObject(URL);
                        JSONArray lstMovies = objectMovies.getJSONArray("results");
                        lstArrayMovies = Movie.getArrayListFromJSON(lstMovies);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                   return true;

                }

                @Override
                protected void onPostExecute(Boolean resp) {
                    //Devolver la lista con los datos obtenidos de la API
                    if (resp){
                        if (lstArrayMovies != null && lstArrayMovies.size() > 0){
                            onLstMoviesListener.onResolve(lstArrayMovies);
                        }
                    } else {
                        onLstMoviesListener.onReject("Error al recoger los datos");
                    }
                }
            }




        }
