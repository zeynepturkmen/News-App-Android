package com.sabanciuniv;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sabanciuniv.model.Comments;

import java.util.List;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.CommentViewHolder> {
    private Context ctx;
    private List<Comments> data;

    public CommentAdapter(Context ctx, List<Comments> data){
        this.ctx = ctx;
        this.data = data;
    }

    @NonNull
    @Override
    public CommentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View root= LayoutInflater.from(ctx).inflate(R.layout.comment_row,parent,false);
        CommentViewHolder holder = new CommentViewHolder(root);
        holder.setIsRecyclable(false);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull CommentViewHolder holder, int position) {
        Comments current = data.get(position);
        holder.commentText.setText(current.getText());
        holder.commentName.setText(current.getName());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class CommentViewHolder extends RecyclerView.ViewHolder{
        TextView commentName;
        TextView commentText;

        public CommentViewHolder(@NonNull View itemView) {
            super(itemView);
            commentName = itemView.findViewById(R.id.commentName);
            commentText= itemView.findViewById(R.id.commentText);
        }
    }
}
