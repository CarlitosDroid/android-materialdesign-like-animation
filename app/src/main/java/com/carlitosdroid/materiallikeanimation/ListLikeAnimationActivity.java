package com.carlitosdroid.materiallikeanimation;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.carlitosdroid.materiallikeanimation.model.FavoriteEntity;
import com.carlitosdroid.materiallikeanimation.view.adapter.FavoriteAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Carlos Leonardo Camilo Vargas Huamán on 2/26/17.
 *
 */

public class ListLikeAnimationActivity extends AppCompatActivity{

    RecyclerView rcvConfiguration;
    private FavoriteAdapter favoriteAdapter;
    private List<FavoriteEntity> objectList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_like_animation);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        rcvConfiguration = (RecyclerView) findViewById(R.id.rcvFavorite);

        favoriteAdapter = new FavoriteAdapter(this, objectList);
        rcvConfiguration.setAdapter(favoriteAdapter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rcvConfiguration.setLayoutManager(layoutManager);
        rcvConfiguration.setItemAnimator(new DefaultItemAnimator());

        loadData();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    private void loadData() {
        objectList.add(new FavoriteEntity(false));
        objectList.add(new FavoriteEntity(false));
        objectList.add(new FavoriteEntity(false));
        objectList.add(new FavoriteEntity(false));
        objectList.add(new FavoriteEntity(false));
        objectList.add(new FavoriteEntity(false));
        objectList.add(new FavoriteEntity(false));
        objectList.add(new FavoriteEntity(false));
        objectList.add(new FavoriteEntity(false));
        objectList.add(new FavoriteEntity(false));
        objectList.add(new FavoriteEntity(false));
        objectList.add(new FavoriteEntity(false));
        objectList.add(new FavoriteEntity(false));
        objectList.add(new FavoriteEntity(false));
        objectList.add(new FavoriteEntity(false));
        favoriteAdapter.notifyDataSetChanged();
    }


    public void handleLikeAnimation(int itemPosition){
        objectList.get(itemPosition).setFavorite(true);
        favoriteAdapter.notifyItemChanged(itemPosition);
    }

    public void handleUnLikeAnimation(int itemPosition){
        objectList.get(itemPosition).setFavorite(false);
        favoriteAdapter.notifyItemChanged(itemPosition);
    }
}
