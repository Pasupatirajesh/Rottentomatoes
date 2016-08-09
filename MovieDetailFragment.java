package com.julyobserver.codepath.rottentomatoes;

import android.annotation.TargetApi;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

/**
 * Created by SSubra27 on 7/22/16.
 */
public class MovieDetailFragment extends Fragment
{
    private static final String TAG = "MovieDetailFragment";
    private VideoView mTrailerView;
    private TextView mOverView;
    private Movie mMovie;

    public static MovieDetailFragment newInstance()
    {
        return new MovieDetailFragment();
    }


    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

    }
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup container, Bundle savedInstanceState)
    {
        layoutInflater = LayoutInflater.from(getContext());
        View view = layoutInflater.inflate(R.layout.fragment_detail,container,false);

        final VideoView mVideoView = (VideoView) view.findViewById(R.id.videoView);
        MediaController mediaController = new MediaController(getContext(),null);
//        mediaController.setAnchorView(mVideoView);
        mVideoView.setMediaController(mediaController);
        mVideoView.setVideoPath("https://www.youtube.com/watch?v=XSMOykMIO3c");
        mVideoView.requestFocus();
        mVideoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            public void onPrepared(MediaPlayer mp) {
                mVideoView.start();
            }});


        mOverView = (TextView) view.findViewById(R.id.movie_overview);

        return view;
    }





    public class YoutubePlayerTask extends AsyncTask<Void, Void, Void>
    {

        @Override
        protected Void doInBackground(Void... params)
        {

            return null;
        }
    }
}
