package com.sabanciuniv;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sabanciuniv.model.News;

import java.util.List;

public class TabFragment extends Fragment {
    ProgressBar prg;
    RecyclerView recView;

    Handler newsHandler = new Handler(new Handler.Callback(){
        @Override
        public boolean handleMessage(@NonNull Message message) {
            Log.d("dev", "Getting the news with newsId in TabFragment...");
            List<News> data = (List<News>)message.obj;
            NewsAdapter adp = new NewsAdapter(requireActivity(), data);
            recView.setAdapter(adp);
            recView.setVisibility(View.VISIBLE);
            prg.setVisibility(View.INVISIBLE);
            return true;
        }
    });

    public static TabFragment newInstance() {
        return new TabFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.news, container, false);
        initViews(view);
        return view;
    }

    // initialise the categories
    private void initViews(View view) {
        prg = view.findViewById(R.id.progBarList);
        recView = view.findViewById(R.id.recViewList);
        recView.setLayoutManager(new LinearLayoutManager(getActivity()));
        prg.setVisibility(View.VISIBLE);
        recView.setVisibility(View.INVISIBLE);

        NewsRepository repo = new NewsRepository();
        repo.getNewsByCategoryId(((NewsApplication)requireActivity().getApplication()).srv, newsHandler,getArguments().getInt("id") );
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}