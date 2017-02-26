package com.carlitosdroid.mdlikeanimation;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.OvershootInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;


/**
 * Created by carlos on 25/05/16.
 * Alias: CarlitosDroid
 */
public class LikeButtonViewBlue extends FrameLayout {
    private static final DecelerateInterpolator DECCELERATE_INTERPOLATOR = new DecelerateInterpolator();
    private static final AccelerateDecelerateInterpolator ACCELERATE_DECELERATE_INTERPOLATOR = new AccelerateDecelerateInterpolator();
    private static final OvershootInterpolator OVERSHOOT_INTERPOLATOR = new OvershootInterpolator(4);

    private AnimatorSet likeAnimatorSet;
    private AnimatorSet unlikeAnimatorSet;

    private ImageView ivStar;
    private DotsView vDotsView;
    private CircleView vCircle;

    private int itemPosition;

    public LikeButtonViewBlue(Context context) {
        super(context);
        init();
    }

    public LikeButtonViewBlue(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public LikeButtonViewBlue(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.view_like_button_white, this, true);
        ivStar = (ImageView) view.findViewById(R.id.ivStar);
        vDotsView = (DotsView) view.findViewById(R.id.vDotsView);
        vCircle = (CircleView) view.findViewById(R.id.vCircle);

        likeAnimatorSet = new AnimatorSet();
        unlikeAnimatorSet = new AnimatorSet();
    }

    public void setImageResource(int imgDrawable) {
        ivStar.setImageResource(imgDrawable);
    }

    public void setItemPosition(int position){
        itemPosition = position;
    }

    public boolean isLikeAnimationRunning(){
        return likeAnimatorSet.isRunning();
    }

    public boolean isUnLikeAnimationRunning(){
        return unlikeAnimatorSet.isRunning();
    }

    @SuppressWarnings("SameParameterValue")
    public void startLikeAnimation() {

        if(!isLikeAnimationRunning()){
            ivStar.setImageResource(R.drawable.ic_star_grey_500_24dp);

            likeAnimatorSet.cancel();
            ivStar.animate().cancel();
            ivStar.setScaleX(0);
            ivStar.setScaleY(0);
            vCircle.setInnerCircleRadiusProgress(0);
            vCircle.setOuterCircleRadiusProgress(0);
            vDotsView.setCurrentProgress(0);

            ObjectAnimator outerCircleAnimator = ObjectAnimator.ofFloat(vCircle, CircleView.OUTER_CIRCLE_RADIUS_PROGRESS, 0.1f, 1f);
            outerCircleAnimator.setDuration(200);
            outerCircleAnimator.setInterpolator(DECCELERATE_INTERPOLATOR);

            ObjectAnimator innerCircleAnimator = ObjectAnimator.ofFloat(vCircle, CircleView.INNER_CIRCLE_RADIUS_PROGRESS, 0.1f, 1f);
            innerCircleAnimator.setDuration(150);
            innerCircleAnimator.setStartDelay(150);
            innerCircleAnimator.setInterpolator(DECCELERATE_INTERPOLATOR);

            ObjectAnimator starScaleYAnimator = ObjectAnimator.ofFloat(ivStar, ImageView.SCALE_Y, 0.2f, 1f);
            starScaleYAnimator.setDuration(200);
            starScaleYAnimator.setStartDelay(200);
            starScaleYAnimator.setInterpolator(OVERSHOOT_INTERPOLATOR);

            ObjectAnimator starScaleXAnimator = ObjectAnimator.ofFloat(ivStar, ImageView.SCALE_X, 0.2f, 1f);
            starScaleXAnimator.setDuration(200);
            starScaleXAnimator.setStartDelay(200);
            starScaleXAnimator.setInterpolator(OVERSHOOT_INTERPOLATOR);

            ObjectAnimator dotsAnimator = ObjectAnimator.ofFloat(vDotsView, DotsView.DOTS_PROGRESS, 0, 1f);
            dotsAnimator.setDuration(450);
            dotsAnimator.setStartDelay(50);
            dotsAnimator.setInterpolator(ACCELERATE_DECELERATE_INTERPOLATOR);

            likeAnimatorSet.playTogether(
                    outerCircleAnimator,
                    innerCircleAnimator,
                    starScaleYAnimator,
                    starScaleXAnimator,
                    dotsAnimator
            );

            likeAnimatorSet.addListener(new AnimatorListenerAdapter() {

                @Override
                public void onAnimationEnd(Animator animation) {
                    //((TabLayoutActivity)getContext()).onLikeAnimationFinished(itemPosition);
                }
            });
            likeAnimatorSet.start();
        }
    }

    public void startUnLikeAnimation() {
        if(!isUnLikeAnimationRunning()){

            ivStar.setImageResource(R.drawable.ic_star_border_grey_500_24dp);

            unlikeAnimatorSet.cancel();

            ObjectAnimator starScaleYAnimator = ObjectAnimator.ofFloat(ivStar, ImageView.SCALE_Y, 1f, 0.7f, 1f);
            starScaleYAnimator.setDuration(200);
            starScaleYAnimator.setInterpolator(DECCELERATE_INTERPOLATOR);

            ObjectAnimator starScaleXAnimator = ObjectAnimator.ofFloat(ivStar, ImageView.SCALE_X, 1f, 0.7f, 1f);
            starScaleXAnimator.setDuration(200);
            starScaleXAnimator.setInterpolator(DECCELERATE_INTERPOLATOR);

            unlikeAnimatorSet.playTogether(
                    starScaleXAnimator,
                    starScaleYAnimator

            );

            unlikeAnimatorSet.addListener(new AnimatorListenerAdapter() {

                @Override
                public void onAnimationEnd(Animator animation) {
                    //((TabLayoutActivity)getContext()).onUnLikeAnimationfinished(itemPosition);
                }
            });

            unlikeAnimatorSet.start();
        }
    }
}
