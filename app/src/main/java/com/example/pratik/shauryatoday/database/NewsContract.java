package com.example.pratik.shauryatoday.database;

import android.provider.BaseColumns;

/**
 * Created by prati on 21-Oct-16.
 */

public class NewsContract {

    public class NewsEntry implements BaseColumns {
        public static final String TABLE_NAME = "news";
        public static final String COLUMN_NEWSID = "newsid";
        public static final String COLUMN_DATE = "date";
        public static final String COLUMN_TIME = "time";
        public static final String COLUMN_HEADLINE = "headline";
        public static final String COLUMN_NEWS_CONTENT = "news_content";
        public static final String COLUMN_IMAGE = "image";
        public static final String COLUMN_CATEGORY = "category";

    }
}
