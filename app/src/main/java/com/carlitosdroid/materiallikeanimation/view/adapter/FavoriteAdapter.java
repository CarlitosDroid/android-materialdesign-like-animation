package com.carlitosdroid.materiallikeanimation.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.carlitosdroid.materiallikeanimation.ListLikeAnimationActivity;
import com.carlitosdroid.materiallikeanimation.R;
import com.carlitosdroid.materiallikeanimation.model.FavoriteEntity;
import com.carlitosdroid.mdlikeanimation.LikeButtonViewBlue;

import java.util.List;

/**
 * Created by Carlos Leonardo Camilo Vargas Huamán on 2/26/17.
 *
 */

public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.ItemViewHolder>{

    private final ListLikeAnimationActivity activity;

    private List<FavoriteEntity> objectList;

    private static final int TYPE_HEADER = 0;
    private static final int TYPE_ITEM = 1;

    public FavoriteAdapter(ListLikeAnimationActivity activity, List<FavoriteEntity> objectList) {
        this.activity = activity;
        this.objectList = objectList;
    }

    @Override
    public int getItemViewType(int position) {
        return TYPE_ITEM;
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_favorite, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
//        if(objectList.get(position).isFavorite()){
//            holder.lbvFavorite.setImageResource(R.drawable.ic_star_blue_500_24dp);
//        }else{
//            holder.lbvFavorite.setImageResource(R.drawable.ic_star_border_blue_500_24dp);
//        }
    }

    @Override
    public int getItemCount() {
        return objectList.size();
    }

    class ItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private final LikeButtonViewBlue lbvFavorite;

        ItemViewHolder(View view) {
            super(view);
            lbvFavorite = (LikeButtonViewBlue) view.findViewById(R.id.lbvFavorite);
            lbvFavorite.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.lbvFavorite:
//                    lbvFavorite.setItemPosition(getAdapterPosition());
//                    if(objectList.get(getAdapterPosition()).isFavorite()){
//                        lbvFavorite.startUnLikeAnimation();
//                    }else{
//                        lbvFavorite.startLikeAnimation();
//                    }
            }
        }
    }
}