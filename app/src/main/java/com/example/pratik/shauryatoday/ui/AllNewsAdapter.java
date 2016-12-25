package com.example.pratik.shauryatoday.ui;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pratik.shauryatoday.R;
import com.example.pratik.shauryatoday.model.News;
import com.example.pratik.shauryatoday.utils.Constants;
import com.firebase.client.Firebase;
import com.firebase.client.Query;
import com.firebase.client.ValueEventListener;
import com.firebase.ui.FirebaseListAdapter;
import com.squareup.picasso.Picasso;

/**
 * Created by prati on 16-Oct-16.
 */

public class AllNewsAdapter extends FirebaseListAdapter<News> {

    public Context context = null;
    public AllNewsAdapter(Activity activity, Class<News> modelClass, int modelLayout,
                          Query ref, Context context) {
        super(activity, modelClass, modelLayout, ref);
        this.context = context;
        this.mActivity = activity;
    }



    @Override
    protected void populateView(View view, News news) {


        ImageView newsImage = (ImageView) view.findViewById(R.id.list_item_news_image);
        final TextView newsHeading = (TextView) view.findViewById(R.id.list_item_news_heading);

        if(news.getImage().equals("@!n#!o$!i%!m^!a&!g*!e(!")){
            Picasso.with(context).load(R.drawable.no_image_available).error(R.drawable.no_image_available).into(newsImage);
        }else {
            String image_url = news.getImage().replace("C:\\fakepath\\","");
            Log.e("All News Adapter ", image_url);
            Picasso.with(context).
                    load(Constants.IMAGE_URL_PATH + image_url)
                    .error(R.drawable.no_image_available)
                    .into(newsImage);
        }
        newsHeading.setText(news.getHeadline());
    }
}
