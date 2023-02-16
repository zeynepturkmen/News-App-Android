package com.sabanciuniv;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sabanciuniv.model.Comments;

import java.util.List;

public class CommentActivity extends AppCompatActivity {
    ProgressBar prg;
    RecyclerView recView;
    int newsId;

    Handler dataHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message msg) {
            Log.d("dev", "Calling the data from CommentActivity");
            List<Comments> data = (List<Comments>)msg.obj;
            CommentAdapter adp = new CommentAdapter(CommentActivity.this, data);
            recView.setAdapter(adp);
            recView.setVisibility(View.VISIBLE);
            prg.setVisibility(View.INVISIBLE); //remove progress bar
            return true;
        }
    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.comments);
        prg = findViewById(R.id.commentProgress);
        recView = findViewById(R.id.commentRecView);
        recView.setLayoutManager(new LinearLayoutManager(this));
        prg.setVisibility(View.VISIBLE);
        recView.setVisibility(View.INVISIBLE);
        newsId = getIntent().getIntExtra("newsId", 1);
        Log.d("dev", "On create of CommentActivity, newsId is: " + newsId);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.create_comment, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.btnCreateComment) {
            Intent i = new Intent(this, CreateCommentActivity.class);
            i.putExtra("newsId", newsId);
            this.startActivity(i);
        }
        else{ //return to previous page while retaining the state
           finish();
           return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        prg.setVisibility(View.VISIBLE); //enable progress bar
        recView.setVisibility(View.INVISIBLE);
        Log.d("dev", "On resume of CommentActivity, this is the newsId: " + newsId);
        NewsRepository repo = new NewsRepository();
        repo.getCommentsByNewsId(((NewsApplication) getApplication()).srv, dataHandler, newsId);
    }
}
