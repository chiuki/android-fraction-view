package com.sqisland.android.fraction_view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class FractionView extends View {
  private Paint mCirclePaint;

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
  }
}