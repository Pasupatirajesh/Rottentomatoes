package com.julyobserver.codepath.rottentomatoes;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import java.util.ArrayList;

/**
 * Created by SSubra27 on 7/22/16.
 */
public class MovieDetailActivity extends YouTubeBaseActivity
{
    private final static String YOUTUBE_API_KEY = "AIzaSyAtLg3ial3Q9uHLczkzUBL-R-b75EKkN1M";
    private Movie mMovie;
    private TextView mTextView;
    public static int id;
    private ArrayList<Movie> mMovies = new ArrayList<>();
    public static String key;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        mTextView = (TextView) findViewById(R.id.movie_overview);

        mMovie = (Movie) getIntent().getSerializableExtra(MovieFeedActivity.SOURCE_KEY);
        id = getIntent().getIntExtra(MovieFeedActivity.SOURCE_POSITION,0);
        YouTubePlayerView mYoutubePlayerView = (YouTubePlayerView) findViewById(R.id.videoView);

        mYoutubePlayerView.initialize(YOUTUBE_API_KEY, new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {

                youTubePlayer.cueVideo(key);
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
                Toast.makeText(MovieDetailActivity.this, "Youtube Failed!", Toast.LENGTH_SHORT).show();
            }
        });

        new TrailerFetcherTask().execute();
    }
    private class TrailerFetcherTask extends AsyncTask<Void,Void,ArrayList<Movie>>
    {

        @Override
        protected ArrayList<Movie> doInBackground(Void... params) {
            return new TrailerFetcher().fetchItems();
        }
        @Override
        protected void onPostExecute(ArrayList<Movie> result)
        {

            mMovies = result;

            for(int i=0; i<mMovies.size();i++) {
                mMovie = mMovies.get(i);
                key = mMovie.getYoutubePath();

                Log.i("contents", "" + key);
            }
        }
    }

}
