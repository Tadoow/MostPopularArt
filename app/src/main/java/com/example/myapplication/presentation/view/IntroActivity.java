package com.example.myapplication.presentation.view;

import android.animation.ValueAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.example.myapplication.databinding.ActivityIntroBinding;

public class IntroActivity extends AppCompatActivity implements View.OnClickListener {

    private ActivityIntroBinding mBinding;
    private ValueAnimator mValueAnimator;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityIntroBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());

        mBinding.paintingsTextView.setOnClickListener(this);
        mBinding.drawingsTextView.setOnClickListener(this);

        mValueAnimator = ValueAnimator.ofInt(1500, 0);
        mValueAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
        mValueAnimator.setDuration(1500);
        mValueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int position = (int) animation.getAnimatedValue();
                mBinding.paintingsTextView.setTranslationY(position);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        mValueAnimator.start();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mValueAnimator.removeAllListeners();
        mValueAnimator.cancel();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.paintings_textView:
                Intent intent1 = new Intent(this, MainActivity.class);
                intent1.putExtra("title", getResources().getString(R.string.paintings_textView));
                startActivity(intent1);
                break;
            case R.id.drawings_textView:
                Intent intent2 = new Intent(this, MainActivity.class);
                startActivity(intent2);
                break;
            default:
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mBinding = null;
    }
}
