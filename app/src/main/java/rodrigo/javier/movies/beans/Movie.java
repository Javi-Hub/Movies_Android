package rodrigo.javier.movies.beans;

import com.google.gson.annotations.SerializedName;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Movie implements Serializable {
    //Atributos obtenidos desde el archivo JSON
    private static ArrayList<Movie> list;
    private static final String ID = "id";
    private static final String TITLE = "title";
    private static final String DATE = "release_date";
    private static final String RATE = "vote_average";
    private static final String URL_IMAGE = "https://image.tmdb.org/t/p/original";
    private static final String POSTER_PATH = "poster_path";
    private static final String LANGUAGE = "original_language";
    private static final String VOTE = "vote_count";
    private static final String OVERVIEW = "overview";
    private int id;
    private String title;
    @SerializedName("release_date")
    private String date;
    @SerializedName("vote_average")
    private String rate;
    @SerializedName("poster_path")
    private String image;
    private String language;
    @SerializedName("vote_count")
    private int vote;
    private String overview;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public String getRate() {
        return rate;
    }
    public void setRate(String vote) {
        this.rate = vote;
    }
    public String getImage() {
        return image;
    }
    public void setImage(String image) {
        this.image = image;
    }
    public String getLanguage() {
        return language;
    }
    public void setLanguage(String language) {
        this.language = language;
    }
    public int getVote() {
        return vote;
    }
    public void setVote(int vote) {
        this.vote = vote;
    }
    public String getOverview() {
        return overview;
    }
    public void setOverview(String overview) {
        this.overview = overview;
    }

    /*Convertir el archivo JSON obtenidos desde la API en array de objetos de la clase Movie
     y devolver la lista de objetos*/
   /* public static ArrayList<Movie> getArrayListFromJSON(JSONArray lstMovies){
        list = null;
        try {
            if (lstMovies != null && lstMovies.length() > 0){
                list = new ArrayList<Movie>();
            }

            for (int i = 0; i < lstMovies.length(); i++) {
                JSONObject json_data = lstMovies.getJSONObject(i);
                Movie movie = new Movie();
                movie.setId(json_data.getInt(ID));
                movie.setTitle(json_data.getString(TITLE));
                movie.setDate(json_data.getString(DATE));
                movie.setRate(json_data.getString(RATE));
                movie.setImage("https://image.tmdb.org/t/p/original" + json_data.getString(POSTER_PATH));
                movie.setLanguage((json_data.getString(LANGUAGE)));
                movie.setVote(json_data.getInt(VOTE));
                movie.setOverview(json_data.getString(OVERVIEW));

                list.add(movie);

            }
        } catch (JSONException e){
                e.printStackTrace();
        }

        return list;
    }*/

    //Método estático que devuelve la lista completa de películas sin filtrar
    public static ArrayList<Movie> getList(){
        return list;
    }

    //Método estático que devuelve la lista de películas filtrada por valoración
    public static ArrayList<Movie> getListFilterRate(ArrayList<Movie> movies) {
        Collections.sort(movies, new Comparator<Movie>() {
            @Override
            public int compare(Movie movie_1, Movie movie_2) {
                return Integer.parseInt(new String(String.valueOf(movie_2.getRate().compareTo(new String(movie_1.getRate())))));
            }
        });
        return movies;
    }

    //Método estático que devuelve la lista de películas filtrada por votos
    public static ArrayList<Movie> getListFilterVote(ArrayList<Movie> movies) {
        Collections.sort(movies, new Comparator<Movie>() {
            @Override
            public int compare(Movie movie_1, Movie movie_2) {
                // Aqui esta el truco, ahora comparamos movie_2 con movie_1
                return new Integer(movie_2.getVote()).compareTo(new Integer(movie_1.getVote()));
            }
        });
        return movies;
    }


}
