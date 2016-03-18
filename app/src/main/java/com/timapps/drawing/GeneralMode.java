package com.timapps.drawing;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class GeneralMode extends AppCompatActivity {
   private String background;
   private String generalMode;
   private int gameLength = 0;

   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_general_mode);

      /*Gets data from Day or Night*/
      Bundle extras = getIntent().getExtras();
      background = extras.getString("BACKGROUND_COLOR");
      gameLength = extras.getInt("GAME_LENGTH");
        /*                           */

      View view = this.getWindow().getDecorView();
      ImageView img = (ImageView) findViewById(R.id.skyObject);

      //Carries over information from previous activity, sets sky object
      if (background.equals("moon")) {
         view.setBackgroundColor(Color.BLACK);
         img.setImageResource(R.drawable.cartoonmoon);
      } else {
         view.setBackgroundColor(Color.WHITE);
         img.setImageResource(R.drawable.cartoonsun);
      }

      Button expandedColor = (Button) findViewById(R.id.expandedColorMode);
      expandedColor.setOnClickListener(new View.OnClickListener() {
         public void onClick(View v) {
            Intent mainDraw = new Intent(GeneralMode.this, ExpandedScreen.class);
            mainDraw.putExtra("BACKGROUND_COLOR", background);
            mainDraw.putExtra("GAME_LENGTH", gameLength);
            startActivity(mainDraw);
         }
      });

      Button normal = (Button) findViewById(R.id.normalMode);
      normal.setOnClickListener(new View.OnClickListener() {
         public void onClick(View v) {
            Intent mainDraw = new Intent(GeneralMode.this, MainScreen.class);
            mainDraw.putExtra("BACKGROUND_COLOR", background);
            mainDraw.putExtra("GAME_LENGTH", gameLength);
            startActivity(mainDraw);
         }
      });

      Button sandwich = (Button) findViewById(R.id.sandwichMode);
      sandwich.setOnClickListener(new View.OnClickListener() {
         public void onClick(View v) {
            Intent mainDraw = new Intent(GeneralMode.this, SandcwichScreen.class);
            mainDraw.putExtra("BACKGROUND_COLOR", background);
            mainDraw.putExtra("GAME_LENGTH", gameLength);
            startActivity(mainDraw);
         }
      });

      ImageView descripButt = (ImageView) findViewById(R.id.description);
      descripButt.setOnClickListener(new View.OnClickListener() {
         public void onClick(View v) {
            ImageView descripButt = (ImageView) findViewById(R.id.description);
            Intent mainDraw = new Intent(GeneralMode.this, generalModeDescription.class);
            mainDraw.putExtra("BACKGROUND_COLOR", background);
            startActivity(mainDraw);
         }
      });
   }
}