package rodrigo.javier.movies.movies.lstMovies.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import org.w3c.dom.CharacterData;

import java.sql.SQLOutput;
import java.util.ArrayList;

import rodrigo.javier.movies.R;
import rodrigo.javier.movies.beans.Movie;
import rodrigo.javier.movies.movies.resumeMovie.view.ResumeMovieActivity;

public class LstMovieAdapter extends RecyclerView.Adapter<LstMovieAdapter.MovieViewHolder> {

    private ArrayList<Movie> lstMovies;

    public LstMovieAdapter(ArrayList<Movie> lstMovies){
        this.lstMovies = lstMovies;
    }


    //ViewHolder -> Tantos elementos como objetos quiera mostrar en la fila
    public static class MovieViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        // Context
        public Context context;
        public CardView rowMovie;
        public ImageView img;
        public TextView title;
        public TextView date;
        public TextView rate;
        public TextView vote;
        public ArrayList<Movie> movies;
        public Movie movie_set;

        public MovieViewHolder(View v){
            super(v);
            context = v.getContext();
            rowMovie = v.findViewById(R.id.card_movie);
            img = v.findViewById(R.id.card_movie_image);
            title = v.findViewById(R.id.card_movie_title);
            date = v.findViewById(R.id.card_movie_date);
            rate = v.findViewById(R.id.card_movie_rate);
            vote = v.findViewById(R.id.card_movie_vote);

        }
        
        void setOnClickListeners(){
            rowMovie.setOnClickListener(this);
        }

        /*Al seleccionar la película se cambia de Activity para mostar
        * todos los datos de la selección */
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(context, ResumeMovieActivity.class);
            /*intent.putExtra("title", title.getText());
            intent.putExtra("date", date.getText());
            intent.putExtra("rate", rate.getText());
            intent.putExtra("vote", vote.getText());
            intent.putExtra("movies", movies);*/
            intent.putExtra("movie_set", movie_set);
            context.startActivity(intent);
        }

    }

    //Se rellena cada etiqueta con los datos obtenidos
    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_movie, parent,false);
        return new MovieViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        //Pintado de los objetos de la lista
        final String URL_IMAGE = "https://image.tmdb.org/t/p/original";

        Movie movie = lstMovies.get(position);
        Picasso.get().load(URL_IMAGE + movie.getImage()).into(holder.img);
        holder.title.setText(movie.getTitle());
        holder.date.setText("Fecha: " + movie.getDate());
        holder.rate.setText("Valoración: " + movie.getRate());
        holder.vote.setText("Votos: " + movie.getVote());
        holder.movies = this.lstMovies;
        holder.movie_set = movie;
        holder.setOnClickListeners();
    }

    //Devolver la cantidad de objetos de la lista
    @Override
    public int getItemCount() {
        return lstMovies.size();
    }


}
