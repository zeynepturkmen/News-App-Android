package com.sabanciuniv;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.sabanciuniv.model.News;

public class NewsDetailActivity extends AppCompatActivity {
    TextView title;
    TextView text;
    TextView date;
    ImageView imgView;
    int newsId;

    Handler imgHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message msg) {
            Log.d("dev", "Setting up the image for NewsDetail...");
            Bitmap img = (Bitmap) msg.obj;
            imgView.setImageBitmap(img);
            return true;
        }
    });

    Handler dataHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message msg) {

            News news = (News) msg.obj;

            title.setText(news.getTitle());
            text.setText(news.getText());
            date.setText(news.getDate().toString());
            NewsRepository repo = new NewsRepository();
            repo.downloadImage(((NewsApplication)getApplication()).srv, imgHandler, news.getImgPath());

            getSupportActionBar().setTitle(news.getCategory());
            return true;
        }
    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.news_detail);

        newsId = getIntent().getIntExtra("newsId", 1);
        text = findViewById(R.id.detText);
        title = findViewById(R.id.detTitle);
        date = findViewById(R.id.detDate);
        imgView = findViewById(R.id.detImg);

        NewsRepository repo = new NewsRepository();
        repo.getNewsById(((NewsApplication)getApplication()).srv, dataHandler, newsId);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.comments, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.btnCreateComment) {
            Intent i = new Intent(this, CommentActivity.class);
            i.putExtra("newsId", newsId);
            this.startActivity(i);
        }
        else{
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("dev", "On resume of NewsDetail: " + newsId);
    }

    @Override
    protected void onPause() {
        super.onPause();
    }
}