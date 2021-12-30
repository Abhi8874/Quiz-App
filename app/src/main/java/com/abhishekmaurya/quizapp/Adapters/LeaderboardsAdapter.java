package com.abhishekmaurya.quizapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.abhishekmaurya.quizapp.Models.User;
import com.abhishekmaurya.quizapp.R;
import com.abhishekmaurya.quizapp.databinding.RowLeaderboardBinding;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class LeaderboardsAdapter extends RecyclerView.Adapter<LeaderboardsAdapter.LeaderboardViewHolder> {

    ArrayList<User> users;
    Context context;


    public LeaderboardsAdapter(Context context, ArrayList<User> users) {
        this.users = users;
        this.context = context;
    }

    @NonNull
    @Override
    public LeaderboardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.row_leaderboard, parent, false);
        return new LeaderboardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LeaderboardViewHolder holder, int position) {

        User user = users.get(position);

        holder.binding.name.setText(user.getName());
        holder.binding.coins.setText(String.valueOf(user.getCoins()));
        holder.binding.index.setText(String.format("#%d", position + 1));

        Glide.with(holder.binding.laterrank.getContext())
                .load(user.getProfileImg())
                .into(holder.binding.laterrank);
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public class LeaderboardViewHolder extends RecyclerView.ViewHolder {

        RowLeaderboardBinding binding;

        public LeaderboardViewHolder(@NonNull View itemView) {


            super(itemView);
            binding = RowLeaderboardBinding.bind(itemView);
        }
    }
}
