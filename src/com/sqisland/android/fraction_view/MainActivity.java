package com.sqisland.android.fraction_view;

import android.os.Bundle;
import android.app.Activity;

public class MainActivity extends Activity {
  private FractionView mFractionView;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    mFractionView = (FractionView) findViewById(R.id.fraction);

    mFractionView.setFraction(1, 3);
  }
}