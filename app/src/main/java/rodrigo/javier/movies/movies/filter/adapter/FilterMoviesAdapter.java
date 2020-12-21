package rodrigo.javier.movies.movies.filter.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.ArrayList;

import rodrigo.javier.movies.R;
import rodrigo.javier.movies.beans.Movie;
import rodrigo.javier.movies.movies.lstMovies.adapter.LstMovieAdapter;
import rodrigo.javier.movies.movies.resumeMovie.view.ResumeMovieActivity;

public class FilterMoviesAdapter extends RecyclerView.Adapter<FilterMoviesAdapter.MovieViewHolder> {

    private ArrayList<Movie> lstMoviesFilter;

    public FilterMoviesAdapter(ArrayList<Movie> lstMovies){
        this.lstMoviesFilter = lstMovies;
    }


    /*ViewHolder -> Tantos elementos como objetos quiera mostrar en la fila*/
    public static class MovieViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        // Context
        public Context context;
        public LinearLayout rowFilterMovie;
        public ImageView img;
        public TextView title;
        public TextView date;
        public TextView rate;
        public TextView vote;

        public MovieViewHolder(View v){
            super(v);
            context = v.getContext();
            rowFilterMovie = (LinearLayout) v.findViewById(R.id.rowFilterMovie);
            img = (ImageView) v.findViewById(R.id.imgFilterMovie);
            title = (TextView) v.findViewById(R.id.txtFilterTitle);
            date = (TextView) v.findViewById(R.id.txtFilterDate);
            rate = (TextView) v.findViewById(R.id.txtFilterRate);
            vote = (TextView) v.findViewById(R.id.txtFilterVote);
            v.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(context, ResumeMovieActivity.class);
            intent.putExtra("title", title.getText());
            intent.putExtra("date", date.getText());
            intent.putExtra("rate", rate.getText());
            intent.putExtra("vote", vote.getText());
            context.startActivity(intent);
        }

    }

    @NonNull
    @Override
    public FilterMoviesAdapter.MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_filter_movie, parent,false);
        return new FilterMoviesAdapter.MovieViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        // Enlace -> Pintado
        Movie movie = lstMoviesFilter.get(position);
        Picasso.get().load(movie.getImage()).into(holder.img);
        holder.title.setText(movie.getTitle());
        holder.date.setText("Fecha: " + movie.getDate());
        holder.rate.setText("Valoración: " + movie.getRate());
        holder.vote.setText("Votos:" + movie.getVote());
    }


    @Override
    public int getItemCount() {
        return lstMoviesFilter.size();
    }
}
