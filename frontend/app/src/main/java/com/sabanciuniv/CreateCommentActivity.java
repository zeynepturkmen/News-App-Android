package com.sabanciuniv;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.concurrent.ExecutorService;

public class CreateCommentActivity extends AppCompatActivity {
    EditText name;
    EditText comment;
    TextView messageText;
    Button btn;
    ProgressBar prgBar;
    int newsId;

    Handler postHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message message) {
            if(message.obj.toString().equals("1")) {
                Log.d("dev", "Comment has been posted!");
                finish();
            }
            else {
                messageText.setText("Failed to post comment");
                Log.d("dev", "Failed to post comment.");
            }
            prgBar.setVisibility(View.INVISIBLE);
            return true;
        }
    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_comment);
        btn = findViewById(R.id.btnPostComment);
        comment = findViewById(R.id.txtComment);
        name = findViewById(R.id.txtName);
        prgBar = findViewById(R.id.progPost);
        messageText = findViewById(R.id.msgTxt);
        newsId = getIntent().getIntExtra("newsId", 1);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                prgBar.setVisibility(View.VISIBLE);
                ExecutorService srv = ((NewsApplication)getApplication()).srv;
                NewsRepository repo = new NewsRepository();
                String commenter = name.getText().toString();
                String text = comment.getText().toString();
                String news_id = Integer.toString(newsId);
                if(commenter.isEmpty()|| text.isEmpty()){
                    messageText.setText("Fields cannot be empty!");
                    prgBar.setVisibility(View.INVISIBLE);
                }
                else{
                   Log.d("dev", "Creating comment with the news id: " + news_id);
                   repo.postComment(srv, postHandler, commenter, text, news_id);
                  /* //To test refresh without adding too much comments to the database :)
                    finish();
                  */
                }
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public boolean onOptionsItemSelected(MenuItem item)
    {
        finish();
        return true;
    }
}
