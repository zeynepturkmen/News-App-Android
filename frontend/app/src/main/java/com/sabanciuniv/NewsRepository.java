package com.sabanciuniv;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;

import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.ExecutorService;
import android.os.Message;
import android.util.Log;

import com.sabanciuniv.model.Comments;
import com.sabanciuniv.model.News;
import com.sabanciuniv.model.NewsCategory;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class NewsRepository {

    public void getCategories(ExecutorService srv, Handler uiHandler) {
        srv.execute(()->{
            try {
                URL url = new URL("http://10.0.2.2:8080/newsapp/getallnewscategories");
                HttpURLConnection conn=
                        (HttpURLConnection)url.openConnection();
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(conn.getInputStream())
                );

                StringBuilder buffer = new StringBuilder();
                String line ="";
                while((line = reader.readLine())!=null){
                    buffer.append(line);
                }
                JSONObject obj = new JSONObject(buffer.toString());
                JSONArray arr = obj.getJSONArray("items");
                List<NewsCategory> data = new ArrayList<>();

                for (int i = 0; i < arr.length(); i++) {
                    JSONObject current = arr.getJSONObject(i);
                    NewsCategory cat = new NewsCategory(current.getInt("id"),
                            current.getString("name"));
                    data.add(cat);
                }

                Message msg = new Message();
                msg.obj = data;
                uiHandler.sendMessage(msg);
                conn.disconnect();

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        });
    }

    public void getNewsByCategoryId(ExecutorService srv, Handler uiHandler, int id){
        srv.execute(()->{
            try {
                URL url = new URL("http://10.0.2.2:8080/newsapp/getbycategoryid/"+id);
                HttpURLConnection conn=
                        (HttpURLConnection)url.openConnection();

                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(conn.getInputStream())
                );
                Log.d("dev", "yoo");
                StringBuilder buffer = new StringBuilder();
                String line ="";
                while((line = reader.readLine())!=null){
                    buffer.append(line);
                }
                Log.d("dev", "yoo");
                JSONObject obj = new JSONObject(buffer.toString());
                JSONArray arr = obj.getJSONArray("items");
                List<News> data = new ArrayList<>();

                for (int i = 0; i < arr.length(); i++) {
                    JSONObject current = arr.getJSONObject(i);
                    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                        DateTimeFormatter formatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME;

                        News news = new News(current.getInt("id"),
                                current.getString("category"), current.getString("title"),current.getString("text"), LocalDateTime.parse(current.getString("date")+"+00:00", formatter), current.getString("imgPath"));
                        data.add(news);
                    }
                }

                Message msg = new Message();
                msg.obj = data;
                uiHandler.sendMessage(msg);
                conn.disconnect();

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        });
    }
    public void getNewsById(ExecutorService srv, Handler uiHandler, int id){
        srv.execute(()->{
            try {
                URL url = new URL("http://10.0.2.2:8080/newsapp/getnewsbyid/"+id);
                HttpURLConnection conn=
                        (HttpURLConnection)url.openConnection();

                //InputStreamReader -> read char by char, supports encoding
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(conn.getInputStream())
                );
                StringBuilder buffer = new StringBuilder();
                String line ="";
                while((line = reader.readLine())!=null){
                    buffer.append(line);

                }
                JSONObject obj = new JSONObject(buffer.toString());
                JSONObject current = obj.getJSONObject("items");
                Message msg = new Message();


                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                    DateTimeFormatter formatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME;
                    News news = new News(current.getInt("id"),
                            current.getString("category"), current.getString("title"),current.getString("text"),
                            LocalDateTime.parse(current.getString("date")+"+00:00", formatter), current.getString("imgPath"));
                    msg.obj = news;
                }

                uiHandler.sendMessage(msg);
                conn.disconnect();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        });
    }
    public void getCommentsByNewsId(ExecutorService srv, Handler uiHandler, int id){
        srv.execute(()->{
            try {
                URL url = new URL("http://10.0.2.2:8080/newsapp/getcommentsbynewsid/"+id);
                HttpURLConnection conn=
                        (HttpURLConnection)url.openConnection();
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(conn.getInputStream())
                );
                StringBuilder buffer = new StringBuilder();
                String line ="";
                while((line = reader.readLine())!=null){
                    buffer.append(line);

                }

                JSONObject obj = new JSONObject(buffer.toString());
                JSONArray arr = obj.getJSONArray("items");
                List<Comments> data = new ArrayList<>();

                for (int i = 0; i < arr.length(); i++) {
                    JSONObject current = arr.getJSONObject(i);
                    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                        DateTimeFormatter formatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME;

                        Comments comment = new Comments(current.getString("id"),
                                current.getString("news_id"), current.getString("text"),current.getString("name"));
                        data.add(comment);
                    }
                }

                Message msg = new Message();
                msg.obj = data;
                uiHandler.sendMessage(msg);
                conn.disconnect();

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        });
    }
    public void postComment(ExecutorService srv, Handler uiHandler, String a, String b, String news_id){
        srv.execute(()->{

            try {
                URL url = new URL("http://10.0.2.2:8080/newsapp/savecomment");
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();

                conn.setDoInput(true);
                conn.setDoOutput(true);
                conn.setRequestMethod("POST");
                conn.setRequestProperty("Content-Type","application/JSON");

                JSONObject outputData  = new JSONObject();
                outputData.put("name", b);
                outputData.put("text", a);
                outputData.put("news_id", news_id);

                BufferedOutputStream writer = new BufferedOutputStream(conn.getOutputStream());
                writer.write(outputData.toString().getBytes(StandardCharsets.UTF_8));
                writer.flush();

                BufferedReader reader
                        = new BufferedReader(new InputStreamReader(conn.getInputStream()));

                StringBuilder buffer = new StringBuilder();
                String line ="";

                while((line=reader.readLine())!=null){
                    buffer.append(line);
                }

                JSONObject retVal = new JSONObject(buffer.toString());
                conn.disconnect();

                Message msg = new Message();
                msg.obj = retVal.get("serviceMessageCode");

                uiHandler.sendMessage(msg);

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        });
    }

    public void downloadImage(ExecutorService srv, Handler uiHandler,String path){
        srv.execute(()->{
            try {
                URL url = new URL(path);
                HttpURLConnection conn = (HttpURLConnection)url.openConnection();
                Bitmap bitmap =  BitmapFactory.decodeStream(conn.getInputStream());
                Message msg = new Message();
                msg.obj = bitmap;
                uiHandler.sendMessage(msg);

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
