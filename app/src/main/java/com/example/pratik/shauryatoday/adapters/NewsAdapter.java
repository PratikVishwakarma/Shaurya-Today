package com.example.pratik.shauryatoday.adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pratik.shauryatoday.R;
import com.example.pratik.shauryatoday.model.News;
import com.example.pratik.shauryatoday.ui.allNews.fetchAllNews;
import com.example.pratik.shauryatoday.ui.singleNews.singleNews;
import com.example.pratik.shauryatoday.utils.Constants;
import com.example.pratik.shauryatoday.utils.Utility;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by prati on 11-Aug-16.
 */
public class NewsAdapter extends ArrayAdapter<News> {
    private Context context;

    public NewsAdapter(Context context, List<News> news){
        super(context, 0, news);
        this.context = context;
    }



    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if an existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.news_list_item, parent, false);
        }

        // Get the {@link Word} object located at this position in the list
        News currentNews = getItem(position);

        TextView headline = (TextView) listItemView.findViewById(R.id.list_item_news_heading);
        TextView date = (TextView) listItemView.findViewById(R.id.list_item_news_date);
        ImageView newsImage = (ImageView) listItemView.findViewById(R.id.list_item_news_image);

        if(currentNews.getImage().equals("@!n#!o$!i%!m^!a&!g*!e(!")){
            Picasso.with(context).load(R.drawable.no_image_available).error(R.drawable.no_image_available).into(newsImage);
        }else {
            String image_url = currentNews.getImage().replace("C:\\fakepath\\","");
            Log.e("All News Adapter ", image_url);
            Picasso.with(context).
                    load(Constants.IMAGE_URL_PATH + image_url)
                    .error(R.drawable.no_image_available)
                    .into(newsImage);
        }
        Utility utility = new Utility();
        date.setText(utility.AdjustDate(currentNews.getDate()));
        headline.setText(currentNews.getHeadline());

        // Return the whole list item layout (containing 2 TextViews) so that it can be shown in
        // the ListView.
        return listItemView;
    }
}