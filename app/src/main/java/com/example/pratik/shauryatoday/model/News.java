package com.example.pratik.shauryatoday.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by prati on 16-Oct-16.
 */

public class News implements Parcelable {
    private String headline;
    private String news_content;
    private String date;
    private String time;
    private String image;
    private String category;
    private String newsid;


    public News()  {
    }

    public News(String headline, String news_content, String date, String time, String image, String category, String newsid) {
        this.headline = headline;
        this.news_content = news_content;
        this.date = date;
        this.time = time;
        this.image = image;
        this.category = category;
        this.newsid = newsid;
    }

    protected News(Parcel in) {
        headline = in.readString();
        news_content = in.readString();
        date = in.readString();
        time = in.readString();
        image = in.readString();
        category = in.readString();
        newsid = in.readString();
    }

    public static final Creator<News> CREATOR = new Creator<News>() {
        @Override
        public News createFromParcel(Parcel in) {
            return new News(in);
        }

        @Override
        public News[] newArray(int size) {
            return new News[size];
        }
    };

    public String getNewsid() {
        return newsid;
    }

    public String getHeadline() {
        return headline;
    }

    public String getNews_content() {
        return news_content;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getImage() {
        return image;
    }

    public String getCategory() {
        return category;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(headline);
        dest.writeString(news_content);
        dest.writeString(date);
        dest.writeString(time);
        dest.writeString(image);
        dest.writeString(category);
        dest.writeString(newsid);
    }
}
