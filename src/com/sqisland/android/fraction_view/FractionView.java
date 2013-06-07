package com.sqisland.android.fraction_view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class FractionView extends View {
  private Paint mCirclePaint;
  private Paint mSectorPaint;

  private RectF mSectorOval;

  private int mNumerator = 1;
  private int mDenominator = 5;

  public FractionView(Context context) {
    super(context);
    init();
  }

  public FractionView(Context context, AttributeSet attrs) {
    super(context, attrs);
    init();
  }

  public FractionView(Context context, AttributeSet attrs, int defStyle) {
    super(context, attrs, defStyle);
    init();
  }

  private void init() {
    // Avoid allocating new memory in onDraw by initializing mCirclePaint
    // in the constructor.
    mCirclePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    mCirclePaint.setColor(Color.CYAN);
    mCirclePaint.setStyle(Paint.Style.FILL);

    mSectorPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    mSectorPaint.setColor(Color.BLUE);
    mSectorPaint.setStyle(Paint.Style.FILL);

    mSectorOval = new RectF();
  }

  @Override
  protected void onDraw(Canvas canvas) {
    setBackgroundColor(Color.LTGRAY);

    int width = getWidth() - getPaddingLeft() - getPaddingRight();
    int height = getHeight() - getPaddingTop() - getPaddingBottom();
    int size = Math.min(width, height);
    int cx = width / 2 + getPaddingLeft();
    int cy = height / 2 + getPaddingTop();
    int radius = size / 2;

    canvas.drawCircle(cx, cy, radius, mCirclePaint);

    mSectorOval.top = (height - size) / 2 + getPaddingTop();
    mSectorOval.left = (width - size) / 2 + getPaddingLeft();
    mSectorOval.bottom = mSectorOval.top + size;
    mSectorOval.right = mSectorOval.left + size;

    canvas.drawArc(mSectorOval, 0, getSweepAngle(), true, mSectorPaint);
  }

  private float getSweepAngle() {
    return mNumerator * 360f / mDenominator;
  }

  public void setFraction(int numerator, int denominator) {
    if (numerator < 0) {
      return;
    }
    if (denominator <= 0) {
      return;
    }
    if (numerator > denominator) {
      return;
    }

    mNumerator = numerator;
    mDenominator = denominator;

    // Request a redraw
    invalidate();
  }

  @Override
  public boolean onTouchEvent(MotionEvent event) {
    if (event.getAction() != MotionEvent.ACTION_UP) {
      return true;
    }

    int width = getWidth() - getPaddingLeft() - getPaddingRight();
    int height = getHeight() - getPaddingTop() - getPaddingBottom();
    int size = Math.min(width, height);
    int cx = width / 2 + getPaddingLeft();
    int cy = height / 2 + getPaddingTop();
    int radius = size / 2;

    // Reject touch events outside of the circle
    float dx = event.getX() - cx;
    float dy = event.getY() - cy;
    if (dx * dx + dy * dy > radius * radius) {
      return true;
    }

    // Increment the numerator, cycling back to 0 when we have filled the
    // whole circle.
    int numerator = mNumerator + 1;
    if (numerator > mDenominator) {
      numerator = 0;
    }
    setFraction(numerator, mDenominator);

    return true;
  }

  public Parcelable onSaveInstanceState() {
    Bundle bundle = new Bundle();
    bundle.putParcelable("superState", super.onSaveInstanceState());
    bundle.putInt("numerator", mNumerator);
    bundle.putInt("denominator", mDenominator);
    return bundle;
  }

  public void onRestoreInstanceState(Parcelable state) {
    if (state instanceof Bundle) {
      Bundle bundle = (Bundle) state;
      mNumerator = bundle.getInt("numerator");
      mDenominator = bundle.getInt("denominator");
      super.onRestoreInstanceState(bundle.getParcelable("superState"));
    } else {
      super.onRestoreInstanceState(state);
    }
    setFraction(mNumerator, mDenominator);
  }
}