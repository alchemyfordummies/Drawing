package com.timapps.drawing;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

public class GameMode extends AppCompatActivity {
   private String generalMode;

   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_game_mode);
      //getSupportActionBar().hide();
      //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
      //setSupportActionBar(toolbar);

      /*Gets data from Day or Night*/
      Bundle extras = getIntent().getExtras();
      String value = extras.getString("BACKGROUND_COLOR");
      generalMode = extras.getString("GENERAL_MODE");
        /*                           */

      View view = this.getWindow().getDecorView();
      ImageView img = (ImageView) findViewById(R.id.skyObject);

      //Carries over information from previous activity, sets sky object
      if (value.equals("moon")) {
         view.setBackgroundColor(Color.BLACK);
         img.setImageResource(R.drawable.cartoonmoon);
      } else {
         view.setBackgroundColor(Color.WHITE);
         img.setImageResource(R.drawable.cartoonsun);
      }
   }

   public void launchMainNormal(View v) {
      Bundle extras = getIntent().getExtras();
      String value = extras.getString("BACKGROUND_COLOR");
      Intent mainDraw = new Intent(this, GeneralMode.class);
      mainDraw.putExtra("BACKGROUND_COLOR", value);
      mainDraw.putExtra("GAME_LENGTH", 15);
      startActivity(mainDraw);
   }

   public void launchMainHalfMarathon(View v) {
      Bundle extras = getIntent().getExtras();
      String value = extras.getString("BACKGROUND_COLOR");
      Intent mainDraw = new Intent(this, GeneralMode.class);
      mainDraw.putExtra("BACKGROUND_COLOR", value);
      mainDraw.putExtra("GAME_LENGTH", 50);
      startActivity(mainDraw);
   }

   public void launchMainMarathon(View v) {
      Bundle extras = getIntent().getExtras();
      String value = extras.getString("BACKGROUND_COLOR");
      Intent mainDraw = new Intent(this, GeneralMode.class);
      mainDraw.putExtra("BACKGROUND_COLOR", value);
      mainDraw.putExtra("GAME_LENGTH", 100);
      startActivity(mainDraw);
   }
}
