package com.julyobserver.codepath.rottentomatoes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by SSubra27 on 7/14/16.
 */
public class MoviesAdapter extends ArrayAdapter<Movie>
{
    public class ViewHolder
    {
        TextView title;
        TextView criticScore;
        ImageView imageView;
        ImageView backgroundImgView;
    }

    public MoviesAdapter(Context context, ArrayList<Movie> movies)
    {
        super(context, 0, movies);
    }
    // Converts your Model into a View
    // CONVERTVIEW == sad recycled view


    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        // get the data item for this position
        Movie movie = getItem(position);
        // Check if an existing view is being reused, otherwise inflate it from the layout
        ViewHolder viewHolder;
        if(convertView== null)
        {
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_movie,parent,false);
            viewHolder.title = (TextView) convertView.findViewById(R.id.tv_textView);
            viewHolder.criticScore =(TextView) convertView.findViewById(R.id.critic_score);
            viewHolder.imageView = (ImageView) convertView.findViewById(R.id.imageView);
            viewHolder.backgroundImgView = (ImageView)convertView.findViewById(R.id.backdropImgView);

            convertView.setTag(viewHolder);
        } else
        {
            viewHolder =(ViewHolder) convertView.getTag();
        }

//        Populate the data into the template view using the data object

        // Lookup view for data population
        viewHolder.title.setText(movie.mTitle);

        viewHolder.criticScore.setText(movie.getScoreLabel());

        String imageUri = "https://image.tmdb.org/t/p/w185/"+ movie.getPosterUrl();
        Picasso.with(getContext()).load(imageUri).into(viewHolder.imageView);
        String backgroundImageView = "https://image.tmdb.org/t/p/w185/"+ movie.getBackgroundPath();
        Picasso.with(getContext()).load(backgroundImageView).into(viewHolder.backgroundImgView);

        // return the convertView to be rendered on Screen
        return convertView;
    }

}
