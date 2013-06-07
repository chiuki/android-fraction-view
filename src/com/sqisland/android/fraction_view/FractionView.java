package com.sqisland.android.fraction_view;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;

public class FractionView extends View {
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
    setBackgroundColor(Color.LTGRAY);
  }
}