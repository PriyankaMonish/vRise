package com.vrise.bazaar.newpages.top.temps;

import android.annotation.SuppressLint;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup.MarginLayoutParams;
import android.view.ViewParent;

public class MultiTouchListener implements OnTouchListener {

    private float mPrevX;
    private float mPrevY;

    private FragmentActivity mainActivity;
    private ViewParent parent;

    public MultiTouchListener(FragmentActivity mainActivity, ViewParent parent) {
        this.mainActivity = mainActivity;
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouch(View view, MotionEvent event) {
        float currX, currY;
        int action = event.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN: {

                mPrevX = event.getX();
                mPrevY = event.getY();
                break;
            }

            case MotionEvent.ACTION_MOVE: {

                currX = event.getRawX();
                currY = event.getRawY();


                MarginLayoutParams marginParams = new MarginLayoutParams(view.getLayoutParams());
                marginParams.setMargins((int) (currX - mPrevX), (int) (currY - mPrevY), 0, 0);
                ConstraintLayout.LayoutParams layoutParams = new ConstraintLayout.LayoutParams(marginParams);
                view.setLayoutParams(layoutParams);


                break;
            }


            case MotionEvent.ACTION_CANCEL:
                break;

            case MotionEvent.ACTION_UP:

                break;
        }

        return true;
    }

}