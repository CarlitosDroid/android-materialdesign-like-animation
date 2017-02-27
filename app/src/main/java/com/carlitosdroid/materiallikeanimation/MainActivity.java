package com.carlitosdroid.materiallikeanimation;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.carlitosdroid.interfaces.OnLikeAnimationListener;
import com.carlitosdroid.mdlikeanimation.LikeButtonViewBlue;

/**
 * Created by Carlos Leonardo Camilo Vargas Huamán on 2/26/17.
 *
 */


public class MainActivity extends AppCompatActivity implements OnLikeAnimationListener {

    private LikeButtonViewBlue lbvFavorite;
    private boolean favorite = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        lbvFavorite = (LikeButtonViewBlue) findViewById(R.id.lbvFavorite);

        lbvFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(favorite){
                    lbvFavorite.startLikeAnimation();
                }else{
                    lbvFavorite.startUnLikeAnimation();
                }
                favorite = !favorite;
            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, ListLikeAnimationActivity.class));

                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        lbvFavorite.setOnLikeAnimationClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onLikeAnimationFinished() {
        Toast.makeText(this, "Liked", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUnLikeAnimationFinished() {
        Toast.makeText(this, "UnLiked", Toast.LENGTH_SHORT).show();
    }
}
