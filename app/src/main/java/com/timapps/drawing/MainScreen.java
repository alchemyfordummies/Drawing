package com.timapps.drawing;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainScreen extends AppCompatActivity {
    /* GENERAL CLASS VARIABLES */

   //DATA STRUCTURES
   private HashMap<Integer, String> roygbiv = new HashMap<Integer, String>();
   private ArrayList timeList = new ArrayList();

   //INTEGERS
   private int key          = 0; //the correct number
   private int endOfGame    = 0; //number of correct to finish the game
   private int counter      = 0; //User's total correct
   private int wrongCounter = 0;

   //LONGS (for time)
   private long startTime, endTime;

   //BOOLEANS
   private boolean isStarted = false; //Checks the number of times start was pressed.
   //true means to show reset, false is to show
   //start.

   //STRINGS
   private String totalTime    = ""; //will take the difference between startTime and
   //endTime
   private String currentColor = ""; //the current background
   private String prevColor    = ""; //stores the previous selection
   private String background   = ""; //color from previous activities
    /*             END               */

   //General purpose android studio, sets defaults
   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_main_screen);
      getSupportActionBar().hide();
        /*Gets data from Day or Night*/
      Bundle extras = getIntent().getExtras();
      background = extras.getString("BACKGROUND_COLOR");
      endOfGame  = extras.getInt("GAME_LENGTH");
        /*                           */

      View view = this.getWindow().getDecorView();
      ImageView img = (ImageView) findViewById(R.id.mainSkyObject);
      fillHash(); //initiates the hashmap in ROYGBIV order

      //Carries over information from previous activity, sets sky object
      if (background.equals("moon")) {
         view.setBackgroundColor(Color.BLACK);
         img.setImageResource(R.drawable.cartoonmoon);
      } else {
         view.setBackgroundColor(Color.WHITE);
         img.setImageResource(R.drawable.cartoonsun);
      }

        /*All buttons at bottom: listeners are set here*/
      Button prev = (Button) findViewById(R.id.prevButt);
      prev.setOnClickListener(new View.OnClickListener() {
         public void onClick(View v) {
            prevOnClick();
         }
      });

      Button next = (Button) findViewById(R.id.nextButt);
      next.setOnClickListener(new View.OnClickListener() {
         public void onClick(View v) {
            nextOnClick();
         }
      });

      Button go = (Button) findViewById(R.id.goButt);
      go.setOnClickListener(new View.OnClickListener() {
         public void onClick(View v) {
            //has the try catch block because of the thread sleep,
            //which was removed but it still has to be there I guess
            try {
               goOnClick();
            } catch (InterruptedException e) {
               e.printStackTrace();
            }
         }
      });

      Button start = (Button) findViewById(R.id.startButt);
      start.setOnClickListener(new View.OnClickListener() {
         public void onClick(View v) {
            startOnClick();
         }
      });
        /*End listeners*/
   }


   //Crashes program right now, figure out what's going on with that,
   //probably declaring it wrong or the file doesn't exist.
   public void changeType(TextView t) {
      Typeface type = Typeface.createFromAsset(getAssets(), "fonts/arcade.ttf");
      t.setTypeface(type);
   }

   //ROYGBIV, simple order and integer keys
   public void fillHash() {
      roygbiv.put(1, "red");
      roygbiv.put(2, "orange");
      roygbiv.put(3, "yellow");
      roygbiv.put(4, "green");
      roygbiv.put(5, "blue");
      roygbiv.put(6, "indigo");
      roygbiv.put(7, "violet");
   }

   //Button listener for prevButt
   //Checks if start has been pressed, then moves the current key of
   //the hashmap, moving the background color to the left.
   //If it goes all the way to the left (1) it wraps around to 7
   //(all the way at the end).
   public int prevOnClick() {
      ImageView matchColor = (ImageView) findViewById(R.id.randColor);
      if (isStarted) {
         key -= 1;
         if (key < 1) {
            key = 7;
         }

         //Gets the string associated with the current key value,
         //then runs the changeColor function, then says the current
         //color is that string value.
         String temp = String.valueOf(roygbiv.get(key));
         changeColor(temp, matchColor);
         currentColor = temp;
      }

      return key;
   }

   //Does the same as prevOnClick but moves in the opposite direction
   public int nextOnClick() {
      ImageView matchColor = (ImageView) findViewById(R.id.randColor);
      if (isStarted) {
         key += 1;
         if (key > 7) {
            key = 1;
         }

         String temp = String.valueOf(roygbiv.get(key));
         changeColor(temp, matchColor);
         currentColor = temp;
      }

      return key;
   }

   //Assigned to the goButt
   //1. it checks if the program has been started
   //2. it checks the current color versus the text of the
   //randomly generated color
   //If they match, it will generate a new color, increment the
   //number of correct matches
   //3. it will change the textfield to reflect the new count,
   //and set the text color to grey
   //4. If the colors don't match, it sets the counter textfield
   //to say "wrong" in the red color, and increments the wrongCounter
   public void goOnClick() throws InterruptedException {
      TextView colorText = (TextView) findViewById(R.id.colorNow);
      TextView countText = (TextView) findViewById(R.id.test);
      if (isStarted) {
         if (currentColor.equals(colorText.getText())) {
            randColor();
            counter++;
            countText.setText(String.valueOf(counter));
            countText.setTextColor(Color.rgb(142, 142, 142));

            //This is the endgame condition
            //1. Checks to see if the user has gotten the correct number
            //right (5 is a test, the counter will switch depending on the
            //constant and the rest of this text will be relegated to a
            //function.
            //2. If it matches, it takes the endtime (which will be a tiny
            //bit off, but close enough)
            //3. Calls the elapsedTime() function, setting it equal to totalTime,
            //says it's started, adds the time to the list, and resets the color
            //to blank
            isGameDone(endOfGame);
         } else {
            countText.setText("WRONG");
            countText.setTextColor(Color.RED);
            wrongCounter++;
         }
      }
   }

   //Linked to the startButt
   //If it hasn't been clicked yet, it generates a random color,
   //changes isStarted, takes the startTime, then changes the
   //button into the reset button
   //If it has already been started, it performs the resetClick()
   //operation, taking it back to original state
   public void startOnClick() {
      Button startButton = (Button) findViewById(R.id.startButt);
      TextView timeText = (TextView) findViewById(R.id.time);
      timeText.setText(String.valueOf(endOfGame));
      if (!isStarted) {
         startTime = System.nanoTime();
         randColor();
         isStarted = true;
         startButton.setText("Reset");
         startButton.setBackgroundColor(Color.RED);
      } else {
         //If startButt is in its reset state, and it is clicked
         //1. Says it's no longer started and takes a new time
         //2. It changes the button back to its default state
         TextView countText = (TextView) findViewById(R.id.test);
         TextView colorText = (TextView) findViewById(R.id.colorNow);
         startTime = System.nanoTime();
         isStarted = false;
         counter = 0;
         countText.setText("0");
         colorText.setText("");
         startButton.setText("Start");
         startButton.setBackgroundColor(Color.GREEN);
      }

   }

   //Starts the program off with a new Text color which the
   //user must match
   public int randColor() {
      TextView colorText = (TextView) findViewById(R.id.colorNow);
      Random goRand = new Random();
      int num = goRand.nextInt(7) + 1;
      if (roygbiv.get(num).equals(prevColor)) {
         randColor();
      } else {
         colorText.setText(roygbiv.get(num));
      }
      
      return num;
   }

   //Changes the color based on the text received. Might replace with a
   //for loop, would probably be shorter.
   public void changeColor(String temp, ImageView colorBackground) {
      switch (temp) {
         case "red":
            colorBackground.setBackgroundColor(Color.RED);
            break;
         case "orange":
            colorBackground.setBackgroundColor(Color.rgb(255, 132, 0));
            break;
         case "yellow":
            colorBackground.setBackgroundColor(Color.YELLOW);
            break;
         case "green":
            colorBackground.setBackgroundColor(Color.GREEN);
            break;
         case "blue":
            colorBackground.setBackgroundColor(Color.BLUE);
            break;
         case "indigo":
            colorBackground.setBackgroundColor(Color.rgb(51, 0, 102));
            break;
         case "violet":
            colorBackground.setBackgroundColor(Color.rgb(102, 0, 102));
            break;
      }
   }

   //calculates the elapsed time, assuming that a startTime was already initiated
   //and endTime was just taken. It properly formats the time to three decimal
   //places.
   public String elapsedTime() {
      double temp = (endTime - startTime) * 0.000000001;
      DecimalFormat form = new DecimalFormat("#.###");
      form.setRoundingMode(RoundingMode.CEILING);
      return form.format(temp);
   }

   //Checks the game mode constant to see if the user has enough correct
   //to win the game yet
   //Takes the end time, then calculates the total time run using the
   //elapsedTime() function, then changes the text, sets some defaults, and
   //adds the time to the bank of times.
   public void isGameDone(int winningCount) {
      TextView countText = (TextView) findViewById(R.id.test);
      TextView colorText = (TextView) findViewById(R.id.colorNow);
      if (counter == winningCount) {
         endTime = System.nanoTime();
         totalTime = elapsedTime();
         countText.setText(totalTime);
         saveScores(totalTime);
         isStarted = false;
         colorText.setText("");
      }
   }

   public void saveScores(String time) {
      //save the items in the ArrayList
      timeList.add(time);
      //write to the saves.txt file
   }

   //When the counter hits the constant value, the program stops and
   // the new activity is available. It will display scores and
   // information and whatnot, and it will get passed a bunch of
   // variables, but right now it's crashing the program for some reason.
   public void scoreLaunch(View v) {
      Intent launchScore = new Intent(this, ScoresAndStats.class);
      launchScore.putExtra("BACKGROUND_COLOR", background);
      launchScore.putExtra("NUMBER_WRONG", wrongCounter);
      launchScore.putExtra("TIME", totalTime);
      launchScore.putExtra("GAME_MODE", endOfGame);
      startActivity(launchScore);
   }
}