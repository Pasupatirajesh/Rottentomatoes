package com.julyobserver.codepath.rottentomatoes;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class MovieFeedActivity extends AppCompatActivity {

    private static final String TAG="MovieFeedActivity";
    public static final String SOURCE_KEY = "videosourcekey";
    public static final String SOURCE_POSITION = "videosourceposition";
    private ArrayList<Movie> movies;
    private ListView lvMovies;
    private Movie mMovie;
    private int idArray;
    private int pos;
    private SwipeRefreshLayout mSwipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_feed);

        movies = new ArrayList<Movie>();
        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipecontainer);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                new FetchTomatoTask().execute();
                mSwipeRefreshLayout.setRefreshing(false);
            }
        });
        mSwipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);



        lvMovies = (ListView) findViewById(R.id.movie_item_listView);
        lvMovies.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {

                    Intent i = new Intent(getApplicationContext(),MovieDetailActivity.class);
                    Log.i("hello","" + pos);
                    idArray = movies.get(position).getVideoPath();

                    i.putExtra(SOURCE_POSITION,idArray);
                    pos = position;

                    startActivity(i);
            }
        });
        setupAdapter();
        new FetchTomatoTask().execute();
    }
    private void setupAdapter()
    {
        MoviesAdapter moviesAdapter = new MoviesAdapter(getApplicationContext(), movies);
        lvMovies.setAdapter(moviesAdapter);
    }
    @Override
    protected void onDestroy()
    {
        super.onDestroy();
    }

    private class FetchTomatoTask extends AsyncTask<Void,Void,ArrayList<Movie>>
    {

        @Override
        protected ArrayList<Movie> doInBackground(Void... params)
        {

            return  new TomatoFetcher().fetchItems();
        }

        @Override
        protected void onPostExecute(ArrayList<Movie> result)
        {

            movies = result;
            setupAdapter();

        }
    }
}
