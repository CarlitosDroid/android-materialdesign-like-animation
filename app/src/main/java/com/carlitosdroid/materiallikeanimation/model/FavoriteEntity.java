package com.carlitosdroid.materiallikeanimation.model;

public class FavoriteEntity {

    private boolean isFavorite;

    public FavoriteEntity(boolean isFavorite) {
        this.isFavorite = isFavorite;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }
}