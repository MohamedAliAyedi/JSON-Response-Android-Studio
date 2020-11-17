package com.example.jsonloaddataurl;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {

public  static class ViewHolder extends  RecyclerView.ViewHolder{
    private TextView name,username,email;

    public ViewHolder(@NonNull View itemView) {
        super(itemView);
        name = itemView.findViewById(R.id.name);
        username = itemView.findViewById(R.id.username);
        email = itemView.findViewById(R.id.email);

    }
}
    private  Context context;
    private  List<user> User;
    public UserAdapter (Context c , List<user> PostList){
        this.context = c;
        this.User = PostList;
    }

    @Override
    public UserAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.user,parent,false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        user p = User.get(position);
        holder.name.setText(p.getName());
        holder.username.setText(p.getUsername());
        holder.email.setText(p.getEmail());
    }

    @Override
    public int getItemCount() {
        return User.size();
    }

}
