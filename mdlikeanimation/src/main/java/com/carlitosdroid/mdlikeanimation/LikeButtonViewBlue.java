package com.carlitosdroid.mdlikeanimation;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.OvershootInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.carlitosdroid.interfaces.OnLikeAnimationItemClickListener;
import com.carlitosdroid.interfaces.OnLikeAnimationListener;


/**
 * Created by Carlos Leonardo Camilo on 25/05/16.
 * Alias: CarlitosDroid
 */
public class LikeButtonViewBlue extends FrameLayout {

    private final String LOG_TAG = LikeButtonViewBlue.class.getSimpleName();
    private static final DecelerateInterpolator DECCELERATE_INTERPOLATOR = new DecelerateInterpolator();
    private static final AccelerateDecelerateInterpolator ACCELERATE_DECELERATE_INTERPOLATOR = new AccelerateDecelerateInterpolator();
    private static final OvershootInterpolator OVERSHOOT_INTERPOLATOR = new OvershootInterpolator(4);

    private AnimatorSet likeAnimatorSet;
    private AnimatorSet unlikeAnimatorSet;

    private ImageView ivStar;
    private DotsView vDotsView;
    private CircleView vCircle;

    private int likedResId;
    private int unLikedResId;

    private int itemPositionClicked = 0;

    private int mTextColor;

    private OnLikeAnimationListener onLikeAnimationListener;
    private OnLikeAnimationItemClickListener onLikeAnimationItemClickListener;

    public void setOnLikeAnimationClickListener(OnLikeAnimationListener onLikeAnimationListener){
        this.onLikeAnimationListener = onLikeAnimationListener;
    }

    public void setOnLikeAnimationItemClickListener(OnLikeAnimationItemClickListener onLikeAnimationItemClickListener){
        this.onLikeAnimationItemClickListener = onLikeAnimationItemClickListener;
    }

    public LikeButtonViewBlue(Context context) {
        super(context);
        init();
    }

    public LikeButtonViewBlue(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
        getAttributes(context, attrs);
    }

    public LikeButtonViewBlue(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
        getAttributes(context, attrs);
    }

    private void init() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.view_like_button_white, this, true);
        ivStar = (ImageView) view.findViewById(R.id.ivStar);
        vDotsView = (DotsView) view.findViewById(R.id.vDotsView);
        vCircle = (CircleView) view.findViewById(R.id.vCircle);

        likeAnimatorSet = new AnimatorSet();
        unlikeAnimatorSet = new AnimatorSet();
    }

    private void getAttributes(Context context, AttributeSet attrs) {
        TypedArray typedArray = context
                .obtainStyledAttributes(attrs, R.styleable.md_like_animation, 0, 0);

        if (typedArray != null) {
            try {
                mTextColor = typedArray.getColor(R.styleable.md_like_animation_text_color,
                        ContextCompat.getColor(context, android.R.color.darker_gray));

                likedResId =  typedArray.getResourceId(R.styleable.md_like_animation_label_background_res, R.drawable.ic_star_grey_500_24dp);
                unLikedResId =  typedArray.getResourceId(R.styleable.md_like_animation_label_background_res, R.drawable.ic_star_border_grey_500_24dp);
                ivStar.setImageResource(unLikedResId);
            } catch (Exception e) {
                Log.e(LOG_TAG, "Error while creating the view AutoLabelUI: ", e);
            } finally {
                typedArray.recycle();
            }
        }
    }

    public void setImageResource(int imgDrawable) {
        ivStar.setImageResource(imgDrawable);
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
            ivStar.setImageResource(likedResId);

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
                    onLikeAnimationListener.onLikeAnimationFinished();
                    onLikeAnimationItemClickListener.onLikeAnimationItemFinished(itemPositionClicked);
                    //((TabLayoutActivity)getContext()).onLikeAnimationListener(itemPosition);
                }
            });
            likeAnimatorSet.start();
        }
    }

    public void startUnLikeAnimation() {
        if(!isUnLikeAnimationRunning()){

            ivStar.setImageResource(unLikedResId);

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
                    onLikeAnimationListener.onUnLikeAnimationfinished();
                    onLikeAnimationItemClickListener.onUnLikeAnimationItemFinished(itemPositionClicked);
                    //((TabLayoutActivity)getContext()).onUnLikeAnimationfinished(itemPosition);
                }
            });

            unlikeAnimatorSet.start();
        }
    }
}
