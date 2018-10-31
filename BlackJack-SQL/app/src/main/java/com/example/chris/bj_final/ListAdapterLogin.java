package com.example.chris.bj_final;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chris on 9/9/2017.
 */

public class ListAdapterLogin extends RecyclerView.Adapter < listAdapterLogin.ViewHolder > {
    ArrayList < ListItemLogin > itemLists = new ArrayList < > ();
    ListAdapterLogin(ArrayList < ListItemLogin > arrayList) {
        this.itemLists = arrayList;
    }
    private Context context;
    private Context pref;

    public listAdapterLogin(ArrayList < ListItemLogin > itemLists, Context context) {
        this.itemLists = itemLists;
        this.context = context;
    }

    @Override
    public ViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_login, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void OnBindViewHolder(ViewHolder holder, int position) {
        ListItemLogin listItemLogin = itemLists.get(position);

        holder.textName.setText(listItemLogin.getName());
        holder.textHighScore.setText(Integer.toString(listItemLogin.getHighScore()));
    }

    @Override
    public int GetItemCount() {
        return itemLists.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textName, textHighScore;

        ViewHolder(View itemView) {
            super(itemView);

            textName = itemView.findViewById(R.id.textViewUser);
            textHighScore = itemView.findViewById(R.id.textViewHighScore);
        }
    }
}