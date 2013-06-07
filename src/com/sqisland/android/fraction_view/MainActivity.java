package com.sqisland.android.fraction_view;

import android.os.Bundle;
import android.widget.TextView;
import android.app.Activity;

public class MainActivity extends Activity {
  private FractionView mFractionView;
  private TextView mTextView;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    mFractionView = (FractionView) findViewById(R.id.fraction);
    mTextView = (TextView) findViewById(R.id.text);

    mFractionView.setOnChangeListener(new FractionView.OnChangeListener() {
      @Override
      public void onChange(int numerator, int denominator) {
        String fraction = getString(
            R.string.fraction, numerator, denominator);
        mTextView.setText(fraction);
      }
    });

    mFractionView.setFraction(1, 3);
  }
}