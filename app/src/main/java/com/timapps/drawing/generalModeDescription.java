package com.timapps.drawing;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class generalModeDescription extends AppCompatActivity {
   private String background = "";

   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_general_mode_description);
      Bundle extras = getIntent().getExtras();
      background = extras.getString("BACKGROUND_COLOR");

      View view = this.getWindow().getDecorView();
      ImageView img = (ImageView) findViewById(R.id.skyObject);
      TextView text1 = (TextView) findViewById(R.id.mainScreenDescription);
      TextView text2 = (TextView) findViewById(R.id.expandedScreenDescription);
      TextView text3 = (TextView) findViewById(R.id.sandwichScreenDescription);

      if (background.equals("moon")) {
         view.setBackgroundColor(Color.BLACK);
         img.setImageResource(R.drawable.cartoonmoon);
         text1.setTextColor(Color.CYAN);
         text2.setTextColor(Color.CYAN);
         text3.setTextColor(Color.CYAN);
      } else {
         view.setBackgroundColor(Color.WHITE);
         img.setImageResource(R.drawable.cartoonsun);
      }
   }

}
