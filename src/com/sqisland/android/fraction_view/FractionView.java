package com.sqisland.android.fraction_view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
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
}