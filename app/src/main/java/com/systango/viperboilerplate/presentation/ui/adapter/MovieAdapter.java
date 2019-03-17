package com.systango.viperboilerplate.presentation.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.systango.viperboilerplate.R;
import com.systango.viperboilerplate.data.utils.GlideUtils;
import com.systango.viperboilerplate.presentation.entity.Movie;
import com.systango.viperboilerplate.presentation.listener.OnMovieItemClickedListener;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Mohit Rajput on 12/3/19.
 */
public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {

    private Context context;
    private List<Movie> movies;
    private OnMovieItemClickedListener onMovieItemClickedListener;

    public MovieAdapter(Context context, List<Movie> movies) {
        this.context = context;
        this.movies = movies;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_item_movie, viewGroup, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder viewHolder, int position) {
        Movie movie = movies.get(position);
        if (!TextUtils.isEmpty(movie.getPosterPath())) {
            GlideUtils.loadImage(context, movie.getPosterPath(), viewHolder.ivPoster, 0);
        }
        if (!TextUtils.isEmpty(movie.getTitle())) {
            viewHolder.tvTitle.setText(movie.getTitle());
        }
        viewHolder.itemView.setOnClickListener(v -> {
            if (onMovieItemClickedListener != null) {
                onMovieItemClickedListener.onMovieItemClicked(movie);
            }
        });
    }

    public void setOnMovieItemClickedListener(OnMovieItemClickedListener onMovieItemClickedListener) {
        this.onMovieItemClickedListener = onMovieItemClickedListener;
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    class MovieViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.ivPoster)
        ImageView ivPoster;
        @BindView(R.id.tvTitle)
        TextView tvTitle;

        MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
