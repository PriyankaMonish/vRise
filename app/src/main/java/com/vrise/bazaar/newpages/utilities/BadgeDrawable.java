package com.vrise.bazaar.newpages.utilities;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;

import com.vrise.R;

public class BadgeDrawable extends Drawable {

    private Paint mBadgePaint;
    private String mCount = "";
    private Paint mTextPaint;
    private Rect mTxtRect = new Rect();
    private boolean mWillDraw = false;

    public BadgeDrawable(Context paramContext) {
        float mTextSize = paramContext.getResources().getDimension(R.dimen.textsize_14);
        this.mBadgePaint = new Paint();
        this.mBadgePaint.setColor(paramContext.getResources().getColor(R.color.colorAccent));
        this.mBadgePaint.setAntiAlias(true);
        this.mBadgePaint.setStyle(Paint.Style.FILL);
        this.mTextPaint = new Paint();
        this.mTextPaint.setColor(-1);
        this.mTextPaint.setTypeface(Typeface.DEFAULT);
        this.mTextPaint.setTextSize(mTextSize);
        this.mTextPaint.setAntiAlias(true);
        this.mTextPaint.setTextAlign(Paint.Align.CENTER);
    }

    public void draw(Canvas paramCanvas) {
        if (!this.mWillDraw) {
            return;
        }
        Rect localRect = getBounds();
        float width = localRect.right - localRect.left;
        float height = localRect.bottom - localRect.top;
        float circleRadius;
        circleRadius = Math.min(width, height) / 4.0f + 2.5F;
        if (Integer.parseInt(this.mCount) < 10) {
            circleRadius = Math.min(width, height) / 4.0f + 2.5F;
        } else {
            circleRadius = Math.min(width, height) / 4.0f + 4.5F;
        }
        float circleX = width - circleRadius + 6.2F;
        float circleY = circleRadius - 9.5f;
        paramCanvas.drawCircle(circleX, circleY, circleRadius, this.mBadgePaint);
        this.mTextPaint.getTextBounds(this.mCount, 0, this.mCount.length(), this.mTxtRect);
        float textY = circleY + (this.mTxtRect.bottom - this.mTxtRect.top) / 2.0F;
        float textX = circleX;
        if (Integer.parseInt(this.mCount) >= 10) {
            textX = textX - 1.0F;
            textY = textY - 1.0F;
        }
        paramCanvas.drawText(this.mCount, textX, textY, this.mTextPaint);
    }

    public int getOpacity() {
        return PixelFormat.UNKNOWN;
    }

    public void setAlpha(int paramInt) {
    }

    public void setColorFilter(ColorFilter paramColorFilter) {
    }

    public void setCount(int paramInt) {
        this.mCount = Integer.toString(paramInt);
        if (paramInt > 0) {
            this.mWillDraw = true;
            invalidateSelf();
        }
    }

}