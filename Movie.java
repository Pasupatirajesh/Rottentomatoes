package com.julyobserver.codepath.rottentomatoes;

import android.content.Context;

import java.io.Serializable;

/**
 * Created by SSubra27 on 7/14/16.
 */
// Model Class
public class Movie implements Serializable
{
    public String mTitle;
    public String mPosterUrl;
    public String mOverview;
    private int mVideoPath;
    private String mYoutubePath;
    private String mBackgroundPath;

    public Movie(Context context)
    {

    }
    public Movie()
    {

    }
    // Presenter makes the user exp better, takes a raw value and makes it better
    public String getScoreLabel()
    {
        return mOverview + "%";
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getPosterUrl() {
        return mPosterUrl;
    }

    public void setPosterUrl(String posterUrl) {
        mPosterUrl = posterUrl;
    }

    public String getOverview() {
        return mOverview;
    }

    public void setOverview(String overview) {
        mOverview = overview;
    }

    public int getVideoPath() {
        return mVideoPath;
    }

    public void setVideoPath(int videoPath) {
        mVideoPath = videoPath;
    }

    public String getYoutubePath() {
        return mYoutubePath;
    }

    public void setYoutubePath(String youtubePath) {
        mYoutubePath = youtubePath;
    }

    public String getBackgroundPath() {
        return mBackgroundPath;
    }

    public void setBackgroundPath(String backgroundPath) {
        mBackgroundPath = backgroundPath;
    }
}
