package com.farooqkhan.youtubeapi;

public class YoutubeModel {

    public  String videoId ,title,url;



    public YoutubeModel(String videoId, String title, String url) {
        this.videoId = videoId;
        this.title = title;
        this.url = url;
    }
    public YoutubeModel() {
    }

    public String getVideoId() {
        return videoId;
    }

    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
