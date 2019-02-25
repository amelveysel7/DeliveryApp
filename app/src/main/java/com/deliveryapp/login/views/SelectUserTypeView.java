package com.deliveryapp.login.views;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.CardView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.deliveryapp.R;

/**
 *
 */
public class SelectUserTypeView extends RelativeLayout {

  private Context context;

  private CardView supplierCv;
  private RelativeLayout supplierRl;
  private TextView supplierTv;
  private CardView userCv;
  private RelativeLayout userRl;
  private TextView userTv;

  private boolean hasSelected;
  private boolean isSupplierSelected;

  public SelectUserTypeView(Context context) {
    super(context);
    this.context = context;
    init();
  }

  public SelectUserTypeView(Context context, @Nullable AttributeSet attrs) {
    super(context, attrs);
    this.context = context;
    init();
  }

  public SelectUserTypeView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    this.context = context;
    init();
  }

  private void init() {
    View view = LayoutInflater.from(context).inflate(R.layout.select_user_layout, this, true);
    supplierCv = view.findViewById(R.id.supplier_cv);
    supplierRl = view.findViewById(R.id.supplier_rl);
    supplierTv = view.findViewById(R.id.supplier_tv);
    userCv = view.findViewById(R.id.user_cv);
    userRl = view.findViewById(R.id.user_rl);
    userTv = view.findViewById(R.id.user_tv);

    hasSelected = false;
    isSupplierSelected = false;

    supplierCv.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View view) {
        isSupplierSelected = true;
        startScaleAnimation(new Animator.AnimatorListener() {
          @Override
          public void onAnimationStart(Animator animator) {

          }

          @RequiresApi(api = Build.VERSION_CODES.M)
          @Override
          public void onAnimationEnd(Animator animator) {
            setViewUnselected(userRl, userTv);
            setViewSelected(supplierRl, supplierTv);
          }

          @Override
          public void onAnimationCancel(Animator animator) {

          }

          @Override
          public void onAnimationRepeat(Animator animator) {

          }
        });
      }
    });

    userCv.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View view) {
        isSupplierSelected = false;
        startScaleAnimation(new Animator.AnimatorListener() {
          @Override
          public void onAnimationStart(Animator animator) {

          }

          @RequiresApi(api = Build.VERSION_CODES.M)
          @Override
          public void onAnimationEnd(Animator animator) {
            setViewUnselected(supplierRl, supplierTv);
            setViewSelected(userRl, userTv);
          }

          @Override
          public void onAnimationCancel(Animator animator) {

          }

          @Override
          public void onAnimationRepeat(Animator animator) {

          }
        });
      }
    });
  }

  private void startScaleAnimation(Animator.AnimatorListener listener) {
    AnimatorSet onSelectedAnimator = new AnimatorSet();
    AnimatorSet onDeselectedAnimator = new AnimatorSet();

    onSelectedAnimator.setDuration(200);
    onDeselectedAnimator.setDuration(200);

    if (!hasSelected) {
      hasSelected = true;
      onSelectedAnimator.playTogether(createScaleXAnimator(isSupplierSelected ? supplierCv : userCv, true),
        createScaleYAnimator(isSupplierSelected ? supplierCv : userCv, true));

      onSelectedAnimator.addListener(listener);
      onSelectedAnimator.start();
    } else {
      onSelectedAnimator.playTogether(createScaleXAnimator(supplierCv, isSupplierSelected),
        createScaleYAnimator(supplierCv, isSupplierSelected));
      onDeselectedAnimator.playTogether(createScaleXAnimator(userCv, !isSupplierSelected),
        createScaleYAnimator(userCv, !isSupplierSelected));

      onSelectedAnimator.addListener(listener);

      onSelectedAnimator.start();
      onDeselectedAnimator.start();
    }
  }

  private ObjectAnimator createScaleXAnimator(CardView cardView, boolean selected) {
    ObjectAnimator scaleXAnimator;
    if (selected) {
      scaleXAnimator = ObjectAnimator.ofFloat(cardView, "scaleX", 1.3f);
    } else {
      scaleXAnimator = ObjectAnimator.ofFloat(cardView, "scaleX", 1f);
    }
    return scaleXAnimator;
  }

  private ObjectAnimator createScaleYAnimator(CardView cardView, boolean selected) {
    ObjectAnimator scaleYAnimator;
    if (selected) {
      scaleYAnimator = ObjectAnimator.ofFloat(cardView, "scaleY", 1.3f);
    } else {
      scaleYAnimator = ObjectAnimator.ofFloat(cardView, "scaleY", 1f);
    }

    return scaleYAnimator;
  }

  @RequiresApi(api = Build.VERSION_CODES.M)
  private void setViewSelected(RelativeLayout rl, TextView tv) {
    rl.setBackgroundColor(context.getColor(R.color.colorPrimaryLight));
    tv.setTextColor(context.getColor(R.color.colorAccent));
  }

  @RequiresApi(api = Build.VERSION_CODES.M)
  private void setViewUnselected(RelativeLayout rl, TextView tv) {
    rl.setBackgroundColor(context.getColor(R.color.colorDivider));
    tv.setTextColor(context.getResources().getColor(R.color.white));
  }
}
