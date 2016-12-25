package com.example.pratik.shauryatoday.ui.allNews;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.pratik.shauryatoday.R;
import com.example.pratik.shauryatoday.adapters.NewsAdapter;
import com.example.pratik.shauryatoday.ads.ToastListner;
import com.example.pratik.shauryatoday.database.NewsDbHelper;
import com.example.pratik.shauryatoday.model.News;
import com.example.pratik.shauryatoday.ui.AllNewsAdapter;
import com.example.pratik.shauryatoday.ui.singleNews.singleNews;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.drive.query.Query;

import java.util.ArrayList;
import java.util.List;

public class fetchAllNews extends AppCompatActivity {
    private com.example.pratik.shauryatoday.ui.AllNewsAdapter mNewsAdapter;
    private ListView mListView;
    public AdView mAdView;
    public Firebase newsListRef;
    private List<News> dbNewsList = new ArrayList<News>();
    private NewsAdapter nAdapter;

    NewsDbHelper newsDbHelper;
    SQLiteDatabase sqLiteDatabase, db;
    Cursor cursorData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fetch_all_news);
        Firebase.setAndroidContext(this);

        mListView = (ListView) findViewById(R.id.list_view_news);

        fetch_news("all");

        /* AdMob coded Start */
        mAdView = (AdView) findViewById(R.id.adView);
        mAdView.setVisibility(View.GONE);
        if(isNetworkAvailable()){
            mAdView.setVisibility(View.VISIBLE);
            mAdView.setAdListener(new ToastListner(this));
            AdRequest adRequest = new AdRequest.Builder()
                    .build();
            mAdView.loadAd(adRequest);
        }
        else{
            mAdView.setVisibility(View.GONE);
            Toast.makeText(getApplicationContext(), "No Internet Access", Toast.LENGTH_SHORT).show();
        }
        /* AdMob coded End */

        newsListRef = new Firebase(com.example.pratik.shauryatoday.utils.Constants.FIREBASE_URL_NEWS);

        newsDbHelper = new NewsDbHelper(getBaseContext());
        db = newsDbHelper.getWritableDatabase();

        newsListRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()){
                    /**
                     * Create the adapter, giving it the activity, model class, layout for each row in
                     * the list and finally, a reference to the Firebase location with the list data
                     */
                    //mNewsAdapter = new AllNewsAdapter(fetchAllNews.this, News.class, R.layout.news_list_item, newsListRef.orderByChild("newsid"), getApplicationContext());
                    /**
                     * Set the adapter to the mListView
                     */
//                    mListView.setAdapter(mNewsAdapter);
                    newsDbHelper.deleteNews(db);


                    for (DataSnapshot child : dataSnapshot.getChildren()) {
                        News value = child.getValue(News.class);
                        newsDbHelper.addNews(value.getNewsid(), value.getDate(), value.getTime(), value.getHeadline(), value.getNews_content(), value.getImage(),  value.getCategory(), db);
                    }
                    fetch_news("all");

                    Log.e("FetchAllNews ", dataSnapshot.getChildrenCount()+"");
                    //News news = dataSnapshot.getValue(News.class);
                } else {
                    Log.e("FetchAllNews ", "No data exist");
                }
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

    }

    public boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    public void fetch_news(String p_category){
        Log.e("FetchAllNews ", "Fetching data from local database...");
        newsDbHelper = new NewsDbHelper(getApplicationContext());
        sqLiteDatabase = newsDbHelper.getReadableDatabase();
        cursorData = newsDbHelper.getAllNews(sqLiteDatabase,p_category);

        if(cursorData.moveToFirst()){
            int columnCount = cursorData.getColumnCount();
            Log.e("FetchAllNews ", "Data present in database "+columnCount+"");
            News newsProvider;
            dbNewsList.clear();
            do {
                String headline, content, date, time, image, ncategory, newsid;
                int id;
                //Log.e("Database ", String.valueOf(cursorData.getInt(0)));
                //Log.e("Database ", cursorData.getString(4));
                newsid = cursorData.getString(0);
                headline = cursorData.getString(1);
                content = cursorData.getString(2);
                date = cursorData.getString(3);
                time = cursorData.getString(4);
                image = cursorData.getString(5);
                ncategory = cursorData.getString(6);
                newsProvider = new News(headline, content, date, time, image, ncategory, newsid);
                dbNewsList.add(newsProvider);
            }while (cursorData.moveToNext());
            nAdapter = new NewsAdapter(this, dbNewsList);
            mListView.setAdapter(nAdapter);

            mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    News item = nAdapter.getItem(position);
                    Intent intent = new Intent(getApplicationContext(), singleNews.class);
                    intent.putExtra("singleNews", item);
                    startActivity(intent);
                }
            });
        }
        else{
            //no_data_present.setVisibility(View.VISIBLE);
        }
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        //mNewsAdapter.cleanup();
    }

    private void initializeScreen() {
        mListView = (ListView) findViewById(R.id.list_view_news);
    }
}
