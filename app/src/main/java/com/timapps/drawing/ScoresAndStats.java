package com.timapps.drawing;

import android.content.Context;
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

import org.w3c.dom.Text;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Scanner;

public class ScoresAndStats extends AppCompatActivity {
   public int numWrong;
   public int gameMode;

   public String background;
   public String totalTime;

   public ArrayList times = new ArrayList<>();

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

      Button prev = (Button) findViewById(R.id.modeButt);
      prev.setOnClickListener(new View.OnClickListener() {
         public void onClick(View v) {
            launchMode();
         }
      });

      //Carries over information from previous activity, sets sky object
      if (background.equals("moon")) {
         view.setBackgroundColor(Color.BLACK);
         img.setImageResource(R.drawable.cartoonmoon);
      } else {
         view.setBackgroundColor(Color.WHITE);
         img.setImageResource(R.drawable.cartoonsun);
      }

      try {
         setNewTexts();
      } catch (FileNotFoundException e) {
         e.printStackTrace();
      }
   }

   public void manageScores() throws FileNotFoundException {
      FileOutputStream out;
      String name = "saves.txt";
      String words = "Some words.";

      try {
         out = openFileOutput(name, Context.MODE_PRIVATE);
         out.write(words.getBytes());
      } catch (IOException e) {
         e.printStackTrace();
      }

      printScores(name);
   }

   public void printScores(String name) {
      TextView scores = (TextView) findViewById(R.id.topScores);
      Scanner s = new Scanner(name);
      while (s.hasNextLine()) {
         scores.append('\n' + s.nextLine());
      }
   }


   public void setNewTexts() throws FileNotFoundException {
      TextView score   = (TextView) findViewById(R.id.scoreText);
      TextView time    = (TextView) findViewById(R.id.timeScore);
      TextView wrong   = (TextView) findViewById(R.id.wrongScore);
      TextView percent = (TextView) findViewById(R.id.percentScore);
      TextView scores  = (TextView) findViewById(R.id.topScores);
      DecimalFormat df = new DecimalFormat("#.##");
      double temp1 = (double) numWrong;
      double temp2 = (double) gameMode;
      if (background.equals("moon")) {
         score.setTextColor(Color.CYAN);
         time.setTextColor(Color.CYAN);
         wrong.setTextColor(Color.CYAN);
         percent.setTextColor(Color.CYAN);
         scores.setTextColor(Color.CYAN);
      }

      score.setText(String.format("%s %d %s", "Score: ", gameMode, " seconds"));
      time.setText(String.format("Time: %s", totalTime));
      wrong.setText(String.format("%s %d", "Number Wrong: ", numWrong));
      percent.setText(String.format("%s %s%%", "Percent Right: ",
              df.format(100 * (1.00 - temp1 / (temp1 + temp2)))));
      manageScores();
   }

   public void launchMode() {
      Intent mainDraw = new Intent(this, GameMode.class);
      mainDraw.putExtra("BACKGROUND_COLOR", background);
      startActivity(mainDraw);
   }
}
