package rodrigo.javier.movies.movies.lstMovies.model;


import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import rodrigo.javier.movies.BuildConfig;
import rodrigo.javier.movies.beans.Movie;
import rodrigo.javier.movies.movies.lstMovies.contract.LstMoviesContract;
import rodrigo.javier.movies.utils.Post;

public class LstMoviesModel implements LstMoviesContract.Model {

            private ArrayList<Movie> lstArrayMovies;
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
                        /*Obtener el archivo JSON con las peliculas de la API y
                        convertirla en un array de objetos de la clase Movie*/
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
            }




        }
