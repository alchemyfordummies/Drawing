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

import org.w3c.dom.Text;

public class ScoresAndStats extends AppCompatActivity {
   public int numWrong;
   public int gameMode;

   public String background;
   public String totalTime;

   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_scores_and_stats);
      Bundle extras = getIntent().getExtras();
      background = extras.getString("BACKGROUND_COLOR");
      totalTime  = extras.getString("TIME");
      numWrong   = extras.getInt("NUMBER_WRONG");
      gameMode   = extras.getInt("GAME_MODE");

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

      setNewTexts();
   }

   public void setNewTexts() {
      TextView score   = (TextView) findViewById(R.id.scoreText);
      TextView time    = (TextView) findViewById(R.id.timeScore);
      TextView wrong   = (TextView) findViewById(R.id.wrongScore);
      TextView percent = (TextView) findViewById(R.id.percentScore);
      String nowText   = (String) score.getText();
      double temp1 = (double) numWrong;
      double temp2 = (double) gameMode;
      if (background.equals("moon")) {
         score.setTextColor(Color.CYAN);
         time.setTextColor(Color.CYAN);
         wrong.setTextColor(Color.CYAN);
         percent.setTextColor(Color.CYAN);
      }

      score.setText(R.string.scoreText + " " + gameMode);
      time.setText(totalTime);
      wrong.setText(String.valueOf(numWrong));
      percent.setText(String.valueOf(100*(1.00 - temp1/(temp1+temp2))) + "%");
   }
}
