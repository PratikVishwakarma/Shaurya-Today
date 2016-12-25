package com.example.pratik.shauryatoday.ui.singleNews;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pratik.shauryatoday.R;
import com.example.pratik.shauryatoday.model.News;
import com.example.pratik.shauryatoday.utils.Constants;
import com.example.pratik.shauryatoday.utils.Utility;
import com.google.android.gms.vision.text.Text;
import com.squareup.picasso.Picasso;

public class singleNews extends AppCompatActivity {

    public News currentNews;
    private TextView newsHeadine, newsDate, newsContent;
    private ImageView newsImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_news);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent intent = getIntent();
        currentNews = intent.getParcelableExtra("singleNews");

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_SEND);
                i.setType("text/plain");
                i.putExtra(Intent.EXTRA_SUBJECT, "Shaurya Today");
                String sAux = "\n"+currentNews.getHeadline()+"\n\n";
                sAux = sAux + "https://play.google.com/store/apps/details?id=com.shauryatoday.std \n\n";
                i.putExtra(Intent.EXTRA_TEXT, sAux);
                startActivity(Intent.createChooser(i, "choose one"));
            }
        });


        newsHeadine = (TextView) findViewById(R.id.singleNews_headline);
        newsDate = (TextView) findViewById(R.id.singleNews__date);
        newsContent = (TextView) findViewById(R.id.singleNews__content);
        newsImage= (ImageView) findViewById(R.id.singleNews__image);

        Utility utility = new Utility();
        newsHeadine.setText(currentNews.getHeadline());
        newsDate.setText(utility.AdjustDate(currentNews.getDate()));
        newsContent.setText(currentNews.getNews_content());

        if(currentNews.getImage().equals("@!n#!o$!i%!m^!a&!g*!e(!")){
            Picasso.with(getApplicationContext()).load(R.drawable.no_image_available).error(R.drawable.no_image_available).into(newsImage);
        }else {
            String image_url = currentNews.getImage().replace("C:\\fakepath\\","");
            Log.e("All News Adapter ", image_url);
            Picasso.with(getApplicationContext()).
                    load(Constants.IMAGE_URL_PATH + image_url)
                    .error(R.drawable.no_image_available)
                    .into(newsImage);
        }
    }

}
